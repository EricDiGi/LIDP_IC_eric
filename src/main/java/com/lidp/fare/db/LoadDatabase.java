package com.lidp.fare.db;

import com.lidp.fare.dao.FareRepository;
import com.lidp.fare.domain.Fare;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.logging.Logger;

@Configuration
public class LoadDatabase {
    private static final Logger logger = Logger.getLogger(LoadDatabase.class.getName());

    @Bean
    CommandLineRunner initDatabase(FareRepository fareRepository) {
        return args -> {
            logger.info(fareRepository.save(new Fare(Instant.parse("2022-02-02T00:00:00.00Z")
                    , 415.21, 1, 123)).toString());
            logger.info(fareRepository.save(new Fare(Instant.parse("2022-01-01T00:00:00.00Z"), 731.74, 6, 435.21)).toString());
        };
    }
}
