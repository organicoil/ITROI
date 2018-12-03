
<%--
  Created by IntelliJ IDEA.
  User: Анна
  Date: 19.06.2018
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create magazine</title>
    <%@include file="jspf/head.jspf" %>
</head>
<%@include file="jspf/top_panel.jspf" %>
<body>
<div class="container">

    <br>
    <br>
    <br>
    <div class="row">
        <div class=" col-sm-10 col-md-12 ">

            <form style="width: 100%; height: 34px;" class="form-horizontal" method="post" action="/createMagazine">

                <fieldset>

                    <div class="form-group">
                        <label class="col-md-4 control-label"><fmt:message key="registration.name"/> </label>
                        <div class="col-md-4">
                            <input name="name" class="form-control input-md"
                                   required="" type="text">

                        </div>
                    </div>


                    <div class="form-group">
                        <label class="col-md-4 control-label"><fmt:message key="category"/> </label>
                        <div class="col-md-4">
                            <input name="theme" class="form-control input-md"
                                   required="" type="text">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label"><fmt:message key="QuantityInMonth"/>  </label>
                        <div class="col-md-4">
                            <input name="quan"
                                   class="form-control input-md" required="" type="number">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label"><fmt:message key="Price"/>  </label>
                        <div class="col-md-4">
                            <input name="price"
                                   class="form-control input-md" required="" type="number">
                        </div>
                    </div>


                    <%--<div class="form-group">--%>
                        <%--<label class="col-md-4 control-label">Image</label>--%>
                        <%--<div class="col-md-4">--%>
                            <%--<input type="file" name="file"/>--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <div class="form-group">
                        <label class="col-md-4 control-label" ><fmt:message key="description"/> </label>
                        <div class="col-md-4">
                            <textarea class="form-control" name="alldesc" ></textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label"></label>
                        <div class="col-md-4">
                            <button type="submit" style="background-color: #3d0066;color: white " class="btn btn-primary"><fmt:message key="SaveMagazine"/> </button>
                        </div>
                    </div>

                </fieldset>
            </form>

        </div>
        <%--<div class="col-lg-9 col-lg-offset-7">--%>
            <%--<form style="width: 100%; height: 34px;" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/AddImage"  enctype="multipart/form-data">--%>

                <%--<div class="form-group">--%>
                    <%--<div class="col-md-4">--%>
                        <%--<input value="${sessionScope.magazine.id}"  name="id" type="hidden">--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--<div class="form-group">--%>
            <%--<label class="col-md-4 control-label">Image</label>--%>
            <%--<div class="col-md-4">--%>
            <%--<input type="file" style="background-color: white" name="file"/>--%>
            <%--</div>--%>
            <%--</div>--%>
                <%--<div class="form-group">--%>
                    <%--<label class="col-md-4 control-label"></label>--%>
                    <%--<div class="col-md-4">--%>
                        <%--<button type="submit" style="background-color: #3d0066;color: white " class="btn btn-primary">Save image</button>--%>
                    <%--</div>--%>
                <%--</div>--%>

            <%--</form>--%>
        <%--</div>--%>
    </div>
</div>

</body>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</html>
