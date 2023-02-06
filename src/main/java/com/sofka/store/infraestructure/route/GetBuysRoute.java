package com.sofka.store.infraestructure.route;

import com.sofka.store.application.usecase.GetBuysUseCase;
import com.sofka.store.domain.collections.Buy;
import com.sofka.store.domain.dto.BuyDTO;
import io.swagger.v3.oas.annotations.Operation;
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
public class GetBuysRoute {
    @Bean
    @RouterOperation(path = "/get/buys", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetBuysUseCase.class, method = RequestMethod.GET, beanMethod = "getBuys",
            operation = @Operation(operationId = "getBuys", responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation",
                            content = @Content(schema = @Schema(implementation = Buy.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid route supplied")}
            ))
    public RouterFunction<ServerResponse> getBuys(GetBuysUseCase getBuysUseCase){
        return route(GET("/get/buys"), request -> ServerResponse.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromProducer(getBuysUseCase.getBuys(), BuyDTO.class)));
    }
}
