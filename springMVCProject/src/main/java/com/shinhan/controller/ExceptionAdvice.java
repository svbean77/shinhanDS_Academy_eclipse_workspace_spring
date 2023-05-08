package com.shinhan.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice // ControllerAdvice: ��� Controller���� ���� �߻� �� ����
public class ExceptionAdvice {
	Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

	@ExceptionHandler(Exception.class) // Exception�� ���� ���ܸ� ��� ó��, 500 error�� ���� ó��
	public String processException(Exception ex) {
		logger.info(ex.getClass().getName());
		logger.info(ex.getMessage());

		return "error/error500";
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ModelAndView handlerError404(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("errorMessage", "404����");
		mv.addObject("url", request.getRequestURL());
		mv.setViewName("error/error404");
		return mv;

	}
}
