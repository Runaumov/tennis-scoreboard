package com.runaumov.controller;

import com.runaumov.dao.MatchDao;
import com.runaumov.dto.request.RequestMatchesDto;
import com.runaumov.dto.response.ResponseMatchesDto;
import com.runaumov.entity.Match;
import com.runaumov.mapper.MatchMapper;
import com.runaumov.service.managment.MatchSearchService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/matches")
public class MatchesListServlet extends HttpServlet {

    private final MatchMapper matchMapper = new MatchMapper(new ModelMapper());
    private final MatchSearchService matchSearchService = new MatchSearchService();

    private static final int PAGE_SIZE = 2;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String page = req.getParameter("page");
        String playerName = req.getParameter("filter-input");

        int pageNum = getPageNumber(page);

        RequestMatchesDto requestMatchesDto = new RequestMatchesDto(playerName, pageNum);

        List<Match> matches = matchSearchService.getMatches(requestMatchesDto, pageNum, PAGE_SIZE);
        int totalPages = matchSearchService.getTotalPages(PAGE_SIZE, playerName);

        List<ResponseMatchesDto> responseMatchesDto = matches.stream()
                .map(matchMapper::toResponseDto)
                .collect(Collectors.toList());

        req.setAttribute("matches", responseMatchesDto);
        req.setAttribute("currentPage", pageNum);
        req.setAttribute("totalPages", totalPages);

        req.getRequestDispatcher("/matches.jsp").forward(req, resp);
    }

    private int getPageNumber(String page) {
        try {
            return (page != null) ? Math.max(Integer.parseInt(page), 1) : 1;
        } catch (NumberFormatException e) {
            return 1;
        }
    }
}

