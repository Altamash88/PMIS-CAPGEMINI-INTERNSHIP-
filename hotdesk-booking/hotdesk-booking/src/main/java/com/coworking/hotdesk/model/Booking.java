package com.coworking.hotdesk.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Booking(int bookingId, int userId, int spaceId, String spaceName, LocalDate bookingDate,
                      int slotHour, BigDecimal totalCost, BookingStatus status) {
}
