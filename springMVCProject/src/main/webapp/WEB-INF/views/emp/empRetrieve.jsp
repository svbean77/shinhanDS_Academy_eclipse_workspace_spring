<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>순서</th>
				<th>직원번호</th>
				<th>이름</th>
				<th>성</th>
				<th>이메일</th>
				<th>전화번호</th>
				<th>입사일</th>
				<th>직책</th>
				<th>급여</th>
				<th>누적급여</th>
				<th>커미션</th>
				<th>매니저</th>
				<th>부서</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="totalSalary" value="0" />
			<c:forEach items="${empAll}" var="emp" varStatus="status">
				<c:set var="totalSalary" value="${totalSalary + emp.salary}" />
				<tr>
					<td>${status.count}</td>
					<td><a
						href="${path}/emp/empdetail.do?empid=${emp.employee_id}">${emp.employee_id }</a></td>
					<td><a
						href="${path}/emp/empdetail.do?empid=${emp.employee_id}">${emp.first_name}</a></td>
					<td>${emp.last_name}</td>
					<td>${emp.email}</td>
					<td>${emp.phone_number == null ? "-" : emp.phone_number}</td>
					<td><fmt:formatDate value="${emp.hire_date}"
							pattern="yyyy/MM/dd" /></td>
					<td>${emp.job_id}</td>
					<td><fmt:formatNumber
							value="${emp.salary == null ? 0 : emp.salary }"
							groupingUsed="true"></fmt:formatNumber></td>
					<td>${totalSalary}</td>
					<td><fmt:formatNumber
							value="${emp.commission_pct == null ? 0 : emp.commission_pct }"
							type="percent"></fmt:formatNumber></td>
					<td>${emp.manager_id}</td>
					<td>${emp.department_id}</td>
					<td><button class="btnDel" data-del="${emp.employee_id}">삭제</button></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>