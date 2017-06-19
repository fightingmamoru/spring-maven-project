package org.mamoru.activiti.ntest.service.first;


import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class SelectGender implements JavaDelegate
{
	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception
	{
		// Get Variable
		Object gender = delegateExecution.getVariable("gender");

		// Print
		System.out.println("[SelectGender] processInstanceId : " + delegateExecution.getProcessInstanceId() +
				" / gender : " + gender);

		// Set Variable
		delegateExecution.setVariable("addcount", 1);
	}
}
