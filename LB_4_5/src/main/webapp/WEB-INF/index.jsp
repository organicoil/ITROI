<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

    <title>Your Publisher</title>
    <%@include file="../jspf/head.jspf" %>
</head>
<body>



<%@include file="../jspf/top_panel.jspf" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n"/>
<div class="container">
    <div class="row" style="padding-top: 50px">

        <div class=" col-sm-10  col-sm-offset-1">
            <img src="../images/news.png" width="5%"><img src="../images/logo.png">
        </div>
        <div class=" col-sm-10  col-sm-offset-8">
            <form action="/MainPage" method="get">
                <input  name="search" type="text" placeholder=<fmt:message key="index.searchByName"/>>
                <button class="search"  type="submit"></button>
            </form>
        </div>
    </div>

    <br>
    <hr>
    <br>

    <form id="form" action="/MainPage" method="get">
        <input id='namesort' name="nameSort" value='${namesort}' style="display: none">
        <input id='pricesort' name="priceSort" value='${pricesort}' style="display: none">
        <input id='themesort' name="themeSort" value='${themesort}' style="display: none">
        <input id='all' name="all" value='${all}' style="display: none">
    </form>



        <div class=" col-sm-20  col-sm-offset-1">
            <div id="drop" class="dropdown">
                <button class="dropbtn"><fmt:message key="index.ByName"/></button>
                <div class="dropdown-content">
                    <a onclick="setNameAndSubmit('asc')" href="#"><fmt:message key="index.fromZtoA"/></a>
                    <a onclick="setNameAndSubmit('desc')" href="#"><fmt:message key="index.fromAtoZ"/></a>
                </div>
            </div>

            <div id="drop2" class="dropdown">
                <button class="dropbtn"><fmt:message key="index.searchByCategory"/></button>
                <div class="dropdown-content">
                    <a onclick="setThemeAndSubmit('Мода')" href="#"><fmt:message key="index.fashion"/></a>
                    <a onclick="setThemeAndSubmit('Бизнес')" href="#"><fmt:message key="index.business"/></a>
                    <a onclick="setThemeAndSubmit('Досуг')" href="#"><fmt:message key="index.by-time"/></a>
                    <a onclick="setThemeAndSubmit('Спорт')" href="#"><fmt:message key="index.sport"/></a>
                    <a onclick="setThemeAndSubmit('Политика')" href="#"><fmt:message key="index.politics"/></a>
                    <a onclick="setThemeAndSubmit('Путешествия')" href="#"><fmt:message key="index.travels"/></a>
                    <a onclick="setThemeAndSubmit('Кулинария')" href="#"><fmt:message key="index.cooking"/></a>
                    <a onclick="setThemeAndSubmit('Торговля')" href="#"><fmt:message key="index.trade"/></a>
                </div>
            </div>
            <div id="drop3" class="dropdown">
                <button class="dropbtn"><fmt:message key="index.byPrice"/></button>
                <div class="dropdown-content">
                    <a onclick="setPriceAndSubmit('asc')" href="#"><fmt:message key="index.ascending"/></a>
                    <a onclick="setPriceAndSubmit('desc')" href="#"><fmt:message key="index.descending"/></a>
                </div>
            </div>

            <div id="drop4" class="dropdown">
                <button class="dropbtn" onclick="setAll('all')" href="#"><fmt:message key="index.all"/></button>
            </div>
        </div>

    <br>
    <div class=" col-sm-12  col-sm-offset-1">
        <c:forEach items="${requestScope.magazines}" var="item">
            <c:choose>
                <c:when test="${!empty sessionScope.user && user.role == 'ADMIN'}">
                    <div class="gallery">
                        <a href="/MagazineServlet?id=${item.id}">
                            <img <c:if test="${!empty item.imgInBase64}">src="data:image/png;base64,${item.imgInBase64}" style="width: 175px;height: 250px"
                                 </c:if> <c:if test="${empty item.imgInBase64}">src="../images/placeholder.png" style="width: 175px;height: 250px"</c:if>
                                 alt="">
                        </a>
                        <div class="desc">${item.name}</div>
                        <a style="text-decoration: none; color:#3d0066 " href="/EditMagazine?id=${item.id}"><fmt:message key="index.edit"/></a>
                        <a style="text-decoration: none; color:#3d0066 " href="/DeleteMagazine?id=${item.id}"><fmt:message key="index.delete"/></a>
                    </div>
                </c:when>
                <c:otherwise> <div class="gallery">
                    <a href="/MagazineServlet?id=${item.id}">
                        <img <c:if test="${!empty item.imgInBase64}" >src="data:image/png;base64,${item.imgInBase64}" style="width: 175px;height: 250px"
                             </c:if><c:if test="${empty item.imgInBase64}">src="../images/placeholder.png" </c:if>src="data:image/png;base64" alt="no image available" style="width: 175px;height: 250px">
                    </a>
                    <div class="desc">${item.name}</div>

                </div></c:otherwise>
            </c:choose>

        </c:forEach>
    </div>

</div>

</div>

</body>

<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/sort.js?fcdcs=cdsc"></script>

</html>