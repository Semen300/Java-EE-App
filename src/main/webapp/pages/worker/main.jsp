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
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
</head>
<body>
    Личный кабинет сотрудника ${sessionScope.userLogin} <br>
    <br>
    <table width="100%">
        <tr>
            <td width="50%">Список проектов:</td>
            <td width="50%">Состояние проекта:</td>
        </tr>
    </table>
    <table width="100%">
        <tr>
            <td width="50%">
                <table border="1">
                    <c:forEach items="${requestScope.contracts}" var="contract">
                    <tr>
                        <td>${contract.name}</td>
                        <td>${contract.deadline}</td>
                        <c:if test="${param.action!='conInfo' || param.action=='conInfo' && param.id!=contract.id}">
                            <td>
                                <input type="button" value=">" onclick="window.location='${pageContext.request.contextPath}/worker?action=conInfo&id=${contract.id}'">
                            </td>
                        </c:if>
                        <c:if test="${param.action=='conInfo' && param.id==contract.id}">
                            <td>
                                <input type="button" value="<" onclick="window.location='${pageContext.request.contextPath}/worker'">
                            </td>
                        </c:if>
                    </tr>
                    </c:forEach>
                </table>
            </td>
            <td width="50%">
                <c:if test="${requestScope.contract.name != null}">
                    Контракт ${requestScope.contract.name}
                    <table border="1">
                        <tr>
                            <td>Низвание задачи</td>
                            <td>Предмет</td>
                            <td>Количество</td>
                        </tr>
                        <c:forEach items="${requestScope.contract.tasks}" var="task">
                            <tr>
                                <td>${task.name} </td>
                                <td>${task.item.name} </td>
                                <td>${task.amount} </td>
                                <c:if test="${task.finished}"><td>Выполнен</td></c:if>
                                <c:if test="${!task.finished}">
                                    <td>
                                        <input type="button" onclick="window.location='${pageContext.request.contextPath}/worker?action=setFinished&taskID=${task.id}'" value="Пометить выполненным">
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
            </td>
        </tr>
    </table>
    <footer>
        <a href="login?action=login">Возврат на страницу авторизации</a>
    </footer>
</body>
</html>
