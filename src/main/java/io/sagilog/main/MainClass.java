package io.sagilog.main;


import io.sagilog.config.SMTPConfig;
import io.sagilog.domain.Mail;
import io.sagilog.services.EMAILService;
import io.sagilog.services.XLSClientExtractor;
import io.sagilog.ui.SwingMailFrame;
import lombok.SneakyThrows;
import org.apache.commons.cli.*;

import javax.swing.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static io.sagilog.cli.CLIParameters.configFirstParameters;
import static io.sagilog.cli.CLIParameters.configParameters;

public class MainClass {


    private static String to = "";
    private static String content = "";
    private int DAYS_NUMBER_SINCE_LAST_MAIL = 90;


    private XLSClientExtractor clientExtractor = new XLSClientExtractor();
    private EMAILService emailService;

    public MainClass(EMAILService emailService, String clientsFile, String from, String subject) {
        this.emailService = emailService;
        try {
            lunch(clientsFile, from, subject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void lunch(String clientsFile, String from, String subject) throws Exception {

        final LocalDate today = LocalDate.now();
        clientExtractor.extractClients(clientsFile)
                .stream()
                .filter(client -> ChronoUnit.DAYS.between(client.getLastMailSent(), today) > DAYS_NUMBER_SINCE_LAST_MAIL)
                .map(client -> new Mail(client.getEmail(), from, subject, null, content)).
                forEach(emailService::send);
    }

    public void uiLunch() {
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

    @SneakyThrows
    public static void main(String[] args) {
        final Options firstOptions = configFirstParameters();
        final Options options = configParameters(firstOptions);

        final CommandLineParser parser = new DefaultParser();
        final CommandLine firstLine= parser.parse(firstOptions, args,true);


        helpMode(options, firstLine);

        final CommandLine line = parser.parse(options, args);


        String from =line.getOptionValue("from");
        String subject=line.getOptionValue("subject");
        String clientsFile=line.getOptionValue("clients");
        String templateFile=line.getOptionValue("subject");

        String password=readPassword(from);

        new MainClass(new EMAILService(SMTPConfig.session(from, password)), clientsFile, from, subject);


    }

    private static void helpMode(Options options, CommandLine firstLine) {
        // Si mode aide
        boolean helpMode = firstLine.hasOption("help");
        if (helpMode) {
            final HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("Mail Sender", options, true);
            System.exit(0);
        }
    }
    private static String readPassword(String username) {
        char[] password = System.console().readPassword("Enter password for %s user: ", username);
        return password.toString();
    }


}

