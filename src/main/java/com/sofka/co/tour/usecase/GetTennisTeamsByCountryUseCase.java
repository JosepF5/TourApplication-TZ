package com.sofka.co.tour.usecase;

import com.sofka.co.tour.dto.TennisTeamDTO;
import com.sofka.co.tour.mapper.TennisTeamMapper;
import com.sofka.co.tour.repository.TennisTeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class GetTennisTeamsByCountryUseCase {
    private final TennisTeamRepository tennisTeamRepository;
    private final TennisTeamMapper tennisTeamMapper;

    public Flux<TennisTeamDTO> getTennisTeamByCountry(String country){
        return tennisTeamRepository
                .findTennisTeamsByCountry(country)
                .map(tennisTeamMapper::toTennisTeamDTO);
    }
}
