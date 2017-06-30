<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*" %>
<%@ page import="controller.DAO.publisher" %>
<%@ page import="controller.publisherDTO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<%

publisherDTO pubgot=(new publisherDTO());

List <?> got= pubgot.getPublisher();
//out.print(got.get(0).toString());
pageContext.setAttribute("got", got);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

						<form action="bookController">
						<input type="hidden" name="c" value="ADD" />	
						Enter Book Name<input type="text" name="name1"><br><br>
						Enter ISBN<input type="text" name="isbn" value="Enter 10 Digit ISBN"><br><br>
						Enter Price<input type="text" name="price" value="Enter price"><br><br>
						<c:if test="${not empty got}">
						Select Publisher:
						<select name="pid">
						<c:forEach items="${got}" var="pubI"> 
						<option value="<c:out value="${pubI.pid}"/>"><c:out value="${pubI.name}"/></option>			
						</c:forEach>
						</select>
						</c:if>
						<input type="submit" value="Add Book">
						</form>


</body>
</html>