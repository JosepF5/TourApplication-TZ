package com.sofka.co.tour.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class BikerDTO {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String id;

    @NotBlank
    @Length(max = 50, message = "Full name must be less than 50 words")
    @Schema(maxLength = 50)
    private String fullName;
    @NotBlank
    @Length(max = 3, message = "Biker code must be less than 4 digits")
    @Schema(maxLength = 3)
    private String code;
    @NotBlank
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String idTeam;

    @NotBlank
    @Length(max = 25, message = "Country name must be less than 25 words")
    @Schema(maxLength = 25)
    private String country;

}
