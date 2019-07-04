<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title><fmt:message key="title.client"/></title>
    <link href="style/client-menu.css" rel="stylesheet" />
</head>
<body>


<img src="image/head.jpg">

<div class="cabinet">
<h2><fmt:message key="client.hello"/> ${fullName} </h2>
    <form method="get" action="repl">
        <h3>  <fmt:message key="client.balnce"/> :  ${scoreBalance} UAH
            <input type="submit" value = <fmt:message key="client.menu.balance" /> > </h3>
    </form>

 <h3><fmt:message key="client.tariffs"/> :</h3>

<c:forEach var="tariff" items="${infoTariffs}">

             <h3>${tariff.tariffName} - ${tariff.period}</h3>

</c:forEach>

</div>

<div class="butMenu">

    <a href='logout'><fmt:message key="admin.logout"/> </a>

    <a href='listTar'><fmt:message key="client.menu.tariffs"/> </a>

</div>




</body>
</html>
