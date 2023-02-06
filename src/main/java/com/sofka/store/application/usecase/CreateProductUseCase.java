package com.sofka.store.application.usecase;

import com.sofka.store.domain.dto.ProductDTO;
import com.sofka.store.domain.mapper.ProductMapper;
import com.sofka.store.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;


import javax.validation.Valid;

@Service
@Validated
@RequiredArgsConstructor
public class CreateProductUseCase {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Mono<ProductDTO> createProduct(@Valid ProductDTO productDTO){
        return productRepository
                .save(productMapper.toProductEntity(productDTO))
                .map(productMapper::toProductDTO);
    }
}
