<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title><fmt:message key="title.admin"/></title>
    <link href="style/block-update.css" rel="stylesheet" />
</head>
<body>
<img src="image/head.jpg" alt="head"/>


<div class="user-debt-list">

    <table border="2" width="50%" cellpadding="5">
        <tr>
            <th> <fmt:message key="admin.uslist.login"/> </th>
            <th><fmt:message key="admin.uslist.name"/> </th>
            <th><fmt:message key="admin.uslist.tariff"/> </th>
            <th><fmt:message key="admin.uslist.date"/> </th>
            <th> <fmt:message key="admin.uslist.debt"/> </th>
        </tr>
        <c:forEach var="info" items="${sessionScope.infoDebt}">
            <tr>
                <td><p>${info.user.login}</p></td>
                <td><p>${info.user.firstName} ${info.user.surName}</p></td>
                <td><p>${info.tariffName}</p></td>
                <td><p>${info.period}</p></td>
                <td><p>${info.debt}</p></td>
            </tr>
        </c:forEach>
    </table>
</div>

<div class="update">
    <form method="post" action="block">
        <input type="submit" name="up" size="40" value=
        <fmt:message key="admin.update"/>>
    </form>
    <form method="get" action="menu">
        <input type="submit" name="main" size="40" value=
        <fmt:message key="admin.main"/>>
    </form>
</div>

</body>
</html>
