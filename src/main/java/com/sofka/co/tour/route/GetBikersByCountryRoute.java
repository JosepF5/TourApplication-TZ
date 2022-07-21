package com.sofka.co.tour.route;

import com.sofka.co.tour.collections.Biker;
import com.sofka.co.tour.collections.TennisTeam;
import com.sofka.co.tour.dto.BikerDTO;
import com.sofka.co.tour.usecase.GetBikersByCountryUseCase;
import com.sofka.co.tour.usecase.GetBikersByTeamCodeUseCase;
import com.sofka.co.tour.usecase.GetBikersUseCase;
import com.sofka.co.tour.usecase.GetTennisTeamByCodeUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GetBikersByCountryRoute {
    @Bean
    @RouterOperation(path = "/get/bikers/country/{country}", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetBikersByCountryUseCase.class, method = RequestMethod.GET, beanMethod = "getBikersByCountry",
            operation = @Operation(operationId = "getBikersByCountry", responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation",
                            content = @Content(schema = @Schema(implementation = Biker.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid country details supplied")},
                    parameters = {@Parameter(in = ParameterIn.PATH, name = "country")}
            ))
    public RouterFunction<ServerResponse> getBikersByCountry(GetBikersByCountryUseCase getBikersByCountryUseCase) {
        return route(GET("/get/bikers/country/{country}"), request -> ServerResponse.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(getBikersByCountryUseCase.getBikersByCountry(request.pathVariable("country")), BikerDTO.class))
        );
    }
}
