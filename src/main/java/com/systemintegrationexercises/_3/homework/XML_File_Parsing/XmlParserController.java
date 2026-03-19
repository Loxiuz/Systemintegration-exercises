package com.systemintegrationexercises._3.homework.XML_File_Parsing;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/xml/parseToJson")
public class XmlParserController {


    @GetMapping("/companies")
    public ResponseEntity<List<Company>> getCompanies(@RequestParam String filePath) throws ParserConfigurationException, IOException, SAXException {
        return ResponseEntity.ok(XmlParserService.getCompanies(filePath));
    }

}
