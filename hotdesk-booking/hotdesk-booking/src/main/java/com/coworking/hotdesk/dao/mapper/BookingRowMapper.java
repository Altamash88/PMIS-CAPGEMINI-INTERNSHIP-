package com.coworking.hotdesk.dao.mapper;

import com.coworking.hotdesk.model.Booking;
import com.coworking.hotdesk.model.BookingStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingRowMapper implements RowMapper<Booking> {
    @Override
    public Booking mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Booking(
                rs.getInt("booking_id"),
                rs.getInt("user_id"),
                rs.getInt("space_id"),
                rs.getString("space_name"),
                rs.getDate("booking_date").toLocalDate(),
                rs.getInt("slot_hour"),
                rs.getBigDecimal("total_cost"),
                BookingStatus.valueOf(rs.getString("booking_status")));
    }
}
