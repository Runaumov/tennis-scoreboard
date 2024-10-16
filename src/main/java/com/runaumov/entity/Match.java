package com.runaumov.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "matches")
@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "player1_id", referencedColumnName = "id")
    private Player player1Id;

    @ManyToOne
    @JoinColumn(name = "player2_id", referencedColumnName = "id")
    private Player player2Id;

    @ManyToOne
    @JoinColumn(name = "winner", referencedColumnName = "id")
    private Player winner;

    @Transient
    private MatchScore matchScore;

    // TODO: возможно стоит использовать @RequiredArgsConstructor
    public Match(Player player1Id, Player player2Id, MatchScore matchScore) {
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        this.matchScore = matchScore;
    }
}
