package com.sofka.co.tour.usecase;
import com.sofka.co.tour.dto.TennisTeamDTO;
import com.sofka.co.tour.mapper.TennisTeamMapper;
import com.sofka.co.tour.repository.TennisTeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Service
@Validated
@RequiredArgsConstructor
public class CreateTennisTeamUseCase {
    private final TennisTeamRepository tennisTeamRepository;
    private final TennisTeamMapper tennisTeamMapper;

    public Mono<TennisTeamDTO> createTennisTeam(@Valid TennisTeamDTO tennisTeamDTO){
        return tennisTeamRepository
                .save(tennisTeamMapper
                        .toTennisTeamEntity(tennisTeamDTO)).map(tennisTeamMapper::toTennisTeamDTO);
    }
}
