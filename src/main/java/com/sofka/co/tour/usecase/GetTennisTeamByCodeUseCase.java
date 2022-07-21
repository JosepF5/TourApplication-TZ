package com.sofka.co.tour.usecase;

import com.sofka.co.tour.dto.TennisTeamDTO;
import com.sofka.co.tour.mapper.TennisTeamMapper;
import com.sofka.co.tour.repository.TennisTeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GetTennisTeamByCodeUseCase {
    private final TennisTeamRepository tennisTeamRepository;
    private final TennisTeamMapper tennisTeamMapper;

    public Mono<TennisTeamDTO> getTennisTeamByCode(String code){
        return tennisTeamRepository
                .findTennisTeamByCode(code)
                .map(tennisTeamMapper::toTennisTeamDTO);
    }
}
