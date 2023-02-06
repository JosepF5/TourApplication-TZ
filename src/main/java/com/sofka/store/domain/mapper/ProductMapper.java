package com.sofka.store.domain.mapper;

import com.sofka.store.domain.collections.Product;
import com.sofka.store.domain.dto.ProductDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.config.EnableWebFlux;

@Component
@EnableWebFlux
public class ProductMapper {
    private final ModelMapper mapper;

    public ProductMapper(ModelMapper mapper){
        this.mapper=mapper;
    }

    public ProductDTO toProductDTO(Product product){
        return mapper.map(product,ProductDTO.class);
    }

    public Product toProductEntity(ProductDTO productDTO){
        return mapper.map(productDTO,Product.class);
    }
}
