package com.runaumov.service;

import com.runaumov.MatchType;
import com.runaumov.PointScore;
import com.runaumov.entity.MatchScore;
import com.runaumov.dto.RequestMatchScoreDto;
import com.runaumov.entity.Match;
import com.runaumov.entity.Player;

public class MatchCalculateService {

    private static final int GAME_ADVANTAGE_DIFFERENCE = 2;
    private static final int MIN_WIN_GAMES = 6;
    private static final int MIN_WIN_GAMES_FOR_TIEBREAK = 6;
    private static final int TIEBREAK_START_SCORE = 6;
    private static final int SET_POINT_TO_WINNER = 7;
    private static final int SET_POINT_TO_LOOSER = 6;

    public Match updateMatchScore(RequestMatchScoreDto requestMatchScoreDto) {
        Match currentMatch = requestMatchScoreDto.getMatch();
        MatchScore currentMatchScore = currentMatch.getMatchScore();
        int winnerId = requestMatchScoreDto.getPlayerId();

        // TODO : реализовать
        if (isDeuce()) {
            // логика для 40:40
        }

        if (isGameWin(currentMatch)) {
            return updateSetScore(currentMatch, winnerId);
        }

        if (isSetWin(currentMatch)) {
            int gameScorePlayer1 = currentMatchScore.getGameScorePlayer1();
            int gameScorePlayer2 = currentMatchScore.getGameScorePlayer2();

            currentMatchScore.addPreviousSet(gameScorePlayer1, gameScorePlayer2);
            currentMatchScore.setDefaultGameScore();

            return currentMatch;
        }

        if (isTiebreakWon(currentMatchScore)) {
            Player player1 = currentMatch.getPlayer1Id();
            Player player2 = currentMatch.getPlayer2Id();

            if (player1.getId() == winnerId) {
                currentMatchScore.addPreviousSet(SET_POINT_TO_WINNER, SET_POINT_TO_LOOSER);
            } else if (player2.getId() == winnerId){
                currentMatchScore.addPreviousSet(SET_POINT_TO_LOOSER, SET_POINT_TO_WINNER);
            }
            currentMatchScore.setDefaultPointScore();
            currentMatchScore.setDefaultGameScore();
            currentMatchScore.setMatchType(MatchType.NORMAL);
            int a = 1;
        }

        checkForTiebreak(currentMatchScore);
        return updateGameScore(currentMatch, winnerId);
    }

    // TODO : реализовать
    private boolean isDeuce() { // 40:40
        return false;
    }

    private void checkForTiebreak(MatchScore matchScore) { // 6:6
        int gameScorePlayer1 = matchScore.getGameScorePlayer1();
        int gameScorePlayer2 = matchScore.getGameScorePlayer2();

        if (gameScorePlayer1 == TIEBREAK_START_SCORE && gameScorePlayer2 == TIEBREAK_START_SCORE) {
            if (matchScore.getMatchType().equals(MatchType.NORMAL)) {
                matchScore.setDefaultPointScore();
                matchScore.setMatchType(MatchType.TIEBREAK);
            }
        }
    }

    private boolean isTiebreakWon(MatchScore matchScore) {
        if (matchScore.getMatchType().equals(MatchType.TIEBREAK)) {
            int pointScorePlayer1 = Integer.parseInt(matchScore.getPointScorePlayer1());
            int pointScorePlayer2 = Integer.parseInt(matchScore.getPointScorePlayer2());
            int gameDifference = Math.abs(pointScorePlayer1 - pointScorePlayer2);

            return gameDifference >= GAME_ADVANTAGE_DIFFERENCE &&
                    (pointScorePlayer1 >= MIN_WIN_GAMES_FOR_TIEBREAK || pointScorePlayer2 >= MIN_WIN_GAMES_FOR_TIEBREAK);
        }
        return false;
    }

    private boolean isGameWin(Match match) {
        String pointScore1 = match.getMatchScore().getPointScorePlayer1();
        String pointScore2 = match.getMatchScore().getPointScorePlayer2();

        return (pointScore1.toUpperCase().equals(PointScore.FORTY.name()) || pointScore2.toUpperCase().equals(PointScore.FORTY.name()));
    }

    // TODO : Возможно тут баг в условии. Надо чекнуть условия завершения сета
    private boolean isSetWin(Match match) {
        int gameScorePlayer1 = match.getMatchScore().getGameScorePlayer1();
        int gameScorePlayer2 = match.getMatchScore().getGameScorePlayer2();
        int gameDifference = Math.abs(gameScorePlayer1 - gameScorePlayer2);

        if (gameDifference >= GAME_ADVANTAGE_DIFFERENCE &&
           (gameScorePlayer1 >= MIN_WIN_GAMES || gameScorePlayer2 >= MIN_WIN_GAMES)
        ) {
            return true;
        }
        return false;
    }

    // TODO : исключить дублирование
    private Match updateGameScore(Match match, int winnerId) {
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
    private Match updateSetScore(Match match, int winnerId) {
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
}
