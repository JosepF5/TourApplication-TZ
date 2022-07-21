package com.sofka.co.tour.usecase;
import com.sofka.co.tour.collections.Biker;
import com.sofka.co.tour.repository.BikerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DeleteBikerUseCase {
    private final BikerRepository bikerRepository;

    private Mono<Biker> findBikerByID(String id){
        return bikerRepository.findById(id)
                .switchIfEmpty(Mono.error(()->new Throwable("The ID hasnt been found")));
    }

    public Mono<Void> deleteBiker(String id){
        return findBikerByID(id)
                .flatMap(p->bikerRepository.deleteById(p.getId()));
    }
}
