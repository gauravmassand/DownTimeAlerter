package com.downtime.alerter.Downtime.Alerter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DowntimeAlerterApplication {

    public static void main(String[] args) {
        SpringApplication.run(DowntimeAlerterApplication.class, args);
    }

}
