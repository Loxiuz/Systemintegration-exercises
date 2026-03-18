package com.systemintegrationexercises._2.homework.yaml_parsing;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/yml")
public class YamlParseController {

    YamlParseService yamlParseService;

    public YamlParseController(YamlParseService yamlParseService) {
        this.yamlParseService = yamlParseService;
    }

    @GetMapping("/parseToJson")
    public ResponseEntity<List<CompanyYaml>> parseToJson(@RequestParam String filePath) throws FileNotFoundException {
        List<CompanyYaml> companies = yamlParseService.parseToJson(filePath);
        if (companies.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(companies);
    }
}
