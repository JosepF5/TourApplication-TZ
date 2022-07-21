package com.sofka.co.tour.usecase;
import com.sofka.co.tour.dto.BikerDTO;
import com.sofka.co.tour.mapper.BikerMapper;
import com.sofka.co.tour.repository.BikerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UpdateBikerUseCase {
    private final BikerRepository bikerRepository;
    private final BikerMapper bikerMapper;

    public Mono<BikerDTO> updateBiker(String id, BikerDTO bikerDTO) {
        return bikerRepository.findById(id).flatMap(biker -> {
            bikerDTO.setId(biker.getId());
            return bikerRepository.save(bikerMapper.toBikerEntity(bikerDTO));
        }).map(bikerMapper::toBikerDTO)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Biker wasn't found ")));
    }
}
