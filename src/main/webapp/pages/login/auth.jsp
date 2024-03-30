<%--
  Created by IntelliJ IDEA.
  User: Account
  Date: 30.03.2024
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Создание нового профиля заказчика</title>
</head>
<body>
    <form action = "" method="post">
        <div>
            <span> Логин </span>
            <input id="login" name="login" type="text">
            <br>
            <span> Пароль </span>
            <input id="password" name="password" type="text">
            <br>
            <span> Фио </span>
            <input id="fio" name="fio" type="text">
            <br>
            <span> Номер телефона </span>
            <input id="number" name="number" type="text">
            <br>
            <span> Email </span>
            <input id="email" name="email" type="text">
            <br>
            <br>
            <span>${requestScope.errorText}</span>
            <br>
            <br>
            <input type="submit">
        </div>
    </form>
    <a href="login?action=login">На страницу входа</a>
</body>
</html>
