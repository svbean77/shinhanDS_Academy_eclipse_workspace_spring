<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="path" scope="application"></c:set>
<%
String company = "신한금융SW아카데미";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script> <!-- jquery -->
<script>
$(function () {
	// ajax는 '갔다 와라!'이기 때문에 로그아웃 후 페이지 이동이 없음
	$("#btnLogout").on("click", function () {
		$.ajax({ 
			url: "${path}/auth/logout.do",
			success: function (responseData) {
				alert("로그아웃 " + responseData);
				location.href="${path}/auth/loginCheck.do";
			},
			error: function (message) {
				alert(message);
			}
		});
	});
});
</script>
</head>
<body>
<div style="border:5px dotted pink;">
	<c:if test="${loginUser != null}">
		<div>현재 로그인중: '${loginUser.manager_name == null? "guest" : loginUser.manager_name}'님</div>
		<div>(EL문법 1) 세션에서 로그인 사용자 읽기: '${sessionScope.loginUser}'님</div>
		<div>(EL문법 2) 세션에서 로그인 사용자 읽기: '${loginUser}'님</div>
		<div>(ScriptLet)script방법 로그인 사용자 읽기: '<%=session.getAttribute("loginUser") %>'님</div>
		<img src="${path}/uploads/${loginUser.pic}" width="200">
		<button id="btnLogout">로그아웃</button>
	</c:if>
	<c:if test="${loginUser == null }">
		<button onclick="location.href='${path}/auth/loginCheck.do'">로그인</button>
	</c:if>
</div>
</body>
</html>