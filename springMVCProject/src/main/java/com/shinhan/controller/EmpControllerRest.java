package com.shinhan.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.shinhan.model.CompanyService;
import com.shinhan.model.EmpService;
import com.shinhan.vo.EmpVO;

@RestController // @Controller + @ResponseBody
@RequestMapping("/restemp")
public class EmpControllerRest {
	Logger logger = LoggerFactory.getLogger(EmpControllerRest.class);
	@Autowired
	EmpService eService;
	@Autowired
	CompanyService comService;

	// 기존 방법
	@RequestMapping(value = "/empinsert.do", method = RequestMethod.GET)
	public String registerGet(Model model) {
		model.addAttribute("deptList", comService.deptSelectAll());
		model.addAttribute("jobList", comService.jobSelectAll());
		model.addAttribute("managerList", comService.managerSelectAll());
		
		return "emp/empInsert";
	}

	// rest 방법
	@RequestMapping(value = "/empinsert.do", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = "text/plain;charset=utf-8")
	public String registerPost(@RequestBody EmpVO emp, Model model,
			HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr) {
		String result = eService.empInsert(emp);
		redirectAttr.addFlashAttribute("resultMessage", result);

		return "결과는 " + result;
	}
 
	// rest 방법
	// 리스트 객체 전달
	@RequestMapping(value = "/emplist.do", produces = "application/json;charset=utf-8")
	public Map<String, Object> empList(Model model, HttpServletRequest request) { 
		Map<String, Object> returnMap = new HashMap<>();
		
		List<EmpVO> emplist = eService.selectByCondition3(new Integer[] {0}, null, null, null); 
		logger.info(emplist.size() + "건");

		returnMap.put("emplist", emplist);
		returnMap.put("deptList", comService.deptSelectAll());
		returnMap.put("jobList", comService.jobSelectAll());
		returnMap.put("managerList", comService.managerSelectAll());
		
		return returnMap;
	}

	// rest 방법
	// VO 객체 전달
	// APPLICATION_JSON_VALUE: Jackson이 JSON으로 변경하여 리턴해 줌
	// Rest 방식: 주소창 생김새가 /empdetail.do/100
	// 응답 문서에 객체 보내기 가능 -> JS가 객체로 이해하도록 하기 위해 Json 형태로 보내줘야 함 -> produces = MediaType.APPLICATION_JSON_VALUE
	@RequestMapping(value = "/empdetail.do/{empid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public EmpVO empDetail(@PathVariable int empid, Model model) {
		EmpVO emp = eService.selectById(empid);
//		model.addAttribute("deptList", comService.deptSelectAll());
//		model.addAttribute("jobList", comService.jobSelectAll());
//		model.addAttribute("managerList", comService.managerSelectAll());
//		model.addAttribute("emp", emp);
		
		return emp;
	}
	
	// rest 방법
	@RequestMapping(value = "/empdetail.do", method = RequestMethod.PUT, 
			consumes = "application/json", produces = "text/plain;charset=utf-8") // put: update (수정) post는 입력을 의미
	public String empDetailUpdate(@RequestBody EmpVO emp) {
		logger.info(emp.toString());
		String result = eService.empUpdate(emp);
		
		return "결과는 " + result;
	}
	
	// rest 방법
	@DeleteMapping(value = "/empDelete.do/{empid}", produces = "text/plain;charset=utf-8")
	public String empDelete(@PathVariable int empid) {
		logger.info(empid + "번 직원 삭제");
		String result = eService.empDelete(empid);
		
		return "결과는 " + result;
	}
	
	// 기존 방법
	@GetMapping("/empCondition.do")   
	public String selectByCondition(Integer deptid, String jobid, Double salary, Model model) {
		List<EmpVO> emplist = eService.selectByCondition(deptid, jobid, salary);
		model.addAttribute("empAll", emplist);		
		
		return "emp/empRetrieve";
	}
	// 기존 방법
	@GetMapping("/empCondition2.do")  
	public String selectByCondition2(@RequestParam(value = "deptid[]", required = false)Integer[] deptid, String jobid, Double salary, Date hiredate, Model model) {
		logger.info("deptid: " + deptid);
		logger.info("jobid: " + jobid);
		logger.info("salary: " + salary);
		logger.info("hiredate: " + hiredate); 
		List<EmpVO> emplist = eService.selectByCondition2(deptid, jobid, salary, hiredate);
		
		model.addAttribute("empAll", emplist);	
		
		return "emp/empRetrieve";
	}
	// 기존 방법
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
