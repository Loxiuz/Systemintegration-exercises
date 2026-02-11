package com.systemintegrationexercises.two.in_class.txt_file_parsing;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/txt")
public class TxtController {

    TxtService txtService;

    public TxtController(TxtService txtService) {
            this.txtService = txtService;
    }

    @GetMapping("/parseToJson")
    public ResponseEntity<List<CompanyTxt>> parseToJson(@RequestParam String filePath) throws FileNotFoundException {
        List<CompanyTxt> companies = txtService.parseToJson(filePath);
        if(companies.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(companies);
    }
}
