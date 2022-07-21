package com.sofka.co.tour.collections;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@Document(collection = "tenisteam")
public class TennisTeam {
    @Id
    private String id;
    private String name;
    @Indexed(unique = true)
    private String code;
    private String country;
}
