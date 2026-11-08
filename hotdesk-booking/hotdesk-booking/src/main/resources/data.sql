-- =====================================================================
--  Seed data. INSERT IGNORE + fixed IDs => re-running never duplicates
--  rows and never resets wallet balances changed during a demo.
-- =====================================================================

INSERT IGNORE INTO workspaces (space_id, space_name, space_type, hourly_rate, is_active) VALUES
    (1,  'Desk-A10',       'HOT_DESK',       100.00, TRUE),
    (2,  'Desk-A11',       'HOT_DESK',       100.00, TRUE),
    (3,  'Desk-A12',       'HOT_DESK',       120.00, TRUE),
    (4,  'Desk-B01',       'DEDICATED_DESK', 150.00, TRUE),
    (5,  'Desk-B02',       'DEDICATED_DESK', 150.00, TRUE),
    (6,  'Boardroom-1',    'MEETING_ROOM',   600.00, TRUE),
    (7,  'Meeting-Room-2', 'MEETING_ROOM',   400.00, TRUE),
    (8,  'Cabin-C1',       'CABIN',          350.00, TRUE),
    (9,  'Cabin-C2',       'CABIN',          350.00, TRUE),
    (10, 'Desk-A99',       'HOT_DESK',       100.00, FALSE);   -- under maintenance (inactive)

INSERT IGNORE INTO users (user_id, full_name, email, wallet_balance) VALUES
    (1, 'Aarav Sharma', 'aarav.sharma@example.com', 5000.00),
    (2, 'Priya Nair',   'priya.nair@example.com',   3500.00),
    (3, 'Rohan Mehta',  'rohan.mehta@example.com',  1200.00),
    (4, 'Sana Khan',    'sana.khan@example.com',    800.00),
    (5, 'Vikram Iyer',  'vikram.iyer@example.com',  10000.00),
    (6, 'Neha Gupta',   'neha.gupta@example.com',   50.00);    -- low balance: try booking a Boardroom to see rejection

-- Historical bookings (feed the revenue / utilization reports)
INSERT IGNORE INTO bookings (booking_id, user_id, space_id, booking_date, slot_hour, total_cost, booking_status) VALUES
    (1, 1, 1, '2026-10-26',  9, 100.00, 'CONFIRMED'),
    (2, 1, 1, '2026-10-26', 10, 100.00, 'CONFIRMED'),
    (3, 2, 6, '2026-10-27', 14, 600.00, 'CONFIRMED'),
    (4, 3, 8, '2026-10-28', 11, 350.00, 'CONFIRMED'),
    (5, 4, 2, '2026-10-29',  9, 100.00, 'CANCELLED'),
    (6, 2, 4, '2026-10-30', 10, 150.00, 'CONFIRMED');

-- A booking 2 days from "now" so Cancel + Refund can be demoed right away (Vikram Iyer = member 5)
INSERT IGNORE INTO bookings (booking_id, user_id, space_id, booking_date, slot_hour, total_cost, booking_status) VALUES
    (7, 5, 6, CURDATE() + INTERVAL 2 DAY, 15, 600.00, 'CONFIRMED');
