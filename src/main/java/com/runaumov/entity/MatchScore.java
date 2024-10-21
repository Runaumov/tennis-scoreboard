package com.runaumov.entity;

import com.runaumov.PointScore;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MatchScore {
    private PointScore pointScorePlayer1;
    private PointScore pointScorePlayer2;
    private int gameScorePlayer1;
    private int gameScorePlayer2;
}
