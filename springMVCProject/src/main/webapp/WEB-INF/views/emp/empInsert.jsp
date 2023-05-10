<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>직원 정보 등록</title>
<%@ include file="../common/commonfiles.jsp" %>
</head>
<body>
	<div>
	<%@ include file="../common/header.jsp" %>
		<hr>
		<h1>직원정보 입력</h1> 
		<hr>
		
		<form action="<%=request.getContextPath() %>/emp/empinsert.do" class="mb-3" method="post" id="myfrm2">  
			<table>
				<tr class="form-floating">
					<td><label for="employee_id">직원번호</label></td>
					<td><input id="employee_id" type="number" name="employee_id" value="999" readonly></td>
				</tr>
				<tr>
					<td>firstName</td>
					<td><input type="text" name="first_name" maxlength="20" value="홍"></td>
				</tr>
				<tr>
					<td>LastName</td>
					<td><input type="text" name="last_name" required="required" value="길동"></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="email" name="email" required="required" value="user@mail.com"></td>
				</tr>
				<tr>
					<td>phone</td>
					<td><input type="tel" name="phone_number"
						value="010-1111-1111" pattern="010-[0-9]{4}-[0-9]{4}"></td>
				</tr>
				<tr>
					<td>salary</td>
					<td><input type="number" name="salary" value="8000"></td>
				</tr>
				<tr>
					<td>부서</td>
					<td>
					<select name="department_id">
						<option value="0">부서 없음</option>
						<c:forEach items="${deptList}" var="dept" varStatus="status">
							<option value="${dept.department_id}">${status.count} - ${dept.department_name}</option>
						</c:forEach>
					</select>
					</td>
				</tr>
				<tr>
					<td>매니저</td>
					<td>
					<select name="manager_id">
						<option value="0">매니저 없음</option>
						<c:forEach items="${managerList}" var="manager">
							<option value="${manager.employee_id}">${manager.first_name} ${manager.last_name }</option>
						</c:forEach>
					</select>
					</td>
				</tr>
				<tr>
					<td>커미션</td>
					<td><input type="text" name="commission_pct" value="0.2"></td>
				</tr>
				<tr>
					<td>입사일</td>
					<td><input type="date" name="hire_date" value="2021-01-10"
						required="required"></td>
				</tr>
				<tr>
					<td>직급</td>
					<td>
						<select name="job_id">
							<c:forEach items="${jobList}" var="job">
								<option value="${job.job_id}">${job.job_title}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr style="text-align: center;">
					<td>
						<input type="submit" value="직원등록">
						<input id="restBtn4" type="button" value="직원등록(rest)">
					</td>
				</tr>
			</table>
		</form>
		<div id="insertResult"></div>
	</div>
	<script>
	$("#restBtn4").on("click", function () {
		var arr = $("#myfrm2").serializeArray();
		console.log(arr);
		var obj = {};
		$.each(arr, function (idx, item) {
			obj[item.name] = item.value;
		});
		console.log(obj);
		
		$.ajax({
			url: "${path}/restemp/empinsert.do",
			method: "post",
			data: JSON.stringify(obj),
			contentType: "application/json",
			success: function (result) {
				$("#insertResult").html(result);
			},
			error: function (message) {}
		});
	});
	</script>
</body>
</html>