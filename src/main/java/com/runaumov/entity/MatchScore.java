package com.runaumov.entity;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MatchScore {
    private GameScore scorePlayer1;
    private GameScore scorePlayer2;
    private int setPlayer1;
    private int setPlayer2;
}
