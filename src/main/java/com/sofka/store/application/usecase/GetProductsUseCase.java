package com.sofka.store.application.usecase;

import com.sofka.store.domain.dto.ProductDTO;
import com.sofka.store.domain.mapper.ProductMapper;
import com.sofka.store.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class GetProductsUseCase {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Flux<ProductDTO> getProducts(){
        return productRepository
                .findAll()
                .map(productMapper::toProductDTO);
    }
}
