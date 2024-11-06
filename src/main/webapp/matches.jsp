<%@ page import="com.runaumov.dto.ResponseMatchesDto" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <%@include file="header.jsp"%>
</head>
<main>
    <div class="container">
        <h1>Matches</h1>
        <div class="input-container">
            <input class="input-filter" id="filter-input" placeholder="Filter by name" type="text" />
            <div>
                <button class="btn-filter" id="reset-filter-btn">Reset Filter</button>
            </div>
        </div>

        <table class="table-matches">
            <tr>
                <th>Player One</th>
                <th>Player Two</th>
                <th>Winner</th>
            </tr>
            <tbody>
            <%
            // Получаем список матчей из атрибута запроса
            List<ResponseMatchesDto> matches = (List<ResponseMatchesDto>) request.getAttribute("matches");

                // Проверяем, что данные не null
                if (matches != null) {
                // Для каждого матча создаем строку таблицы
                for (ResponseMatchesDto match : matches) {
                out.println("<tr>");
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

        <div class="pagination">
            <c:if test="${currentPage > 1}">
                <a href="?page=${currentPage - 1}">&laquo; Prev page</a>
            </c:if>

            <c:forEach var="i" begin="1" end="${totalPages}">
                <a href="?page=${i}" class="${i == currentPage ? 'active' : ''}">${i}</a>
            </c:forEach>

            <c:if test="${currentPage < totalPages}">
                <a href="?page=${currentPage + 1}">Next page &raquo;</a>
            </c:if>
        </div>
    </div>
</main>
<footer>
    <div class="footer">
        <p>&copy; Tennis Scoreboard, project from <a href="https://zhukovsd.github.io/java-backend-learning-course/">zhukovsd/java-backend-learning-course</a>
            roadmap.</p>
    </div>
</footer>
</body>
</html>