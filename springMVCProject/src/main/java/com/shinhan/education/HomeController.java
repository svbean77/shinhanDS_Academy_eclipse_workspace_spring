package com.shinhan.education;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		// model: 데이터 저장하기 위한 객체 (request.setAttribute()와 같음)
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("myname", "hong-길동");
		model.addAttribute("myage", 25);
		model.addAttribute("mycar", new Car("BMW", 500000));
		
		// ViewResolver에게 home을 주면 '접두사 + home + 접미사'(=view)를 돌려줌
		// => view가 결정됨 (/WEB-INF/views/home.jsp)
		return "home";
	}
	
}
