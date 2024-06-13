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
    <table width="100%">
        <tr>
            <td width="50%">Список рабочих, закрепленных за данным менеджером:</td>
            <td width="50%">Список проектов, доступных данному менеджеру:</td>
        </tr>
    </table>
    <table width="100%">
        <tr>
            <td width="50%">
                <table border="1px">
                    <c:forEach items="${requestScope.workers}" var="worker">
                        <tr>
                            <td>${worker.login}</td>
                            <td>${worker.fio}</td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
            <td width="50%">
                <table border="1px">
                    <c:forEach items="${requestScope.contracts}" var="contract">
                        <tr>
                            <form method="post">
                                <input type="hidden" name="id" value="${contract.id}">
                                <td>${contract.name}</td>
                                <td>${contract.deadline}</td>
                                <td>
                                    <select id="execLogin" name="execLogin">
                                        <option value="">Не назначен</option>
                                        <c:forEach items="${requestScope.workers}" var="worker">
                                            <c:if test="${worker.login==contract.execLogin}"> <option value="${worker.login}" selected>${worker.fio}</option> </c:if>
                                            <c:if test="${worker.login!=contract.execLogin}"> <option value="${worker.login}">${worker.fio}</option> </c:if>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <button type="submit">Назначить</button>
                                </td>
                            </form>
                        </tr>
                    </c:forEach>
                </table>
            </td>
        </tr>
    </table>
    <footer>
        <a href="login?action=login">Возврат на страницу авторизации</a>
    </footer>
</body>
</html>


