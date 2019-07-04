<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title><fmt:message key="title.client"/></title>
    <link href="style/tariff-connection.css" rel="stylesheet">
</head>
<body>
<img src="image/head.jpg">

<div class="conn">

<h3><fmt:message key="tariff.conn.label1"/> : (${requestScope.thisTariff})</h3>
<h4><fmt:message key="tariff.conn.lavel2"/>: (${requestScope.scoreBalance})</h4>
<h4><fmt:message key="tariff.conn.label3"/> :(${requestScope.tariffPrice})</h4>
<h3>${requestScope.eqService}</h3>

<c:choose>
    <c:when test="${requestScope.money<0}">
        <h2><fmt:message key="tariff.conn.label4"/></h2>
    </c:when>
    <c:when test="${requestScope.money>=0}">
        <form  method="post"  action="confirm" name="form"  >
            <input type="submit" value=<fmt:message key="tariff.conn.ok"/>>
        </form>
    </c:when>
</c:choose>
<form  method="get"  action="menu" name="form"  >
    <input type="submit" value=<fmt:message key="admin.main"/>>
</form>
</div>
</body>
</html>
