package com.sofka.store.application.usecase;

import com.sofka.store.domain.dto. BuyDTO;
import com.sofka.store.domain.mapper. BuyMapper;
import com.sofka.store.domain.repository. BuyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class GetBuysUseCase {
    private final BuyRepository buyRepository;
    private final BuyMapper buyMapper;

    public Flux<BuyDTO> getBuys(){
        return buyRepository
                .findAll()
                .map(buyMapper::toBuyDTO);
    }
}
