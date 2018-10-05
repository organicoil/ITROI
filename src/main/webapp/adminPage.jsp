<%--
  Created by IntelliJ IDEA.
  User: Анна
  Date: 19.06.2018
  Time: 19:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>--%>
<%@ taglib uri="/WEB-INF/tags/DateFormat.tld" prefix="df" %>

<html>
<head>
    <title>Admin page</title>
    <%@include file="jspf/head.jspf" %>
</head>
<%@include file="jspf/top_panel.jspf" %>
<body>
<br>
<div class="content">
    <br>
    <div class="row">
        <div class=" col-sm-12  col-sm-offset-8">
            <form method="get" action="/admin">
                <input type="text" name="login"
                <c:if test="${requestScope.login!=null}"> value="${requestScope.login}"</c:if>>
                <br>
                <button type="submit" style="background-color: #3d0066;color: white" class="btn btn-primary"><fmt:message key="search"/>
                </button>
            </form>
        </div>

        <div class="col-sm-9 col-sm-offset-3 col-md-8 col-md-offset-2 main">
            <ul class="nav nav-pills">
                <li class="nav-item">
                    <a class="nav-link active" style="background-color: #3d0066;color: white" href="createMagazine.jsp"><fmt:message key="admin_create"/></a>
                </li>

            </ul>
        </div>
    </div>

    <c class="col-sm-9 col-sm-offset-3 col-md-8 col-md-offset-2 main">

        <h2>Users </h2>
        <table class="table table-striped">
            <thead>
            <tr>
                <th style="width: 25%"><fmt:message key="registration.Login"/></th>
                <th style="width: 25%"><fmt:message key="registration.name"/></th>
                <th style="width: 25%"><fmt:message key="registration.surname"/></th>
                <th style="width: 25%"><fmt:message key="registration.email"/></th>

            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.users}" var="user">
                <tr>

                    <td>${user.login}</td>
                    <td>${user.name}</td>
                    <td>${user.surname}</td>
                    <td>${user.email}</td>
                    <td>
                        <form method="post" action="/admin">
                            <input type="hidden" name="id" value="${user.id}">
                            <c:if test="${!user.ban}">
                                <input type="hidden" name="banUser" value="1">
                                <button type="submit" class="btn btn-danger btn-sm">
                                    <span class="glyphicon glyphicon-remove"></span>
                                </button>
                            </c:if>

                            <c:if test="${user.ban}">
                                <input type="hidden" name="banUser" value="0">
                                <button type="submit" class="btn btn-primary btn-sm">
                                    <span class="glyphicon glyphicon-repeat"></span>
                                </button>
                            </c:if>
                        </form>
                    </td>

                </tr>

            </c:forEach>
            </tbody>
        </table>
        <br>
        <br>

        <c:if test="${requestScope.magazines != null || requestScope.orders != null}">

        <table style="left:25px; padding-top: 220px; position: absolute;  width:20%"
               class="table table-hover table-condensed">
            <thead>
            <tr>
                <th style="width:10%;text-align: center"><fmt:message key="magazine"/></th>
                <th style="width:2%"><fmt:message key="adminQuantityInMonth"/></th>
                    <%--<th style="width:35%" class="text-center">Description</th>--%>

            </tr>
            </thead>
            <tbody>

            <c:forEach items="${requestScope.magazines}" var="item">
                <tr>
                    <td style="text-align: center">
                        <img
                                <c:if test="${!empty item.imgInBase64}">src="data:image/png;base64,${item.imgInBase64}"
                                style="width: 170px; height: auto"
                                style="width: 170px; height: auto"</c:if>
                                <c:if test="${empty item.imgInBase64}">src="images/placeholder.png"
                                style="width: 170px; height: auto"</c:if>/>


                        <h4 class="nomargin">${item.name}</h4></td>
                    <td style="text-align: center"><p>${item.quantityInMounth}</p></td>
                        <%--<td><p>${item.description}</p></td>--%>
                </tr>
            </c:forEach>

            </tbody>

        </table>

        <table class="table table-hover table-condensed"
               style=" padding-top: 70px; position:absolute; right:40px; width:70%">
            <thead>
            <tr>
                <th style="width:10%" class="text-center"><fmt:message key="Period"/></th>
                <th style="width:10%" class="text-center"><fmt:message key="Price"/></th>
                <th style="width:10%" class="text-center"><fmt:message key="Date"/></th>
                <th style="width:10%" class="text-center"><fmt:message key="admin_Action"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.orders}" var="order">
                <tr>
                    <td style="text-align: center" height="275"> <df:format month="${order.firstMounth}" date="${order.date}"/> -
                        <df:format month="${order.secondMounth}" date="${order.date}"/></td>


                    <td style="text-align: center" height="275">${order.price} UAH</td>
                    <td style="text-align: center" height="275">${order.date} </td>


                    <td>

                        <c:if test="${order.status=='IN_PROGRESS'}">

                            <form action="/SetOrderStatus" method="post">
                                <input type="hidden" name="id_order" value="${order.id}">
                                <input type="hidden" name="status" value="DENIED">
                                <button type="submit" class="btn btn-default"
                                        style="background-color: red;color: white"><fmt:message key="admin_denied"/>
                                </button>
                            </form>

                            <br>
                            <br>
                            <br>
                            <form action="/SetOrderStatus" method="post">
                                <input type="hidden" name="id_order" value="${order.id}">
                                <input type="hidden" name="status" value="EXECUTED">
                                <button type="submit" class="btn btn-default"
                                        style="background-color: green;color: white"><fmt:message key="admin_executed"/>
                                </button>
                            </form>


                        </c:if>

                        <c:if test="${order.status=='EXECUTED'}">
                            <label style="color: green"><fmt:message key="admin_executed"/> </label>
                        </c:if>
                        <c:if test="${order.status=='DENIED'}">
                            <label style="color: red"><fmt:message key="admin_denied"/> </label>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
        </c:if>
</div>

</body>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</html>
