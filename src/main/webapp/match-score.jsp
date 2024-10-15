<%--
  Created by IntelliJ IDEA.
  User: Cas
  Date: 14.10.2024
  Time: 22:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Match Score</title>
</head>
<body>
<h1>Match Score</h1>

<table>
    <tr>
        <th>Player 1</th>
        <th>Player 2</th>
    </tr>
    <tr>
        <td>${match.getPlayer1Id()}</td>
        <td>${match.getPlayer2Id()}</td>
    </tr>
    <!--<tr>
        <td>${match.player1Score}</td>
        <td>${match.player2Score}</td>
    </tr>-->
</table>

<form action="${pageContext.request.contextPath}/match-score?uuid=${param.uuid}" method="POST">
    <input type="hidden" name="winnerId" value="${match.getPlayer1Id()}">
    <button type="submit">Player 1 Wins Point</button>
</form>

<form action="${pageContext.request.contextPath}/match-score?uuid=${param.uuid}" method="POST">
    <input type="hidden" name="winnerId" value="${match.getPlayer2Id()}">
    <button type="submit">Player 2 Wins Point</button>
</form>
</body>
</html>
