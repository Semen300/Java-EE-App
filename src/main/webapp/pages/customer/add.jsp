<%--
  Created by IntelliJ IDEA.
  User: Account
  Date: 23.04.2024
  Time: 9:43
  To change this template use File | Settings | File Templates.

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Новый контракт</title>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
</head>
<body>
    Добавление нового контракта
    <form method ="post">
    <table width="100%">
        <tr>
            <td width="50%">

                    <label>Имя проекта</label>
                    <input name="name" value="${requestScope.name}" required> <br>
                    <label>Дедлайн:</label>
                    <input type="date" value="${requestScope.deadline}" name="deadline" required>
                    <input type="hidden" name="consLogin" value="${sessionScope.userLogin}">
                    <br>
                    <button type="submit"> Сохранить </button>
                    <input type="button" onclick="window.location='${pageContext.request.contextPath}/customer'" value="Отмена">
            </td>
            <td width="50%">
                <center>Состав заказа</center>
                <table border="1">
                    <tr>
                        <td>Наименование</td>
                        <td>Цена за шт.</td>
                        <td>Количество</td>
                    </tr>
                    <c:forEach items="${requestScope.items}" var="item">
                        <tr>
                            <td>
                                ${item.name}
                            </td>
                            <td>
                                ${item.price}
                            </td>
                            <td>
                                <input type="number" name="numberOf${item.id}" value="0" min="0">
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
        </tr>
    </table>
    </form>
    <span>${requestScope.errorText}</span>
</body>
</html>
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Новый заказ</title>
    <style>
        <%@include file="/pages/mainStyles.css"%>
    </style>
</head>
<body>
<div class="container" id="container">
    <header>
        <h1>Добавление заказа</h1>
    </header>

    <form method="post">
        <div class="content-container">
            <div class="block">
                <h5>Дедлайн</h5>
                <label>
                <input name="deadline" type="date" value="${requestScope.deadline}" required>
                </label>
                <input type="hidden" name="consLogin" value="${sessionScope.userLogin}" required>
                <div class="error-message" id="error-message">${requestScope.errorText}</div>
            </div>

            <div class="block">
                <table>
                    <thead>
                        <tr>
                            <th>Наименование</th>
                            <th>Цена за шт.</th>
                            <th>Количество</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.items}" var="item">
                            <tr>
                                <td>
                                    ${item.name}
                                </td>
                                <td>
                                    ${item.price}
                                </td>
                                <td class="action-column">
                                    <input type="number" name="numberOf${item.id}" value="0" min="0" required>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <button class="add-btn" type="submit" id="bottom-button1">Сохранить</button>
        <button class="add-btn" id="bottom-button2" onclick="window.location='${pageContext.request.contextPath}/customer'">Отмена</button>
    </form>
</div>
<script>

</script>
</body>
</html>

