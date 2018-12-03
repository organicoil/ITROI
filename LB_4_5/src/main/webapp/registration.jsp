<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>--%>

<%--
  Created by IntelliJ IDEA.
  User: Анна
  Date: 10.06.2018
  Time: 19:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head lang="${language}">
    <title>Регистрация</title>
    <%@include file="jspf/head.jspf" %>
    <link href="css/form.css" rel="stylesheet" type="text/css">
    <%--<link href="css/validation.css" rel="stylesheet" type="text/css">--%>

</head>
<link href="css/validation.css" rel="stylesheet" type="text/css">
<%@include file="jspf/top_panel.jspf" %>
<br>

<div class="row">

    <div class="container-fluid">
        <div class="row">
            <div class=" col-sm-4 col-sm-offset-4">


                <form style="width: 100%; height: 34px;" method="post" action="/Registration" >
                    <c:if test="${!empty err}">
                        <div class="alert alert-danger">
                                ${err}
                        </div>
                    </c:if>
                    <span style=" color: #BA0000; font-family: Verdana; font-size: 16px; font-weight: bold;"> *</span><label
                        class="L"><fmt:message key="necessarily"/></label>

                    <div class="panel-body registerPanel">
                        <div class="form-group">
                            <label class="control-label requiredField"><fmt:message key="registration.name"/>:<span
                                    style="color: #BA0000; font-family: Verdana; font-size: 16px; font-weight: bold;"> *</span></label>
                            <input type="text" pattern="^[а-яА-ЯёЁіІїЇa-zA-Z]{1,12}"
                                   placeholder="Имя должно быть не больше 15 символов" required
                                   class="form-control"
                                   name="name">
                        </div>
                        <div class="form-group">
                            <label class="control-label requiredField"><fmt:message key="registration.surname"/>:<span
                                    style="color: #BA0000; font-family: Verdana; font-size: 16px; font-weight: bold;"> *</span></label>
                            <input type="text" pattern="^[а-яА-ЯёЁіІїЇa-zA-Z]{1,15}"
                                   placeholder="Фамилия должна быть не больше 15 символов" required
                                   class="form-control"
                                   name="surname">
                        </div>
                        <div class="form-group">
                            <label class="control-label requiredField">Email:<span
                                    style="color: #BA0000; font-family: Verdana; font-size: 16px; font-weight: bold;"> *</span></label>
                            <input type="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}"
                                   placeholder="Email должен содержать от 5 до 12 символов" required
                                   class="form-control"
                                   name="email">
                        </div>
                        <div class="form-group">
                            <label class="control-label requiredField" for="login"><fmt:message key="registration.signIn"/>:<span
                                    style="color: #BA0000; font-family: Verdana; font-size: 16px; font-weight: bold;"> *</span></label>
                            <input type="text" id="login" pattern="^[а-яА-ЯёЁіІїЇa-zA-Z0-9]{5,17}"
                                   placeholder="Логин должен содержать от 5 до 17 символов" required
                                   class="form-control"
                                   name="login">
                        </div>
                        <div class="form-group">
                            <label><fmt:message key="registration.password"/>:<span
                                    style="color: #BA0000; font-family: Verdana; font-size: 16px; font-weight: bold;"> *</span></label>
                            <input type="password" pattern="^[а-яА-ЯёЁіІїЇa-zA-Z0-9]{5,10}"
                                   placeholder="Пароль должен содержать от 5 до 10 символов" required
                                   class="form-control"
                                   name="password">
                        </div>
                        <div class="form-group">
                            <label class="control-label requiredField"><fmt:message key="registration.confirmPassword"/>:<span
                                    style="color: #BA0000; font-family: Verdana; font-size: 16px; font-weight: bold;"> *</span></label>
                            <input type="password" pattern="^[а-яА-ЯёЁіІїЇa-zA-Z0-9]{5,10}"
                                   placeholder="Пароль должен содержать от 5 до 10 символов" required
                                   class="form-control"
                                   name="password_confirmation">
                        </div>
                        <div class="panel-footer" style="background-color: #3d0066">
                            <button type="submit" id="submit" class="btn btn-default" onclick="validateForm()"

                                    style="background-color: #3d0066;color: white"><fmt:message key="registration.SignUp"/>
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
<script src="js/validation.js"></script>
</html>
