#!/usr/bin/env bash
# Build and run the Hot-Desk Booking console app (macOS / Linux)
cd "$(dirname "$0")"

command -v java >/dev/null 2>&1 || { echo "Java 21 not found. Install JDK 21 first."; exit 1; }
command -v mvn  >/dev/null 2>&1 || { echo "Maven not found. Install Apache Maven 3.8+ first."; exit 1; }

export DB_USER="${DB_USER:-root}"
if [ -z "${DB_PASSWORD+x}" ]; then
  read -r -s -p "MySQL password for user '$DB_USER' (press Enter if none): " DB_PASSWORD
  echo
  export DB_PASSWORD
fi

echo "Building (the first run downloads dependencies and can take a minute)..."
mvn -q -DskipTests package || { echo "Build failed."; exit 1; }

java -jar target/hotdesk-booking.jar
