package org.mamoru.common.activiti.im.task;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.mamoru.common.service.ImService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
	권한 신청 Task
	- 개별 모듈로 사용
 */
@Component("assignAuthorityTask")
public class AssignAuthorityTask implements JavaDelegate
//public class AssignAuthorityTask implements ActivityBehavior
{
	@Autowired
	ImService imService;

	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception
	{
		System.out.println("[CreateAccountTask] START =====================================================");

		// todo : 계정정보의 일부를 표시
		imService.insertAuthority(delegateExecution.getVariables());

		System.out.println("[CreateAccountTask] END =======================================================");
	}

//	@Override
//	public void execute(ActivityExecution activityExecution) throws Exception
//	{
//		System.out.println("[AssignAuthorityTask] START =====================================================");
//
//		// todo : 계정정보의 일부를 표시
//		imService.insertAuthority(activityExecution.getVariables());
//
//		System.out.println("[AssignAuthorityTask] END =======================================================");
//	}
}
