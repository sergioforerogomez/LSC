package com.lsc.serverTest.entities;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;

<<<<<<< HEAD
@Document(collection = "test")
=======
@Document
>>>>>>> 576d31e... Microservice Test added
@Data
@Accessors(chain = true)
public class TestEntity {
    @Id
    private String id;

    private String test;
}