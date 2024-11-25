package com.runaumov.model;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MatchScore {
    private String pointScorePlayer1;
    private String pointScorePlayer2;
    private int gameScorePlayer1;
    private int gameScorePlayer2;
    private int setScorePlayer1;
    private int setScorePlayer2;
    private MatchType matchType;

    public void setDefaultPointScore() {
        this.pointScorePlayer1 = String.valueOf(PointScore.LOVE);
        this.pointScorePlayer2 = String.valueOf(PointScore.LOVE);
    }

    public void setDefaultGameScore() {
        this.gameScorePlayer1 = 0;
        this.gameScorePlayer2 = 0;
    }

}