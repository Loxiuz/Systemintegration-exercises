package com.systemintegrationexercises.two.homework.yaml_parsing;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@Service
public class YamlParseService {

    public List<CompanyYaml> parseToJson(String filePath) throws FileNotFoundException {
        String companiesFromFile;
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            companiesFromFile = new String(inputStream.readAllBytes());
            IOUtils.closeQuietly(inputStream);

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found at path: " + filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String[] rows = companiesFromFile.split("\\n- ");

        return Arrays.stream(rows)
                .map(row -> row.split("\n"))
                .map(columns -> {
                    List<String> columnData = Arrays.stream(columns)
                            .map(column -> column.strip().split(":")[1]).toList();

                    return new CompanyYaml(
                    columnData.get(0),
                    columnData.get(1),
                    columnData.get(2),
                    columnData.get(3),
                    columnData.get(4),
                    columnData.get(5),
                    columnData.get(6)
                    );
                }
                ).toList();
    }
}
