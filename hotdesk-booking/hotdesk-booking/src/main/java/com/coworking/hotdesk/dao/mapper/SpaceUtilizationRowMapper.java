package com.coworking.hotdesk.dao.mapper;

import com.coworking.hotdesk.model.SpaceType;
import com.coworking.hotdesk.model.SpaceUtilization;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SpaceUtilizationRowMapper implements RowMapper<SpaceUtilization> {
    @Override
    public SpaceUtilization mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new SpaceUtilization(
                rs.getInt("space_id"),
                rs.getString("space_name"),
                SpaceType.valueOf(rs.getString("space_type")),
                rs.getInt("booked_hours"),
                rs.getBigDecimal("revenue"));
    }
}
