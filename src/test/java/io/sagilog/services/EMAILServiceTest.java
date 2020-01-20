package io.sagilog.services;

import io.sagilog.config.SMTPConfig;
import io.sagilog.domain.Client;
import io.sagilog.domain.Mail;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

public class EMAILServiceTest {


    public static final String CVDIRECTORY = "src/test/resources/CVDirectory";
    private EMAILService emailService;

    @BeforeEach
    void init(){
        emailService=new EMAILService(SMTPConfig.session("tahifa.med@gmail.com",""));
    }

    @Test
    void should_return_2_clients() throws Exception{
        Mail mail = new Mail("tahifa.med@gmail.com","m.tahifa@sagilog.io","subject",emailService.extractFilesFromFolder(CVDIRECTORY),"bla bla bla ");

       emailService.send(mail);


    }

    @Test
    void should_return_3_files(){
        List<File> filesExcpected = emailService.extractFilesFromFolder(CVDIRECTORY);
        Assertions.assertThat(filesExcpected.size()).isEqualTo(3);
    }
}
