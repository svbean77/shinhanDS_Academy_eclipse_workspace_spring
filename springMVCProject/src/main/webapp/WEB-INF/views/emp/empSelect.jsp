<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="../common/commonfiles.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
$(function () {
	$("thead tr th").click(function(e){
		var trNum = $(this).closest("th").prevAll().length; 
		var a = $("tbody tr").each(function(index, item){ 
			var col = $(item).find("td:nth-child(" + (trNum+1) + ")");
			console.log(col);
			$(item).find("td").css("background-color", "white");
			$(col).css("background-color", "orange");
		});
	});
	$("#btn1").click(function () {
		$("tr > td:nth-child(2):contains('S')").css("color", "red");
	});
	$("#btn2").click(function () {
		$("tbody tr:even").css("background", "pink");
		$("tbody tr:odd").css("background", "white");
	});
	$("#btn3").click(function() {
		var selector = "tr > td:nth-child(8)";
		
		$(selector).each(function(idx, item){
			var sal = parseInt($(item).html());
			if(sal >= 5000) {
				$(item).css("background-color", "lightblue");
			}
		}); 
	});
	$("#btn4").click(function(){
		var arr = $("tr>td:nth-child(1)");
			$.each(arr, function(index, item){
			console.log($(item).text());
			if(parseInt($(item).text())%2==1){
			$(item).parent().css("background-color", "lightgreen");
			}
		});
	});
	$("#select").change(function () {
		var jobid = $(this).val();
		
		$("tr td").css("color", "black");
		$("tr td:contains('" + jobid + "')").css("color", "red");
	});
	var str = "";
	var arr = ["IT_PROG", "PU_CLERK", "AD_VP", "FI_ACCOUNT", "FI_MGR", "ST_MAN"];
	$.each(arr, function (idx, item) {
		str += "<option>" + item + "</option>";
	});
	$("#jobs").html(str);

	$("#select").change(function () {
		var selector = "tr > td:nth-child(7)";
		
		$(selector).each(function (idx, item) {
			var sel = $("#select").val();
			if(sel == $(item).text()) {
				$(item).css("background-color", "pink");
			}		
			else {
				$(item).css("background-color", "white");
			}
		});
	});
	
	
});
</script>

<title>Insert title here</title>
<style>
</style>
</head>
<body>
	<div id="container" class="container mt-3">
	<jsp:include page="../common/header.jsp"></jsp:include>
		<h1>직원목록</h1>
		<hr>
		<button onclick="location.href='empinsert.do'" type="button" class="btn btn-outline-success">직원등록(버튼, 상대)</button>
		<a type="button" class="btn btn-outline-success" href="${path}/emp/empinsert.do">직원등록(a태그, 절대)</a>
		<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" data-bs-whatever="@mdo">직원등록(modal)</button>
		<%@ include file="empInsertModal.jsp" %>
		<button id="btn1">이름에 S 들어가는 직원</button>
		<button id="btn2">짝수번째 직원</button>
		<button id="btn3">급여가 5000 이상인 직원</button>
		<button id="btn4">직원번호 홀수인 직원</button>
		
		<select id="select">
			<option>AD_PRES</option>
			<option>IT_PROG</option>
			<option>AD_VP</option>
			<option>FI_MGR</option>
			<option>ST_MAN</option>
			<option>PU_CLERK</option>
		</select>
		
		<select id="jobs">
		</select>
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
			<c:set var="totalSalary" value="0"/>
			<c:forEach items="${empAll}" var="emp" varStatus="status">
			<c:set var="totalSalary" value="${totalSalary + emp.salary}"/>
				<tr> 
					<td>${status.count}</td>
					<td><a href="${path}/emp/empdetail.do?empid=${emp.employee_id}">${emp.employee_id }</a></td>
					<td><a href="${path}/emp/empdetail.do?empid=${emp.employee_id}">${emp.first_name}</a></td>
					<td >${emp.last_name}</td>
					<td>${emp.email}</td>
					<td>${emp.phone_number}</td>
					<td><fmt:formatDate value="${emp.hire_date}" pattern="yyyy/MM/dd"/></td>
					<td>${emp.job_id}</td>
					<td><fmt:formatNumber value="${emp.salary}" groupingUsed="true"></fmt:formatNumber></td>
					<td>${totalSalary}</td>
					<td><fmt:formatNumber value="${emp.commission_pct}" type="percent"></fmt:formatNumber></td>
					<td>${emp.manager_id}</td>
					<td>${emp.department_id}</td>
					<td><button class="btnDel" data-del="${emp.employee_id}">삭제</button></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<script>
$(function () {
	
	$(".btnDel").on("click", function () {
		location.href = "${path}/emp/empDelete.do?empid=" + $(this).attr("data-del");
	});
	
});

</script>
</body>
</html>