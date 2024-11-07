package com.runaumov.dto.request;

import com.runaumov.entity.Match;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestMatchScoreDto {

    private Match match;
    private int playerId;
}
