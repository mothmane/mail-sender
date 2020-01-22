package io.sagilog.services;

import io.sagilog.domain.Client;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.Properties;


public class MailAdapter {

    private VelocityEngine velocityEngine;

    public void init(String templatePath) {
        velocityEngine = new VelocityEngine();
        Properties props = new Properties();
        props.put("file.resource.loader.path", templatePath);
        velocityEngine.init();

    }

    public String createMail(Client client, String templateFile) {
        String templatePath = templateFile.substring(0, templateFile.lastIndexOf("/")+1);
    templateFile = templateFile.substring( templateFile.lastIndexOf("/")+1, templateFile.length());
        init(templatePath);

        Template template = velocityEngine.getTemplate(templateFile);

        VelocityContext context = toContext(client);

        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        return writer.toString();
    }


    public String getTemplateAsString(String templateFile) {
        init("");

        Template template = velocityEngine.getTemplate(templateFile);




        //TODO
        return "";
    }

    public VelocityContext toContext(Client client) {
        VelocityContext context = new VelocityContext();
        context.put("firstName", client.getFirstName());
        context.put("lastName", client.getLastName());
        return context;
    }
}
