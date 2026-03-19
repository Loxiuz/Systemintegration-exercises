package com.systemintegrationexercises._3.homework.XML_File_Parsing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class XmlParserService {

    private XmlParserService() {
    }

    private static final Logger log = LoggerFactory.getLogger(XmlParserService.class);

    public static List<Company> getCompanies(String path) throws ParserConfigurationException, IOException, SAXException {
        List<Company> companies = getCompaniesFromXml(path);
        if(companies.isEmpty()) {
            log.warn("companies list is empty");
            return List.of();
        }
        return companies;
    }

    public static List<Company> getCompaniesFromXml(String path) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(new File(path));
        doc.getDocumentElement().normalize();

        List<Company> companies = new ArrayList<>();

        NodeList companiesNodes = doc.getElementsByTagName("company");
        NodeList companyFields = doc.getElementsByTagName("company").item(0).getChildNodes();

        for(int i = 0; i < companiesNodes.getLength(); i++) {
            Company company = new Company();
            for(int j = 0; j < companyFields.getLength(); j++) {
                switch (companyFields.item(j).getNodeName()) {
                    case "cvr" -> company.setCvr(companyFields.item(j).getTextContent());
                    case "name" -> company.setName(companyFields.item(j).getTextContent());
                    case "street" -> company.setStreet(companyFields.item(j).getTextContent());
                    case "number" -> company.setNumber(companyFields.item(j).getTextContent());
                    case "postal_code" -> company.setPostal_code(companyFields.item(j).getTextContent());
                    case "city" -> company.setCity(companyFields.item(j).getTextContent());
                    case "email" -> company.setEmail(companyFields.item(j).getTextContent());
                    default -> log.warn("Unknown field: " + companyFields.item(j).getNodeName());
                }
            }
            companies.add(company);
        }
        return companies;
    }
}
