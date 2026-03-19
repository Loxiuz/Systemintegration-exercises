package com.systemintegrationexercises._2.homework.yaml_parsing;

import lombok.Getter;

@Getter
public record CompanyYaml(String cvr, String name, String street, String number, String postalCode, String city,
                          String email) {

}
