
<%--
  Created by IntelliJ IDEA.
  User: Анна
  Date: 20.06.2018
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit magazine</title>
    <%@include file="jspf/head.jspf" %>
</head>
<%@include file="jspf/top_panel.jspf" %>
<body>
<br>
<br>
<br>
<div class="row">




    <div class=" col-sm-10 col-md-12 ">

        <form style="width: 100%; height: 34px;" class="form-horizontal" method="post" action="/EditMagazine">
            <fieldset>

                <div class="form-group">
                    <div class="col-md-4">
                        <input value="${sessionScope.magazine.id}"  name="id" type="hidden">
                    </div>
                </div>


                <div class="form-group">
                    <label class="col-md-4 control-label"><fmt:message key="new"/> <fmt:message key="registration.name"/> </label>
                    <div class="col-md-4">
                        <input type="text" class="form-control" name="name" value="${sessionScope.magazine.name}">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label"><fmt:message key="new"/> <fmt:message key="category"/> </label>
                    <div class="col-md-4">
                        <input type="text" class="form-control" name="theme" value="${sessionScope.magazine.theme}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label"><fmt:message key="new"/> <fmt:message key="QuantityInMonth"/> </label>
                    <div class="col-md-4">
                        <input type="number" class="form-control"
                                  name="quantityInMounth" value="${sessionScope.magazine.quantityInMounth}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label"><fmt:message key="new"/> <fmt:message key="Price"/> </label>
                    <div class="col-md-4">
                        <input type="number" class="form-control" name="price" value="${sessionScope.magazine.price}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label"><fmt:message key="new"/> <fmt:message key="description"/> </label>
                    <div class="col-md-4">
                        <textarea class="form-control"
                                  name="description">${sessionScope.magazine.description}</textarea>
                    </div>
                </div>
            </fieldset>
                <div class="form-group">
                    <label class="col-md-4 control-label"></label>
                    <div class="col-md-4">
                        <button type="submit" style="background-color: #3d0066;color: white " class="btn btn-primary">Save</button>
                    </div>
                </div>


        </form>


        <div class="col-lg-9 col-lg-offset-6">
        <form style="width: 100%; height: 34px;" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/AddImage" enctype="multipart/form-data">
            <fieldset>

                <div class="form-group">
                    <div class="col-md-4">
                        <input value="${sessionScope.magazine.id}"  name="id" type="hidden">
                    </div>
                </div>


                <!-- File Button -->
                <div class="form-group">
                    <label class="col-md-4 control-label"><fmt:message key="new"/> <fmt:message key="image"/> </label>
                    <div class="col-md-4">
                        <input type="file" style="background-color: white" name="file" />
                    </div>
                </div>

            </fieldset>
            <div class="form-group">
                <label class="col-md-4 control-label"></label>
                <div class="col-md-4">
                    <button type="submit" style="background-color: #3d0066;color: white " class="btn btn-primary"><fmt:message key="load"/> </button>
                </div>
            </div>
        </form>

    </div>
    </div>

</div>
</body>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</html>
