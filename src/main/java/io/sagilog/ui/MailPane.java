package io.sagilog.ui;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;

public class MailPane extends JEditorPane{

    MailPane(){
        this.setEditable(false);
        this.setPreferredSize(new Dimension(700, 130));
        this.setBackground(null);
        // Text font
        Font font = new Font("Sans", Font.PLAIN, 6);
        this.setFont(font);
        // Handle HTML
        this.setEditorKit(new HTMLEditorKit());
        //this.addHyperlinkListener(this);
       // this.addHyperlinkListener(this);

        //this.setText(ERROR_MSG);
        //this.setText(ERROR_MSG);

       // return this;
    }

}
