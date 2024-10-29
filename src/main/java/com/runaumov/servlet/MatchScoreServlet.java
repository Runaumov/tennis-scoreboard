package com.runaumov.servlet;

import com.runaumov.MatchStorage;
import com.runaumov.dto.RequestMatchScoreDto;
import com.runaumov.dto.ResponseMatchScoreDto;
import com.runaumov.entity.Match;
import com.runaumov.entity.Player;
import com.runaumov.service.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String matchIdParam = req.getParameter("uuid");
        UUID matchId = UUID.fromString(matchIdParam);

        Match newMatch = MatchStorage.getInstance().getMatchById(matchId);

        ResponseMatchScoreDto responseMatchScoreDto = new ResponseMatchScoreDto(newMatch, matchId);
        req.setAttribute("responseMatchScoreDto", responseMatchScoreDto);
        req.getRequestDispatcher("match-score.jsp").forward(req, resp);
    }

    // TODO: написать
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO: переименовать playerIdReqP
        String playerNameReqP = req.getParameter("winnerId");
        int playerId = Integer.parseInt(playerNameReqP);
        String matchIdParam = req.getParameter("uuid");
        UUID matchId = UUID.fromString(matchIdParam);

        ScoreService scoreService = new ScoreService();
        TieBreakService tieBreakService = new TieBreakService();
        MatchStatusChecker matchStatusChecker = new MatchStatusChecker();
        MatchResultService matchResultService = new MatchResultService();

        Match currentMatch = MatchStorage.getInstance().getMatchById(matchId);
        RequestMatchScoreDto requestMatchScoreDto = new RequestMatchScoreDto(currentMatch, playerId);

        MatchCalculateService matchCalculateService = new MatchCalculateService(
                scoreService, tieBreakService, matchStatusChecker);
        Match updatedMatch = matchCalculateService.updateMatchScore(requestMatchScoreDto);

        ResponseMatchScoreDto responseMatchScoreDto = new ResponseMatchScoreDto(updatedMatch, matchId);

        if (matchStatusChecker.isMatchWin(updatedMatch)) {
            Player winner = matchResultService.getWinner(updatedMatch);
            responseMatchScoreDto.setWinner(winner);
        }

        req.setAttribute("responseMatchScoreDto", responseMatchScoreDto);
        req.getRequestDispatcher("match-score.jsp").forward(req, resp);
    }
}
