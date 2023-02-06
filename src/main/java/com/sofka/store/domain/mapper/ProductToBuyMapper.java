package com.sofka.store.domain.mapper;

import com.sofka.store.domain.collections.ProductToBuy;
import com.sofka.store.domain.dto.ProductToBuyDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.config.EnableWebFlux;

@Component
@EnableWebFlux
public class ProductToBuyMapper {
    private final ModelMapper mapper;

    public ProductToBuyMapper(ModelMapper mapper){
        this.mapper=mapper;
    }

    public ProductToBuyDTO toProductToBuyDTO(ProductToBuy productToBuy){
        return mapper.map(productToBuy,ProductToBuyDTO.class);
    }

    public ProductToBuy toProductToBuyEntity(ProductToBuyDTO productToBuyDTO){
        return mapper.map(productToBuyDTO,ProductToBuy.class);
    }
}
