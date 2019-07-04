<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Administrator</title>
    <link href="style/statistic.css" rel="stylesheet"  />
</head>
<body>
<table border="2" width="60%" cellpadding="5">
    <tr>
        <th>Name</th>
        <th>Connections</th>
        <th>Money/Month</th>
    </tr>
    <c:forEach var="stat" items="${sessionScope.statisticList}">
        <tr>
            <td><p>${stat.tariffName}</p></td>
            <td><p>${stat.amountUsers} </p></td>
            <td><p>${stat.money}</p></td>
        </tr>
    </c:forEach>
</table>

<div class="replenish">
    <form method="get" action="menu">
        <p><input type="submit" value="main"></p>
    </form>
</div>

</body>
</html>
