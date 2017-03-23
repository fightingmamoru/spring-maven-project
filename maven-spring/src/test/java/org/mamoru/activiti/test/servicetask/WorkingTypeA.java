package org.mamoru.activiti.test.servicetask;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class WorkingTypeA implements JavaDelegate
{
	public void execute(DelegateExecution delegateExecution) throws Exception
	{
		System.out.println("[WorkingTypeA] execute");

		// Set Variable
		delegateExecution.setVariable("result", "typeA[" + delegateExecution.getId() +"] Result");
	}
}
