package com.coworking.hotdesk.service;

import com.coworking.hotdesk.dao.UserDao;
import com.coworking.hotdesk.exception.InvalidRequestException;
import com.coworking.hotdesk.exception.ResourceNotFoundException;
import com.coworking.hotdesk.model.User;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class UserService {

    private static final BigDecimal MAX_WALLET = new BigDecimal("99999999.99");

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    /** FR-1: onboard a member with an initial wallet credit. */
    @Transactional
    public User onboardMember(String fullName, String email, BigDecimal initialCredit) {
        if (fullName == null || fullName.isBlank()) {
            throw new InvalidRequestException("Full name is required.");
        }
        fullName = fullName.trim();
        if (fullName.length() > 100) {
            throw new InvalidRequestException("Full name must be at most 100 characters.");
        }
        if (email == null || !email.trim().matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            throw new InvalidRequestException("A valid email address is required.");
        }
        email = email.trim().toLowerCase();
        if (email.length() > 120) {
            throw new InvalidRequestException("Email must be at most 120 characters.");
        }
        if (initialCredit == null || initialCredit.signum() < 0) {
            throw new InvalidRequestException("Initial wallet credit cannot be negative.");
        }
        initialCredit = initialCredit.setScale(2, RoundingMode.HALF_UP);
        if (initialCredit.compareTo(MAX_WALLET) > 0) {
            throw new InvalidRequestException("Initial wallet credit cannot exceed " + MAX_WALLET + ".");
        }
        try {
            int id = userDao.insert(fullName, email, initialCredit);
            return userDao.findById(id).orElseThrow();
        } catch (DuplicateKeyException e) {
            throw new InvalidRequestException("A member with email '" + email + "' already exists.");
        }
    }

    /** Adds prepaid credit to a member's wallet. */
    @Transactional
    public User topUpWallet(int userId, BigDecimal amount) {
        if (amount == null || amount.signum() <= 0) {
            throw new InvalidRequestException("Top-up amount must be greater than zero.");
        }
        amount = amount.setScale(2, RoundingMode.HALF_UP);
        User current = userDao.findByIdForUpdate(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Member #" + userId + " not found."));
        if (current.walletBalance().add(amount).compareTo(MAX_WALLET) > 0) {
            throw new InvalidRequestException("Wallet balance cannot exceed " + MAX_WALLET + ".");
        }
        userDao.credit(userId, amount);
        return userDao.findById(userId).orElseThrow();
    }

    @Transactional(readOnly = true)
    public User getUser(int userId) {
        return userDao.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Member #" + userId + " not found."));
    }

    @Transactional(readOnly = true)
    public List<User> listAll() {
        return userDao.findAll();
    }
}
