<%--
  Created by IntelliJ IDEA.
  User: Анна
  Date: 19.06.2018
  Time: 20:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Wallet</title>
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

        <form style="width: 100%; height: 34px;" method="post" action="/editWallet">
            <table class="table">

                <tbody>
                <tr>

                    <td><fmt:message key="Balance"/></td>
                    <td>${user.walletBalance}</td>
                </tr>
                <tr>
                    <td>Add</td>
                    <td width="300px"><input type="number" name="amount"></td>
                </tr>
                </tbody>
            </table>
            <input type=submit style="background-color: #3d0066;color: white" value=" <fmt:message key="add"/>"/>
        </form>
    </div>
</div>
</body>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</html>
