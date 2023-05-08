<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>404Error</title>
</head>
<body>
<h1>요청하신 주소는 존재하지 않습니다.</h1>
<h2 id="here"></h2>

<h2>에러: ${errorMessage }</h2>
<h2>요청주소: ${url }</h2>

<script>
	here.innerHTML = "요청 주소: " + location.href;
</script>
</body>
</html>