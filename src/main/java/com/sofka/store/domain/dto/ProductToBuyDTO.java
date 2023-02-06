package com.sofka.store.domain.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductToBuyDTO {
    @NotBlank(message = "idProduct is required")
    private String idProduct;
    @NotBlank(message = "quantity is required")
    private String quantity;
}