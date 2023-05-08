package com.shinhan.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

// DispatcherServlet(Front Controller)�� ��û�� ���� Controller�� ã��
@Controller
@RequestMapping("/first") // class level�� �ۼ�: �Ʒ� ��û���� ���� ����
public class SampleController {
	Logger logger = LoggerFactory.getLogger(SampleController.class);
	
	// model: view�� �̸��� ���� �����ؾ� ��
	@RequestMapping("/sample1") // /first/sample1
	public String test1(Model model) {
		logger.info("----- info: first/sample1 ��û�� ���� -----");
		logger.warn("----- warn: first/sample1 ��û�� ���� -----");
		
		model.addAttribute("subject", "������ �����ӿ�ũ model");
		
		return "sample/sampleView1";
	}
	
	// model and view: mv�� view �̸��� ���� ���� �� mv�� �����ϸ� ��
	// ������(model)�� ��(view)�� ����
	@RequestMapping("/sample2") // /first/sample2
	public ModelAndView test2() {
		logger.info("----- info: first/sample2 ��û�� ���� -----");
		logger.warn("----- warn: first/sample2 ��û�� ���� -----");
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("subject", "������ �����ӿ�ũ model and view");
		mv.setViewName("sample/sampleView1");
		
		return mv;
	}
	
	// method level�� mapping�� ����
	@RequestMapping // /first
	public ModelAndView test3() {
		logger.info("----- info: / ��û�� ���� -----");
		logger.warn("----- warn: / ��û�� ���� -----");
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("subject", "class-level, method-level");
		mv.setViewName("sample/sampleView1");
		
		return mv;
	}
	
	// �Ӽ� - value, method
	@RequestMapping(value= {"/a.do", "/b.do"}, method = RequestMethod.GET) // ��û URL ���� ��, default�� GET��û
	public ModelAndView test4() {
		logger.info("----- info: / ��û�� ���� -----");
		logger.warn("----- warn: / ��û�� ���� -----");
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("subject", "��û �ּҰ� ���� ��!!!!");
		mv.setViewName("sample/sampleView1");
		
		return mv;
	}
	
	// �Ӽ� - params
	@RequestMapping(value= {"/paramTest"}, params = {"userid=hello", "userpass", "!email"})
	public ModelAndView test5(@RequestParam(value="userid") String id, @RequestParam String userpass, 
			@RequestParam String email2, @RequestParam String address, @RequestParam int age) {
		logger.info("----- info: / ��û�� ���� -----");
		logger.warn("----- warn: / ��û�� ���� -----");
		logger.info("id (parameter �̸��� �ٸ��� �ٲ�): " + id);
		logger.info("userpass: " + userpass);
		logger.info("email2: " + email2);
		logger.info("address: " + address);
		logger.info("age: " + age);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("subject", "userid�� hello�� ��, userpass�� ���� ������ ���� ��, email�� ���� �� ����� ��!");
		mv.setViewName("sample/sampleView1");
		
		return mv;
	}
}
