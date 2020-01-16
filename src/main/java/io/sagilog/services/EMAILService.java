package io.sagilog.services;

import io.sagilog.config.SMTPConfig;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EMAILService {

    private static String FROM ="maniar.othmane@gmail.com";
    private static String SUBJECT ="subject";

    public void send( String email ,String mail,Session session){

        try {

            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(FROM));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject(SUBJECT);
            message.setContent(mail, "text/html");
            Transport.send(message);

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

}
