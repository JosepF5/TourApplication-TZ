package com.sofka.store.domain.collections;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection="buys")
public class Buy {
    @Id
    private String id;
    private String date;
    private String idType;
    private String clientName;
    private List<ProductToBuy> products;
}
