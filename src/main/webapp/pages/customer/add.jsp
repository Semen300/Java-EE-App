<%--
  Created by IntelliJ IDEA.
  User: Account
  Date: 23.04.2024
  Time: 9:43
  To change this template use File | Settings | File Templates.
--%>
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
                    <c:forEach items="${requestScope.items}" var="item">
                        <tr>
                            <td>
                                <input type="hidden" name="nameOf${item.id}" value="${item.name}">
                                ${item.name}
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
