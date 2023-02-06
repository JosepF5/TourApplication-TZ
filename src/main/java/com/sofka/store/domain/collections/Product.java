package com.sofka.store.domain.collections;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection="product")
public class Product {
    @Id
    private String id;
    private String name;
    private int inInventory;
    private boolean enabled;
    private int min;
    private int max;
}
