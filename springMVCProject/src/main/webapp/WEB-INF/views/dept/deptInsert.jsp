<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
</style>
</head>
<body>
	<div id="container">
		<%@ include file="../common/header.jsp"%>
		<h1>신규 부서 등록</h1>
		<hr>
		<form action="${path }/dept/deptinsert.do"
			class="mb-3" method="post">
			<table>
				<tr class="form-floating">
					<td><label for="department_id">부서번호</label></td>
					<td><input id="department_id" type="number"
						name="department_id" value="999"></td>
				</tr>
				<tr>
					<td>부서명</td>
					<td><input type="text" name="department_name" maxlength="20"
						value="ICT"></td>
				</tr>
				<tr>
					<td>매니저</td>
					<td><select name="manager_id">
					<option value="">매니저 없음</option>
							<c:forEach items="${empList}" var="emp">
								<option value="${emp.employee_id}">${emp.first_name}
									${emp.last_name }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>부서위치</td>
					<td><input type="number" name="location_id" value="3000"></td>
				</tr>
				<tr style="text-align: center;">
					<td colspan="2"><input type="submit" value="부서등록"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>