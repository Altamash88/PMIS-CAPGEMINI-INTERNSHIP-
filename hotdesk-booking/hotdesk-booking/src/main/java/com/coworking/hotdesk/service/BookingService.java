package com.coworking.hotdesk.service;

import com.coworking.hotdesk.dao.BookingDao;
import com.coworking.hotdesk.dao.UserDao;
import com.coworking.hotdesk.dao.WorkspaceDao;
import com.coworking.hotdesk.exception.InsufficientBalanceException;
import com.coworking.hotdesk.exception.InvalidRequestException;
import com.coworking.hotdesk.exception.ResourceNotFoundException;
import com.coworking.hotdesk.exception.SlotUnavailableException;
import com.coworking.hotdesk.model.Booking;
import com.coworking.hotdesk.model.BookingStatus;
import com.coworking.hotdesk.model.User;
import com.coworking.hotdesk.model.Workspace;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Business rules for availability, atomic prepaid booking and cancellation with refund.
 * Every write method is one database transaction: any exception rolls everything back.
 */
@Service
public class BookingService {

    private final WorkspaceDao workspaceDao;
    private final UserDao userDao;
    private final BookingDao bookingDao;

    public BookingService(WorkspaceDao workspaceDao, UserDao userDao, BookingDao bookingDao) {
        this.workspaceDao = workspaceDao;
        this.userDao = userDao;
        this.bookingDao = bookingDao;
    }

    /** FR-2: workspaces that are free for the given date and slot hour. */
    @Transactional(readOnly = true)
    public List<Workspace> findAvailableSpaces(LocalDate date, int slotHour) {
        validateSlotHour(slotHour);
        return workspaceDao.findAvailable(date, slotHour);
    }

    /**
     * FR-3: atomic prepaid booking.
     * 1) validate  2) lock the member's wallet row  3) check funds  4) check collision
     * 5) insert CONFIRMED booking  6) debit wallet.  All in one transaction.
     */
    @Transactional
    public Booking bookSpace(int userId, int spaceId, LocalDate date, int slotHour) {
        validateSlotHour(slotHour);
        if (date == null) {
            throw new InvalidRequestException("Booking date is required.");
        }
        if (hasEnded(date, slotHour)) {
            throw new InvalidRequestException("That slot is already in the past. Choose a current or future slot.");
        }

        Workspace workspace = workspaceDao.findById(spaceId)
                .orElseThrow(() -> new ResourceNotFoundException("Workspace #" + spaceId + " not found."));
        if (!workspace.active()) {
            throw new InvalidRequestException(workspace.spaceName() + " is not available for lease right now.");
        }

        // Lock the wallet row so two simultaneous bookings by the same member cannot overspend.
        User user = userDao.findByIdForUpdate(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Member #" + userId + " not found."));

        BigDecimal cost = workspace.hourlyRate();   // one slot = one hour
        if (user.walletBalance().compareTo(cost) < 0) {
            throw new InsufficientBalanceException(String.format(
                    "Insufficient wallet balance: %s costs Rs.%s but %s has only Rs.%s. Top up the wallet and try again.",
                    workspace.spaceName(), cost, user.fullName(), user.walletBalance()));
        }

        if (bookingDao.existsConfirmed(spaceId, date, slotHour)) {
            throw slotTaken(workspace, date, slotHour);
        }

        int bookingId;
        try {
            bookingId = bookingDao.insertConfirmed(userId, spaceId, date, slotHour, cost);
        } catch (DuplicateKeyException e) {
            // Another transaction won the race; the UNIQUE constraint is the final guard.
            throw slotTaken(workspace, date, slotHour);
        }

        if (!userDao.debit(userId, cost)) {
            throw new InsufficientBalanceException("Wallet debit failed; booking rolled back.");
        }
        return bookingDao.findById(bookingId).orElseThrow();
    }

    /**
     * FR-4: cancel a FUTURE booking and refund its cost to the member's wallet, atomically.
     */
    @Transactional
    public Booking cancelBooking(int userId, int bookingId) {
        // Same lock order as bookSpace (wallet row first) to avoid deadlocks.
        userDao.findByIdForUpdate(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Member #" + userId + " not found."));

        Booking booking = bookingDao.findByIdForUpdate(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking #" + bookingId + " not found."));

        if (booking.userId() != userId) {
            throw new InvalidRequestException("Booking #" + bookingId + " does not belong to member #" + userId + ".");
        }
        if (booking.status() != BookingStatus.CONFIRMED) {
            throw new InvalidRequestException("Booking #" + bookingId + " is already cancelled.");
        }
        if (!isFuture(booking.bookingDate(), booking.slotHour())) {
            throw new InvalidRequestException("Only future bookings can be cancelled; this slot has already started or passed.");
        }

        if (!bookingDao.markCancelled(bookingId)) {
            throw new InvalidRequestException("Booking #" + bookingId + " could not be cancelled (status changed).");
        }
        userDao.credit(userId, booking.totalCost());
        return bookingDao.findById(bookingId).orElseThrow();
    }

    @Transactional(readOnly = true)
    public List<Booking> getBookingsForUser(int userId) {
        userDao.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Member #" + userId + " not found."));
        return bookingDao.findByUser(userId);
    }

    // ---------------------------------------------------------------- helpers

    private static void validateSlotHour(int slotHour) {
        if (slotHour < 0 || slotHour > 23) {
            throw new InvalidRequestException("Slot hour must be between 0 and 23 (24-hour format).");
        }
    }

    /** A slot has ended once its hour is before the current hour on today's date (or the date is past). */
    private static boolean hasEnded(LocalDate date, int slotHour) {
        LocalDateTime now = LocalDateTime.now();
        LocalDate today = now.toLocalDate();
        return date.isBefore(today) || (date.isEqual(today) && slotHour < now.getHour());
    }

    /** "Future" = the slot has not started yet. */
    private static boolean isFuture(LocalDate date, int slotHour) {
        LocalDateTime now = LocalDateTime.now();
        LocalDate today = now.toLocalDate();
        return date.isAfter(today) || (date.isEqual(today) && slotHour > now.getHour());
    }

    private static SlotUnavailableException slotTaken(Workspace workspace, LocalDate date, int slotHour) {
        return new SlotUnavailableException(String.format(
                "%s is already booked on %s at %02d:00. Use 'Check slot availability' to find a free space.",
                workspace.spaceName(), date, slotHour));
    }
}
