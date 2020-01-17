package io.sagilog.services;

import io.sagilog.config.SMTPConfig;
import io.sagilog.domain.Client;
import io.sagilog.domain.Mail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EMAILServiceTest {


    private EMAILService emailService;

    @BeforeEach
    void init(){

        emailService=new EMAILService(SMTPConfig.session("maniar.othmane@gmail.com",""));
    }

    @Test
    void should_return_2_clients() throws Exception{
        Mail mail = new Mail("tahifa.med@gmail.com","maniar.othmane@gmail.com","subject",null,"bla bla bla");

       emailService.send(mail);


    }
}
