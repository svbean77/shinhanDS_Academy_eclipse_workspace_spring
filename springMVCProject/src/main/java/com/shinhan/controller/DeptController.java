package com.shinhan.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.shinhan.model.DeptService;
import com.shinhan.model.EmpService;
import com.shinhan.vo.DeptVO;

@Controller
@RequestMapping("/dept")
public class DeptController {
	Logger logger = LoggerFactory.getLogger(DeptController.class);
	@Autowired
	DeptService dService;
	@Autowired
	EmpService eService;
	
	@RequestMapping("/deptlist.do")
	public String deptListGet(HttpServletRequest request, Model model) {
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		if(flashMap != null) {
			Object message = flashMap.get("resultMessage");
			logger.info("입력/수정/삭제: " + message);
		}
		
		List<DeptVO> deptlist = dService.deptList();
		logger.info(deptlist.toString());
		model.addAttribute("deptAll", deptlist);
		
		return "dept/deptSelect";
	}
	
	@RequestMapping("/deptinsert.do")
	public String deptInsertGet(Model model) {
		model.addAttribute("empList", eService.selectAll());
		return "dept/deptInsert";
	}
	
	@RequestMapping(value = "/deptinsert.do", method = RequestMethod.POST)
	public String deptInsertPost(@ModelAttribute("dept") DeptVO dept, RedirectAttributes redirectAttr) {
		logger.info("dept: " + dept);
		String result = dService.deptInsert(dept);
		redirectAttr.addFlashAttribute("resultMessage", result);
		
		return "redirect:/dept/deptlist.do";
	}
	
	@RequestMapping("/deptdetail.do")
	public String deptDetailGet(Model model, int deptid) {
		logger.info(deptid + "");
		model.addAttribute("dept", dService.selectById(deptid));
		model.addAttribute("empList", eService.selectAll());
		
		return "dept/deptDetail";
	}
	
	@RequestMapping(value = "/deptdetail.do", method = RequestMethod.POST)
	public String deptDetailPost(DeptVO dept, RedirectAttributes redirectAttr) {
		logger.info(dept.toString());
		String result = dService.deptUpdate(dept);
		redirectAttr.addFlashAttribute("resultMessage", result);
		
		return "redirect:/dept/deptlist.do";
	}
	
	@RequestMapping("/deptDelete.do")
	public String deptDelete(int deptid, RedirectAttributes redirectAttr) {
		logger.info(deptid + "번 부서 삭제");
		String result = dService.deptDelete(deptid);
		redirectAttr.addFlashAttribute("resultMessage", result);
		
		return "redirect:/dept/deptlist.do";
	}
	
	// local error 처리: 해당 컨트롤러에서 에러가 발생했을 때 -> 다른 컨트롤러에서 발생한 에러도 해결하려면 global로 처리해야 함
//	@ExceptionHandler(Exception.class)
//	public String errorProcess(Exception ex) {
//		ex.printStackTrace();
//		logger.info(ex.getMessage());
//		return "error/error500";
//	}
}
