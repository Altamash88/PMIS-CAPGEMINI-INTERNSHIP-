package com.coworking.hotdesk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HotDeskApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(HotDeskApplication.class, args);
        } catch (Exception e) {
            Throwable root = e;
            while (root.getCause() != null && root.getCause() != root) {
                root = root.getCause();
            }
            System.err.println();
            System.err.println("Application failed to start: " + root.getMessage());
            System.err.println("Check that MySQL 8 is running and that DB_USER / DB_PASSWORD / DB_URL are correct.");
            System.exit(1);
        }
    }
}
