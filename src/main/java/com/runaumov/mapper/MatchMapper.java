package com.runaumov.mapper;

import com.runaumov.dto.response.ResponseMatchesDto;
import com.runaumov.entity.Match;
import org.modelmapper.ModelMapper;

public class MatchMapper {
    private final ModelMapper modelMapper;

    public MatchMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        configureMapping();
    }

    private void configureMapping() {
        modelMapper.typeMap(Match.class, ResponseMatchesDto.class).addMappings(mapper -> {
            mapper.map(src -> src.getPlayer1Id().getName(), ResponseMatchesDto::setPlayer1Id);
            mapper.map(src -> src.getPlayer2Id().getName(), ResponseMatchesDto::setPlayer2Id);
            mapper.map(src -> src.getWinner().getName(), ResponseMatchesDto::setWinner);
        });
    }

    public ResponseMatchesDto toResponseDto(Match match) {
        return modelMapper.map(match, ResponseMatchesDto.class);
    }
}
