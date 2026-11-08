package com.coworking.hotdesk.exception;

/** Base class for all business-rule failures. Unchecked, so @Transactional rolls back automatically. */
public class HotDeskException extends RuntimeException {
    public HotDeskException(String message) {
        super(message);
    }
}
