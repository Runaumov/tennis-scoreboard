package com.runaumov;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MatchScore {
    private GameScore gameScorePlayer1;
    private GameScore gameScorePlayer2;
    private int setScorePlayer1;
    private int setScorePlayer2;
}
