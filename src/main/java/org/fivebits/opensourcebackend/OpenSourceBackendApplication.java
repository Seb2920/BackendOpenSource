package org.fivebits.opensourcebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * OpenSourceBackendApplication main class
 * @summary Entry point for the BrewDog Supply Chain and Inventory REST API.
 * @author Andre Sebastian Quispe Almonacid
 */

@EnableJpaAuditing
@SpringBootApplication
public class OpenSourceBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenSourceBackendApplication.class, args);
    }

}
