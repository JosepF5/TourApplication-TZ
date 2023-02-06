package com.sofka.store.domain.mapper;

import com.sofka.store.domain.collections.Buy;
import com.sofka.store.domain.dto.BuyDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.config.EnableWebFlux;

@Component
@EnableWebFlux
public class BuyMapper {
    private final ModelMapper mapper;

    public BuyMapper(ModelMapper mapper){
        this.mapper=mapper;
    }

    public BuyDTO toBuyDTO(Buy buy){
        return mapper.map(buy,BuyDTO.class);
    }

    public Buy toBuyEntity(BuyDTO buyDTO){
        return mapper.map(buyDTO,Buy.class);
    }
}
