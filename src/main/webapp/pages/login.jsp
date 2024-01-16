<%--
  Created by IntelliJ IDEA.
  User: Account
  Date: 16.01.2024
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Авторизация</title>
</head>
<body>
    <form action = "" method="post">
        <div>
            <span> Логин </span>
            <input id="login" name="login" type="text">
            <br>
            <span> Пароль </span>
            <input id="password" name="password" type="password">
            <br>
            <br>
            <span>${requestScope.errorText}</span>
            <br>
            <br>
            <input type="submit">
        </div>
    </form>
</body>
</html>
