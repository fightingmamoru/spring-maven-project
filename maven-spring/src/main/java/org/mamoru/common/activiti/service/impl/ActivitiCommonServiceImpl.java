package org.mamoru.common.activiti.service.impl;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.mamoru.common.activiti.service.ActivitiCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ActivitiCommonServiceImpl implements ActivitiCommonService
{
	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private HistoryService historyService;

	public String startProcessInstanceByKey(String processDefinitionKey)
	{
		return runtimeService.startProcessInstanceByKey(processDefinitionKey).getId();
	}

	public void setTaskAssignee(String taskId, String assigneeUserId)
	{
		taskService.setAssignee(taskId, assigneeUserId);
	}

	public void completeTask(String taskId)
	{
		taskService.complete(taskId);
	}


	public List<HistoricProcessInstance> getProcessInstanceList(String processInstanceId)
	{
		return StringUtils.isEmpty(processInstanceId) ?
				historyService.createHistoricProcessInstanceQuery().list()
				:
				historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).list();
	}

	public List<HistoricActivityInstance> getActivityList(String processInstanceId)
	{
		return StringUtils.isEmpty(processInstanceId) ?
				historyService.createHistoricActivityInstanceQuery().list()
				:
				historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).list();
	}

	public String getActiveTaskId(String processInstanceId)
	{
		return taskService.createTaskQuery().processInstanceId(processInstanceId)
				.active().list().get(0).getId();
	}
}
