<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title><fmt:message key="title.client"/></title>
    <link href="style/tariff-page.css" rel="stylesheet" />
</head>
<body>

<p><img src="image/head.jpg"></p>

<p>${sort}</p>

<form action="listTar" method="post">

<select name="sort">
    <option  value="az" ><fmt:message key="tariff.list.by1"/> </option>
    <option  value="za" ><fmt:message key="tariff.list.by2"/></option>
    <option  value="pr" ><fmt:message key="tariff.list.by3"/></option>
</select>
    <input type="submit" name="ok" value=<fmt:message key="tariff.conn.ok"/>>
</form>


<div class="back4">
    <form  method="get"  action="menu" name="form"  >
        <input type="submit" value=<fmt:message key="admin.main"/>>
    </form>
</div>


<div class="allTariff">

<c:forEach var="service" items="${requestScope.services}">

    <h4>${service.name}</h4>

<table border="2" width="75%" cellpadding="5">
    <tr>
        <th><fmt:message key="tariff.config.tariff"/></th>
        <th><fmt:message key="tariff.config.spec"/></th>
        <th><fmt:message key="tariff.config.price"/></th>
        <th><fmt:message key="tariff.list.conn"/></th>
        <th><fmt:message key="tariff.list.download"/></th>
    </tr>
    
    <c:forEach var="tariff" items="${requestScope.tariffs}">
        <tr>
        <c:if test="${service.id == tariff.serviceId}">
            <td><p>${tariff.name}</p></td>
            <td><p>${tariff.specification}</p></td>
            <td><p>${tariff.price}</p></td>
            <td>
                <div class="take">
                <form method="post" action="connTar">
                    <input type="number" hidden name="id" value="${tariff.id}" />
                    <input type="submit" name="sel" value=<fmt:message key="tariff.list.select"/>>
                </form>
                </div>
            </td>
            <td>
                <div class="down">
                <form method="post" action="dwl">
                    <input type="number" hidden name="id" value="${tariff.id}" />
                    <input type="submit" name="sel" value="pdf"/>
                </form>
                </div>
            </td>
        </c:if>
        </tr>
      </c:forEach>

</table>
</c:forEach>


</div>
</body>
</html>