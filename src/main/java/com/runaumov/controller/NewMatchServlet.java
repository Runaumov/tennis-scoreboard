package com.runaumov.controller;

import com.runaumov.dto.request.RequestNewMatchDto;
import com.runaumov.service.managment.NewMatchService;
import com.runaumov.validator.RequestValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {

    private static final String MATCH_SCORE_PATH = "/match-score?uuid=";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String playerOneName = req.getParameter("playerOne");
        String playerTwoName = req.getParameter("playerTwo");

        RequestNewMatchDto requestNewMatchDto = new RequestNewMatchDto(playerOneName, playerTwoName);
        RequestValidator.validateRequestNewMatchDto(requestNewMatchDto);

        NewMatchService newMatchService = new NewMatchService();
        UUID matchId = newMatchService.initMatch(requestNewMatchDto);

        resp.sendRedirect(req.getContextPath() + MATCH_SCORE_PATH + matchId);

    }
}
