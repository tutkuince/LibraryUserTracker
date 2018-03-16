<%@page import="com.usertracker.model.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Library User Tracker App</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>Ege University Library</h2>
		</div>
	</div>

	<div id="container">

		<div id="content">

			<input type="button" value="Add User"
				onclick="window.location.href='add-user-form.jsp'; return false;"
				class="add-user-button" />

			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
				<c:forEach var="user" items="${userList }">

					<!-- Set up a link to update for each student -->
					<c:url var="updateLink" value="userservlet">
						<c:param name="command" value="LOAD" />
						<c:param name="userId" value="${user.id }" />
					</c:url>

					<!-- Set up a link to delete for each student -->
					<c:url var="deleteLink" value="userservlet">
						<c:param name="command" value="DELETE" />
						<c:param name="userId" value="${user.id }" />
					</c:url>
					<tr>
						<td>${user.firstName }</td>
						<td>${user.lastName }</td>
						<td>${user.email }</td>
						<td><a href="${updateLink }">UPDATE</a> | 
						<a href="${deleteLink }"
							onclick="if(!(confirm('Are you sure you want to delete this student?'))) return false;">DELETE</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>