package com.runaumov.dto;

import com.runaumov.entity.Match;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseMatchScoreDto {

    private Match match;
    private UUID matchId;
}
