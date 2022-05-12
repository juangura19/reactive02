package com.juangutierrez.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "persons")
public class Person {
    @Id
    private String id;

    private String document;

    private String names;

    private String lastnames;

    private int status;
}
