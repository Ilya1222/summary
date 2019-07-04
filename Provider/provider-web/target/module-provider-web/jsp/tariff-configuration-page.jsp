<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title><fmt:message key="title.admin"/></title>
    <link href="style/tariff-configs.css" rel="stylesheet">
</head>
<body>

<p><img src="image/head.jpg"></p>

<div class="fre">

    <h2><fmt:message key="tariff.config.label"/></h2>

    <div class="messs"> <p>${sessionScope.nullTariff}</p></div>

    <form method="post" action="con">
        <p><fmt:message key="tariff.config.name"/></p>
        <label><input type="text" name="tarn" size="30"></label>
        <p><fmt:message key="tariff.config.spec"/></p>
        <label><textarea rows="5"  name="spec" size="30"></textarea></label>
        <p><fmt:message key="tariff.config.price"/></p>
        <label><input type="text" name="price" size="30"></label>
        <p><fmt:message key="tariff.config.service"/></p>
        <label><select name="service" >
            <option  value="Internet" >Internet</option>
            <option  value="Phone" >Phone</option>
            <option  value="TV" >TV</option>
            <option  value="IP-TV" >IP-TV</option>
        </select></label>
        <p><input type="submit" value= <fmt:message key="tariff.config.add"/> ></p>
    </form>
</div>

<div class="back1"> <p> <a href="menu" ><fmt:message key="admin.main"/></a></p></div>

<div class="tb">
    <table border="1" width="70%" cellpadding="5">
        <tr>
            <th><fmt:message key="tariff.config.tariff"/></th>
            <th><fmt:message key="tariff.config.spec"/></th>
            <th><fmt:message key="tariff.config.price"/></th>
            <th><fmt:message key="tariff.config.configs"/></th>
        </tr>
        <c:forEach  var="tariff" items="${requestScope.tariffs}">
            <tr>

                <td><c:out value="${tariff.name}"/></td>
                <td><c:out value="${tariff.specification}"/></td>
                <td><c:out value="${tariff.price}"/></td>

                <td>
                    <form method="get" action="updTariff">
                        <input type="number" hidden name="id" value="${tariff.id}" />
                        <input type="submit" name="upd" value=<fmt:message key="admin.update"/> />
                    </form>

                    <form method="post" action="del">
                        <input type="number" hidden name="id" value="${tariff.id}" />
                        <input type="submit" name="del" value=<fmt:message key="tariff.config.delete"/> />
                    </form>
                </td>
            </tr>

        </c:forEach>
    </table>


</div>


</body>
</html>
