# Co-Working Space Hot-Desk Booking Engine

Spring Boot 3 + Spring JDBC (`JdbcTemplate`) + MySQL 8 console application.
Prepaid-wallet bookings for hot desks, dedicated desks, meeting rooms and cabins, with
transactional safety against double-booking and overspending.

## 1. Prerequisites
| Tool | Version |
|------|---------|
| JDK | 21 (17 also works if you change java.version in pom.xml) |
| Apache Maven | 3.8+ |
| MySQL Server | 8.x (must be running on `localhost:3306`) |

## 2. Run it
**Windows:** double-click `run.bat`  
**macOS / Linux:** `./run.sh`

The script asks for your MySQL password (press Enter if `root` has none), builds the project and starts the console menu.
The database `hotdesk_db` is **created automatically**; `schema.sql` and `data.sql` run on every start and are idempotent
(re-running never duplicates seed data or resets wallets).

Manual run:
```bash
mvn -DskipTests package
java -jar target/hotdesk-booking.jar
```

### Configuration (environment variables, all optional)
| Variable | Default |
|----------|---------|
| `DB_USER` | `root` |
| `DB_PASSWORD` | *(empty)* |
| `DB_URL` | `jdbc:mysql://localhost:3306/hotdesk_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC` |

Or edit `src/main/resources/application.properties`.
To reset everything: `DROP DATABASE hotdesk_db;` and start the app again.

## 3. Quick demo script (covers FR-1 to FR-5)
Seed data: 10 workspaces (one inactive), 6 members, a few historical bookings, and booking #7 two days from today.

1. **Option 3 / 4** - list workspaces and members.
2. **FR-1: Option 1** - register `Desk-A20`, `HOT_DESK`, `110`. **Option 2** - onboard a member with Rs.2000.
3. **FR-2: Option 5** - `tomorrow`, hour `10` - lists free spaces.
4. **FR-3: Option 6** - member `1`, space `6`, `tomorrow`, hour `10` - confirmed, wallet debited.
5. **Double booking:** repeat step 4 with member `2` - rejected (slot already booked).
6. **Insufficient funds:** Option 6 with member `6` (Rs.50 wallet) - rejected, nothing deducted, no booking saved.
7. **FR-4: Option 7** - member `5`, booking `7` - cancelled, Rs.600 refunded. Booking it again is now possible.
8. **FR-5: Option 10** - revenue by space type. **Option 11** - utilization per workspace.

## 4. Architecture
```
com.coworking.hotdesk
 |- HotDeskApplication          Spring Boot entry point
 |- config/AppConfig            @Configuration: DataSource (Hikari), JdbcTemplate, TransactionManager
 |- model/                      Workspace, User, Booking, RevenueSummary, SpaceUtilization (+ enums)
 |- dao/                        @Repository: WorkspaceDao, UserDao, BookingDao  (JdbcTemplate, ? placeholders only)
 |  `- mapper/                  Custom RowMapper per model
 |- service/                    @Service: WorkspaceService, UserService, BookingService, ReportService (@Transactional)
 |- exception/                  HotDeskException + Invalid / NotFound / InsufficientBalance / SlotUnavailable
 `- ui/ConsoleRunner            CommandLineRunner: interactive menu with input validation
```

### How the key requirements are met
- **Atomic booking (FR-3):** `BookingService.bookSpace` is one `@Transactional` unit: validate, row-lock the member's wallet
  (`SELECT ... FOR UPDATE`), check funds, check collision, insert the CONFIRMED booking, then debit with a guarded
  `UPDATE ... WHERE wallet_balance >= ?`. Any failure rolls back everything.
- **No double booking:** the availability check is backed by a database constraint. `bookings` has a generated column
  `active_slot` (1 when CONFIRMED, NULL when CANCELLED) inside `UNIQUE (space_id, booking_date, slot_hour, active_slot)`.
  Two confirmed bookings for the same space/date/hour are impossible, even under concurrency, while cancelled slots can be re-booked.
- **Cancellation + refund (FR-4):** only future slots; status set to CANCELLED and cost credited back in one transaction.
- **Aggregations (FR-5):** `GROUP BY space_type` revenue query and a per-workspace utilization query, both with `LEFT JOIN` so empty categories still show.
- **Safety nets in the schema:** `CHECK (wallet_balance >= 0)`, valid space types / statuses, slot hour 0-23, foreign keys.

## 5. GitHub workflow for a team of 6
Do not commit to `main`. Suggested split (one feature branch + PR each, reviewed by a teammate):

| Member | Branch | Scope |
|--------|--------|-------|
| 1 | `feature/project-setup` | pom.xml, `AppConfig`, `application.properties`, .gitignore, README |
| 2 | `feature/schema-data` | `schema.sql`, `data.sql` |
| 3 | `feature/dao-layer` | models, RowMappers, the three DAOs |
| 4 | `feature/service-rules` | `BookingService` (booking, cancellation, validation), exceptions |
| 5 | `feature/user-workspace-reports` | `UserService`, `WorkspaceService`, `ReportService` |
| 6 | `feature/console-ui` | `ConsoleRunner`, run scripts, demo script |

```bash
git init && git add . && git commit -m "Initial project skeleton"
git branch -M main && git remote add origin <your-repo-url> && git push -u origin main
git checkout -b feature/dao-layer      # work, then:
git commit -m "Add BookingDao with revenue aggregation"
git push -u origin feature/dao-layer   # open a Pull Request, get a review, merge
```
Tip: make small, meaningful commits per person so the history shows balanced contributions.
