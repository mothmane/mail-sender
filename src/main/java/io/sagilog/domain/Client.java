package io.sagilog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    private String firstName;
    private String lastName;
    private String email;
    private String company;
    private LocalDate lastMailSent;


}
