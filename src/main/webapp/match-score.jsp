<%@ page import="com.runaumov.dto.response.ResponseMatchScoreDto" %>
<html>
<head>
    <%@include file="header.jsp"%>
</head>
<main>
    <div class="container">
        <h1>Match Score No ${responseMatchScoreDto.matchId}</h1>
        <div class="current-match-image"></div>

        <!-- TODO : сделать кнопки перехода на новый матч или к списку матчей, заблокировать кнопки SCORE-->
        <%
            ResponseMatchScoreDto responseMatchScoreDto = (ResponseMatchScoreDto) request.getAttribute("responseMatchScoreDto");
            if (responseMatchScoreDto.getWinner() != null) {
        %>
        <div class="winner">
            <h2>Winner is: ${responseMatchScoreDto.winner.name}</h2>
        </div>
        <% } %>

        <section class="score">
            <table class="table">
                <thead class="result">
                <tr>
                    <th class="table-text">Player</th>
                    <th class="table-text">Sets</th>
                    <th class="table-text">Games</th>
                    <th class="table-text">Points</th>
                </tr>
                </thead>
                <tbody>

                <tr class="player1">
                    <td class="table-text">${responseMatchScoreDto.match.player1Id.name}</td>
                    <td class="table-text">${responseMatchScoreDto.match.matchScore.setScorePlayer1}</td>
                    <td class="table-text">${responseMatchScoreDto.match.matchScore.gameScorePlayer1}</td>
                    <td class="table-text">${responseMatchScoreDto.match.matchScore.pointScorePlayer1}</td>
                        <%
                            if (responseMatchScoreDto.getWinner() == null) {
                        %>
                            <td class="table-text">
                                <form action="${pageContext.request.contextPath}/match-score?uuid=${responseMatchScoreDto.matchId}" method="POST">
                                    <input type="hidden" name="winnerId" value="${responseMatchScoreDto.match.player1Id.id}">
                                    <input type="hidden" name="matchId" value="${responseMatchScoreDto.matchId}">
                                    <button type="submit">Score</button>
                                </form>
                            </td>
                        <% } %>
                </tr>

                <tr class="player2">
                    <td class="table-text">${responseMatchScoreDto.match.player2Id.name}</td>
                    <td class="table-text">${responseMatchScoreDto.match.matchScore.setScorePlayer2}</td>
                    <td class="table-text">${responseMatchScoreDto.match.matchScore.gameScorePlayer2}</td>
                    <td class="table-text">${responseMatchScoreDto.match.matchScore.pointScorePlayer2}</td>
                    <%
                        if (responseMatchScoreDto.getWinner() == null) {
                    %>
                        <td class="table-text">
                            <form action="${pageContext.request.contextPath}/match-score?uuid=${responseMatchScoreDto.matchId}" method="POST">
                                <input type="hidden" name="winnerId" value="${responseMatchScoreDto.match.player2Id.id}">
                                <input type="hidden" name="matchId" value="${responseMatchScoreDto.matchId}">
                                <button type="submit">Score</button>
                            </form>
                        </td>
                    <% } %>
                </tr>
                </tbody>
            </table>
        </section>

    </div>
</main>
<footer>
    <div class="footer">
        <p>&copy; Tennis Scoreboard, project from <a href="https://zhukovsd.github.io/java-backend-learning-course/">zhukovsd/java-backend-learning-course</a> roadmap.</p>
    </div>
</footer>
</body>
</html>