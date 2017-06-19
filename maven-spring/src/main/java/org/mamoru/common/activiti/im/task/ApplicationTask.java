package org.mamoru.common.activiti.im.task;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.mamoru.common.service.ImService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
	결재 정보 저장 Task
	- 공통 모듈로 사용
 */
@Component("applicationTask")
public class ApplicationTask implements JavaDelegate
{
	@Autowired
	private ImService imService;

	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception
	{
		System.out.println("[ApplicationTask] START ===============================================");

		imService.insertApplication(delegateExecution.getVariables());

		delegateExecution.setVariable("processInstanceId", delegateExecution.getProcessInstanceId());

		System.out.println("[ApplicationTask] END =================================================");
	}
}