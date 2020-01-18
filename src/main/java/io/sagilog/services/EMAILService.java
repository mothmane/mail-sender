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

    public EMAILService(Session session){
        this.session=session;
    }

    public void send(Mail mail){

        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mail.getFrom()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail.getContent()));
            message.setSubject(mail.getSubject());
            message.setContent(mail, MESSAGE_TYPE);
            Transport.send(message);

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

}
