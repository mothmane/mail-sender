package io.sagilog.main;


import io.sagilog.config.SMTPConfig;
import io.sagilog.domain.Client;
import io.sagilog.domain.Mail;
import io.sagilog.services.EMAILService;
import io.sagilog.services.MailAdapter;
import io.sagilog.ui.SwingMailFrame;

import javax.swing.*;

public class MainClass {

    public static final String TEMPLATES_MAIL_VM = "src/main/resources/templates/mail.vm";
    private static EMAILService emailService= new EMAILService(SMTPConfig.session("tahifa.med@gmail.com","otmjjwukkoaqtant"));


    public void uiMain(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SwingMailFrame().setVisible(true);
            }
        });
    }

    public static void main(String[] args) {

        MailAdapter mailAdapter = new MailAdapter();
        String mailString = mailAdapter.createMail(new Client("firstName","lastName",null,null), TEMPLATES_MAIL_VM);
        Mail mail = new Mail("tahifa.med@gmail.com","m.tahifa@sagilog.io","subject",null,mailString);
        emailService.send(mail);
    }
}

