

<%--
  Created by IntelliJ IDEA.
  User: Анна
  Date: 17.06.2018
  Time: 12:22
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/tags/DateFormat.tld" prefix="df" %>
<html>
<head>
    <title>Profile</title>
    <%@include file="jspf/head.jspf" %>
</head>
<body style="padding-top: 20px">
<%@include file="jspf/top_panel.jspf" %>
<div class="container">
    <br>
    <br>
    <br>
    <br>
    <br>
<div class="col-sm-8 col-sm-offset=8">

    <table class="table">

        <tbody>
        <tr>

            <td><fmt:message key="registration.signIn"/></td>
            <td>${sessionScope.user.login}</td>

        </tr>
        <tr>

            <td><fmt:message key="registration.name"/></td>
            <td>${sessionScope.user.name}</td>

        </tr>
        <tr>

            <td><fmt:message key="registration.surname"/></td>
            <td>${sessionScope.user.surname}</td>

        </tr>
        <tr>

            <td><fmt:message key="registration.email"/></td>
            <td>${sessionScope.user.email}</td>

        </tr>
        </tbody>
    </table>

</div>
<div class="row">

<br>

        <a class="dropbtn"  style="text-decoration: none; color: white;" href="/profileEditor.jsp"><fmt:message key="index.edit"/></a>
</div>

        <br>
        <br>

        <table class="table">
            <tr>
                <td height="20%"><fmt:message key="Wallet"/>:</td>
                <td height="20%">${sessionScope.user.walletBalance}</td>



        <br>

        </tr>
        <%--<c:forEach items="${sessionScope.orders}" var="order">--%>
            <%--<tr>--%>

                <%--<td>${order.date}</td>--%>
                <%--<td>${order.price}</td>--%>

            <%--</tr>--%>
        <%--</c:forEach>--%>
    </table>
    <td height="20%">  <a class="dropbtn"  style="height:20% ; text-decoration: none;color: white" href="editWallet.jsp"><fmt:message key="Wallet"/></a></td>
</div>
<br>
<br>
<br>

<table style="left:75px; padding-top: 70px; position: absolute;  width:60%" class="table table-hover table-condensed">
    <thead>
    <tr>
        <th style="width:10%;text-align: center"><fmt:message key="magazine"/></th>
        <th style="width:4%"><fmt:message key="adminQuantityInMonth"/></th>
        <th style="width:30%" class="text-center"><fmt:message key="description"/></th>

    </tr>
    </thead>
    <tbody>

    <c:forEach items="${sessionScope.magazines}" var="item">
        <tr>
            <td style="text-align: center">
                <img
                        <c:if test="${!empty item.imgInBase64}">src="data:image/png;base64,${item.imgInBase64}" style="width: 170px; height: auto"
                        style="width: 170px; height: auto"</c:if>
                        <c:if test="${empty item.imgInBase64}">src="images/placeholder.png" style="width: 170px; height: auto"</c:if>/>


                <h4 class="nomargin">${item.name}</h4></td>
            <td><p>${item.quantityInMounth}</p></td>
            <td><p>${item.description}</p></td>
        </tr>
    </c:forEach>

    </tbody>

</table>

<table class="table table-hover table-condensed" style=" padding-top: 70px; position:absolute;  right:40px; width:30%">
    <thead>
    <th style="width:5%" class="text-center"><fmt:message key="Period"/></th>
    <th style="width:5%" class="text-center"><fmt:message key="Price"/></th>
    <th style="width:5%" class="text-center"><fmt:message key="Date"/></th>
    <tbody>
    <tr>
        <c:forEach items="${requestScope.orders2}" var="order">
        <td height="270"> <df:format month="${order.firstMounth}" date="${order.date}"/> -
            <df:format month="${order.secondMounth}" date="${order.date}"/> </td>


        <td height="270">${order.price} UAH</td>
            <td height="270">${order.date} </td>

    </tr>


    </c:forEach>
    </tbody>

</table>
</body>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</html>
