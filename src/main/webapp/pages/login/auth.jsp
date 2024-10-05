<%--
  Created by IntelliJ IDEA.
  User: Account
  Date: 30.03.2024
  Time: 16:04
  To change this template use File | Settings | File Templates.

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Создание нового профиля заказчика</title>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
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
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        <%@include file="/pages/login/loginStyles.css"%>
    </style>
    <title>Регистрация</title>
</head>
<body>
<div class="container">
    <div class="auth-box">
        <h2>Регистрация</h2>
        <form class="auth-form" id="auth-form" method="post">
            <input type="text" name="login" id="login" placeholder="Логин" required autocomplete="off">
            <input type="password" name="password" id="password" placeholder="Пароль" required autocomplete="off">
            <input type="text" name="fio" id="fio" placeholder="ФИО" required autocomplete="off">
            <input type="email" name="email" id="email" placeholder="Email" required autocomplete="off">
            <input type="tel" name="number" id="number" size="10" placeholder="Номер" required autocomplete="off">
            <div class="error-message" id="error-message"></div>
            <button type="submit" id="submit-button" disabled>Зарегестрироваться</button>
            <button type="button" onclick="window.location='${pageContext.request.contextPath}/login?action=login'">Авторизация</button>
        </form>
    </div>
</div>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        const usernameInput = document.getElementById('login');
        const passwordInput = document.getElementById('password');
        const fioInput = document.getElementById('fio');
        const numberInput = document.getElementById('number');
        const emailInput = document.getElementById('email');
        const submitButton = document.getElementById('submit-button');


        function toggleSubmitButton() {
            submitButton.disabled = !usernameInput.value || !passwordInput.value || !fioInput.value || !numberInput.value || !emailInput.value;
        }

        usernameInput.addEventListener('input', toggleSubmitButton);
        passwordInput.addEventListener('input', toggleSubmitButton);
        fioInput.addEventListener('input', toggleSubmitButton);
        numberInput.addEventListener('input', toggleSubmitButton);
        emailInput.addEventListener('input', toggleSubmitButton);
    });
</script>
</body>
</html>