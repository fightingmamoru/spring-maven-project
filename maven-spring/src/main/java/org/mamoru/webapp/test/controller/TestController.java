package org.mamoru.webapp.test.controller;

import org.mamoru.common.util.JavaScriptUtil;
import org.mamoru.webapp.test.vo.TestUserVO;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/test")
public class TestController
{
	private static final String JSP_PATH = "test/";

	@RequestMapping(value = "/formTestUser")
	public ModelAndView formTestUser()
	{
		ModelAndView mav = new ModelAndView();

		mav.setViewName(JSP_PATH  + "formtestuser");
		mav.addObject("testUserVO", new TestUserVO());

		return mav;
	}

	@RequestMapping(value = "/updateTestUser")
	public ModelAndView updateTestUser(@Valid @ModelAttribute("testUserVO") TestUserVO testUserVO,
									   BindingResult bindingResult,
									   SessionStatus sessionStatus)
	{
		ModelAndView mav = new ModelAndView();

		mav.setViewName(JSP_PATH  + "formtestuser");

		if ( bindingResult.hasErrors() )
		{
			System.out.println("[Validation Check Fail]");

			List<ObjectError> errorList = bindingResult.getAllErrors();

			for ( ObjectError error : errorList )
			{
				System.out.println("[updateTestUser Error] " + error);
			}
		}
		else
		{
			System.out.println("[Validation Check Success]");
		}

		return mav;
	}

	@RequestMapping(value = "/rhino")
	public ModelAndView rhino()
	{
		ModelAndView mav = new ModelAndView();

		mav.setViewName(JSP_PATH  + "rhino");

		return mav;
	}

	@RequestMapping(value = "/setItem")
	public void setItem() throws Exception
	{
		String script = "function setValue() { localStorage.setItem('itemKey', 'testValue'); }";

		Object returnValue = JavaScriptUtil.callJavaScript(script, "setValue");

		System.out.println("[setItem()] " + returnValue);
	}

	@RequestMapping(value = "/getItem")
	public void getItem() throws Exception
	{
		String script = "function getValue() { return localStorage.getItem('itemKey'); }";

		Object returnValue = JavaScriptUtil.callJavaScript(script, "getValue");

		System.out.println("[getItem()] " + returnValue);
	}
}
