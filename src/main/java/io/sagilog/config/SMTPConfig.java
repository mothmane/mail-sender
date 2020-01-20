package io.sagilog.config;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

import static io.sagilog.config.PropertiesLoader.load;

public class SMTPConfig {
    private static Properties props;

    public static Properties get() {
        return load("smtp.properties");
    }

    public static Session session(String username, String password) {
        return Session.getInstance(get(),
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

    }


}
