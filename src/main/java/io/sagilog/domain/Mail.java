package io.sagilog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mail {
    private String from;
    private String to;
    private String subject;
    private List<File> attached;
    private String content;
}
