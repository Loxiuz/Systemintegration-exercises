package com.systemintegrationexercises._2.homework.csv_parsing;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/csv")
public class CsvParseController {

    CsvParseService csvParseService;

    public CsvParseController(CsvParseService csvParseService) {
        this.csvParseService = csvParseService;
    }

    @GetMapping("/parseToJson")
    public ResponseEntity<List<HashMap<String, String>>> parseToJson(@RequestParam String filePath) throws FileNotFoundException {
    List<HashMap<String, String>> companies = csvParseService.parseToJson(filePath);
    if (companies.isEmpty()) {
        return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(companies);
    }
}
