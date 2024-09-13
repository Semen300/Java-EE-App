<%--
  Created by IntelliJ IDEA.
  User: Account
  Date: 13.09.2024
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Отказано в доступе</title>
</head>
<body>
    У вас нет доступа к данной странице <br>
    <a href="${pageContext.request.contextPath}/login?action=login">Вернуться на страницу авторизации</a>
</body>
</html>
