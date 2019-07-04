<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title><fmt:message key="title.client"/></title>
    <link href="style/refill.css" rel="stylesheet" />
</head>
<body>
<img src="image/head.jpg">

<div class="forma1">
<form method="post" action="repl" name="form">
    <h3><fmt:message key="replenis.label"/></h3>
    <label><input type="text" name="currentAmount" placeholder=<fmt:message key="replenis.amount"/>></label>
    <p><input type="submit" value=<fmt:message key="replenish.repl"/>></p>
</form>
<form method="get" action="menu">
    <p><input type="submit" value=<fmt:message key="admin.main"/>></p>
</form>
</div>
</body>
</html>
