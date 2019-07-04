<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title><fmt:message key="title.client"/></title>
    <link href="style/client-blocked.css" rel="stylesheet" />
</head>
<body>

<img src="image/head.jpg" />

<h2> <fmt:message key="block.dear"/> ${fullName}.<fmt:message key="block.mess1"/> .</h2>
<h2><fmt:message key="block.mess2"/> :</h2>

<c:forEach var="debt" items="${debtUserInfo}">
    <h3>${debt.tariffName}  -  ${debt.money}  UAH</h3>
</c:forEach>

<h2><fmt:message key="client.balnce"/>  : ${scoreBalance} </h2>

<h2><fmt:message key="block.mess3"/> </h2>

<div class="replenish">
<form method="get" action="repl">
    <p><input type="submit" value=<fmt:message key="client.menu.balance"/>></p>
</form>
 <form method="get" action="logout">
     <p><input type="submit" value=<fmt:message key="admin.logout"/>></p>
 </form>
</div>



</body>
</html>

