package com.sofka.store.infraestructure.route;

import com.sofka.store.application.usecase.DeleteBuyUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class DeleteBuyRoute {
    @Bean
    @RouterOperation(path = "/delete/buy/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.DELETE, beanClass = DeleteBuyRoute.class, beanMethod = "deleteBuy"
            , operation = @Operation(operationId = "deleteBuy", responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "Boolean")),
            @ApiResponse(responseCode = "400", description = "Invalid Buy ID supplied"),
            @ApiResponse(responseCode = "404", description = "Buy not found")}, parameters = {
            @Parameter(in = ParameterIn.PATH, name = "id")}
    ))
    public RouterFunction<ServerResponse> deleteBuy(DeleteBuyUseCase deleteBuyUseCase){
        return route(DELETE("/delete/buy/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> deleteBuyUseCase.deleteBuy(request.pathVariable("id"))
                        .flatMap((p)-> ServerResponse.status(HttpStatus.ACCEPTED).build())
                        .onErrorResume(e -> ServerResponse.status(HttpStatus.NOT_FOUND).build()));
    }
}
