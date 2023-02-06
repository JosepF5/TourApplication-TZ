package com.sofka.store.infraestructure.route;

import com.sofka.store.application.usecase.GetProductsUseCase;
import com.sofka.store.domain.collections.Product;
import com.sofka.store.domain.dto.ProductDTO;
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
public class GetProductsRoute {
    @Bean
    @RouterOperation(path = "/get/products", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetProductsUseCase.class, method = RequestMethod.GET, beanMethod = "getProducts",
            operation = @Operation(operationId = "getProducts", responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation",
                            content = @Content(schema = @Schema(implementation = Product.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid route supplied")}
            ))
    public RouterFunction<ServerResponse> getProducts(GetProductsUseCase getProductsUseCase){
        return route(GET("/get/products"), request -> ServerResponse.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromProducer(getProductsUseCase.getProducts(), ProductDTO.class)));
    }
}
