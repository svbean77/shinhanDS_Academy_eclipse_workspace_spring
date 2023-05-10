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

	// ���� ���
	@RequestMapping(value = "/empinsert.do", method = RequestMethod.GET)
	public String registerGet(Model model) {
		model.addAttribute("deptList", comService.deptSelectAll());
		model.addAttribute("jobList", comService.jobSelectAll());
		model.addAttribute("managerList", comService.managerSelectAll());
		
		return "emp/empInsert";
	}

	// rest ���
	@RequestMapping(value = "/empinsert.do", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = "text/plain;charset=utf-8")
	public String registerPost(@RequestBody EmpVO emp, Model model,
			HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr) {
		String result = eService.empInsert(emp);
		redirectAttr.addFlashAttribute("resultMessage", result);

		return "����� " + result;
	}
 
	// rest ���
	// ����Ʈ ��ü ����
	@RequestMapping(value = "/emplist.do", produces = "application/json;charset=utf-8")
	public Map<String, Object> empList(Model model, HttpServletRequest request) { 
		Map<String, Object> returnMap = new HashMap<>();
		
		List<EmpVO> emplist = eService.selectByCondition3(new Integer[] {0}, null, null, null); 
		logger.info(emplist.size() + "��");

		returnMap.put("emplist", emplist);
		returnMap.put("deptList", comService.deptSelectAll());
		returnMap.put("jobList", comService.jobSelectAll());
		returnMap.put("managerList", comService.managerSelectAll());
		
		return returnMap;
	}

	// rest ���
	// VO ��ü ����
	// APPLICATION_JSON_VALUE: Jackson�� JSON���� �����Ͽ� ������ ��
	// Rest ���: �ּ�â ������� /empdetail.do/100
	// ���� ������ ��ü ������ ���� -> JS�� ��ü�� �����ϵ��� �ϱ� ���� Json ���·� ������� �� -> produces = MediaType.APPLICATION_JSON_VALUE
	@RequestMapping(value = "/empdetail.do/{empid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public EmpVO empDetail(@PathVariable int empid, Model model) {
		EmpVO emp = eService.selectById(empid);
//		model.addAttribute("deptList", comService.deptSelectAll());
//		model.addAttribute("jobList", comService.jobSelectAll());
//		model.addAttribute("managerList", comService.managerSelectAll());
//		model.addAttribute("emp", emp);
		
		return emp;
	}
	
	// rest ���
	@RequestMapping(value = "/empdetail.do", method = RequestMethod.PUT, 
			consumes = "application/json", produces = "text/plain;charset=utf-8") // put: update (����) post�� �Է��� �ǹ�
	public String empDetailUpdate(@RequestBody EmpVO emp) {
		logger.info(emp.toString());
		String result = eService.empUpdate(emp);
		
		return "����� " + result;
	}
	
	// rest ���
	@DeleteMapping(value = "/empDelete.do/{empid}", produces = "text/plain;charset=utf-8")
	public String empDelete(@PathVariable int empid) {
		logger.info(empid + "�� ���� ����");
		String result = eService.empDelete(empid);
		
		return "����� " + result;
	}
	
	// ���� ���
	@GetMapping("/empCondition.do")   
	public String selectByCondition(Integer deptid, String jobid, Double salary, Model model) {
		List<EmpVO> emplist = eService.selectByCondition(deptid, jobid, salary);
		model.addAttribute("empAll", emplist);		
		
		return "emp/empRetrieve";
	}
	// ���� ���
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
	// ���� ���
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
