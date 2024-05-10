<%--
  Created by IntelliJ IDEA.
  User: Account
  Date: 20.01.2024
  Time: 18:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Информация о контракте</title>
</head>
<body>
  Информация о контракте № ${requestScope.conId} пользователя ${sessionScope.userLogin}<br>
  "${requestScope.conName}"<br>
  Описание:
  <br>
  ${requestScope.disc}<br>
  Дедлайн: ${requestScope.deadline} <br>
  Список задач: <br>
  <table border="1">
  <c:forEach items="${requestScope.tasks}" var="task">
      <tr>
          <td> ${task.name}</td>
          <td> ${task.disc}</td>
          <td> ${task.deadline}</td>
      </tr>
  </c:forEach>
  </table>
</body>
</html>
