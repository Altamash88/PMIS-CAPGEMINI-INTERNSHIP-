package com.coworking.hotdesk.model;

import java.math.BigDecimal;

public record Workspace(int spaceId, String spaceName, SpaceType spaceType, BigDecimal hourlyRate, boolean active) {
}
