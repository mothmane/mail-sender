package io.sagilog.services;

import io.sagilog.config.SMTPConfig;
import io.sagilog.domain.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EMAILServiceTest {


    private EMAILService emailService;

    @BeforeEach
    void init(){

        emailService=new EMAILService();
    }

    @Test
    void should_return_2_clients() throws Exception{

       emailService.send("tahifa.med@gmail.com","bla bla bla", SMTPConfig.session("maniar.othmane@gmail.com",""));


    }
}
