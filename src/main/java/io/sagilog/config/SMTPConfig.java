package io.sagilog.config;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class SMTPConfig {




    public static Properties get(){

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true") ;
        props.put("mail.smtp.auth", "true") ;

        return props;

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
