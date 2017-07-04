<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
						<form action="bookController" method="post">
						<input type="hidden" name="c" value="search" />	
						<strong>Search By </strong>&nbsp;&nbsp;&nbsp;&nbsp;
						<select name="search">
						<option value="name">Name</option>			
						<option value="isbn">ISBN</option>				
						</select> <br><br>
						<input type="text" name="param"><br><br>
						<input type="submit" value="Search Book">
						</form>
</body>
</html>





