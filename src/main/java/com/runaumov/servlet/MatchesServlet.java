package com.runaumov.servlet;

import com.runaumov.dao.MatchesDao;
import com.runaumov.dto.RequestMatchesDto;
import com.runaumov.dto.ResponseMatchesDto;
import com.runaumov.entity.Match;
import com.runaumov.service.MatchesService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {

    private final MatchesDao matchesDao = new MatchesDao();
    private final ModelMapper modelMapper = new ModelMapper();
    private final MatchesService matchesService = new MatchesService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(Match.class, ResponseMatchesDto.class).addMappings(mapper -> {
            mapper.map(src -> src.getPlayer1Id().getName(), ResponseMatchesDto::setPlayer1Id);
            mapper.map(src -> src.getPlayer2Id().getName(), ResponseMatchesDto::setPlayer2Id);
            mapper.map(src -> src.getWinner().getName(), ResponseMatchesDto::setWinner);
        });

        String page = req.getParameter("page");
        String playerName = req.getParameter("filter_by_player_name");

        // TODO: подумать над именем переменной
        // TODO: потом вынести в отдельный класс-валидатор
        int pageNum = (page != null) ? Integer.parseInt(page) : 1;
        int pageSize = 2;

        RequestMatchesDto requestMatchesDto = new RequestMatchesDto(playerName, pageNum);

        List<Match> matches = matchesService.getPlayers(requestMatchesDto, pageNum, pageSize);

        List<ResponseMatchesDto> responseMatchesDto = new ArrayList<>();
        for (Match match : matches) {
            responseMatchesDto.add(modelMapper.map(match, ResponseMatchesDto.class));
        }

        int totalPages = matchesService.getTotalPages(pageSize, playerName);
        req.setAttribute("matches", responseMatchesDto);
        req.setAttribute("currentPage", pageNum);
        req.setAttribute("totalPages", totalPages);

        req.getRequestDispatcher("/matches.jsp").forward(req, resp);

    }
}

