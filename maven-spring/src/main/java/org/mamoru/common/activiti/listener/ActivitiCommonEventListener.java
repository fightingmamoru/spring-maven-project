package org.mamoru.common.activiti.listener;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;

public class ActivitiCommonEventListener implements ActivitiEventListener
{
	@Override
	public void onEvent(ActivitiEvent activitiEvent)
	{
		System.out.println("[ActivitiCommonEventListener] EventType : " + activitiEvent.getType());
	}

	@Override
	public boolean isFailOnException()
	{
		// 이러한 리스너의 onEvent() 메소드의 로직은 크리티컬 하지 않음
		// 로깅이 실패하면, exception은무시될 수 있음
		return false;
	}
}