package com.sofka.co.tour.repository;

import com.sofka.co.tour.collections.TennisTeam;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TennisTeamRepository extends ReactiveMongoRepository<TennisTeam,String> {
    Flux<TennisTeam> findTennisTeamsByCountry(String country);
    Mono<TennisTeam> findTennisTeamByCode(String code);
}
