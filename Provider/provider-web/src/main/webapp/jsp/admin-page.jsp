<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title><fmt:message key="title.admin"/></title>
    <meta charset="utf-8">
    <link href="style/admin-menu.css" rel="stylesheet" />
</head>
<body>
<img src="image/head.jpg"/>
<h2><fmt:message key="title.admin"/>: ${fullName}</h2>

<div class="top-menu">
    <a href='reg'><h3><fmt:message key="admin.registration"/> </h3></a>
    <p></p>
    <a href='statistic'><h3>Statistic</h3></a>
    <p></p>
    <a href='con'><h3><fmt:message key="admin.config"/> </h3></a>
    <p></p>
    <a href='block'><h3><fmt:message key="admin.block"/> </h3></a>
    <p></p>
    <a href='logout'><h3><fmt:message key="admin.logout"/> </h3></a>
</div>

<div class="user-list">

     <table border="2" width="60%" cellpadding="5">
         <tr>
             <th><fmt:message key="admin.uslist.login"/></th>
             <th><fmt:message key="admin.uslist.name"/></th>
             <th><fmt:message key="admin.uslist.tariff"/></th>
             <th><fmt:message key="admin.uslist.date"/></th>
         </tr>
         <c:forEach var="info" items="${infoList}">
         <tr>
             <td><p>${info.user.login}</p></td>
             <td><p>${info.user.firstName} ${info.user.surName}</p></td>
             <td><p>${info.tariffName}</p></td>
             <td><p>${info.period}</p></td>
         </tr>
         </c:forEach>
     </table>


</div>

</body>
</html>

