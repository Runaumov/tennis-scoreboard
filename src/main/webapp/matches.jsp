<%@ page import="com.runaumov.dto.response.ResponseMatchesDto" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <%@include file="header.jsp"%>
</head>
<main>
    <div class="container">
        <h1>Matches</h1>
        <div class="input-container">
            <form action="${pageContext.request.contextPath}/matches" method="GET">
                <input class="input-filter" id="filter-input" placeholder="Filter by name" type="text" name="filter-input"
                       value="<%= request.getParameter("filter-input") != null ? request.getParameter("filter-input") : "" %>" />
                <button class="btn-filter" id="apply-filter-btn">Apply Filter</button>
                <a class="btn-filter" id="reset-filter-btn" href="${pageContext.request.contextPath}/matches">Reset Filter</a>
            </form>
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
            <%
                int currentPage = (Integer) request.getAttribute("currentPage");
                int totalPages = (Integer) request.getAttribute("totalPages");
                if (currentPage > 1) {
            %>
            <a class="prev" href="?page=${currentPage - 1}&filter-input=<%= request.getParameter("filter-input") != null ? request.getParameter("filter-input") : "" %>"> < </a>
            <% } %>
            <% for (int i = 1; i <= totalPages; i++) { %>
            <a class="num-page <%= (i == currentPage) ? "current" : "" %>"
               href="?page=<%= i %>&filter-input=<%= request.getParameter("filter-input") != null ? request.getParameter("filter-input") : "" %>"><%= i %></a>
            <% } %>
            <%
                if (currentPage < totalPages) {
            %>
            <a class="next" href="?page=${currentPage + 1}&filter-input=<%= request.getParameter("filter-input") != null ? request.getParameter("filter-input") : "" %>"> > </a>
            <% } %>
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