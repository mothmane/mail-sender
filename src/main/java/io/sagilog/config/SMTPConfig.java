package io.sagilog.config;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class SMTPConfig {




    public static Properties get(){

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        return prop;

    }

    public static Session session(String username,String password){
      return   Session.getInstance(get(),
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

    }


}
