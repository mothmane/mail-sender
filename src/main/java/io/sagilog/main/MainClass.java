package io.sagilog.main;


import io.sagilog.config.SMTPConfig;
import io.sagilog.domain.Mail;
import io.sagilog.services.EMAILService;
import io.sagilog.services.XLSClientExtractor;
import io.sagilog.ui.SwingMailFrame;

import javax.swing.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class MainClass {

    private final String clientFile = "";
    private final String templateFile = "";
    private final String to = "";
    private final String from = "maniar.othmane@gmail.com";
    private final String password = "secret";
    private final String subject = "";
    private final String content = "";
    private final int DAYS_NUMBER_SINCE_LAST_MAIL = 90;


    private EMAILService emailService = new EMAILService(SMTPConfig.session(from, password));
    private XLSClientExtractor clientExtractor = new XLSClientExtractor();

    public MainClass() {
        try {
            lunch();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void lunch() throws Exception {

        final LocalDate today = LocalDate.now();
        clientExtractor.extractClients(clientFile)
                .stream()
                .filter(client -> ChronoUnit.DAYS.between(client.getLastMailSent(), today) > DAYS_NUMBER_SINCE_LAST_MAIL)
                .map(client -> new Mail(client.getEmail(), from, subject, null, content)).
                forEach(emailService::send);


    }

    public void uiMain() {
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


    }


}

