package com.runaumov.validator;

import com.runaumov.dto.request.RequestNewMatchDto;
import com.runaumov.exception.InvalidRequestException;
import java.util.regex.Pattern;

public class RequestValidator {
    private static final Pattern PLAYER_NAME_PATTERN = Pattern.compile("^[A-Z]\\. [A-Z][a-z]*$");

    public static void validateRequestNewMatchDto(RequestNewMatchDto requestNewMatchDto) {
        String[] playerNames = {requestNewMatchDto.getPlayer1Name(), requestNewMatchDto.getPlayer2Name()};
        validatePlayerNames(playerNames);
    }

    private static void validatePlayerNames(String[] playerNames) {
        for (int i = 0; i < playerNames.length; i++) {
            if (!PLAYER_NAME_PATTERN.matcher(playerNames[i]).matches()) {
                throw new InvalidRequestException("Invalid players name");
            }
        }

    }
}
