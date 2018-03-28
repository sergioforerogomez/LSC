package com.lsc.server.entities;

        import lombok.Data;
        import lombok.experimental.Accessors;
        import org.springframework.data.annotation.Id;
        import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "test")
@Data
@Accessors(chain = true)
public class TestDTO {
    @Id
    private String id;

    private String test;
}
