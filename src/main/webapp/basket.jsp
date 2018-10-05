
<%--
  Created by IntelliJ IDEA.
  User: Анна
  Date: 17.06.2018
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/tags/DateFormat.tld" prefix="df" %>
<html>
<head>
    <title>Cart</title>
    <%@include file="jspf/head.jspf" %>
</head>
<div>
    <br>
    <br>
    <%@include file="jspf/top_panel.jspf" %>

    <div class="container" style="padding-top: 70px">

        <table id="cart" style="left:75px; padding-top: 70px; position: absolute;  width:67%" class="table table-hover table-condensed">
            <thead>
            <tr>
                <th style="width:15%;text-align: center"><fmt:message key="magazine"/></th>
                <th style="width:10%"><fmt:message key="QuantityInMonth"/></th>
                <th style="width:40%" class="text-center"><fmt:message key="description"/></th>
                <%--<th style="width:5%" class="text-center">Period</th>--%>
                <%--<th style="width:10%" class="text-center">Price</th>--%>

            </tr>
            </thead>
            <tbody>

            <c:forEach items="${magazines}" var="item">
                <tr>
                    <td style="text-align: center">
                        <img
                                <c:if test="${!empty item.imgInBase64}">src="data:image/png;base64,${item.imgInBase64}"
                                style="width: 170px; height: auto"</c:if>
                                <c:if test="${empty item.imgInBase64}">src="images/placeholder.png" style="width: 170px; height: auto"</c:if>/>


                        <h4 class="nomargin">${item.name}</h4></td>
                    <td><p>${item.quantityInMounth}</p></td>
                    <td><p>${item.description}</p></td>
                </tr>
            </c:forEach>

            </tbody>
            <tfoot>
            <tr>
                <td><a href="/MainPage"  style="background-color: #3d0066;color: white" class="btn btn-success btn-block" ><i class="fa fa-angle-left"></i><fmt:message key="back"/></a></td>
                <td width="5px" colspan="2" class="hidden-xs"><a  class="btn btn-success btn-block" href="clearCart" style="background-color: #3d0066;color: white"><i
                        class="fa fa-angle-left"></i><fmt:message key="cart.clearcart"/></a></td>

            </tr>
            </tfoot>


        </table>

        <table class="table table-hover table-condensed" style=" padding-top: 70px; position:absolute;  right:40px; width:25%">
            <thead>
            <th style="width:6%" class="text-center">Period</th>
            <th style="width:8%" class="text-center">Price</th>
            <tbody>
            <tr>
                <c:forEach items="${sessionScope.orders}" var="order">
                <td height="279">  <df:format month="${order.firstMounth}"/> -
                    <df:format month="${order.secondMounth}"/></td>


                <td height="279">${order.price} UAH</td>

            </tr>


            </c:forEach>
            </tbody>
            <tfoot>
            <tr>

                <td><a href="/createOrder" style="background-color: #3d0066;color: white" class="btn btn-success btn-block"><fmt:message key="cart.create"/> <i
                        class="fa fa-angle-right"></i></a></td>
            </tr>

            <tr><td> <c:if test="${!empty err2}">
                <div class="alert alert-danger">
                        ${err2}
                </div>
            </c:if>
            </td></tr>
            </tfoot>
        </table>

    </div>

</div>
</div>
</body>

<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</html>
