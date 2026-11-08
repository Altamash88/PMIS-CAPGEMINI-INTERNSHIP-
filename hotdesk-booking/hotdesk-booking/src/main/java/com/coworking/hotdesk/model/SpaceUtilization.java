package com.coworking.hotdesk.model;

import java.math.BigDecimal;

/** One row of the per-workspace utilization aggregate query. */
public record SpaceUtilization(int spaceId, String spaceName, SpaceType spaceType, int bookedHours, BigDecimal revenue) {
}
