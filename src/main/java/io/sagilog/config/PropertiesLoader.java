package io.sagilog.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

    public static Properties load(String fileName) {
        var props = new Properties();
        try (InputStream input = new FileInputStream(fileName)) {
            props.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;

    }
}
