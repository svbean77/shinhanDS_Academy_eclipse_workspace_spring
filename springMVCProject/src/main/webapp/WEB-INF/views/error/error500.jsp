<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>500Error</title>
</head>
<body>
<h1>코드 오류</h1>
<p><%=exception.getMessage() %></p>
<hr>
<p><%=exception%></p>
</body>
</html>