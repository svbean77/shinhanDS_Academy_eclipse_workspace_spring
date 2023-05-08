package com.shinhan.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

// DispatcherServlet(Front Controller)가 요청을 맏아 Controller를 찾음
@Controller
@RequestMapping("/first") // class level에 작성: 아래 요청들의 공통 사항
public class SampleController {
	Logger logger = LoggerFactory.getLogger(SampleController.class);
	
	// model: view의 이름을 직접 리턴해야 함
	@RequestMapping("/sample1") // /first/sample1
	public String test1(Model model) {
		logger.info("----- info: first/sample1 요청을 받음 -----");
		logger.warn("----- warn: first/sample1 요청을 받음 -----");
		
		model.addAttribute("subject", "스프링 프레임워크 model");
		
		return "sample/sampleView1";
	}
	
	// model and view: mv에 view 이름을 직접 설정 후 mv를 리턴하면 됨
	// 데이터(model)와 뷰(view)를 설정
	@RequestMapping("/sample2") // /first/sample2
	public ModelAndView test2() {
		logger.info("----- info: first/sample2 요청을 받음 -----");
		logger.warn("----- warn: first/sample2 요청을 받음 -----");
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("subject", "스프링 프레임워크 model and view");
		mv.setViewName("sample/sampleView1");
		
		return mv;
	}
	
	// method level의 mapping이 없음
	@RequestMapping // /first
	public ModelAndView test3() {
		logger.info("----- info: / 요청을 받음 -----");
		logger.warn("----- warn: / 요청을 받음 -----");
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("subject", "class-level, method-level");
		mv.setViewName("sample/sampleView1");
		
		return mv;
	}
	
	// 속성 - value, method
	@RequestMapping(value= {"/a.do", "/b.do"}, method = RequestMethod.GET) // 요청 URL 여러 개, default는 GET요청
	public ModelAndView test4() {
		logger.info("----- info: / 요청을 받음 -----");
		logger.warn("----- warn: / 요청을 받음 -----");
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("subject", "요청 주소가 여러 개!!!!");
		mv.setViewName("sample/sampleView1");
		
		return mv;
	}
	
	// 속성 - params
	@RequestMapping(value= {"/paramTest"}, params = {"userid=hello", "userpass", "!email"})
	public ModelAndView test5(@RequestParam(value="userid") String id, @RequestParam String userpass, 
			@RequestParam String email2, @RequestParam String address, @RequestParam int age) {
		logger.info("----- info: / 요청을 받음 -----");
		logger.warn("----- warn: / 요청을 받음 -----");
		logger.info("id (parameter 이름과 다르게 바꿈): " + id);
		logger.info("userpass: " + userpass);
		logger.info("email2: " + email2);
		logger.info("address: " + address);
		logger.info("age: " + age);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("subject", "userid가 hello일 때, userpass가 뭔지 모르지만 있을 때, email이 없을 때 여기로 옴!");
		mv.setViewName("sample/sampleView1");
		
		return mv;
	}
}
