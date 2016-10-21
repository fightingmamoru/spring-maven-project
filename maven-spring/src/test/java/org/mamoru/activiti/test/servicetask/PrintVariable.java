package org.mamoru.activiti.test.servicetask;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class PrintVariable implements JavaDelegate
{
	public void execute(DelegateExecution execution) throws Exception
	{
		// make variable
		int firstNum = 10;
		int secondNum = 20;

		// print variable
		System.out.println("[PrintVariable] First Number : " + firstNum);
		System.out.println("[PrintVariable] Second Number : " + secondNum);

		// set variable in execution
		execution.setVariable("firstNum", firstNum);
		execution.setVariable("secondNum", secondNum);

		// Thread sleep
		Thread.sleep(5000);
	}
}
