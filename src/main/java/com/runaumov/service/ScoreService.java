package com.runaumov.service;

import com.runaumov.MatchType;
import com.runaumov.PointScore;
import com.runaumov.entity.Match;
import com.runaumov.entity.Player;

public class ScoreService {

    // TODO : исключить дублирование
    public Match updatePointScore(Match match, int winnerId) {
        Player player1 = match.getPlayer1Id();
        Player player2 = match.getPlayer2Id();
        String pointScorePlayer1 = match.getMatchScore().getPointScorePlayer1();
        String pointScorePlayer2 = match.getMatchScore().getPointScorePlayer2();

        // TODO : отрефакторить
        if (match.getMatchScore().getMatchType().equals(MatchType.TIEBREAK)) {
            if (player1.getId() == winnerId) {
                int pointScorePlayer1Int = Integer.parseInt(pointScorePlayer1) + 1;
                pointScorePlayer1 = String.valueOf(pointScorePlayer1Int);
                match.getMatchScore().setPointScorePlayer1(pointScorePlayer1);
            } else if (player2.getId() == winnerId) {
                int pointScorePlayer2Int = Integer.parseInt(pointScorePlayer2) + 1;
                pointScorePlayer2 = String.valueOf(pointScorePlayer2Int);
                match.getMatchScore().setPointScorePlayer2(pointScorePlayer2);
            }
            return match;

        } else {
            if (player1.getId() == winnerId) {
                String updatedPointScore = PointScore.getNextGameScore(PointScore.getPointScoreFromString(pointScorePlayer1));
                match.getMatchScore().setPointScorePlayer1(updatedPointScore);
                return match;
            } else if (player2.getId() == winnerId) {
                String updatedPointScore = PointScore.getNextGameScore(PointScore.getPointScoreFromString(pointScorePlayer2));
                match.getMatchScore().setPointScorePlayer2(updatedPointScore);
                return match;
            } else {
                // TODO : доделать
                throw new IllegalArgumentException("Ошибка");
            }
        }
    }

    // TODO : исключить дублирование
    public Match updateGameScore(Match match, int winnerId) {
        Player player1 = match.getPlayer1Id();
        Player player2 = match.getPlayer2Id();
        if (player1.getId() == winnerId) {
            match.getMatchScore().setGameScorePlayer1(match.getMatchScore().getGameScorePlayer1() + 1);
            match.getMatchScore().setPointScorePlayer1(String.valueOf(PointScore.LOVE));
            match.getMatchScore().setPointScorePlayer2(String.valueOf(PointScore.LOVE));
            return match;
        } else if (player2.getId() == winnerId) {
            match.getMatchScore().setGameScorePlayer2(match.getMatchScore().getGameScorePlayer2() + 1);
            match.getMatchScore().setPointScorePlayer1(String.valueOf(PointScore.LOVE));
            match.getMatchScore().setPointScorePlayer2(String.valueOf(PointScore.LOVE));
            return match;
        } else {
            // TODO : доделать
            throw new IllegalArgumentException("Ошибка");
        }
    }

    // TODO : исключить дублирование
    public Match updateSetScore(Match match, int winnerId) {
        Player player1 = match.getPlayer1Id();
        Player player2 = match.getPlayer2Id();
        if (player1.getId() == winnerId) {
            match.getMatchScore().setSetScorePlayer1(match.getMatchScore().getSetScorePlayer1() + 1);
            match.getMatchScore().setPointScorePlayer1(String.valueOf(PointScore.LOVE));
            match.getMatchScore().setPointScorePlayer2(String.valueOf(PointScore.LOVE));
            return match;
        } else if (player2.getId() == winnerId) {
            match.getMatchScore().setSetScorePlayer1(match.getMatchScore().getSetScorePlayer2() + 1);
            match.getMatchScore().setPointScorePlayer1(String.valueOf(PointScore.LOVE));
            match.getMatchScore().setPointScorePlayer2(String.valueOf(PointScore.LOVE));
            return match;
        } else {
            // TODO : доделать
            throw new IllegalArgumentException("Ошибка");
        }
    }
}
