package org.mamoru.activiti.ntest.service;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =  {
		"classpath*:spring/root-context.xml",
		"classpath*:spring/servlet/servlet-common-context.xml",
		"classpath*:spring/servlet/servlet-datasource-context.xml",
		"classpath*:spring/servlet/servlet-mybatis-context.xml",
		"classpath*:spring/servlet/servlet-activiti-context.xml"
})
public class SecondProcessTest
{
	@Autowired
	@Rule
	public ActivitiRule activitiRule;

	@Autowired
	RuntimeService runtimeService;

	@Autowired
	RepositoryService repositoryService;

	@Autowired
	HistoryService historyService;

	@Autowired
	TaskService taskService;


	@Test
	public void firstAction()
	{
		// set variable and start process instance

		// 01. set variable
		Map<String, Object> processVariable = new HashMap<String, Object>();
		processVariable.put("name", "kimrego");
		processVariable.put("age", 33);
		processVariable.put("gender", "F");

		// 02. start process instance
		String processDefinitionKey = "secondProcess";
		String processInstanceId = runtimeService.startProcessInstanceByKey(processDefinitionKey, processVariable).getId();

		System.out.println("[SecondProcess - firstAction()] processInstanceId : " + processInstanceId);
	}

	@Test
	public void secondAction()
	{
		 String processInstanceId = "170001";
//		String processInstanceId = "172501";

		// View process instance
		Object processInstance = historyService.createHistoricProcessInstanceQuery()
				.processInstanceId(processInstanceId)
				.singleResult();

		// view finished activity
		List activityInstanceList = historyService.createHistoricActivityInstanceQuery()
				.processInstanceId(processInstanceId)
				.finished()
				.list();

		// view variable
		List variableList = historyService.createHistoricVariableInstanceQuery()
				.processInstanceId(processInstanceId)
				.list();

		System.out.println("A");

		// todo : 다음 task의 실행을 위해서는 executionId가 필요 - 실제로 processInstanceId ?
		// - The process instance id = the root execution id of the tree of all executions.

		// - variable은 과거 버전은 저장하지 않음
		// - variable은 최종의 버전만을 저장
	}

	@Test
	public void thirdAction()
	{
		// The process instance id = the root execution id of the tree of all executions.
		// String executionId = "170001";
		String executionId = "172501";

		// signal to receiveTask
		runtimeService.signal(executionId);
	}
}