package com.sofka.co.tour.mapper;

import com.sofka.co.tour.collections.Biker;
import com.sofka.co.tour.dto.BikerDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.config.EnableWebFlux;

@Component
@EnableWebFlux
public class BikerMapper {
    private final ModelMapper mapper;

    public BikerMapper(ModelMapper mapper){
        this.mapper=mapper;
    }

    public BikerDTO toBikerDTO(Biker biker){
        return mapper.map(biker,BikerDTO.class);
    }

    public Biker toBikerEntity(BikerDTO bikerDTO){
        return mapper.map(bikerDTO,Biker.class);
    }
}
