package com.coworking.hotdesk.dao;

import com.coworking.hotdesk.dao.mapper.SpaceUtilizationRowMapper;
import com.coworking.hotdesk.dao.mapper.WorkspaceRowMapper;
import com.coworking.hotdesk.model.SpaceType;
import com.coworking.hotdesk.model.SpaceUtilization;
import com.coworking.hotdesk.model.Workspace;
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
public class WorkspaceDao {

    private static final String SELECT_ALL_COLUMNS =
            "SELECT space_id, space_name, space_type, hourly_rate, is_active FROM workspaces";

    private static final String INSERT =
            "INSERT INTO workspaces (space_name, space_type, hourly_rate) VALUES (?, ?, ?)";

    // FR-2: active workspaces that have NO confirmed booking for the given date + hour
    private static final String FIND_AVAILABLE =
            "SELECT w.space_id, w.space_name, w.space_type, w.hourly_rate, w.is_active " +
            "FROM workspaces w " +
            "WHERE w.is_active = TRUE " +
            "  AND NOT EXISTS (SELECT 1 FROM bookings b " +
            "                  WHERE b.space_id = w.space_id " +
            "                    AND b.booking_date = ? " +
            "                    AND b.slot_hour = ? " +
            "                    AND b.booking_status = 'CONFIRMED') " +
            "ORDER BY w.space_type, w.space_name";

    // Aggregation: utilization (booked hours + revenue) per workspace
    private static final String UTILIZATION =
            "SELECT w.space_id, w.space_name, w.space_type, " +
            "       COUNT(b.booking_id) AS booked_hours, " +
            "       COALESCE(SUM(b.total_cost), 0) AS revenue " +
            "FROM workspaces w " +
            "LEFT JOIN bookings b ON b.space_id = w.space_id AND b.booking_status = 'CONFIRMED' " +
            "GROUP BY w.space_id, w.space_name, w.space_type " +
            "ORDER BY booked_hours DESC, w.space_name";

    private final JdbcTemplate jdbcTemplate;
    private final WorkspaceRowMapper rowMapper = new WorkspaceRowMapper();
    private final SpaceUtilizationRowMapper utilizationMapper = new SpaceUtilizationRowMapper();

    public WorkspaceDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insert(String name, SpaceType type, BigDecimal hourlyRate) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setString(2, type.name());
            ps.setBigDecimal(3, hourlyRate);
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    public Optional<Workspace> findById(int spaceId) {
        return jdbcTemplate.query(SELECT_ALL_COLUMNS + " WHERE space_id = ?", rowMapper, spaceId)
                .stream().findFirst();
    }

    public List<Workspace> findAll() {
        return jdbcTemplate.query(SELECT_ALL_COLUMNS + " ORDER BY space_id", rowMapper);
    }

    public List<Workspace> findAvailable(LocalDate date, int slotHour) {
        return jdbcTemplate.query(FIND_AVAILABLE, rowMapper, Date.valueOf(date), slotHour);
    }

    public List<SpaceUtilization> findUtilization() {
        return jdbcTemplate.query(UTILIZATION, utilizationMapper);
    }
}
