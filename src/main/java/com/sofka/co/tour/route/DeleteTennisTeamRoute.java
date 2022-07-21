package com.sofka.co.tour.route;

import com.sofka.co.tour.usecase.DeleteBikerUseCase;
import com.sofka.co.tour.usecase.DeleteTennisTeamUseCase;
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

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class DeleteTennisTeamRoute {
    @Bean
    @RouterOperation(path = "/delete/tennisTeam/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.DELETE, beanClass = DeleteTennisTeamUseCase.class, beanMethod = "deleteTennisTeam"
            , operation = @Operation(operationId = "deleteTennisTeam", responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "Boolean")),
            @ApiResponse(responseCode = "400", description = "Invalid Biker ID supplied"),
            @ApiResponse(responseCode = "404", description = "TennisTeam not found")}, parameters = {
            @Parameter(in = ParameterIn.PATH, name = "id")}
    ))
    public RouterFunction<ServerResponse> deleteTennisTeam(DeleteTennisTeamUseCase deleteTennisTeamUseCase){
        return route(DELETE("/delete/tennisTeam/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> deleteTennisTeamUseCase.deleteTennisTeam(request.pathVariable("id"))
                        .flatMap((p)-> ServerResponse.status(HttpStatus.ACCEPTED).build())
                        .onErrorResume(e -> ServerResponse.status(HttpStatus.NOT_FOUND).build()));
    }
}
