package io.sagilog.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CVLoader {

    public  static List<File> load(String folder){
        List<File> files = null;
        Stream<Path> stream =null;
        try {
            stream = Files.walk(Paths.get(folder));
            files = stream.filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        stream.close();
        return files;
    }
}
