package com.sofka.co.tour.usecase;
import com.sofka.co.tour.dto.BikerDTO;
import com.sofka.co.tour.mapper.BikerMapper;
import com.sofka.co.tour.repository.BikerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GetBikersUseCase {
    private final BikerRepository bikerRepository;
    private final BikerMapper bikerMapper;

    public Flux<BikerDTO> getBikers(){
        return bikerRepository
                .findAll()
                .map(bikerMapper::toBikerDTO);
    }
}
