package com.sofka.co.tour.usecase;

import com.sofka.co.tour.collections.TennisTeam;
import com.sofka.co.tour.repository.TennisTeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DeleteTennisTeamUseCase {
    private final TennisTeamRepository tennisTeamRepository;

    private Mono<TennisTeam> findTennisTeamByID(String id){
        return tennisTeamRepository.findById(id)
                .switchIfEmpty(Mono.error(()->new Throwable("The ID hasnt been found")));
    }

    public Mono<Void> deleteTennisTeam(String id){
        return findTennisTeamByID(id)
                .flatMap(p->tennisTeamRepository.deleteById(p.getId()));
    }
}
