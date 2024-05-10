<%--
  Created by IntelliJ IDEA.
  User: Account
  Date: 23.04.2024
  Time: 9:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Новый контракт</title>
    <script src="add.js"> </script>
</head>
<body>
    Добавление нового контракта
    <form method ="post" name="addform">
        <label>Имя проекта</label>
        <input name="name" required> <br>
        <label>Дедлайн:</label>
        <input type="date" name="deadline" required>
        <input type="hidden" name="consLogin" value="${sessionScope.userLogin}">
        <br>
        <button type="submit" onclick="check();"> Сохранить </button>
        <input type="button" onclick="window.location='${pageContext.request.contextPath}/customer'" value="Отмена">
    </form>
</body>
</html>
