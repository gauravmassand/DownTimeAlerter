package com.downtime.alerter.Downtime.Alerter;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import static com.downtime.alerter.Downtime.Alerter.TwilioConstants.ACCOUNT_SID;
import static com.downtime.alerter.Downtime.Alerter.TwilioConstants.AUTH_TOKEN;

@Service
public class TwilioSMS {

    @Bean("initTwilio")
    public void initTwilio() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    public void sendSMS(String messageBody) {
        Message
                .creator(new PhoneNumber("+919028415812"), // to
                        new PhoneNumber(TwilioConstants.TWILIO_NUMBER), // from
                        messageBody)
                .create();
    }
}
