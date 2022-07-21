package com.sofka.co.tour.usecase;
import com.sofka.co.tour.dto.TennisTeamDTO;
import com.sofka.co.tour.mapper.TennisTeamMapper;
import com.sofka.co.tour.repository.TennisTeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UpdateTennisTeamUseCase {
    private final TennisTeamRepository tennisTeamRepository;
    private final TennisTeamMapper tennisTeamMapper;

    public Mono<TennisTeamDTO> updateTennisTeam(String id, TennisTeamDTO tennisTeamDTO) {
        return tennisTeamRepository.findById(id).flatMap(tennisTeam -> {
            tennisTeamDTO.setId(tennisTeam.getId());
            return tennisTeamRepository.save(tennisTeamMapper.toTennisTeamEntity(tennisTeamDTO));
        }).map(tennisTeamMapper::toTennisTeamDTO)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Team wasn't found ")));
    }
}
