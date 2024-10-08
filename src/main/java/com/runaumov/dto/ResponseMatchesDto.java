package com.runaumov.dto;

import com.runaumov.entity.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseMatchesDto {

    private int id;
    private String player1Id;
    private String player2Id;
    private String winner;
}
