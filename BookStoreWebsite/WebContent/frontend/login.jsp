<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Login</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div class="center">
		<h2>Customer Login</h2>

		<c:if test="${message != null}">
			<div class="center">
				<h4 class="message">${message}</h4>
			</div>
		</c:if>

		<form id="loginForm" action="login" method="post">
			<table align="center" class="login-form-panel">
				<tr>
					<td class="label">Email:</td>
					<td>
						<div class="field">
							<input type="text" name="email" id="email" size="20">
						</div>
					</td>
				</tr>
				<tr>

					<td class="label">Password:</td>
					<td>
						<div class="field">
							<input type="password" name="password" id="password" size="20">
						</div>

					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button type="submit">Login</button>
					</td>
				</tr>
			</table>
		</form>
	</div>

	<jsp:directive.include file="footer.jsp" />

	<script type="text/javascript">
		$(document).ready(function() {
			$("#loginForm").validate({
				rules : {
					email : {
						required : true,
						email : true
					},

					password : "required",
				},

				messages : {
					email : {
						required : "Please enter email",
						email : "Please enter an valid email address"
					},

					password : "Please enter password"
				}
			});

		});
	</script>
</body>
</html>