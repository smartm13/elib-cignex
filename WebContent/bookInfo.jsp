<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${book.name }</title>
</head>
<body>
Book details:
<br>BID=${book.bid}
<br>ISBN=${book.isbn }
<br>Name=${book.name }
<br>PubId=${book.pid }
<br>Publisher=${pub_name }
<br>Price=Rs.${book.price }
</body>
</html>