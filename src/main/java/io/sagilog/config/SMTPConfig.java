package io.sagilog.config;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SMTPConfig {
    private static Properties props ;

    public static Properties get() {
        if(props!=null) {
            props = new Properties();
            try (InputStream input = new FileInputStream("smtp.properties")) {
                props.load(input);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return props;

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
