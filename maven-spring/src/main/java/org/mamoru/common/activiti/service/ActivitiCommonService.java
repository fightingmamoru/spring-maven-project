package org.mamoru.common.activiti.service;

import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;

import java.util.List;

public interface ActivitiCommonService
{
	String startProcessInstanceByKey(String processDefinitionKey);

	void setTaskAssignee(String taskId, String assigneeUserId);

	void completeTask(String taskId);

	List<HistoricProcessInstance> getProcessInstanceList(String processInstanceId);

	List<HistoricActivityInstance> getActivityList(String processInstanceId);

	String getActiveTaskId (String processInstanceId);
}
