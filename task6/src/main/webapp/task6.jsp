<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Task 6</title>
</head>

<body>
	<p>Click to see how PRG works</p>
	<form action="PRG" method="POST">
		<input type="submit">
	</form>
	<p>${prgMessage}</p>
</body>

</html>