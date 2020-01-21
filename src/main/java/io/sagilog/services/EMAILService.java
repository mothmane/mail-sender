package io.sagilog.services;

import io.sagilog.domain.Mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EMAILService {

    private Session session;
    private final String MESSAGE_TYPE = "text/html";

    public EMAILService(Session session) {
        this.session = session;
    }

    public void send(Mail mail, boolean dry) {

        try {

            MimeMessage message = new MimeMessage(session);
            Multipart multipart = new MimeMultipart();

            message.setFrom(new InternetAddress(mail.getFrom()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail.getTo()));
            message.setSubject(mail.getSubject());

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(mail.getContent(), MESSAGE_TYPE);

            multipart.addBodyPart(messageBodyPart);

            for (File attachedFile:mail.getAttached()){
                MimeBodyPart attachPart = new MimeBodyPart();
                attachPart.attachFile(attachedFile);
                attachPart.setFileName(attachedFile.getName());
                multipart.addBodyPart(attachPart);
            }
            // Send the complete message parts
            message.setContent(multipart);

            if (dry) {
                System.out.println("an email should be sent to +"+mail.getTo() + " with subject " + mail.getSubject());
            } else {
                Transport.send(message);
            }
        } catch (MessagingException | IOException mex) {
            mex.printStackTrace();
        }
    }



    public void send(Mail mail){
        send(mail,false);
    }

}
