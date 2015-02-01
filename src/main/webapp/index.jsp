<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>User</title>
</head>
<body>

	<form name="new_user" action="createNewUser" method="get">
		User Name: <input name="user_name" type="text" size="25"> <input
			type="submit"> <input type="reset" value="Clean">

	</form>



<c:if test="${user}">

<c:out value="${user.name}"></c:out>

</c:if>


</body>
</html>
