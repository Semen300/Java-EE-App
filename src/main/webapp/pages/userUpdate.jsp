<%--
  Created by IntelliJ IDEA.
  User: Account
  Date: 21.01.2024
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Изменение</title>
</head>
<body>
  <form method="post">
    <dl>
      <dt>Логин:</dt>
      <dd><input type="text" name="newLogin" value="${requestScope.login}"></dd>
    </dl>
    <dl>
      <dt>Пароль:</dt>
      <dd><input type="text" name="newPassword"></dd>
    </dl>
    <dl>
      <dt>ФИО:</dt>
      <dd><input type="text" name="newFio" value="${requestScope.fio}"></dd>
    </dl>
    <button type="submit">Сохранить</button>
  </form>
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
  </table>
</body>
</html>
