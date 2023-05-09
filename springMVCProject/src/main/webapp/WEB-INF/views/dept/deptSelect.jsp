<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../common/commonfiles.jsp"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body>
	<div class="container mt-3">
		<jsp:include page="../common/header.jsp"></jsp:include>
		<h1>부서목록</h1>
		<a type="button" class="btn btn-outline-success" href="${path}/dept/deptinsert.do">부서등록</a>
		<hr>
		<table class="table">
			<tr>
				<th>index</th>
				<th>부서번호</th>
				<th>부서명</th>
				<th>부서담당자</th>
				<th>부서위치</th>
				<th>삭제</th>
			</tr>
			<c:forEach items="${deptAll }" var="dept" varStatus="status">
				<tr>
					<td>${status.count }</td>
					<td><a
						href="${path}/dept/deptdetail.do?deptid=${dept.department_id }">${dept.department_id }</a></td>
					<td><a
						href="${path}/dept/deptdetail.do?deptid=${dept.department_id }">${dept.department_name }</a></td>
					<td>${dept.manager_id == null ? "-" : dept.manager_id }</td>
					<td>${dept.location_id }</td>
					<td><button class="btnDel" data-del="${dept.department_id }">삭제</button></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<script>
		$(function() {

			$(".btnDel").on(
					"click",
					function() {
						location.href = "${path}/dept/deptDelete.do?deptid="
								+ $(this).attr("data-del");
					});

		});
	</script>
</body>
</html>