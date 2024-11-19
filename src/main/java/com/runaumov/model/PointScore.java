package com.runaumov.model;

public enum PointScore {
    LOVE, FIFTEEN, THIRTY, FORTY, WIN;

    public static String getNextGameScore(PointScore currentPointScore) {
        PointScore[] values = PointScore.values();
        int index = currentPointScore.ordinal();
        if (index < values.length - 1) {
            return values[index + 1].name();
        }
        return values[0].name();
    }

    public static PointScore getPointScoreFromString(String string) {
        try {
            return PointScore.valueOf(string.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

}
