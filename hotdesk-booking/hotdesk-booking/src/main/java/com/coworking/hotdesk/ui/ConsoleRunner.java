package com.coworking.hotdesk.ui;

import com.coworking.hotdesk.exception.HotDeskException;
import com.coworking.hotdesk.model.Booking;
import com.coworking.hotdesk.model.RevenueSummary;
import com.coworking.hotdesk.model.SpaceType;
import com.coworking.hotdesk.model.SpaceUtilization;
import com.coworking.hotdesk.model.User;
import com.coworking.hotdesk.model.Workspace;
import com.coworking.hotdesk.service.BookingService;
import com.coworking.hotdesk.service.ReportService;
import com.coworking.hotdesk.service.UserService;
import com.coworking.hotdesk.service.WorkspaceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/** Interactive console UI that demonstrates every functional requirement (FR-1 .. FR-5). */
@Component
public class ConsoleRunner implements CommandLineRunner {

    private final WorkspaceService workspaceService;
    private final UserService userService;
    private final BookingService bookingService;
    private final ReportService reportService;

    private final Scanner in = new Scanner(System.in);

    public ConsoleRunner(WorkspaceService workspaceService, UserService userService,
                         BookingService bookingService, ReportService reportService) {
        this.workspaceService = workspaceService;
        this.userService = userService;
        this.bookingService = bookingService;
        this.reportService = reportService;
    }

    /** Thrown when stdin is closed (Ctrl+D / Ctrl+Z) so the app exits cleanly. */
    private static class InputClosedException extends RuntimeException {
    }

    @Override
    public void run(String... args) {
        System.out.println();
        System.out.println("==============================================");
        System.out.println("   CO-WORKING HOT-DESK BOOKING ENGINE");
        System.out.println("==============================================");

        boolean running = true;
        while (running) {
            printMenu();
            try {
                String choice = readLine("Choose an option");
                switch (choice) {
                    case "1" -> registerWorkspace();
                    case "2" -> onboardMember();
                    case "3" -> printWorkspaces(workspaceService.listAll());
                    case "4" -> printUsers(userService.listAll());
                    case "5" -> checkAvailability();
                    case "6" -> bookSpace();
                    case "7" -> cancelBooking();
                    case "8" -> viewBookings();
                    case "9" -> topUpWallet();
                    case "10" -> revenueReport();
                    case "11" -> utilizationReport();
                    case "0" -> running = false;
                    default -> System.out.println("  [!] Invalid option. Enter a number from the menu.");
                }
            } catch (InputClosedException e) {
                running = false;
            } catch (HotDeskException e) {
                System.out.println("  [!] " + e.getMessage());
            } catch (DataAccessException e) {
                System.out.println("  [!] Database error: " + e.getMostSpecificCause().getMessage());
            }
        }
        System.out.println("\nGoodbye!");
    }

    // ------------------------------------------------------------------ menu

    private void printMenu() {
        System.out.println();
        System.out.println("---------------- MAIN MENU ----------------");
        System.out.println(" 1. Register a workspace");
        System.out.println(" 2. Onboard a member (with wallet credit)");
        System.out.println(" 3. List workspaces");
        System.out.println(" 4. List members");
        System.out.println(" 5. Check slot availability");
        System.out.println(" 6. Book a space (pay from wallet)");
        System.out.println(" 7. Cancel a booking (refund to wallet)");
        System.out.println(" 8. View a member's bookings");
        System.out.println(" 9. Top up a member's wallet");
        System.out.println("10. Revenue by space type");
        System.out.println("11. Space utilization report");
        System.out.println(" 0. Exit");
        System.out.println("-------------------------------------------");
    }

    // --------------------------------------------------------------- actions

    private void registerWorkspace() {
        System.out.println("\n-- Register workspace --");
        String name = readRequired("Workspace name (e.g. Desk-A20)");
        SpaceType[] types = SpaceType.values();
        for (int i = 0; i < types.length; i++) {
            System.out.println("  " + (i + 1) + ". " + types[i]);
        }
        SpaceType type = types[readIntInRange("Type (1-" + types.length + ")", 1, types.length) - 1];
        BigDecimal rate = readMoney("Hourly rate (Rs.)");
        Workspace w = workspaceService.registerWorkspace(name, type, rate);
        System.out.printf("  [OK] Registered workspace #%d: %s (%s) at %s/hour%n",
                w.spaceId(), w.spaceName(), w.spaceType(), money(w.hourlyRate()));
    }

    private void onboardMember() {
        System.out.println("\n-- Onboard member --");
        String name = readRequired("Full name");
        String email = readRequired("Email");
        BigDecimal credit = readMoney("Initial wallet credit (Rs., 0 allowed)");
        User u = userService.onboardMember(name, email, credit);
        System.out.printf("  [OK] Member #%d onboarded: %s <%s>, wallet %s%n",
                u.userId(), u.fullName(), u.email(), money(u.walletBalance()));
    }

    private void checkAvailability() {
        System.out.println("\n-- Slot availability --");
        LocalDate date = readDate("Date (yyyy-MM-dd, or 'today' / 'tomorrow')");
        int hour = readIntInRange("Slot hour (0-23, e.g. 9 = 09:00-10:00)", 0, 23);
        List<Workspace> free = bookingService.findAvailableSpaces(date, hour);
        System.out.printf("%n  Free on %s at %s:%n", date, slotLabel(hour));
        printWorkspaces(free);
    }

    private void bookSpace() {
        System.out.println("\n-- Book a space --");
        int userId = readInt("Member ID");
        int spaceId = readInt("Workspace ID");
        LocalDate date = readDate("Date (yyyy-MM-dd, or 'today' / 'tomorrow')");
        int hour = readIntInRange("Slot hour (0-23)", 0, 23);
        Booking b = bookingService.bookSpace(userId, spaceId, date, hour);
        User u = userService.getUser(userId);
        System.out.printf("  [OK] Booking #%d CONFIRMED: %s on %s at %s, charged %s%n",
                b.bookingId(), b.spaceName(), b.bookingDate(), slotLabel(b.slotHour()), money(b.totalCost()));
        System.out.printf("       Remaining wallet balance: %s%n", money(u.walletBalance()));
    }

    private void cancelBooking() {
        System.out.println("\n-- Cancel booking --");
        int userId = readInt("Member ID");
        int bookingId = readInt("Booking ID");
        Booking b = bookingService.cancelBooking(userId, bookingId);
        User u = userService.getUser(userId);
        System.out.printf("  [OK] Booking #%d CANCELLED. Refunded %s to wallet.%n", b.bookingId(), money(b.totalCost()));
        System.out.printf("       New wallet balance: %s%n", money(u.walletBalance()));
    }

    private void viewBookings() {
        System.out.println("\n-- Member bookings --");
        int userId = readInt("Member ID");
        List<Booking> bookings = bookingService.getBookingsForUser(userId);
        if (bookings.isEmpty()) {
            System.out.println("  (no bookings)");
            return;
        }
        System.out.printf("  %-6s %-16s %-12s %-13s %12s  %s%n", "ID", "Space", "Date", "Slot", "Cost", "Status");
        for (Booking b : bookings) {
            System.out.printf("  %-6d %-16s %-12s %-13s %12s  %s%n", b.bookingId(), b.spaceName(), b.bookingDate(),
                    slotLabel(b.slotHour()), money(b.totalCost()), b.status());
        }
    }

    private void topUpWallet() {
        System.out.println("\n-- Top up wallet --");
        int userId = readInt("Member ID");
        BigDecimal amount = readMoney("Amount to add (Rs.)");
        User u = userService.topUpWallet(userId, amount);
        System.out.printf("  [OK] %s's wallet is now %s%n", u.fullName(), money(u.walletBalance()));
    }

    private void revenueReport() {
        System.out.println("\n-- Revenue by space type (confirmed bookings) --");
        List<RevenueSummary> rows = reportService.revenueBySpaceType();
        System.out.printf("  %-16s %10s %16s%n", "Space type", "Bookings", "Revenue");
        BigDecimal total = BigDecimal.ZERO;
        for (RevenueSummary r : rows) {
            System.out.printf("  %-16s %10d %16s%n", r.spaceType(), r.bookingCount(), money(r.revenue()));
            total = total.add(r.revenue());
        }
        System.out.printf("  %-16s %10s %16s%n", "TOTAL", "", money(total));
    }

    private void utilizationReport() {
        System.out.println("\n-- Space utilization (confirmed booked hours per workspace) --");
        List<SpaceUtilization> rows = reportService.spaceUtilization();
        System.out.printf("  %-4s %-16s %-16s %8s %14s%n", "ID", "Space", "Type", "Hours", "Revenue");
        for (SpaceUtilization s : rows) {
            System.out.printf("  %-4d %-16s %-16s %8d %14s%n",
                    s.spaceId(), s.spaceName(), s.spaceType(), s.bookedHours(), money(s.revenue()));
        }
    }

    // -------------------------------------------------------------- printing

    private void printWorkspaces(List<Workspace> list) {
        if (list.isEmpty()) {
            System.out.println("  (none)");
            return;
        }
        System.out.printf("  %-4s %-16s %-16s %12s  %s%n", "ID", "Name", "Type", "Rate/hour", "Status");
        for (Workspace w : list) {
            System.out.printf("  %-4d %-16s %-16s %12s  %s%n", w.spaceId(), w.spaceName(), w.spaceType(),
                    money(w.hourlyRate()), w.active() ? "Active" : "Inactive");
        }
    }

    private void printUsers(List<User> list) {
        if (list.isEmpty()) {
            System.out.println("  (none)");
            return;
        }
        System.out.printf("  %-4s %-22s %-32s %14s%n", "ID", "Name", "Email", "Wallet");
        for (User u : list) {
            System.out.printf("  %-4d %-22s %-32s %14s%n", u.userId(), u.fullName(), u.email(), money(u.walletBalance()));
        }
    }

    private static String money(BigDecimal value) {
        return String.format(Locale.ENGLISH, "Rs.%,.2f", value);
    }

    private static String slotLabel(int hour) {
        return String.format("%02d:00-%02d:00", hour, (hour + 1) % 24);
    }

    // ----------------------------------------------------------- input utils

    private String readLine(String label) {
        System.out.print(label + ": ");
        if (!in.hasNextLine()) {
            throw new InputClosedException();
        }
        return in.nextLine().trim();
    }

    private String readRequired(String label) {
        while (true) {
            String s = readLine(label);
            if (!s.isEmpty()) {
                return s;
            }
            System.out.println("  [!] This field is required.");
        }
    }

    private int readInt(String label) {
        while (true) {
            String s = readLine(label);
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("  [!] Please enter a whole number.");
            }
        }
    }

    private int readIntInRange(String label, int min, int max) {
        while (true) {
            int v = readInt(label);
            if (v >= min && v <= max) {
                return v;
            }
            System.out.printf("  [!] Please enter a number between %d and %d.%n", min, max);
        }
    }

    private BigDecimal readMoney(String label) {
        while (true) {
            String s = readLine(label);
            try {
                BigDecimal v = new BigDecimal(s).setScale(2, RoundingMode.HALF_UP);
                if (v.signum() < 0) {
                    System.out.println("  [!] Amount cannot be negative.");
                    continue;
                }
                return v;
            } catch (NumberFormatException e) {
                System.out.println("  [!] Please enter a valid amount, e.g. 250 or 99.50.");
            }
        }
    }

    private LocalDate readDate(String label) {
        while (true) {
            String s = readLine(label).toLowerCase(Locale.ENGLISH);
            if (s.equals("today")) {
                return LocalDate.now();
            }
            if (s.equals("tomorrow")) {
                return LocalDate.now().plusDays(1);
            }
            try {
                return LocalDate.parse(s);
            } catch (DateTimeParseException e) {
                System.out.println("  [!] Use the format yyyy-MM-dd (e.g. 2026-11-05), or 'today' / 'tomorrow'.");
            }
        }
    }
}
