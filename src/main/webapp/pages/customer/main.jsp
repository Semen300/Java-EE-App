<%--
  Created by IntelliJ IDEA.
  User: Account
  Date: 30.03.2024
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Заказчик</title>
</head>
<body>
    Личный кабинет заказчика ${sessionScope.userLogin} <br>

    Список контрактов:
    <table border="1">
        <tr>
            <td>Назавание контракта</td>
            <td>Имя исполнителя</td>
        </tr>
        <c:forEach items="${requestScope.contracts}"  var="contract">
            <tr>
                <td>${contract.name}</td>
                <td>${contract.execLogin}</td>
                <td><input type="button" onclick="window.location='${pageContext.request.contextPath}/customer?action=delete&id=${contract.id}'" value="Удалить"></td>
            </tr>
        </c:forEach>
        <tr>
            <td><input type="button" onclick="window.location='${pageContext.request.contextPath}/customer?action=add'" value="Добавить"></td>
        </tr>
    </table>
<footer>
    <a href="login?action=login">Возврат на страницу авторизации</a><br>
</footer>
</body>
</html>
