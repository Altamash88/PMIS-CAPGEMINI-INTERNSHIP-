package com.coworking.hotdesk.dao;

import com.coworking.hotdesk.dao.mapper.BookingRowMapper;
import com.coworking.hotdesk.dao.mapper.RevenueSummaryRowMapper;
import com.coworking.hotdesk.model.Booking;
import com.coworking.hotdesk.model.RevenueSummary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class BookingDao {

    private static final String SELECT_JOINED =
            "SELECT b.booking_id, b.user_id, b.space_id, w.space_name, b.booking_date, " +
            "       b.slot_hour, b.total_cost, b.booking_status " +
            "FROM bookings b JOIN workspaces w ON w.space_id = b.space_id ";

    private static final String INSERT_CONFIRMED =
            "INSERT INTO bookings (user_id, space_id, booking_date, slot_hour, total_cost, booking_status) " +
            "VALUES (?, ?, ?, ?, ?, 'CONFIRMED')";

    // FR-5: revenue grouped by workspace category (every category listed, even with no revenue)
    private static final String REVENUE_BY_TYPE =
            "SELECT w.space_type, " +
            "       COUNT(b.booking_id) AS booking_count, " +
            "       COALESCE(SUM(b.total_cost), 0) AS revenue " +
            "FROM workspaces w " +
            "LEFT JOIN bookings b ON b.space_id = w.space_id AND b.booking_status = 'CONFIRMED' " +
            "GROUP BY w.space_type " +
            "ORDER BY revenue DESC, w.space_type";

    private final JdbcTemplate jdbcTemplate;
    private final BookingRowMapper rowMapper = new BookingRowMapper();
    private final RevenueSummaryRowMapper revenueMapper = new RevenueSummaryRowMapper();

    public BookingDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean existsConfirmed(int spaceId, LocalDate date, int slotHour) {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM bookings WHERE space_id = ? AND booking_date = ? " +
                "AND slot_hour = ? AND booking_status = 'CONFIRMED'",
                Integer.class, spaceId, Date.valueOf(date), slotHour);
        return count != null && count > 0;
    }

    public int insertConfirmed(int userId, int spaceId, LocalDate date, int slotHour, BigDecimal totalCost) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(INSERT_CONFIRMED, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, userId);
            ps.setInt(2, spaceId);
            ps.setDate(3, Date.valueOf(date));
            ps.setInt(4, slotHour);
            ps.setBigDecimal(5, totalCost);
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    public Optional<Booking> findById(int bookingId) {
        return jdbcTemplate.query(SELECT_JOINED + "WHERE b.booking_id = ?", rowMapper, bookingId)
                .stream().findFirst();
    }

    /** Row-locks only the booking row (not the workspace) while it is being cancelled. */
    public Optional<Booking> findByIdForUpdate(int bookingId) {
        return jdbcTemplate.query(SELECT_JOINED + "WHERE b.booking_id = ? FOR UPDATE OF b", rowMapper, bookingId)
                .stream().findFirst();
    }

    public List<Booking> findByUser(int userId) {
        return jdbcTemplate.query(SELECT_JOINED + "WHERE b.user_id = ? ORDER BY b.booking_date DESC, b.slot_hour DESC",
                rowMapper, userId);
    }

    /** Only flips CONFIRMED -> CANCELLED; returns false if it was not CONFIRMED any more. */
    public boolean markCancelled(int bookingId) {
        int rows = jdbcTemplate.update(
                "UPDATE bookings SET booking_status = 'CANCELLED' WHERE booking_id = ? AND booking_status = 'CONFIRMED'",
                bookingId);
        return rows == 1;
    }

    public List<RevenueSummary> findRevenueByType() {
        return jdbcTemplate.query(REVENUE_BY_TYPE, revenueMapper);
    }
}
