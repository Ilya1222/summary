<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale"/>

<html lang="en">

<head>
    <link href="style/index.css" rel="stylesheet" />
    <meta charset="utf-8">
    <title>SmartNET</title>
</head>


<div class="head-i">
    <img  src="image/head.jpg"  />
</div>

<form method="post" action="chLock">
    <select name="locale" >
        <option  value="en" >en</option>
        <option  value="ru" >ru</option>
    </select>
    <input type="submit" value="ok" >
</form>



<div class="side-i">
    <img src="image/log3.png">
</div>



<div class="aut">
    <form class="decor" method="post" action="menu">
        <div class="form-left-decoration"></div>
        <div class="form-right-decoration"></div>
        <div class="circle"></div>
        <div class="form-inner">
            <input type="text" name="login" placeholder = <fmt:message key="login.log"/>>
            <input type="password" name="pass" placeholder = <fmt:message key="login.password"/>>
            <input type="submit" value=<fmt:message key="login.enter"/>>
        </div>
    </form>
</div>


</body>
</html>
