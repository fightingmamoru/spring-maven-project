package org.mamoru.webapp.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping (value = "/hellospring")
public class HelloSpringController
{
	private static final String JSP_PATH = "hellospring/";

	@RequestMapping (value = "/helloSpring")
	public ModelAndView helloSpring()
	{
		ModelAndView mav = new ModelAndView();

		mav.setViewName(JSP_PATH + "helloSpring");

		mav.addObject("title", "Spring Web MVC - HelloSprig");
		mav.addObject("message", "Hello Spring!");

		return mav;
	}
}