package com.sofka.store.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import com.sofka.store.domain.collections.ProductToBuy;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
public class BuyDTO {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String id;
    private String date=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date());
    @NotNull
    private String idType;
    @NotNull
    private String clientName;
    @NotNull
    private List<ProductToBuy> products;
}
