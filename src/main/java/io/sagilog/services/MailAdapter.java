package io.sagilog.services;

import io.sagilog.domain.Client;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;


public class MailAdapter {

    private VelocityEngine velocityEngine;

    public void init() {
        velocityEngine = new VelocityEngine();
        velocityEngine.init();

    }

    public String createMail(Client client, String templateFile) {
        init();

        Template template = velocityEngine.getTemplate(templateFile);

        VelocityContext context = toContext(client);

        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        return writer.toString();
    }

    public VelocityContext toContext(Client client) {
        VelocityContext context = new VelocityContext();
        context.put("firstName", client.getFirstName());
        context.put("lastName", client.getLastName());
        return context;
    }
}
