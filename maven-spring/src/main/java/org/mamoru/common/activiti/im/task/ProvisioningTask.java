package org.mamoru.common.activiti.im.task;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.mamoru.common.service.ImService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
	프로비저닝 Task
	- 공통 모듈로 사용
 */
@Component("provisioningTask")
public class ProvisioningTask implements JavaDelegate
{
	@Autowired
	ImService imService;

	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception
	{
		System.out.println("[ProvisioningTask] START ======================================================");

		// todo : 계정 정보의 일부를 표시
		imService.provisionAccount(delegateExecution.getVariables());

		System.out.println("[ProvisioningTask] END ========================================================");
	}
}
