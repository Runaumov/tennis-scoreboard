<%--
  Created by IntelliJ IDEA.
  User: Cas
  Date: 06.11.2024
  Time: 22:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="header.jsp"%>
</head>
<body>
    <% String errorMessage = (String) request.getAttribute("errorMessage");
       Integer statusCode = (Integer) request.getAttribute("statusCode");
    %>
    <p>Код ошибки: <%= statusCode != null ? statusCode : "Неизвестный код" %></p>
    <h2>Ошибка: <%= errorMessage %></h2>
</body>
</html>
