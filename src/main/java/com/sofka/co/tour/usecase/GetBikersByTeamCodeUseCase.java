package com.sofka.co.tour.usecase;

import com.sofka.co.tour.dto.BikerDTO;
import com.sofka.co.tour.mapper.BikerMapper;
import com.sofka.co.tour.repository.BikerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class GetBikersByTeamCodeUseCase {
    private final BikerRepository bikerRepository;
    private final BikerMapper bikerMapper;

    public Flux<BikerDTO> getBikersByTeamCode(String code){
        return bikerRepository
                .findBikersByIdTeam(code)
                .map(bikerMapper::toBikerDTO);
    }
}
