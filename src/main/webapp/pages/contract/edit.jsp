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
    <title>Изменение контракта</title>
</head>
<body>
    Изменение контракта ${requestScope.contract.name}
    <form>
        <input type="hidden" value="${requestScope.contract.id}">
        <label>Имя проекта</label>
        <input name="name" value="${requestScope.contract.name}">
        <input type="date" name="deadline" value="${requestScope.contract.deadline}">
        <button type="submit"> </button>
    </form>
</body>
</html>
