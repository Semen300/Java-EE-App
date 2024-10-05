<%--
  Created by IntelliJ IDEA.
  User: Account
  Date: 16.01.2024
  Time: 18:48
  To change this template use File | Settings | File Templates.

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Авторизация</title>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
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
    <a href="login?action=auth">Добавить нового пользователя</a>
</body>
</html>
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        <%@include file="/pages/login/loginStyles.css"%>
    </style>
    <title>Авторизация</title>
</head>
<body>
<div class="container">
    <div class="auth-box">
        <h2>Авторизация</h2>
        <form class="auth-form" id="auth-form" method="post">
            <input type="text" id="username" name="login" placeholder="Логин" required autocomplete="off">
            <input type="password" id="password" name="password" placeholder="Пароль">
            <div class="error-message" id="error-message">${requestScope.errorText}</div>
            <button type="submit" id="submit-button" disabled>Войти</button>
            <button type="button" onclick="window.location='${pageContext.request.contextPath}/login?action=auth'">Регистрация</button>
        </form>
    </div>
</div>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        const usernameInput = document.getElementById('username');
        const passwordInput = document.getElementById('password');
        const submitButton = document.getElementById('submit-button');

        function toggleSubmitButton() {
            submitButton.disabled = !usernameInput.value || !passwordInput.value;
        }

        usernameInput.addEventListener('input', toggleSubmitButton);
        passwordInput.addEventListener('input', toggleSubmitButton);
    });
</script>
</body>
</html>
