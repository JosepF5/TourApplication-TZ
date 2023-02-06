package com.sofka.store.application.usecase;

import com.sofka.store.domain.collections.Buy;
import com.sofka.store.domain.repository.BuyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DeleteBuyUseCase {
    private final BuyRepository buyRepository;

    private Mono<Buy> findBuyByID(String id){
        return buyRepository.findById(id)
                .switchIfEmpty(Mono.error(()->new Throwable("The ID hasnt been found")));
    }

    public Mono<Void> deleteBuy(String id){
        return findBuyByID(id)
                .flatMap(p->buyRepository.deleteById(p.getId()));
    }
}

