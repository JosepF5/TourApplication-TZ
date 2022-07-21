package com.sofka.co.tour.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;

@Data
public class TennisTeamDTO {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String id;

    @NotBlank
    @Length(max = 25, message = "Name must be less than 30 characters")
    @Schema(maxLength = 25)
    private String name;

    @NotBlank
    @Length(max = 3, message = "TennisTeam code must be less than 4 digits")
    @Schema(maxLength = 3)
    private String code;

    @NotBlank
    @Length(max = 25, message = "Country name must be less than 25 words")
    @Schema(maxLength = 25)
    private String country;
}
