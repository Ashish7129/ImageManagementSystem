<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<p><%=session.getAttribute("currentUser") %></p>
	<%	
		session.removeAttribute("currentUser");
		session.removeAttribute("totalSize");
		session.removeAttribute("ïmageList");
		session.invalidate();
		response.sendRedirect("LoginPage.jsp");
	%>
</body>
</html>