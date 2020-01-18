package io.sagilog.config;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class SMTPConfig {
    private static Properties props;

    public static Properties get() {
        return PropertiesLoader.load("smtp.properties");
    }

    public static Session session(String username, String password) {
        return Session.getInstance(get(),
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

    }


}
