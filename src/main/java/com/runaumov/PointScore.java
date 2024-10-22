package com.runaumov;

public enum PointScore {
    LOVE, FIFTEEN, THIRTY, FORTY, AD;

    public static String getNextGameScore(PointScore currentPointScore) {
        PointScore[] values = PointScore.values();
        for (int i = 0; i < values.length - 2; i++) {
            if (values[i] == currentPointScore) {
                return values[i + 1].name();
            }
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
