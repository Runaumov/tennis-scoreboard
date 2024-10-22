package com.runaumov.entity;

import com.runaumov.PointScore;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

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
    private List<int[]> previousSets;

    public void addPreviousSet(int gameScorePlayer1, int gameScorePlayer2) {
        previousSets.add(new int[]{gameScorePlayer1, gameScorePlayer2});
    }

    public void setDefaultTieBreakScore() {

    }

    public void setDefaultGameScore() {
        this.gameScorePlayer1 = 0;
        this.gameScorePlayer2 = 0;
    }
}
