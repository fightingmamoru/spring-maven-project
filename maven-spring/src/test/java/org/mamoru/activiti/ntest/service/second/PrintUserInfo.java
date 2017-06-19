package org.mamoru.activiti.ntest.service.second;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class PrintUserInfo implements JavaDelegate
{
	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception
	{
		// get variable
		Object name = delegateExecution.getVariable("name");
		Object age = delegateExecution.getVariable("age");
		Object gender = delegateExecution.getVariable("gender");

		// print variable
		System.out.println("[SecondProcess - PrintUserInfo] processInstanceId : " + delegateExecution.getProcessInstanceId() +
				" / name : " + name +
				" / age : " + age +
				" / gender : " + gender);
	}
}
