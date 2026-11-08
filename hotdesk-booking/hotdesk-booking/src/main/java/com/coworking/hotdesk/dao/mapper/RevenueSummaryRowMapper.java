package com.coworking.hotdesk.dao.mapper;

import com.coworking.hotdesk.model.RevenueSummary;
import com.coworking.hotdesk.model.SpaceType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RevenueSummaryRowMapper implements RowMapper<RevenueSummary> {
    @Override
    public RevenueSummary mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new RevenueSummary(
                SpaceType.valueOf(rs.getString("space_type")),
                rs.getInt("booking_count"),
                rs.getBigDecimal("revenue"));
    }
}
