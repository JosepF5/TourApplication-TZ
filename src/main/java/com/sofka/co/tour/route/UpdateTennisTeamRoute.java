package com.sofka.co.tour.route;

import com.sofka.co.tour.collections.Biker;
import com.sofka.co.tour.collections.TennisTeam;
import com.sofka.co.tour.dto.TennisTeamDTO;
import com.sofka.co.tour.usecase.UpdateBikerUseCase;
import com.sofka.co.tour.usecase.UpdateTennisTeamUseCase;
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
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import org.springframework.context.annotation.Configuration;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UpdateTennisTeamRoute {
    @Bean
    @RouterOperation(path = "/update/tennisTeam/{id}"
            , produces = {
            MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.PUT, beanClass = UpdateTennisTeamUseCase.class, beanMethod = "updateTennisTeam",
            operation = @Operation(operationId = "updateTennisTeam", responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = TennisTeam.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid TennisTeam ID supplied"),
                    @ApiResponse(responseCode = "404", description = "TennisTeam not found")}, parameters = {
                    @Parameter(in = ParameterIn.PATH, name = "id")}
                    , requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = TennisTeam.class))))
    )
    RouterFunction<ServerResponse> updateTennisTeam(UpdateTennisTeamUseCase updateTennisTeamUseCase){
        return route(PUT("/update/tennisTeam/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(TennisTeamDTO.class)
                        .flatMap(tennisTeamDTO -> updateTennisTeamUseCase.updateTennisTeam(request.pathVariable("id"),tennisTeamDTO))
                        .flatMap(result -> result.getId()!=null
                                ? ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(result)
                                : ServerResponse.status(HttpStatus.NOT_FOUND)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(result)));
    }
}
