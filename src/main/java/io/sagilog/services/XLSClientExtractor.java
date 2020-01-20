package io.sagilog.services;

import io.sagilog.domain.Client;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class XLSClientExtractor implements ClientExtractor {

    private Integer DEFAULT_SHEET_INDEX = 0;

    private Integer FIRST_NAME_INDEX=3;
    private Integer LAST_NAME_INDEX=4;
    private Integer EMAIL_INDEX=7;
    private Integer COMPANY_INDEX=1;
    private Integer DATE_INDEX=9;
    private DataFormatter dataFormatter=new DataFormatter();



    public List<Client> extractClients(String fileName)  throws InvalidFormatException,IOException {

        var workbook = WorkbookFactory.create(new File(fileName));
        var sheet = workbook.getSheetAt(DEFAULT_SHEET_INDEX);

        var clients=readClients(sheet);

        workbook.close();
        return clients;
    }

    private Client extractClient(Row row) {

        var firstName=convertCellToString(FIRST_NAME_INDEX,row);
        var lastName=convertCellToString(LAST_NAME_INDEX,row);
        var email=convertCellToString(EMAIL_INDEX,row);
        var company=convertCellToString(COMPANY_INDEX,row);
        var lastMailSent=convertCellToLocalDate(DATE_INDEX,row);

        return new Client(firstName,lastName,email,company, lastMailSent);

    }

    private List<Client> readClients(Sheet sheet){
        Iterable<Row> iterable = () -> sheet.iterator();
       return  StreamSupport.stream(iterable.spliterator(), false)
               .map(this::extractClient)
               .collect(Collectors.toList());

    }

    private String convertCellToString(int index,Row row){
        return  dataFormatter.formatCellValue(row.getCell(index));

    }
    private LocalDate convertCellToLocalDate(int index,Row row){
        try {
           return  LocalDate.parse(dataFormatter.formatCellValue(row.getCell(index)));
        }catch (Exception e){
            return LocalDate.now();
        }

    }

}
