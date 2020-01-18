package io.sagilog.services;

import io.sagilog.domain.Client;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class MailAdapterTest {

    private final String TEMPLATE01="src/test/resources/templates/mail.vm";
    private MailAdapter mailAdapter;

    @BeforeEach
    void init(){

        mailAdapter=new MailAdapter();
    }

    @Test
    void should_return_2_clients() throws Exception{

        Client hamid=new Client("hamid","hamida",null,null, LocalDate.now());

       String result=  mailAdapter.createMail(hamid,TEMPLATE01);

        System.out.println(result);


    }
}
