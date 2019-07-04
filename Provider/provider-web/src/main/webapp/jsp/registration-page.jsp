
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title><fmt:message key="title.admin"/></title>
    <link href="style/registration.css" rel="stylesheet" />
</head>
<body>

<p><img src="image/head.jpg"></p>

<div class="fr">


<h2><fmt:message key="registration.label"/> </h2>

    <div class="mes">
        <p>${requestScope.messageErr}</p>
    </div>

<form method="post" action="reg">
    <p><fmt:message key="registration.login"/> :</p>
    <label><input type="text" name="login" size="30" placeholder =<fmt:message key="placeholder.registration.login"/> ></label>
    <p><fmt:message key="registration.pass"/> :</p>
    <label><input type="text" name="pass" size="30" placeholder=<fmt:message key="placeholder.registration.pass"/>></label>
    <p><fmt:message key="registration.name"/> :</p>
    <label><input type="text" name="name" size="30" placeholder=<fmt:message key="placeholder.registration.name"/>></label>
    <p><fmt:message key="registration.sur"/> :</p>
    <label><input type="text" name="sur" size="30"placeholder= <fmt:message key="placeholder.registration.sur"/>></label>
    <p><fmt:message key="registration.last"/> :</p>
    <label><input type="text" name="last" size="30"  placeholder=<fmt:message key="placeholder.registration.last"/>></label>
    <p><fmt:message key="registration.email"/> :</p>
    <label><input type="text" name="email" size="30" placeholder=<fmt:message key="placeholder.registration.ema"/>></label>
    <p><fmt:message key="registration.phone"/> :</p>
    <label><input type="text" name="numb" size="30" placeholder=<fmt:message key="placeholder.registration.number"/>></label>
    <p><input type="submit" value=<fmt:message key="registration.ok"/> ></p>
</form>
</div>



<div class="back">
    <p><a href="menu" ><fmt:message key="admin.main"/></a></p>
</div>


<div class="usAll">
    <h2 class="h"><fmt:message key="registration.label2"/></h2><br/>


    <table border="2">

        <tr>
            <th><fmt:message key="registration.login"/></th>
            <th><fmt:message key="registration.name"/></th>
            <th><fmt:message key="registration.email"/></th>
            <th><fmt:message key="registration.phone"/></th>
            <th><fmt:message key="admin.uslist.debt"/></th>
        </tr>


        <c:forEach var="user" items="${requestScope.users}">

            <tr>
                <td><p>${user.login}</p></td>
                <td><p>${user.firstName} ${user.lastName}</p></td>
                <td><p>${user.email}</p></td>
                <td><p>${user.phoneNumber}</p></td>
                <td><p>${user.blocked}</p></td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
