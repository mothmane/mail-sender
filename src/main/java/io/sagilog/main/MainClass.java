package io.sagilog.main;


import io.sagilog.config.SMTPConfig;
import io.sagilog.domain.Mail;
import io.sagilog.services.EMAILService;
import io.sagilog.ui.SwingMailFrame;

import javax.swing.*;

public class MainClass {

    private static EMAILService emailService= new EMAILService(SMTPConfig.session("maniar.othmane@gmail.com",""));


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
        Mail mail= new Mail();

        emailService.send(mail);

    }
}

