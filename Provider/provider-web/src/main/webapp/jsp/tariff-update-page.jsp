<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title><fmt:message key="title.admin"/> </title>
    <link href="style/tariff-upd.css" rel="stylesheet" />
</head>
<body>

<p><img src="image/head.jpg"></p>

<div class="forma" >
<form method="post" action="updTariff">
    <p>  <fmt:message key="tariff.new"/> : </p>
    <label><input type="text" name="priceNew"  size="20" placeholder=<fmt:message key="replenis.amount"/>></label>
    <input type="text" hidden name="current" value="${id}">
    <p><input type="submit" value=<fmt:message key="admin.update"/> ></p>
</form>
<form method="get" action="con">
    <input type="submit" value=<fmt:message key="admin.main"/>>
</form>
</div>
</body>
</html>
