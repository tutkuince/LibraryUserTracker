<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Update User</title>
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-student-style.css">
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>Ege University Library</h2>
		</div>
	</div>

	<div id="container">
		<h3>Update User</h3>

		<form action="userservlet" method="get">

			<input type="hidden" name="command" value="UPDATE" />
			
			<input type="hidden" name="userId" value="${THE_USER.id }" />

			<table>
				<tbody>
					<tr>
						<td><label>First Name</label></td>
						<td><input type="text" name="firstName" value="${THE_USER.firstName }" /></td>
					</tr>
					<tr>
						<td><label>Last Name</label></td>
						<td><input type="text" name="lastName" value="${THE_USER.lastName }" /></td>
					</tr>
					<tr>
						<td><label>Email</label></td>
						<td><input type="text" name="email" value="${THE_USER.email }" /></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
				</tbody>
			</table>

		</form>
		<div style="clear: both;"></div>
		<p>
			<a href="userservlet">Back to List</a>
		</p>
	</div>
</body>
</html>