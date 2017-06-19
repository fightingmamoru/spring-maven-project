package org.mamoru.activiti.im.task;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class ApprovalGenerateTask implements JavaDelegate
{
	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception
	{
		/*
			Exception 핸들링
			- BpmnError를 사용합니다.
		 */

		try
		{
			// todo : Exception 처리부터 확인 필요
		}
		catch (Exception e)
		{
			// throw new BpmnError("ErrorCode - Error Message");
			// todo : Exception 매핑 처리 확인 필요
		}
	}
}