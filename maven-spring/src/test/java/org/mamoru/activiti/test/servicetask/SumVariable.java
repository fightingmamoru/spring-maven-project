package org.mamoru.activiti.test.servicetask;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class SumVariable implements JavaDelegate
{
	public void execute(DelegateExecution execution) throws Exception
	{
		// get variable
		int firstNum = (Integer) execution.getVariable("firstNum");
		int secondNum = (Integer) execution.getVariable("secondNum");

		// sum variable
		int result = firstNum + secondNum;

		// print variable and result
		System.out.println("[SumVariable] First Number : " + firstNum);
		System.out.println("[SumVariable] Second Number : " + secondNum);
		System.out.println("[SumVariable] Result : " + result);

		// set result variable in execution
		execution.setVariable("result", result);

		// thread sleep
		Thread.sleep(3000);
	}
}
