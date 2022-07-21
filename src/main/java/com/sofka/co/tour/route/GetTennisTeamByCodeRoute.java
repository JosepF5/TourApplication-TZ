package com.sofka.co.tour.route;

import com.sofka.co.tour.collections.TennisTeam;
import com.sofka.co.tour.dto.TennisTeamDTO;
import com.sofka.co.tour.usecase.GetTennisTeamByCodeUseCase;
import com.sofka.co.tour.usecase.GetTennisTeamsByCountryUseCase;
import com.sofka.co.tour.usecase.GetTennisTeamsUseCase;
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
public class GetTennisTeamByCodeRoute {
    @Bean
    @RouterOperation(path = "/get/tennisTeam/{teamCode}", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetTennisTeamByCodeUseCase.class, method = RequestMethod.GET, beanMethod = "getTennisTeamByTeamCode",
            operation = @Operation(operationId = "getTennisTeamByTeamCode", responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation",
                            content = @Content(schema = @Schema(implementation = TennisTeam.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid TeamCode details supplied")},
                    parameters = {@Parameter(in = ParameterIn.PATH, name = "teamCode")}
            ))
    public RouterFunction<ServerResponse> getTennisTeamByTeamCode(GetTennisTeamByCodeUseCase getTennisTeamByCodeUseCase) {
        return route(GET("/get/tennisTeam/{teamCode}"), request -> ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getTennisTeamByCodeUseCase.getTennisTeamByCode(request.pathVariable("teamCode")), TennisTeamDTO.class))
        );
    }
}
