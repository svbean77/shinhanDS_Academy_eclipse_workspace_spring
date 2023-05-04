<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<p>이름: ${myname }</p>
<p>나이: ${myage }</p>
<p>차: ${mycar }</p>
<p>차종: ${mycar.model }</p>

<hr>
<h3>SampleController의 test1~4로 이동하기 위한 태그</h3>
<ul>
<li><a href="first/sample1">test1: model 사용</a></li>
<li><a href="first/sample2">test2: model and view 사용</a></li>
<li><a href="first">test3: method level mapping 없음</a></li>
<li><a href="first/a.do">test4: 요청 주소 여러 개: a.do</a></li>
<li><a href="first/b.do">test4: 요청 주소 여러 개: b.do</a></li>
</ul>

<hr>
<h3>SampleController의 test5로 이동하기 위한 form</h3>
<form action="first/paramTest">
	아이디: <input type="text" name="userid" value="hello"><br>
	패스워드: <input type="text" name="userpass"><br>
	이메일: <input type="text" name="email2"><br> <!-- name이 email이라면 에러!! -->
	주소: <input type="text" name="address"><br>
	나이: <input type="number" name="age"><br>
	<input type="submit" value="제출">
</form>
</body>
</html>
