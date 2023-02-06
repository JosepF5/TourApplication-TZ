package com.sofka.store.application.usecase;

import com.sofka.store.domain.dto.BuyDTO;
import com.sofka.store.domain.mapper.BuyMapper;
import com.sofka.store.domain.repository.BuyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Service
@Validated
@RequiredArgsConstructor
public class CreateBuyUseCase {
    private final BuyRepository buyRepository;
    private final BuyMapper buyMapper;

    public Mono<BuyDTO> createBuy(@Valid BuyDTO buyDTO){
        return buyRepository
                .save(buyMapper.toBuyEntity(buyDTO))
                .map(buyMapper::toBuyDTO);
    }
}
