package io.sagilog.services;

import io.sagilog.domain.Client;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XLSClientExtractor implements ClientExtractor {

    private Integer DEFAULT_SHEET_INDEX = 0;


    public Client extractClient(String line) {
        return null;
    }

    public List<Client> extractClients(String fileName)  throws InvalidFormatException,IOException {

        Workbook workbook = WorkbookFactory.create(new File(fileName));
        Sheet sheet = workbook.getSheetAt(DEFAULT_SHEET_INDEX);

        List<Client> clients=readClients(sheet);


        workbook.close();

        return clients;
    }

    public Client extractClient(Row row) {

        String firstName=convertCellToString(0,row);
        String lastName=convertCellToString(1,row);
        String email=convertCellToString(2,row);

        return new Client(firstName,lastName,email);

    }

    private List<Client> readClients( Sheet sheet){
        List<Client> clients= new ArrayList<>();
        sheet.forEach(row -> {
            clients.add(extractClient(row));
        });
        return clients;
    }

    private String convertCellToString(int index,Row row){
        DataFormatter dataFormatter = new DataFormatter();
       return  dataFormatter.formatCellValue(row.getCell(index));

    }
}
