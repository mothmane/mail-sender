package io.sagilog.services;

import io.sagilog.domain.Client;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.List;

@FunctionalInterface
public interface ClientExtractor {

    public List<Client> extractClients(String fileName) throws InvalidFormatException, IOException;

}
