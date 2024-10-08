<%@ page import="com.runaumov.dto.ResponseMatchesDto" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Таблица с матчами</title>
</head>
<body>
<h2>Матчи</h2>

<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Player 1</th>
        <th>Player 2</th>
        <th>SCORE</th>
    </tr>
    </thead>
    <tbody>
    <%
        // Получаем список матчей из атрибута запроса
        List<ResponseMatchesDto> matches = (List<ResponseMatchesDto>) request.getAttribute("matches");

        // Проверяем, что данные не null
        if (matches != null) {
            // Для каждого матча создаем строку таблицы
            for (ResponseMatchesDto match : matches) {
                out.println("<tr>");
                out.println("<td>" + match.getId() + "</td>");
                out.println("<td>" + match.getPlayer1Id() + "</td>");
                out.println("<td>" + match.getPlayer2Id() + "</td>");
                out.println("<td>" + match.getWinner() + "</td>");
                out.println("</tr>");
            }
        } else {
            out.println("<tr><td colspan='4'>Нет данных для отображения</td></tr>");
        }
    %>
    </tbody>
</table>

<h2>Filter by Player Name</h2>
<form action="/tennis-scoreboard/matches" method="GET">
    <label for="playerName">Enter player name:</label>
    <input type="text" id="playerName" name="filter_by_player_name" />
    <button type="submit">Filter</button>
</form>

<div class="pagination">
    <c:if test="${currentPage > 1}">
        <a href="?page=${currentPage - 1}">&laquo; Предыдущая</a>
    </c:if>

    <c:forEach var="i" begin="1" end="${totalPages}">
        <a href="?page=${i}" class="${i == currentPage ? 'active' : ''}">${i}</a>
    </c:forEach>

    <c:if test="${currentPage < totalPages}">
        <a href="?page=${currentPage + 1}">Следующая &raquo;</a>
    </c:if>
</div>

</body>
</html>
