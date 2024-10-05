<%--
  Created by IntelliJ IDEA.
  User: Account
  Date: 16.01.2024
  Time: 18:56
  To change this template use File | Settings | File Templates.

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Менеджер</title>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
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
                    <tr>
                        <td>Логин</td>
                        <td>Фамилия И.О.</td>
                        <td>Количество контрактов</td>
                    </tr>
                    <c:forEach items="${requestScope.workers}" var="worker">
                        <tr>
                            <td>${worker.login}</td>
                            <td>${worker.fio}</td>
                            <td>${worker.numberOfContracts}</td>
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
                                        <c:if test="${contract.execLogin=='null'}"> <option value="" selected>Не назначен</option> </c:if>
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
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ЛК Менеджера</title>
    <style>
        <%@include file="/pages/mainStyles.css"%>
    </style>
</head>
<body>
<div class="container" id="container">
    <header>
        <h1>Личный кабинет менеджера ${sessionScope.userLogin}</h1>
        <button class="logout-btn" onclick="window.location='${pageContext.request.contextPath}/login?action=login'">Выход</button>
    </header>

        <div class="content-container">
            <div class="block">
                <h3>Список рабочих, закрепленных за данным менеджером</h3>
                <table>
                    <thead>
                        <tr>
                            <th>Логин</th>
                            <th>Фамилия И.О.</th>
                            <th>Количество контрактов</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.workers}" var="worker">
                            <tr>
                                <td>${worker.login}</td>
                                <td>${worker.fio}</td>
                                <td>${worker.numberOfContracts}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="block">
                <h3>Список заказов, доступных данному менеджеру</h3>
                <table>
                    <thead>
                        <tr>
                            <th>Название</th>
                            <th>Дедлайн</th>
                            <th>Исполнитель</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.contracts}" var="contract">
                            <tr>
                                <form method="post">
                                    <input type="hidden" name="id" value="${contract.id}">
                                    <td>${contract.name}</td>
                                    <td>${contract.deadline}</td>
                                    <td>
                                        <select id="execLogin" name="execLogin">
                                            <c:if test="${contract.execLogin=='null'}"> <option value="" selected>Не назначен</option> </c:if>
                                            <c:forEach items="${requestScope.workers}" var="worker">
                                                <c:if test="${worker.login==contract.execLogin}"> <option value="${worker.login}" selected>${worker.fio}</option> </c:if>
                                                <c:if test="${worker.login!=contract.execLogin}"> <option value="${worker.login}">${worker.fio}</option> </c:if>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td class="action-column">
                                        <button type="submit" class="action-btn">Назначить</button>
                                    </td>
                                </form>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
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

