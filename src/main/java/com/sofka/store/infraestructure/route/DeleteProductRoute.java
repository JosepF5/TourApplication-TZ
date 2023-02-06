package com.sofka.store.infraestructure.route;

import com.sofka.store.application.usecase.DeleteProductUseCase;
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
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class DeleteProductRoute {
    @Bean
    @RouterOperation(path = "/delete/product/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.DELETE, beanClass = DeleteProductRoute.class, beanMethod = "deleteProduct"
            , operation = @Operation(operationId = "deleteProduct", responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "Boolean")),
            @ApiResponse(responseCode = "400", description = "Invalid Product ID supplied"),
            @ApiResponse(responseCode = "404", description = "Product not found")}, parameters = {
            @Parameter(in = ParameterIn.PATH, name = "id")}
    ))
    public RouterFunction<ServerResponse> deleteProduct(DeleteProductUseCase deleteProductUseCase){
        return route(DELETE("/delete/product/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> deleteProductUseCase.deleteProduct(request.pathVariable("id"))
                        .flatMap((p)-> ServerResponse.status(HttpStatus.ACCEPTED).build())
                        .onErrorResume(e -> ServerResponse.status(HttpStatus.NOT_FOUND).build()));
    }
}
