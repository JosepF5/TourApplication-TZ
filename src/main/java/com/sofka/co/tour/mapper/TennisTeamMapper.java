package com.sofka.co.tour.mapper;

import com.sofka.co.tour.collections.Biker;
import com.sofka.co.tour.collections.TennisTeam;
import com.sofka.co.tour.dto.BikerDTO;
import com.sofka.co.tour.dto.TennisTeamDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.config.EnableWebFlux;

@Component
@EnableWebFlux
public class TennisTeamMapper {
    private final ModelMapper mapper;

    public TennisTeamMapper(ModelMapper mapper){
        this.mapper=mapper;
    }

    public TennisTeamDTO toTennisTeamDTO(TennisTeam tennisTeam){
        return mapper.map(tennisTeam,TennisTeamDTO.class);
    }

    public TennisTeam toTennisTeamEntity(TennisTeamDTO tennisTeamDTO){
        return mapper.map(tennisTeamDTO,TennisTeam.class);
    }
}
