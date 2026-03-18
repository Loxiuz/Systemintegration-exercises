package com.systemintegrationexercises._2.homework.csv_parsing;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@Service
public class CsvParseService {

    private final String rowChar = "\n";
    private final String columnChar = ",";


    public List<HashMap<String, String>> parseToJson(String filePath) throws FileNotFoundException {
        String companiesFromFile;
        try{
            FileInputStream inputStream = new FileInputStream(filePath);
            companiesFromFile = new String(inputStream.readAllBytes());
            IOUtils.closeQuietly(inputStream);

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found at path: " + filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String[] rows = companiesFromFile.split(rowChar);
        String[] columnNames = rows[0].split(columnChar);

        return Arrays.stream(rows)
                .skip(1)
                .map(row -> {
                    HashMap<String, String> mappedRow = new HashMap<>();
                    String[] columns = row.split(columnChar);
                    for(int i = 0; i < columns.length; i++){
                        mappedRow.put(columnNames[i], columns[i]);
                    }
                    return mappedRow;
                }).toList();
    }
}
