package com.shinhan.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.shinhan.model.EmpService;
import com.shinhan.vo.EmpVO;

@Controller
@RequestMapping("/emp")
public class EmpController {
	Logger logger = LoggerFactory.getLogger(EmpController.class);
	@Autowired
	EmpService eService;

	@RequestMapping(value = "/insert.do", method = RequestMethod.GET)
	public String registerGet(HttpServletRequest request) {
		// �׳� �������� ������

		// redirect �� ������ ����
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		if (flashMap != null) {
			Object message = flashMap.get("resultMessage");
			logger.info("message -> " + message);
		}

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
	@RequestMapping(value = "/insert.do", method = RequestMethod.POST)
	public String registerPost(@ModelAttribute("emp") EmpVO emp, String address, Model model,
			HttpServletRequest request, HttpSession session) {
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

		return "emp/empInsertResult";
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

		return "redirect:/emp/insert.do";
	}

	// DB ����: emp list
	@RequestMapping("/list")
	public String empList(Model model) {
		List<EmpVO> emplist = eService.selectAll();
		logger.info(emplist.size() + "��");
		
		model.addAttribute("empAll", emplist);
		
		return "emp/empSelect";
	}
	
}
