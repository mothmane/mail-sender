package io.sagilog.ui;

import io.sagilog.config.SMTPConfig;
import io.sagilog.services.EMAILService;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Properties;

public class SwingMailFrame extends JFrame {
    //private ConfigUtility configUtil = new ConfigUtility();
    private EMAILService emailService= new EMAILService(SMTPConfig.session("username","passw"));

    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuFile = new JMenu("File");
    private JMenuItem menuItemSetting = new JMenuItem("Settings..");

    private JLabel labelTo = new JLabel("To: ");
    private JLabel labelSubject = new JLabel("Subject: ");

    private JTextField fieldTo = new JTextField(30);
    private JTextField fieldSubject = new JTextField(30);

    private JButton buttonSend = new JButton("SEND");

    private JFilePicker filePickerTemplate = new JFilePicker("Template", "choose template...");
    private JFilePicker filePickerXLS = new JFilePicker("Excel file", "choose Excel...");

    private MailPane mailPane = new MailPane();

    private GridBagConstraints constraints = new GridBagConstraints();

    public SwingMailFrame() {
        super("Swing E-mail Sender Program");

        // set up layout
        setLayout(new GridBagLayout());
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);

        setupMenu();
        setupForm();

        pack();
        setLocationRelativeTo(null);    // center on screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setupMenu() {
        menuItemSetting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
              //  SettingsDialog dialog = new SettingsDialog(SwingEmailSender.this, configUtil);
              //  dialog.setVisible(true);
            }
        });

        menuFile.add(menuItemSetting);
        menuBar.add(menuFile);
        setJMenuBar(menuBar);
    }

    private void add(Component component,GridBagConstraints constraints,int x,int y){
        constraints.gridx = x;
        constraints.gridy = y;
        add(component, constraints);
    }

    private void setupForm() {

        add(labelTo, constraints,0,0);

        constraints.gridx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(fieldTo, constraints,1,0);


        add(labelSubject, constraints,0,1);


        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(fieldSubject, constraints,1,1);


        constraints.gridheight = 2;
        constraints.fill = GridBagConstraints.BOTH;
        buttonSend.setFont(new Font("Arial", Font.BOLD, 16));
        add(buttonSend, constraints,2,0);

        buttonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                buttonSendActionPerformed(event);
            }
        });

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridheight = 1;
        constraints.gridwidth = 3;
        filePickerTemplate.setMode(JFilePicker.MODE_OPEN);
        add(filePickerTemplate, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridheight = 1;
        constraints.gridwidth = 3;
        filePickerXLS.setMode(JFilePicker.MODE_OPEN);
        add(filePickerXLS, constraints);


        constraints.gridy = 4;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;

        add(new JScrollPane(mailPane), constraints);
    }

    private void buttonSendActionPerformed(ActionEvent event) {
        if (!validateFields()) {
            return;
        }

        String toAddress = fieldTo.getText();
        String subject = fieldSubject.getText();
        String message = mailPane.getText();

        File[] attachFiles = null;

      /* if (!filePicker.getSelectedFilePath().equals("")) {
            File selectedFile = new File(filePicker.getSelectedFilePath());
            attachFiles = new File[] {selectedFile};
        }*/


        try {
            Properties smtpProperties = SMTPConfig.get();
           // emailService.send();


          //  EmailUtility.sendEmail(smtpProperties, toAddress, subject, message, attachFiles);

            JOptionPane.showMessageDialog(this,
                    "The e-mail has been sent successfully!");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error while sending the e-mail: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateFields() {

        if( !validateField(fieldTo,"To address")
                || !validateField(fieldSubject,"subject")
                || !validateField(mailPane,"mail")
           ){
            return false;
          }

        return true;
    }

    private boolean validateField(JTextComponent field, String fieldName){
        if (field.getText().equals("")) {
            JOptionPane.showMessageDialog(this,
                    "Please enter "+fieldName+"!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            field.requestFocus();
            return false;
        }
        return true;
    }


}
