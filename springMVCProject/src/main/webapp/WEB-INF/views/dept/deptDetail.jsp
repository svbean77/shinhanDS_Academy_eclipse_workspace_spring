<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../common/commonfiles.jsp"%>
<style>
#container {
	margin: 10px auto;
	width: 50%;
}

table {
	margin: 0 auto;
}

h1 {
	text-align: center;
}

input[name='email'], input[name='department_id'], input[name='job_id'],
	input[name='salary'], input[name='manager_id'], input[name='hire_date']
	{
	background-color: pink;
}
</style>
</head>
<body>
	<div id="container">
		<%@ include file="../common/header.jsp"%>
		<h1>${dept.department_name }부서상세 정보</h1>
		<hr>
		<form action="${path }/dept/deptdetail.do" class="mb-3" method="post">
			<table>
				<tr class="form-floating">
					<td><label for="department_id">부서번호</label></td>
					<td><input id="department_id" type="number"
						name="department_id" value="${dept.department_id }" readonly></td>
				</tr>
				<tr>
					<td>부서명</td>
					<td><input type="text" name="department_name" maxlength="20"
						value="${dept.department_name }"></td>
				</tr>
				<tr>
					<td>매니저</td>
					<td><select name="manager_id">
							<option value="" ${empList == null ? "selected" : ""}>매니저 없음</option>
							<c:forEach items="${empList}" var="emp">
								<option value="${emp.employee_id}"
									${emp.employee_id == dept.manager_id ? "selected" : ""}>${emp.first_name}
									${emp.last_name }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>부서위치</td>
					<td><input type="number" name="location_id"
						value="${dept.location_id }"></td>
				</tr>
				<tr style="text-align: center;">
					<td colspan="2"><input type="submit" value="부서수정"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>