<%--
  Created by IntelliJ IDEA.
  User: Account
  Date: 30.03.2024
  Time: 14:06
  To change this template use File | Settings | File Templates.

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Заказчик</title>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <script type="text/javascript" language="JavaScript">
        function showConfirm(contextPath, id, refundSum, fullSum) {
            const result =  confirm("Вы жействительно желаете удалить данный заказ? " +
                "На основании степени его выполнения, " + refundSum + " из " + fullSum + " руб. будут возвращены");
            if(result) {
                window.location.href = contextPath + "/customer?action=delete&id=" + id;
            }
        }
    </script>
</head>
<body>
    Личный кабинет заказчика ${sessionScope.userLogin} <br>

    Список контрактов:
    <table>
        <tr>
            <td>Назавание контракта</td>
            <td>Сумма контракта</td>
            <td>Статус</td>
            <td>Имя исполнителя</td>
            <td>Завершен на</td>
        </tr>
        <c:forEach items="${requestScope.contracts}"  var="contract">
            <tr>
                <td>${contract.name}</td>
                <td>${contract.price}</td>
                <td>
                    <c:if test="${contract.status==0}">Завершён</c:if>
                    <c:if test="${contract.status==1}">Принят в работу</c:if>
                    <c:if test="${contract.status==2}">В работе</c:if>
                    <c:if test="${contract.status==3}">Ожидает оплаты</c:if>
                </td>
                <td>
                    <c:if test="${contract.execLogin=='null'}">Не назначен</c:if>
                    <c:if test="${contract.execLogin!='null'}">${contract.execLogin}</c:if>
                </td>
                <td>${contract.percentOfCompletion}%</td>
                <td>
                    <c:if test="${contract.status==3}">
                        <input type="button" onclick="window.location='${pageContext.request.contextPath}/customer?action=pay&id=${contract.id}'" value="Оплатить">
                        <input type="button" onclick="window.location='${pageContext.request.contextPath}/customer?action=delete&id=${contract.id}'" value="Удалить">
                    </c:if>
                </td>
                <td><c:if test="${contract.status!=3}"><input type="button" onclick="showConfirm('${pageContext.request.contextPath}', ${contract.id}, ${contract.priceOfUnfinished}, ${contract.price});" value="Удалить"></c:if></td>
                <c:if test="${contract.status==0}"><td><input type="button" onclick="window.location='${pageContext.request.contextPath}/customer?action=delete&id=${contract.id}'" value="Подтвердить получение"></td></c:if>
            </tr>
        </c:forEach>
        <tr>
            <td><input type="button" onclick="window.location='${pageContext.request.contextPath}/customer?action=add'" value="Добавить"></td>
        </tr>
    </table>
<footer>
    <a href="login?action=login">Возврат на страницу авторизации</a>
</footer>
</body>
</html>
--%>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ЛК Заказчика</title>
    <style>
        <%@include file="/pages/mainStyles.css"%>
    </style>
</head>
<body>
<div class="container">
    <header>
        <h1>Личный кабинет заказчика ${sessionScope.userLogin}</h1>
        <button class="logout-btn" onclick="window.location='${pageContext.request.contextPath}/login?action=login'">Выход</button>
    </header>

    <div class="content-container">
        <table>
            <thead>
            <tr>
                <th>Назавание контракта</th>
                <th>Сумма контракта</th>
                <th>Статус</th>
                <th>Имя исполнителя</th>
                <th>Завершен на</th>

            </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.contracts}"  var="contract">
                    <tr>
                    <td>${contract.name}</td>
                    <td>${contract.price}</td>
                    <td>
                        <c:choose>
                            <c:when test="${contract.status==0}">Завершён</c:when>
                            <c:when test="${contract.status==1}">Принят в работу</c:when>
                            <c:when test="${contract.status==2}">В работе</c:when>
                            <c:when test="${contract.status==3}">Ожидает оплаты</c:when>
                        </c:choose>
                    </td>
                    <td>
                        <c:if test="${contract.execLogin=='null'}">Не назначен</c:if>
                        <c:if test="${contract.execLogin!='null'}">${contract.execLogin}</c:if>
                    </td>
                    <td>${contract.percentOfCompletion}%</td>
                    <td class="action-column">
                        <c:if test="${contract.status==3}">
                            <input class="action-btn" type="button" onclick="window.location='${pageContext.request.contextPath}/customer?action=delete&id=${contract.id}'" value="Удалить">
                            <input class="action-btn" type="button" onclick="window.location='${pageContext.request.contextPath}/customer?action=pay&id=${contract.id}'" value="Оплатить">
                        </c:if>
                        <c:if test="${contract.status!=3}"><input type="button" class="action-btn" onclick="showConfirm('${pageContext.request.contextPath}', ${contract.id}, ${contract.priceOfUnfinished}, ${contract.price});" value="Удалить"></c:if>
                        <c:if test="${contract.status==0}"><input type="button" class="action-btn" onclick="window.location='${pageContext.request.contextPath}/customer?action=delete&id=${contract.id}'" value="Подтвердить получение"></c:if>
                    </td>

                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <button class="add-btn" onclick="window.location='${pageContext.request.contextPath}/customer?action=add'">Добавить новую запись</button>
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

    function showConfirm(contextPath, id, refundSum, fullSum) {
        const result =  confirm("Вы жействительно желаете удалить данный заказ? " +
            "На основании степени его выполнения, " + refundSum + " из " + fullSum + " руб. будут возвращены");
        if(result) {
            window.location.href = contextPath + "/customer?action=delete&id=" + id;
        }
    }
</script>
</body>
</html>

