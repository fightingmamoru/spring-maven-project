package org.mamoru.common.activiti.im.task;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.mamoru.common.service.ImService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
	결재 Task
	- 공통 모듈로 사용
 */
@Component("approvalTask")
public class ApprovalTask implements JavaDelegate
{
	@Autowired
	private ImService imService;

	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception
	{
		System.out.println("[ApprovalTask] START ==========================================================");

		imService.insertApproval(delegateExecution.getVariables());

		System.out.println("[ApprovalTask] END ============================================================");
	}
}