package org.mamoru.common.activiti.listener;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;

public class ActivitiCommonEventListener implements ActivitiEventListener
{
	public void onEvent(ActivitiEvent activitiEvent)
	{
		System.out.println("[ActivitiCommonEventListener] EventType : " + activitiEvent.getType());
	}

	public boolean isFailOnException()
	{
		return false;
	}
}
