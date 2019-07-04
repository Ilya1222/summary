<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title><fmt:message key="title.client"/></title>
    <link href="style/tariff-list.css" rel="stylesheet">
</head>
<body>
<h3><fmt:message key="tariff.conn.label5"/> </h3>
<p><a href="menu"><fmt:message key="admin.main"/></a></p>
</body>
</html>

