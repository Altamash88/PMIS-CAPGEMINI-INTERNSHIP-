package com.coworking.hotdesk.service;

import com.coworking.hotdesk.dao.BookingDao;
import com.coworking.hotdesk.dao.WorkspaceDao;
import com.coworking.hotdesk.model.RevenueSummary;
import com.coworking.hotdesk.model.SpaceUtilization;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReportService {

    private final BookingDao bookingDao;
    private final WorkspaceDao workspaceDao;

    public ReportService(BookingDao bookingDao, WorkspaceDao workspaceDao) {
        this.bookingDao = bookingDao;
        this.workspaceDao = workspaceDao;
    }

    /** FR-5: total revenue grouped by workspace category. */
    @Transactional(readOnly = true)
    public List<RevenueSummary> revenueBySpaceType() {
        return bookingDao.findRevenueByType();
    }

    /** Space utilization: booked hours and revenue per workspace. */
    @Transactional(readOnly = true)
    public List<SpaceUtilization> spaceUtilization() {
        return workspaceDao.findUtilization();
    }
}
