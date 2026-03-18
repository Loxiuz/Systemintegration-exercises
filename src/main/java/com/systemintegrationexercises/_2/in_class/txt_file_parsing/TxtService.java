package com.systemintegrationexercises._2.in_class.txt_file_parsing;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class TxtService {

    private final String rowChar = "~";
    private final String columnChar = "|";
    private final String lastChars = "#EOF#";

    public List<CompanyTxt> parseToJson(String filePath) throws FileNotFoundException {
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

        companiesFromFile = companiesFromFile.replace(lastChars, "");

        String[] rows = companiesFromFile.split(rowChar);

        return Arrays.stream(rows)
                .map(row -> row.split("\\" + columnChar))
                .map(columns -> new CompanyTxt(
                        columns[0],
                        columns[1],
                        columns[2],
                        columns[3],
                        columns[4],
                        columns[5],
                        columns[6]
                ))
                .toList();
    }
}
