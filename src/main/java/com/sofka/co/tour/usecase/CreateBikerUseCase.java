package com.sofka.co.tour.usecase;
import com.sofka.co.tour.collections.Biker;
import com.sofka.co.tour.dto.BikerDTO;
import com.sofka.co.tour.mapper.BikerMapper;
import com.sofka.co.tour.repository.BikerRepository;
import com.sofka.co.tour.repository.TennisTeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Service
@Validated
@RequiredArgsConstructor
public class CreateBikerUseCase {
    private final BikerRepository bikerRepository;
    private final TennisTeamRepository tennisTeamRepository;
    private final BikerMapper bikerMapper;

    public Mono<BikerDTO> createBiker(@Valid BikerDTO bikerDTO){
        return bikerRepository.countByIdTeam(bikerDTO.getIdTeam()).flatMap(cantidad -> {
            if (cantidad < 8) {
                return tennisTeamRepository.findTennisTeamByCode(bikerDTO.getIdTeam()).flatMap(team->{
                            bikerDTO.setIdTeam(team.getCode());
                            return  bikerRepository.save(bikerMapper.toBikerEntity(bikerDTO));
                        }).map(bikerMapper::toBikerDTO)
                        .switchIfEmpty(Mono.error(new IllegalArgumentException("Team "+bikerDTO.getIdTeam()+" not found.")));
            }
            return Mono.error(new Exception("Team full of members... for now"));
        });
    }
}
