# Down Time Alerter

The Application checks if the server is responding to ping, if not then its sends a notification via Twilio Integration

## Getting Started

Application uses Spring boot framework. All dependencies can be downloaded via maven.

### Prerequisites

Twilio Account is required and can be made on https://www.twilio.com/.

### Installing

Before installing the application update the URL in application.properties file

```
url.passed = https://github.com
```

Update the Twilio account details in TwilioConstants.java file

```
public static final String ACCOUNT_SID = "XXXXXXXXX";
public static final String AUTH_TOKEN = "YYYYYYYY";
public static final String TWILIO_NUMBER = "XXYXYXYX";
```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [Twilio](https://www.twilio.com) - Used to send notifications.


