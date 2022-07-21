package com.sofka.co.tour.repository;

import com.sofka.co.tour.collections.Biker;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface BikerRepository extends ReactiveMongoRepository<Biker,String> {
    Flux<Biker> findBikersByIdTeam(String code);
    Mono<Biker> findBikerByCode(String code);
    Flux<Biker> findBikersByCountry(String country);
    Mono<Long> countByIdTeam(String idTeam);
}
