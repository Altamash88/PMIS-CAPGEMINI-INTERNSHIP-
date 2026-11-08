package com.coworking.hotdesk.model;

import java.math.BigDecimal;

/** One row of the "revenue by space type" aggregate query. */
public record RevenueSummary(SpaceType spaceType, int bookingCount, BigDecimal revenue) {
}
