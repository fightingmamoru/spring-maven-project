package org.mamoru.webapp.hellospring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping (value = "/hellospring")
public class HelloSpringController
{
	private static final String JSP_PATH = "hellospring/";

	// Logger
	private static final Logger LOGGER = LoggerFactory.getLogger(HelloSpringController.class);

	@RequestMapping (value = "/helloSpring")
	public ModelAndView helloSpring()
	{
		// Start Log
		String startLogMessage = "START Log";
		LOGGER.debug("HelloSpring Log - {}", startLogMessage);

		ModelAndView mav = new ModelAndView();

		mav.setViewName(JSP_PATH + "helloSpring");

		mav.addObject("title", "Spring Web MVC - HelloSprig");
		mav.addObject("message", "Hello Spring!");

		// End Log
		String endLogMessage = "END Log";
		LOGGER.debug("HelloSpring Log - {}", endLogMessage);

		return mav;
	}
}