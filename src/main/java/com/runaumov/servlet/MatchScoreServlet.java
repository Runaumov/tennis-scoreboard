package com.runaumov.servlet;

import com.runaumov.MatchStorage;
import com.runaumov.dto.ResponseMatchScoreDto;
import com.runaumov.entity.Match;
import com.runaumov.service.MatchesService;
import com.runaumov.service.NewMatchService;
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
        String playerId = req.getParameter("winnerID");
        String matchIdParam = req.getParameter("uuid");
        UUID matchId = UUID.fromString(matchIdParam);


        int a = 1;
    }
}
