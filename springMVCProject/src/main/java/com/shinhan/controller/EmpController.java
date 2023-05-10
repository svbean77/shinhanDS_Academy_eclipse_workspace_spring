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
		// �׳� �������� ������
		model.addAttribute("deptList", comService.deptSelectAll());
		model.addAttribute("jobList", comService.jobSelectAll());
		model.addAttribute("managerList", comService.managerSelectAll());
		
		return "emp/empInsert";
	}

	// ���: parameter�� �ϳ��� ������
//	@RequestMapping(value = "/insert.do", method = RequestMethod.POST)
//	public String registerPost(int employee_id, String first_name, String last_name, String email, String phone_number, 
//			int salary, int department_id, int manager_id, String commission_pct, Date hire_date, String job_id) {
//		// ����ϸ� ����
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

	// �ڹٺ� ��ü: HTML form�� ���� ���� Request parameter�� �о� VO�� ������(new) setting���� ���� (vo
	// ���� �̸��� html name�� ���ƾ� ��)
	// @ModelAttribute: view���� ������ ���� (model.addAttribute ����� ����)
	@RequestMapping(value = "/empinsert.do", method = RequestMethod.POST)
	public String registerPost(@ModelAttribute("emp") EmpVO emp, String address, Model model,
			HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr) {
		// �� ����
		logger.info("emp: " + emp);
		logger.info("address: " + address);
		model.addAttribute("emp2", emp);
		model.addAttribute("address", address);

		// Servlet API ����
		String address2 = request.getParameter("address");
		String contextPath = request.getContextPath();
		String method = request.getMethod();
		String remoteAddr = request.getRemoteAddr();
		logger.info("address2: " + address2);
		logger.info("contextPath: " + contextPath);
		logger.info("method: " + method);
		logger.info("remoteAddr: " + remoteAddr);

		// model.addAttribute�� request.setAttribute�� ���� �ǹ� -> request�� ���� set X
		session.setAttribute("userInfo", "���ǿ� ����� ���� ����");

		String result = eService.empInsert(emp);
		redirectAttr.addFlashAttribute("resultMessage", result);

		return "redirect:/emp/emplist.do";
	}

	// Map�� �̿��� ���: @RequestParam ���� �Ұ���
//	@RequestMapping(value = "/insert.do", method = RequestMethod.POST)
//	public String registerPost(@RequestParam Map<String, String> map) {
//		for(String key : map.keySet()) {
//			logger.info(map.get(key));
//		}
//		
//		return "emp/empInsertResult";
//	}

	/*
	 * Controller �Լ��� return Ÿ�� 1. String: page �̸�, ���û(redirect) 2. ModelAndView:
	 * model + view
	 */
	@RequestMapping("/one")
	public ModelAndView test1(@RequestParam(name = "my", required = false) String myname) {
		ModelAndView mv = new ModelAndView("sample/sampleView1"); // ó������ view �̸� �ֱ� ����
		mv.addObject("subject", "model and view return ���� - " + myname);

		return mv;
	}

	// redirect ����: redirect �� ���� �����ϱ� ���� RedirectAttributes �Ķ���� �߰�
	@RequestMapping("/two")
	public String test2(RedirectAttributes redirectAttr) {
		logger.info("redirect ���� -- test2�� �Դ�!");
		redirectAttr.addFlashAttribute("resultMessage", "���û�մϴ�. insert �ϼ���!"); // ���û �ϸ鼭 �� ���� ������!

		return "redirect:/emp/empinsert.do";
	}

	// DB ����: emp list
	@RequestMapping("/emplist.do")
	public String empList(Model model, HttpServletRequest request) {
		// redirect �� ������ ����
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		if (flashMap != null) {
			Object message = flashMap.get("resultMessage");
			logger.info("�Է�/����/���� ��� message -> " + message);
		}

		// modal â�� ����� ���̱� ������ �ʿ��� ������!
		model.addAttribute("deptList", comService.deptSelectAll());
		model.addAttribute("jobList", comService.jobSelectAll());
		model.addAttribute("managerList", comService.managerSelectAll());
		
//		List<EmpVO> emplist = eService.selectByCondition2(0, "", 0.0);
		logger.info("test");
		List<EmpVO> emplist = eService.selectByCondition3(new Integer[] {0}, null, null, null);
//		List<EmpVO> emplist = eService.selectAll();
		logger.info(emplist.size() + "��");

		model.addAttribute("empAll", emplist);
		
		logger.debug("����: debug");
		logger.info("����: info");
		logger.warn("����: warn");
		logger.error("����: error");
		
		// ControllerAdvice ó��
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
		logger.info(empid + "�� ���� ����");
		String result = eService.empDelete(empid);
		redirectAttr.addFlashAttribute("resultMessage", result);
		
		return "redirect:/emp/emplist.do";
	}
	
	@GetMapping("/empCondition.do") // get ����̸� GetMapping���� ��� ��! (PostMaiing�� ����)
	// day053: ���� ��ȸ �߰�
	public String selectByCondition(Integer deptid, String jobid, Double salary, Model model) {
		List<EmpVO> emplist = eService.selectByCondition(deptid, jobid, salary);
		model.addAttribute("empAll", emplist);		
		
		return "emp/empRetrieve";
	}
	@GetMapping("/empCondition2.do") // get ����̸� GetMapping���� ��� ��! (PostMaiing�� ����)
	// day053: ���� ��ȸ �߰�
	// Ajax ��û �� �迭�� ���� @RequestParam("deptid[]") Integer[] deptid => '�迭'�̶�� ǥ�ø� �� ����� ��
	// �Ϲ� ��û �� �迭�� ���� @RequestParam("deptid") Integer[] deptid
	// �Ϲ� ��û �� �迭�� ���� Integer[] deptid
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
