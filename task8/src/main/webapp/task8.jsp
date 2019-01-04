<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Task 8</title>
</head>

<body>
	<c:choose>
		<c:when test="${books.size() > 0}">
			<table border=1>
            	<tr>
            		<td>№</td>
            		<td>Name</td>
            		<td>Price</td>
            		<td>Author</td>
            	</tr>
            	<c:forEach var="book" items="${books}" varStatus="loop">
            		<tr>
            			<td>${loop.index+1}</td>
            			<td>${book.name}</td>
            			<td>${book.price}</td>
            			<td>${book.author}</td>
            		</tr>
            	</c:forEach>
            <table>
		</c:when>
		<c:otherwise>
			<p>No books found :(</p>
		</c:otherwise>
	</c:choose>
</body>

</html>