<%--
  Created by IntelliJ IDEA.
  User: Анна
  Date: 13.06.2018
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="myLib" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Your Publisher</title>
    <%@include file="jspf/head.jspf" %>
    <link rel="stylesheet" href="css/jquery-ui.css">

    <script src="js/jquery.min.js"></script>
    <script src="js/jquery-ui.js"></script>
</head>
<body>
<%@include file="jspf/top_panel.jspf" %>
<br>
<br>
<div class="container">
    <div class="row" style="padding-top: 50px">
        <p>
            <label for="amount"><fmt:message key="subscribe"/>:</label>
            <input type="text" id="amount" readonly style="border:0; color:#420080; font-weight:bold;">
        </p>
        <div id="slider-range"></div>
        <br>
        <br>
        <label for="amount"><fmt:message key="cart.price"/>: <span id="pricePerMounth">${magazine.price}</span> UAH</label>
        <br>
        <label for="amount"><fmt:message key="QuantityInMonth"/>: <span id="quantity">${magazine.quantityInMounth}</span></label>
        <br>
        <label href="#"> <fmt:message key="totalPrice"/>: <span name="totalPrice" id="totalPrice"></span> UAH </label>

        <br>
        <br>
        <table>

            <tr>
                <td>
                    <img
                            <c:if test="${!empty magazine.imgInBase64}">src="data:image/png;base64,${magazine.imgInBase64}"
                            </c:if>
                            <c:if test="${empty item.imgInBase64}">src="images/placeholder.png"
                            </c:if>src="data:image/png;base64" alt="Картинка отстутствует" width="200" height="auto">
                </td>
                <td valign="top">
                    <br>
                    ${magazine.description}
                </td>
            </tr>
            <tr>
                <td align="center">
                    ${magazine.name}
                </td>
            </tr>

        </table>
        <form method="post" id="form" action="/addToCart">
            <input type="hidden" name="id" value="${magazine.id}">
            <input type="hidden" name="min_mounth" id="min_mounth">
            <input type="hidden" name="max_mounth" id="max_mounth">
            <input type="hidden" name="min_year" id="min_year">
            <input type="hidden" name="max_year" id="max_year">

            <div class="row">
                <c:choose>
                    <c:when test="${empty user}">
                        <div class="col-sm-12 col-sm-offset-1">
                            <br>
                            <label style="color: red; font-size: medium"><fmt:message key="err"/>
                            </label>
                        </div>
                    </c:when>


                    <c:when test="${!empty sessionScope.user && user.role == 'REGISTR_CLIENT'}">
                        <button type="submit" id="send-order" style="background-color: #3d0066; width: 210px"
                                class="btn btn-success">
                            <fmt:message key="subscribe"/>
                        </button>

                    </c:when>
                </c:choose>
            </div>
        </form>
    </div>
</div>

</body>
<script src="js/range.js?3e=frhy"></script>
<script src="js/bootstrap.min.js"></script>
</html>
