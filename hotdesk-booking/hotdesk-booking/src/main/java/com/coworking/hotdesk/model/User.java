package com.coworking.hotdesk.model;

import java.math.BigDecimal;

public record User(int userId, String fullName, String email, BigDecimal walletBalance) {
}
