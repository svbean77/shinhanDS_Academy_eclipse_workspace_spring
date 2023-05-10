<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="../common/commonfiles.jsp"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
	$(function() {
		$("thead tr th").click(function(e) {
			var trNum = $(this).closest("th").prevAll().length;
			var a = $("tbody tr").each(function(index, item) {
				var col = $(item).find("td:nth-child(" + (trNum + 1) + ")");
				console.log(col);
				$(item).find("td").css("background-color", "white");
				$(col).css("background-color", "orange");
			});
		});
		$("#btn1").click(function() {
			$("tr > td:nth-child(2):contains('S')").css("color", "red");
		});
		$("#btn2").click(function() {
			$("tbody tr:even").css("background", "pink");
			$("tbody tr:odd").css("background", "white");
		});
		$("#btn3").click(function() {
			var selector = "tr > td:nth-child(8)";

			$(selector).each(function(idx, item) {
				var sal = parseInt($(item).html());
				if (sal >= 5000) {
					$(item).css("background-color", "lightblue");
				}
			});
		});
		$("#btn4").click(function() {
			var arr = $("tr>td:nth-child(1)");
			$.each(arr, function(index, item) {
				console.log($(item).text());
				if (parseInt($(item).text()) % 2 == 1) {
					$(item).parent().css("background-color", "lightgreen");
				}
			});
		});
		$("#select").change(function() {
			var jobid = $(this).val();

			$("tr td").css("color", "black");
			$("tr td:contains('" + jobid + "')").css("color", "red");
		});
		var str = "";
		var arr = [ "IT_PROG", "PU_CLERK", "AD_VP", "FI_ACCOUNT", "FI_MGR",
				"ST_MAN" ];
		$.each(arr, function(idx, item) {
			str += "<option>" + item + "</option>";
		});
		$("#jobs").html(str);

		$("#select").change(function() {
			var selector = "tr > td:nth-child(7)";

			$(selector).each(function(idx, item) {
				var sel = $("#select").val();
				if (sel == $(item).text()) {
					$(item).css("background-color", "pink");
				} else {
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
		<!-- day054: restful 방식 연습 -->
		<div>
			<input type="number" id="empid" value="100"/>
			<button id="restBtn1">1건의 직원 조회</button>: 
			<span id="empResult1"></span>
		</div>
		<div>
			<button id="restBtn2">모든 직원 조회</button>: 
			<span id="empResult2"></span>
		</div>
		<hr>
		<h1>직원목록</h1>
		<hr>
		<button onclick="location.href='empinsert.do'" type="button"
			class="btn btn-outline-success">직원등록(버튼, 상대)</button>
		<a type="button" class="btn btn-outline-success"
			href="${path}/emp/empinsert.do">직원등록(a태그, 절대)</a>
		<button type="button" class="btn btn-primary" data-bs-toggle="modal"
			data-bs-target="#exampleModal" data-bs-whatever="@mdo">직원등록(modal)</button>
		<%@ include file="empInsertModal.jsp"%>
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
		</select> <select id="jobs">
		</select>

		<hr>
		<select id="deptid2" name="deptid2" multiple="multiple">
			<option value="0">부서</option>
			<c:forEach items="${deptList }" var="dept">
				<option value="${dept.department_id }">${dept.department_id } - ${dept.department_name }</option>
			</c:forEach>
		</select>
		<select id="jobid2" name="jobid2">
			<option value="">직책</option>
			<c:forEach items="${jobList }" var="job">
				<option value="${job.job_id }">${job.job_id }</option>
			</c:forEach>
		</select>
		<input id="salary2" type="number" name="salary2" placeholder="급여"/><span>이상</span>
		<input id="hiredate2" type="date" name="hiredate2"/><span>이후</span>
		<button id="conditionBtn2" type="button" class="btn btn-outline-success">조회2 (for)</button>
		<hr>
		<select id="deptid3" name="deptid3" multiple="multiple">
			<option value="0">부서</option>
			<c:forEach items="${deptList }" var="dept">
				<option value="${dept.department_id }">${dept.department_id } - ${dept.department_name }</option>
			</c:forEach>
		</select>
		<select id="jobid3" name="jobid3">
			<option value="">직책</option>
			<c:forEach items="${jobList }" var="job">
				<option value="${job.job_id }">${job.job_id }</option>
			</c:forEach>
		</select>
		<input id="salary3" type="number" name="salary3" placeholder="급여"/><span>이상</span>
		<input id="hiredate3" type="date" name="hiredate3"/><span>이후</span>
		<button id="conditionBtn3" type="button" class="btn btn-outline-success">조회3 (map)</button>

		<div id="empListTable">
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
						<th>삭제(rest)</th>
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
							<td><button class="restBtn5" data-del="${emp.employee_id}">삭제rest</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<script>
		$(function() {

			$(".btnDel").on(
					"click",
					function() {
						location.href = "${path}/emp/empDelete.do?empid="
								+ $(this).attr("data-del");
					});

		});
	</script>
	<script>
	$("#conditionBtn2").on("click", function () {
		var deptid = $("#deptid2").val();
		var jobid = $("#jobid2").val();
		var salary = $("#salary2").val();
		var hiredate = $("#hiredate2").val() || undefined; // undefined가 넘어갈 때 null로 넘어감
		
		console.log(deptid);
		$.ajax({
			url: "empCondition2.do", // ${path}/emp/empCondition2.do
			data: {"deptid": deptid, "jobid": jobid, "salary": salary, "hiredate": hiredate},
			success: function (result) {
				$("#empListTable").html(result);
			},
			error: function (message) {
				$("#empListTable").html(message);
			}
		});
	});
	$("#conditionBtn3").on("click", function () {
		var deptid = $("#deptid3").val();
		var jobid = $("#jobid3").val();
		var salary = $("#salary3").val();
		var hiredate = $("#hiredate3").val() || undefined; // undefined가 넘어갈 때 null로 넘어감
		
		console.log(deptid);
		$.ajax({
			url: "empCondition3.do", // ${path}/emp/empCondition2.do
			data: {"deptid": deptid, "jobid": jobid, "salary": salary, "hiredate": hiredate},
			success: function (result) {
				$("#empListTable").html(result);
			},
			error: function (message) {
				$("#empListTable").html(message);
			}
		});
	});
	</script>
	<script>
	/* da054: restful 방식 연습 */
	// 값만 넘어감 -> 보안상 안전! (넘어간 값이 무엇에 관한 값인지 모름)
	$("#restBtn1").on("click", function () {
		var empid = $("#empid").val();
		$.ajax({
			url: "${path}/restemp/empdetail.do/" + empid,
			success: function (result) {
				console.log(result);
				var name = result.first_name + " " + result.last_name;
				$("#empResult1").html(name);
			},
			error: function (message) {}
		});
	});
	
	$("#restBtn2").on("click", function () {
		$.ajax({
			url: "${path}/restemp/emplist.do/",
			success: function (result) {
				console.log(result);
				var output = "<ul>";
				
				$.each(result["emplist"], function (idx, item) {
					output += "<li>" + item.first_name + " " + item.last_name + "</li>";
				});
				$("#empResult2").html(output + "</ul>");
			},
			error: function (message) {}
		});
	});
	
	$(".restBtn5").on("click", function () {
		var empid = $(this).attr("data-del");
		console.log(empid);
		$.ajax({
			url: "${path}/restemp/empDelete.do/" + empid,
			method: "delete",
			success: function (result) {
				alert(result);
				location.href = "${path}/emp/emplist.do/"
			}
		});
		
	});
	</script>
</body>
</html>