<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" errorPage="error.jsp"%>
<%@include file="index.jsp"%>
<div class="container">
<div class="jumbotron">
	<h2>Login In</h2>
	<form action="Login" method="post">
		<div class="form-group">
			<label for="username">Username</label> <input type="text" name="username" class="form-control" required>
		</div>
		<div class="form-group">
			<label for="password">Password</label> 
			<input type="password" name="password" class="form-control" required>
		</div>
		<button type="submit" class="btn btn-default">Login</button>
		<a href=#>Forget Password</a>
	</form>
	<div class ="text-danger">
		<%
			if (Boolean.parseBoolean(request.getParameter("UserNotFound"))) {
				out.println(Constants.USER_NOT_MATCH);
			}
		%>
	</div>
	</div>
</div>
</body>
</html>