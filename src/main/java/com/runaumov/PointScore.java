package com.runaumov;

public enum PointScore {
    LOVE, FIFTEEN, THIRTY, FORTY, AD;

    public static PointScore getNextGameScore(PointScore currentPointScore) {
        PointScore[] values = PointScore.values();
        for (int i = 0; i < values.length - 2; i++) {
            if (values[i] == currentPointScore) {
                return values[i + 1];
            }
        }
        return values[0];
    }

}
