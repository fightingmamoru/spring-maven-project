package org.mamoru.activiti.ntest.service.first;


import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class PrintMilitarySerialNumber implements JavaDelegate
{
	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception
	{
		// Get Variable
		Object gender = delegateExecution.getVariable("gender");
		Object addcount = delegateExecution.getVariable("addcount");

		// Print
		System.out.println("[PrintMilitarySerialNumber] processInstanceId : " + delegateExecution.getProcessInstanceId() +
				" / gender : " + gender + " / addcount : " + gender);

		// Set Variable
		delegateExecution.setVariable("addcount", 2);
	}
}
