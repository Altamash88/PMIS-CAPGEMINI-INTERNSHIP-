package com.coworking.hotdesk.service;

import com.coworking.hotdesk.dao.WorkspaceDao;
import com.coworking.hotdesk.exception.InvalidRequestException;
import com.coworking.hotdesk.model.SpaceType;
import com.coworking.hotdesk.model.Workspace;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class WorkspaceService {

    private static final BigDecimal MAX_RATE = new BigDecimal("999999.99");

    private final WorkspaceDao workspaceDao;

    public WorkspaceService(WorkspaceDao workspaceDao) {
        this.workspaceDao = workspaceDao;
    }

    /** FR-1: register a workspace with its type and hourly rate. */
    @Transactional
    public Workspace registerWorkspace(String name, SpaceType type, BigDecimal hourlyRate) {
        if (name == null || name.isBlank()) {
            throw new InvalidRequestException("Workspace name is required.");
        }
        name = name.trim();
        if (name.length() > 100) {
            throw new InvalidRequestException("Workspace name must be at most 100 characters.");
        }
        if (type == null) {
            throw new InvalidRequestException("Workspace type is required.");
        }
        if (hourlyRate == null || hourlyRate.signum() <= 0) {
            throw new InvalidRequestException("Hourly rate must be greater than zero.");
        }
        hourlyRate = hourlyRate.setScale(2, RoundingMode.HALF_UP);
        if (hourlyRate.compareTo(MAX_RATE) > 0) {
            throw new InvalidRequestException("Hourly rate cannot exceed " + MAX_RATE + ".");
        }
        try {
            int id = workspaceDao.insert(name, type, hourlyRate);
            return workspaceDao.findById(id).orElseThrow();
        } catch (DuplicateKeyException e) {
            throw new InvalidRequestException("A workspace named '" + name + "' already exists.");
        }
    }

    @Transactional(readOnly = true)
    public List<Workspace> listAll() {
        return workspaceDao.findAll();
    }
}
