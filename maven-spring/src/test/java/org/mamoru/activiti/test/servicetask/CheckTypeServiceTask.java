package org.mamoru.activiti.test.servicetask;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class CheckTypeServiceTask implements JavaDelegate
{
	public void execute(DelegateExecution delegateExecution) throws Exception
	{
		System.out.println("[CheckTypeServiceTask] execute");

		// Get
		Object type = delegateExecution.getVariable("type");

		// Set Variable
		delegateExecution.setVariable("type", type);
	}
}