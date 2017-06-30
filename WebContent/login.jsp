<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-student-style.css">	
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>LDCE Library System</h2>
		</div>
	</div>
	
	<div id="container">
	<h3>Login </h3>
	<form action="loginController" method="post">
	<table>
				<tbody>
					<tr>
						<td><Strong>Enter Email :</Strong></td>
						<td><input type="text" name="em"><br><br>
						</td>
					</tr>
					<tr>
						<td><Strong>Enter Password:</Strong></td>
						<td><input type="password" name="pw"><br><br></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Submit" class="save" /></td>
					</tr>
				</tbody>
			</table>
	</form> 
	</div>
</body>
</html>