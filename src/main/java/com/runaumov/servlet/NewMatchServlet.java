package com.runaumov.servlet;

import com.runaumov.dto.RequestNewMatchDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String palyer1Name = req.getParameter("player1Name");
        String palyer2Name = req.getParameter("player2Name");

        //TODO: реализовать валидацию входных данных

        RequestNewMatchDto requestNewMatchDto = new RequestNewMatchDto(palyer1Name, palyer2Name);


    }
}
