package com.downtime.alerter.Downtime.Alerter;

import org.apache.commons.validator.routines.UrlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class DownTimeCheck {

    @Value("${url.passed}")
    private String urlPassed;

    private static final Logger logger = LoggerFactory.getLogger(DownTimeCheck.class);

    @Autowired
    TwilioSMS twilioSMS;

    private AtomicBoolean urlInvalidSent = new AtomicBoolean(false);

    @Scheduled(fixedDelay = 10000)
    public void checkResponse() throws IOException {
        if (urlInvalidSent.get()) {
            logger.info("SMS already sent for invalid url");
        } else {
            logger.info("checking respone of URL");
        }
        if (!urlInvalidSent.get() && checkValidURL()) {
            URL url = new URL(urlPassed);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            int statusCode = http.getResponseCode();
            if (statusCode == 200) {
                logger.info("Server is up");
            } else {
                logger.info("Server is down sending sms");
                twilioSMS.sendSMS("Server is down. Please check");
            }
        }
    }

    @Bean
    @DependsOn("initTwilio")
    public boolean checkValidURL() {
        logger.info("checking for validity of URL");
        UrlValidator urlValidator = new UrlValidator();
        boolean isValid = urlValidator.isValid(urlPassed);
        if (isValid) {
            return true;
        } else {
            urlInvalidSent.set(true);
            twilioSMS.sendSMS("URL passed is invalid");
            return false;
        }
    }

}
