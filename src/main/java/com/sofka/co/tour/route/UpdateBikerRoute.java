package com.sofka.co.tour.route;

import com.sofka.co.tour.collections.Biker;
import com.sofka.co.tour.dto.BikerDTO;
import com.sofka.co.tour.usecase.UpdateBikerUseCase;
import com.mongodb.internal.connection.Server;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
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
public class UpdateBikerRoute {
    @Bean
    @RouterOperation(path = "/update/biker/{id}"
            , produces = {
            MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.PUT, beanClass = UpdateBikerUseCase.class, beanMethod = "updateBiker",
            operation = @Operation(operationId = "updateBiker", responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Biker.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid Biker ID supplied"),
                    @ApiResponse(responseCode = "404", description = "Biker not found")}, parameters = {
                    @Parameter(in = ParameterIn.PATH, name = "id")}
                    , requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = Biker.class))))
    )
    RouterFunction<ServerResponse> updateBiker(UpdateBikerUseCase updateBikerUseCase){
        return route(PUT("/update/biker/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(BikerDTO.class)
                        .flatMap(bikerDTO -> updateBikerUseCase.updateBiker(request.pathVariable("id"),bikerDTO))
                        .flatMap(result -> result.getId()!=null
                                ? ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(result)
                                : ServerResponse.status(HttpStatus.NOT_FOUND)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(result)));
    }
}
