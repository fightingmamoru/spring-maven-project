package org.mamoru.activiti.test.servicetask;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class WorkingTypeB implements JavaDelegate
{
	public void execute(DelegateExecution delegateExecution) throws Exception
	{
		System.out.println("[WorkingTypeB] execute");

		// Set Variable
		delegateExecution.setVariable("result", "typeB[" + delegateExecution.getId() +"] Result");
	}
}
