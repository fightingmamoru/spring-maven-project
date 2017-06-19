package org.mamoru.activiti.ntest.service.second;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class PrintUserResult implements JavaDelegate
{
	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception
	{
		// get variable
		Object name = delegateExecution.getVariable("name");
		Object age = delegateExecution.getVariable("age");
		Object gender = delegateExecution.getVariable("gender");
		Object result = delegateExecution.getVariable("result");

		// print variable
		System.out.println("[SecondProcess - PrintUserResult] processInstanceId : " + delegateExecution.getProcessInstanceId() +
				" / name : " + name +
				" / age : " + age +
				" / gender : " + gender +
				" / result : " + result);

		// set variable
		if ( gender.equals("M") )
		{
			delegateExecution.setVariable("result", "Man");
		}
		else
		{
			delegateExecution.setVariable("result", "Woman");
		}

		delegateExecution.setVariable("age", Integer.valueOf(String.valueOf(age))+1);
	}
}
