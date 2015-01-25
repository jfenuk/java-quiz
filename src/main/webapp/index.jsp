<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Java Quiz Page</title>
</head>
<body>
<c:url ></c:url>
	<c:url value="/addPerson" var="addURL"></c:url>
	<c:url value="/editPerson" var="editURL"></c:url>

	<%-- Edit Request --%>
	<c:if test="${requestScope.person ne null}">
		<form action='<c:out value="${editURL}"></c:out>' method="post">
			ID: <input type="text" value="${requestScope.person.id}"
				readonly="readonly" name="id"><br> Name: <input
				type="text" value="${requestScope.person.name}" name="name"><br>
			Country: <input type="text" value="${requestScope.person.country}"
				name="country"><br> <input type="submit"
				value="Edit Person">
		</form>
	</c:if>

	<%-- Add Request --%>
	<c:if test="${requestScope.person eq null}">
		<form action='<c:out value="${addURL}"></c:out>' method="post">
			Name: <input type="text" name="name"><br> Country: <input
				type="text" name="country"><br> <input type="submit"
				value="Add Person">
		</form>
	</c:if>

	<%-- Persons List Logic --%>
	<c:if test="${not empty requestScope.persons}">
		<table>
			<tbody>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Country</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
				<c:forEach items="${requestScope.persons}" var="person">
					<c:url value="/editPerson" var="editURL">
						<c:param name="id" value="${person.id}"></c:param>
					</c:url>
					<c:url value="/deletePerson" var="deleteURL">
						<c:param name="id" value="${person.id}"></c:param>
					</c:url>
					<tr>
						<td><c:out value="${person.id}"></c:out></td>
						<td><c:out value="${person.name}"></c:out></td>
						<td><c:out value="${person.country}"></c:out></td>
						<td><a
							href='<c:out value="${editURL}" escapeXml="true"></c:out>'>Edit</a></td>
						<td><a
							href='<c:out value="${deleteURL}" escapeXml="true"></c:out>'>Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</body>
</html>