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
import java.util.Optional;

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
        RequestMatchesDto requestMatchesDto = new RequestMatchesDto(playerName, pageNum);


        List<Match> matches = matchesService.getPlayers(requestMatchesDto);

        List<ResponseMatchesDto> responseMatchesDto = new ArrayList<>();
        for (Match match : matches) {
            responseMatchesDto.add(modelMapper.map(match, ResponseMatchesDto.class));
        }

        req.setAttribute("matches", responseMatchesDto);
        req.getRequestDispatcher("/table.jsp").forward(req, resp);

    }
}

