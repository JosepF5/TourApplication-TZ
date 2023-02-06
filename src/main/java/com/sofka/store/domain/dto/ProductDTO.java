package com.sofka.store.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProductDTO {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String id;
    @NotNull
    private String name;
    @NotNull
    private Long inInventory;
    @NotNull
    private Boolean                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              enabled;
    @NotNull
    private Long min;
    @NotNull
    private Long max;
}
