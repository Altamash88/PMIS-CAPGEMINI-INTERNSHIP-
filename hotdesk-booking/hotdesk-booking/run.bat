@echo off
REM Build and run the Hot-Desk Booking console app (Windows)
cd /d "%~dp0"

where java >nul 2>nul
if errorlevel 1 (
  echo Java 21 not found. Install JDK 21 first.
  pause
  exit /b 1
)
where mvn >nul 2>nul
if errorlevel 1 (
  echo Maven not found. Install Apache Maven 3.8+ and add it to PATH.
  pause
  exit /b 1
)

if not defined DB_USER set DB_USER=root
if not defined DB_PASSWORD set /p DB_PASSWORD=MySQL password for user %DB_USER% (press Enter if none): 

echo Building (the first run downloads dependencies and can take a minute)...
call mvn -q -DskipTests package
if errorlevel 1 (
  echo Build failed.
  pause
  exit /b 1
)

java -jar target\hotdesk-booking.jar
pause
