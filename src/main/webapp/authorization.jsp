<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>--%>
<%--
  Created by IntelliJ IDEA.
  User: Анна
  Date: 12.06.2018
  Time: 21:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Авторизация</title>
    <%@include file="jspf/head.jspf" %>
    <link href="css/form.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@include file="jspf/top_panel.jspf" %>


<br>

<div class="row">


        <div class="row">
            <div class=" col-sm-4 col-sm-offset-4">


                <form style="width: 100%; height: 34px;"  method="get" action="/Authorization" >
                    <c:if test="${!empty err}">
                        <div class="alert alert-danger">
                                ${err}
                        </div>
                    </c:if>
<br>
                    <div class="panel-body">

                        <div class="form-group">
                            <label class="control-label requiredField"><fmt:message key="registration.signIn"/>:</label>
                            <input type="text"
                                   required
                                   class="form-control"
                                   name="login">
                        </div>
                        <div class="form-group">
                            <label class="control-label requiredField"><fmt:message key="registration.password"/>:</label>
                            <input type="password"
                                   required
                                   class="form-control"
                                   name="password">
                        </div>

                        <div class="panel-footer" style="background-color: #3d0066">
                            <button type="submit" id="submit" class="btn btn-default" onclick="validateForm()"
                                    style="background-color: #3d0066;color: white"><fmt:message key="registration.Login"/>
                            </button>
                        </div>
                    </div>

                </form>

            </div>
        </div>
    </div>

</div>
</body>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</html>
