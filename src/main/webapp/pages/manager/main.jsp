<%--
  Created by IntelliJ IDEA.
  User: Account
  Date: 16.01.2024
  Time: 18:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Менеджер</title>
</head>
<body>
    Личный кабинет менеджера ${sessionScope.userLogin}
    <br>
    <%--
    Список пользователей:
    <table border="1">
    <c:forEach items="${requestScope.users}"  var="user">
        <tr>
            <td><a href="userInfo?login=${user.login}">${user.login}</a></td>
            <td>${user.fio}</td>
        </tr>
    </c:forEach>
    </table>
    --%>
    <a href="login?action=login">Возврат на страницу авторизации</a>
</body>
</html>
