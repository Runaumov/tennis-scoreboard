package com.runaumov.dto.response;

import com.runaumov.entity.Match;
import com.runaumov.entity.Player;
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
    private Player winner;

    public ResponseMatchScoreDto(Match match, UUID matchId) {
        this.match = match;
        this.matchId = matchId;
    }
}
