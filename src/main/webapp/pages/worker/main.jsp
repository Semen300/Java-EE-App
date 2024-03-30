<%--
  Created by IntelliJ IDEA.
  User: Account
  Date: 17.01.2024
  Time: 19:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Рабочий</title>
</head>
<body>
    Информация о пользователе ${sessionScope.user_login} <br>
    <a href="login">Возврат на страницу авторизации</a>
    <%--Логин: ${requestScope.login}
    <br>
    ФИО: ${requestScope.fio}
    <br>
    Список проектов:
    <br>
    <table border="1">
    <c:forEach items="${requestScope.contracts}" var="contract">
        <tr>
            <td><a href="contractInfo?conId=${contract.id}">${contract.name}</a></td>
            <td>${contract.disc}</td>
            <td>${contract.deadline}</td>
        </tr>
    </c:forEach>
    </table> --%>
</body>
</html>
