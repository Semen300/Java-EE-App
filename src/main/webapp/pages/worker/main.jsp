<%--
  Created by IntelliJ IDEA.
  User: Account
  Date: 17.01.2024
  Time: 19:10
  To change this template use File | Settings | File Templates.

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
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Сайт с таблицей</title>
    <style>
        <%@include file="/pages/mainStyles.css"%>
    </style>
</head>
<body>
<div class="container" id="container">
    <header>
        <h1>Личный кабинет сотрудника ${sessionScope.userLogin}</h1>
        <button class="logout-btn" onclick="window.location='${pageContext.request.contextPath}/login?action=login'">Выход</button>
    </header>

    <div class="content-container">

        <div class="block">
            <h3>Список проектов</h3>
            <table>
                <thead>
                <tr>
                    <th>Название</th>
                    <th>Дедлайн</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.contracts}" var="contract">
                        <tr>
                            <td>${contract.name}</td>
                            <td>${contract.deadline}</td>
                            <td class="action-column">
                            <c:if test="${param.action!='conInfo' || param.action=='conInfo' && param.id!=contract.id}">
                                <input class="action-btn" type="button" value=">" onclick="window.location='${pageContext.request.contextPath}/worker?action=conInfo&id=${contract.id}'">
                            </c:if>
                            <c:if test="${param.action=='conInfo' && param.id==contract.id}">
                                <input class="action-btn" type="button" value="<" onclick="window.location='${pageContext.request.contextPath}/worker'">
                            </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="block">
            <h3>Состояние проекта</h3>
            <c:if test="${requestScope.contract.name != null}">
                Контракт ${requestScope.contract.name}
                <table>
                    <thead>
                        <tr>
                            <th>Низвание задачи</th>
                            <th>Предмет</th>
                            <th>Количество</th>
                            <th>Статус</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.contract.tasks}" var="task">
                            <tr>
                                <td>${task.name} </td>
                                <td>${task.item.name} </td>
                                <td>${task.amount} </td>
                                <td class="action-column">
                                    <c:if test="${task.finished}">Выполнен</c:if>
                                    <c:if test="${!task.finished}">
                                            <input class="action-btn" type="button" onclick="window.location='${pageContext.request.contextPath}/worker?action=setFinished&taskID=${task.id}'" value="Пометить выполненным">
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
    </div>
</div>
<script>
    function setBlockHeight() {
        const percent = 90;

        // Получаем полную высоту документа
        const totalHeight = document.body.scrollHeight;

        const container = document.getElementById('container');
        const bottombutton1 = document.getElementById('bottom-button1');
        const bottombutton2 = document.getElementById('bottom-button2');

        bottombutton1.style.marginBottom = (totalHeight * (100 - percent) /4 / 100 + 20) + 'px';
        bottombutton2.style.marginBottom = (totalHeight * (100 - percent) /4 / 100 + 20) + 'px';
        container.style.marginTop = (totalHeight * (100 - percent) /4 / 100) + 'px';

        const totalHeightNew = document.body.scrollHeight;

        container.style.height = (totalHeightNew * percent / 100) + 'px';
    }

    // Устанавливаем высоту блока при загрузке страницы
    window.onload = setBlockHeight;

    // Обновляем высоту блока при изменении размера окна
    window.onresize = setBlockHeight;
</script>
</body>
</html>