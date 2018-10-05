<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="jspf/head.jspf"%>
<body>
<%@include file="jspf/top_panel.jspf"%>
<div class="container">
    <div class="jumbotron">
        <div class="text-center"><i class="fa fa-5x fa-frown-o" style="color:#d9534f;"></i></div>
        <h1 class="text-center"><fmt:message key="InnerError"/></h1>
        <p class="text-center"><fmt:message key="PleaseTryLater"/></p>
    </div>
</div>

</body>
</html>
