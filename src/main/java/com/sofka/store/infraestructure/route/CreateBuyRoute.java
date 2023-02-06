package com.sofka.store.infraestructure.route;

import com.sofka.store.application.usecase.CreateBuyUseCase;
import com.sofka.store.domain.collections.Buy;
import com.sofka.store.domain.dto.BuyDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CreateBuyRoute {
    @Bean
    @RouterOperation(path = "/create/buy", produces = {
            MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST, beanClass = CreateBuyUseCase.class, beanMethod = "createBuy",
            operation = @Operation(operationId = "createBuy", responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Buy.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid Buy details supplied")}
                    , requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = Buy.class)))
            ))
    public RouterFunction<ServerResponse> createBuy(CreateBuyUseCase createBuyUseCase){
        Function<BuyDTO, Mono<ServerResponse>> executor = buyDTO -> createBuyUseCase.createBuy(buyDTO)
                .flatMap(result-> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result))
                .onErrorResume(e -> ServerResponse.badRequest()
                        .contentType(MediaType.TEXT_PLAIN)
                        .bodyValue(String.format(
                                "Buy %s already exists.", buyDTO.getId()
                        )));
        return route(POST("/create/buy").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(BuyDTO.class).flatMap(executor)
                        .onErrorResume(throwable -> ServerResponse.badRequest()
                                .contentType(MediaType.TEXT_PLAIN)
                                .bodyValue("Error: " + throwable.getMessage()))
        );
    }
}
