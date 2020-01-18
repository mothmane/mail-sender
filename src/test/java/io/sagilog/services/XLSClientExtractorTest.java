package io.sagilog.services;

import io.sagilog.domain.Client;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class XLSClientExtractorTest {

    private final String CLIENTS01="src/test/resources/clients01.xlsx";
    private final String DEMO="src/test/resources/example.xlsx";
    private ClientExtractor extrator;

    @BeforeEach
    void init(){

        extrator=new XLSClientExtractor();
    }

    @Test
    void should_return_2_clients() throws Exception{

        List<Client> actual=extrator.extractClients(CLIENTS01);


        List<Client> expected=List.of(new Client("hamid","hamida","hamid.hamida@hamid.com",null, LocalDate.now()),
                                      new Client("jalil","jalila","jalil.jalila@jalil.com",null,LocalDate.now()));

        Assertions.assertThat(actual).isEqualTo(expected);

    }
    @Test
    void should_return_all_clients() throws Exception{

        List<Client> actual=extrator.extractClients(DEMO);

        actual.forEach(System.out::println);

    }
}
