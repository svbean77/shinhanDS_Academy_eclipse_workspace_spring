package com.shinhan.controller;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.shinhan.model.CompanyService;
import com.shinhan.model.EmpService;
import com.shinhan.util.DateUtil;
import com.shinhan.vo.EmpVO;

@Controller
@RequestMapping("/emp")
public class EmpController {
	Logger logger = LoggerFactory.getLogger(EmpController.class);
	@Autowired
	EmpService eService;
	@Autowired
	CompanyService comService;

	@RequestMapping(value = "/empinsert.do", method = RequestMethod.GET)
	public String registerGet(Model model) {
		// 그냥 페이지를 보여줌
		model.addAttribute("deptList", comService.deptSelectAll());
		model.addAttribute("jobList", comService.jobSelectAll());
		model.addAttribute("managerList", comService.managerSelectAll());
		
		return "emp/empInsert";
	}

	// 방법: parameter를 하나씩 보내기
//	@RequestMapping(value = "/insert.do", method = RequestMethod.POST)
//	public String registerPost(int employee_id, String first_name, String last_name, String email, String phone_number, 
//			int salary, int department_id, int manager_id, String commission_pct, Date hire_date, String job_id) {
//		// 등록하면 보임
//		logger.info("employee_id: " + employee_id);
//		logger.info("first_name: " + first_name);
//		logger.info("last_name: " + last_name);
//		logger.info("email: " + email);
//		logger.info("phone_number: " + phone_number);
//		logger.info("salary: " + salary);
//		logger.info("department_id: " + department_id);
//		logger.info("manager_id: " + manager_id);
//		logger.info("commission_pct: " + commission_pct);
//		logger.info("hire_date: " + hire_date);
//		logger.info("job_id: " + job_id);
//		
//		return "emp/empInsertResult";
//	}

	// 자바빈 객체: HTML form을 통해 들어온 Request parameter를 읽어 VO를 생성해(new) setting까지 해줌 (vo
	// 변수 이름과 html name이 같아야 함)
	// @ModelAttribute: view에게 데이터 전달 (model.addAttribute 방법도 있음)
	@RequestMapping(value = "/empinsert.do", method = RequestMethod.POST)
	public String registerPost(@ModelAttribute("emp") EmpVO emp, String address, Model model,
			HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr) {
		// 값 전달
		logger.info("emp: " + emp);
		logger.info("address: " + address);
		model.addAttribute("emp2", emp);
		model.addAttribute("address", address);

		// Servlet API 전달
		String address2 = request.getParameter("address");
		String contextPath = request.getContextPath();
		String method = request.getMethod();
		String remoteAddr = request.getRemoteAddr();
		logger.info("address2: " + address2);
		logger.info("contextPath: " + contextPath);
		logger.info("method: " + method);
		logger.info("remoteAddr: " + remoteAddr);

		// model.addAttribute가 request.setAttribute와 같은 의미 -> request는 굳이 set X
		session.setAttribute("userInfo", "세션에 사용자 정보 저장");

		String result = eService.empInsert(emp);
		redirectAttr.addFlashAttribute("resultMessage", result);

		return "redirect:/emp/emplist.do";
	}

	// Map을 이용한 방법: @RequestParam 생략 불가능
//	@RequestMapping(value = "/insert.do", method = RequestMethod.POST)
//	public String registerPost(@RequestParam Map<String, String> map) {
//		for(String key : map.keySet()) {
//			logger.info(map.get(key));
//		}
//		
//		return "emp/empInsertResult";
//	}

	/*
	 * Controller 함수의 return 타입 1. String: page 이름, 재요청(redirect) 2. ModelAndView:
	 * model + view
	 */
	@RequestMapping("/one")
	public ModelAndView test1(@RequestParam(name = "my", required = false) String myname) {
		ModelAndView mv = new ModelAndView("sample/sampleView1"); // 처음부터 view 이름 주기 가능
		mv.addObject("subject", "model and view return 연습 - " + myname);

		return mv;
	}

	// redirect 연습: redirect 시 값을 전달하기 위해 RedirectAttributes 파라미터 추가
	@RequestMapping("/two")
	public String test2(RedirectAttributes redirectAttr) {
		logger.info("redirect 연습 -- test2에 왔다!");
		redirectAttr.addFlashAttribute("resultMessage", "재요청합니다. insert 하세요!"); // 재요청 하면서 이 값도 가져가!

		return "redirect:/emp/empinsert.do";
	}

	// DB 연동: emp list
	@RequestMapping("/emplist.do")
	public String empList(Model model, HttpServletRequest request) {
		// redirect 시 데이터 전달
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		if (flashMap != null) {
			Object message = flashMap.get("resultMessage");
			logger.info("입력/삭제/수정 결과 message -> " + message);
		}

		// modal 창을 사용할 것이기 때문에 필요한 정보들!
		model.addAttribute("deptList", comService.deptSelectAll());
		model.addAttribute("jobList", comService.jobSelectAll());
		model.addAttribute("managerList", comService.managerSelectAll());
		
//		List<EmpVO> emplist = eService.selectByCondition2(0, "", 0.0);
		logger.info("test");
		List<EmpVO> emplist = eService.selectByCondition3(new Integer[] {0}, null, null, null);
//		List<EmpVO> emplist = eService.selectAll();
		logger.info(emplist.size() + "건");

		model.addAttribute("empAll", emplist);
		
		logger.debug("정보: debug");
		logger.info("정보: info");
		logger.warn("정보: warn");
		logger.error("정보: error");
		
		// ControllerAdvice 처리
//		int a = 10 / 0;

		return "emp/empSelect";
	}

	@RequestMapping(value = "/empdetail.do", method = RequestMethod.GET)
	public String empDetail(int empid, Model model) {
		EmpVO emp = eService.selectById(empid);
		model.addAttribute("deptList", comService.deptSelectAll());
		model.addAttribute("jobList", comService.jobSelectAll());
		model.addAttribute("managerList", comService.managerSelectAll());
		model.addAttribute("emp", emp);
		
		return "emp/empDetail";
	}
	
	@RequestMapping(value = "/empdetail.do", method = RequestMethod.POST)
	public String empDetailUpdate(EmpVO emp, RedirectAttributes redirectAttr) {
		logger.info(emp.toString());
		String result = eService.empUpdate(emp);
		redirectAttr.addFlashAttribute("resultMessage", result);
		
		return "redirect:/emp/emplist.do";
	}
	
	@RequestMapping("empDelete.do")
	public String empDelete(int empid, RedirectAttributes redirectAttr) {
		logger.info(empid + "번 직원 삭제");
		String result = eService.empDelete(empid);
		redirectAttr.addFlashAttribute("resultMessage", result);
		
		return "redirect:/emp/emplist.do";
	}
	
	@GetMapping("/empCondition.do") // get 방식이면 GetMapping으로 적어도 됨! (PostMaiing도 존재)
	// day053: 조건 조회 추가
	public String selectByCondition(Integer deptid, String jobid, Double salary, Model model) {
		List<EmpVO> emplist = eService.selectByCondition(deptid, jobid, salary);
		model.addAttribute("empAll", emplist);		
		
		return "emp/empRetrieve";
	}
	@GetMapping("/empCondition2.do") // get 방식이면 GetMapping으로 적어도 됨! (PostMaiing도 존재)
	// day053: 조건 조회 추가
	// Ajax 요청 시 배열이 오면 @RequestParam("deptid[]") Integer[] deptid => '배열'이라는 표시를 꼭 해줘야 함
	// 일반 요청 시 배열이 오면 @RequestParam("deptid") Integer[] deptid
	// 일반 요청 시 배열이 오면 Integer[] deptid
	public String selectByCondition2(@RequestParam(value = "deptid[]", required = false)Integer[] deptid, String jobid, Double salary, Date hiredate, Model model) {
		logger.info("deptid: " + deptid);
		logger.info("jobid: " + jobid);
		logger.info("salary: " + salary);
		logger.info("hiredate: " + hiredate);
//		Date date = hiredate == "" ? null : DateUtil.convertToDate(hiredate);
		List<EmpVO> emplist = eService.selectByCondition2(deptid, jobid, salary, hiredate);
		
		model.addAttribute("empAll", emplist);	
		
		return "emp/empRetrieve";
	}
	@GetMapping("/empCondition3.do")
	public String selectByCondition3(@RequestParam(value = "deptid[]", required = false)Integer[] deptid, String jobid, Double salary, Date hiredate, Model model) {
		logger.info("deptid: " + deptid);
		logger.info("jobid: " + jobid);
		logger.info("salary: " + salary);
		logger.info("hiredate: " + hiredate);
		List<EmpVO> emplist = eService.selectByCondition3(deptid, jobid, salary, hiredate);
		
		model.addAttribute("empAll", emplist);	
		
		return "emp/empRetrieve";
	}
}
