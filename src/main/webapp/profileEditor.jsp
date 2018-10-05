<%--
  Created by IntelliJ IDEA.
  User: Анна
  Date: 19.06.2018
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>profileEditor</title>
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
    <div class="col-sm-12 col-sm-offset=8">
        <form style="width: 100%; height: 34px;" method="post" action="/ProfileEditor">
        <table class="table">

            <tbody>
            <tr>

                <td><fmt:message key="registration.login"/></td>
                <td>${sessionScope.user.login}</td>
                <td width="300px"><input  type="text" name="login"></td>

            </tr>

            <tr>

                <td><fmt:message key="registration.name"/></td>
                <td>${sessionScope.user.name}</td>
                <td width="300px"><input  type="text" name="name"></td>

            </tr>
            <tr>

                <td><fmt:message key="registration.surname"/></td>
                <td>${sessionScope.user.surname}</td>
                <td width="300px"><input  type="text" name="surname"></td>

            </tr>
            <tr>
                <td>Email</td>
                <td>${sessionScope.user.email}</td>
                <td width="300px"><input  type="text" name="email"></td>
            </tr>
            </tbody>
        </table>
            <button class="dropbtn" type="submit" href="profileEditor.jsp"><fmt:message key="Save"/></button>
        </form>
    </div>
</div>
</body>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</html>
