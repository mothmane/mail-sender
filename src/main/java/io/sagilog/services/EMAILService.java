package io.sagilog.services;

import io.sagilog.domain.Mail;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EMAILService {

    private Session session;
    private final String MESSAGE_TYPE = "text/html";

    public EMAILService(Session session) {
        this.session = session;
    }

    public void send(Mail mail, boolean dry) {

        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mail.getFrom()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail.getTo()));
            message.setSubject(mail.getSubject());
            message.setContent(mail, MESSAGE_TYPE);
            if (dry) {
                System.out.println("an email should be sent to +"+mail.getTo() + " with subject " + mail.getSubject());
            } else {
                Transport.send(message);
            }
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    public void send(Mail mail){
        send(mail,false);
    }

}
