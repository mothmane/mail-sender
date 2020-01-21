package io.sagilog.main;


import io.sagilog.config.SMTPConfig;
import io.sagilog.domain.Mail;
import io.sagilog.services.CVLoader;
import io.sagilog.services.EMAILService;
import io.sagilog.services.MailAdapter;
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
    private int DAYS_NUMBER_SINCE_LAST_MAIL = 90;


    private XLSClientExtractor clientExtractor = new XLSClientExtractor();
    private EMAILService emailService;

    public MainClass(EMAILService emailService,String templatePath, String clientsFile, String from, String subject,String cvFolder) {
        this.emailService = emailService;

        try {
            lunch( templatePath,clientsFile, from, subject, cvFolder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void lunch(String templatePath, String clientsFile, String from, String subject,String cvFolder) throws Exception {
        System.out.println("STARTING PROCESS");

        var mailAdapter= new MailAdapter();
        final LocalDate today = LocalDate.now();
        var clients=clientExtractor.extractClients(clientsFile);
        System.out.println("size client "+clients.size());
        clients
                .stream()
               // .filter(client -> ChronoUnit.DAYS.between(client.getLastMailSent(), today) > DAYS_NUMBER_SINCE_LAST_MAIL)
                .map(client -> new Mail(from, client.getEmail(), subject, CVLoader.load(cvFolder), mailAdapter.createMail(client,templatePath))).
                forEach(mail -> {
                    System.out.println("client" + mail);
                    emailService.send(mail, true);
                }
                );
        System.out.println("ENDING PROCESS");
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
        String cvFolder=line.getOptionValue("directory");

        String templateFile=line.getOptionValue("template");

        String password=readPassword(from);

        System.out.println("from "+ from);
        System.out.println("subject "+ subject);
        System.out.println("clientsFile "+ clientsFile);
        System.out.println("cvFolder "+ cvFolder);
        System.out.println("templateFile "+ templateFile);

        new MainClass(new EMAILService(SMTPConfig.session(from, password)),templateFile, clientsFile, from, subject,cvFolder);


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

