-- =====================================================================
--  Co-Working Hot-Desk Booking Engine : schema (MySQL 8.x)
--  Idempotent: safe to run on every application start.
-- =====================================================================

CREATE TABLE IF NOT EXISTS workspaces (
    space_id     INT AUTO_INCREMENT PRIMARY KEY,
    space_name   VARCHAR(100) NOT NULL,
    space_type   VARCHAR(50)  NOT NULL,
    hourly_rate  DECIMAL(8,2) NOT NULL,
    is_active    BOOLEAN DEFAULT TRUE,
    CONSTRAINT uq_workspace_name UNIQUE (space_name),
    CONSTRAINT chk_workspace_type CHECK (space_type IN ('HOT_DESK', 'DEDICATED_DESK', 'MEETING_ROOM', 'CABIN')),
    CONSTRAINT chk_workspace_rate CHECK (hourly_rate > 0)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS users (
    user_id         INT AUTO_INCREMENT PRIMARY KEY,
    full_name       VARCHAR(100) NOT NULL,
    email           VARCHAR(120) NOT NULL UNIQUE,
    wallet_balance  DECIMAL(10,2) DEFAULT 0.00,
    CONSTRAINT chk_wallet_not_negative CHECK (wallet_balance >= 0)
) ENGINE=InnoDB;

-- active_slot is a generated column: 1 while the booking is CONFIRMED, NULL once CANCELLED.
-- MySQL allows many NULLs in a UNIQUE index, so:
--   * two CONFIRMED bookings for the same space/date/hour are rejected by the database itself
--     (this is the final guard against double-booking, even under concurrency), and
--   * a CANCELLED slot can be booked again.
CREATE TABLE IF NOT EXISTS bookings (
    booking_id      INT AUTO_INCREMENT PRIMARY KEY,
    user_id         INT NOT NULL,
    space_id        INT NOT NULL,
    booking_date    DATE NOT NULL,
    slot_hour       INT NOT NULL,
    total_cost      DECIMAL(8,2) NOT NULL,
    booking_status  VARCHAR(30) NOT NULL,
    active_slot     TINYINT GENERATED ALWAYS AS (CASE WHEN booking_status = 'CONFIRMED' THEN 1 ELSE NULL END) STORED,
    CONSTRAINT fk_booking_user  FOREIGN KEY (user_id)  REFERENCES users (user_id),
    CONSTRAINT fk_booking_space FOREIGN KEY (space_id) REFERENCES workspaces (space_id),
    CONSTRAINT uq_space_date_slot UNIQUE (space_id, booking_date, slot_hour, active_slot),
    CONSTRAINT chk_booking_slot CHECK (slot_hour BETWEEN 0 AND 23),
    CONSTRAINT chk_booking_status CHECK (booking_status IN ('CONFIRMED', 'CANCELLED'))
) ENGINE=InnoDB;
