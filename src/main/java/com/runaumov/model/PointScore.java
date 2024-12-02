package com.runaumov.model;

import com.runaumov.exception.ScoreUpdateException;

public enum PointScore {
    LOVE, FIFTEEN, THIRTY, FORTY, AD, WIN;

    public static String getNextGameScore(PointScore currentPointScore, GameType gameType) {
        if (gameType.equals(GameType.NORMAL)) {
            return switch (currentPointScore) {
                case LOVE -> FIFTEEN.name();
                case FIFTEEN -> THIRTY.name();
                case THIRTY -> FORTY.name();
                case FORTY -> WIN.name();
                default -> throw new ScoreUpdateException("Invalid state for normal game.");
            };
        }
        if (gameType.equals(GameType.DEUCE)) {
            return switch (currentPointScore) {
                case FORTY -> AD.name();
                case AD -> WIN.name();
                default -> throw new ScoreUpdateException("Invalid state for deuce game.");
            };
        }
        throw new ScoreUpdateException("Error for update gameScore");
    }


    public static PointScore getPointScoreFromString(String string) {
        try {
            return PointScore.valueOf(string.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

}
