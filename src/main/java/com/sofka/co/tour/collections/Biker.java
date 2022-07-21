package com.sofka.co.tour.collections;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@Document(collection = "biker")
public class Biker {
    @Id
    private String id;
    private String fullName;
    @Indexed(unique = true)
    private String code;
    private String idTeam;
    private String country;

}
