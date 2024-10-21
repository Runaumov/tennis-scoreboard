<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Match Score</title>
</head>
<body>
<h1>Match Score № ${responseMatchScoreDto.matchId}</h1>

<table>
    <tr>
        <th>Player 1</th>
        <th>Player 2</th>
    </tr>
    <tr>
        <td>${responseMatchScoreDto.match.player1Id.name}</td>
        <td>${responseMatchScoreDto.match.player2Id.name}</td>
    </tr>
    <tr>
        <td>Set: ${responseMatchScoreDto.match.matchScore.setScorePlayer1}</td>
        <td>Set: ${responseMatchScoreDto.match.matchScore.setScorePlayer2}</td>
    </tr>
    <tr>
        <td>Points: ${responseMatchScoreDto.match.matchScore.pointScorePlayer1}</td>
        <td>Points: ${responseMatchScoreDto.match.matchScore.pointScorePlayer2}</td>
    </tr>

</table>

<form action="${pageContext.request.contextPath}/match-score?uuid=${responseMatchScoreDto.matchId}" method="POST">
    <input type="hidden" name="winnerId" value="${responseMatchScoreDto.match.player1Id.id}">
    <input type="hidden" name="matchId" value="${responseMatchScoreDto.matchId}">
    <button type="submit">Player 1 Wins Point</button>
</form>

<form action="${pageContext.request.contextPath}/match-score?uuid=${responseMatchScoreDto.matchId}" method="POST">
    <input type="hidden" name="winnerId" value="${responseMatchScoreDto.match.player2Id.id}">
    <input type="hidden" name="matchId" value="${responseMatchScoreDto.matchId}">
    <button type="submit">Player 2 Wins Point</button>
</form>

</body>
</html>
