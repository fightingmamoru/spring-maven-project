package org.mamoru.activiti.test;

import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Spring을 연동하지 않고 Java Source 작성
 * - ActivitiRule을 사용하지 않고 작성
 * - processInstance 및 processInstanceConfig 소스코드로 지정
 * - 진행과정에서 생성 된 데이터는 모두 DB에 저장됨
 */
public class TestMyProcessSingleTest
{
	@Test
	// @Deployment(resources = {"activiti/deployment/TestMyProcess.bpmn20.xml"})
	public void test()
	{
		// 01. Create Activiti Process Engine (Use mysql)
		ProcessEngine processEngine = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration()
																.setDatabaseSchemaUpdate("false")
																.setJdbcDriver("com.mysql.jdbc.Driver")
																.setJdbcUrl("jdbc:mysql://localhost:3306/activiti01")
																.setJdbcUsername("activiti01")
																.setJdbcPassword("activiti01")
																.buildProcessEngine();

		// 02. Set Acitiviti Services
		RepositoryService repositoryService = processEngine.getRepositoryService();
		RuntimeService runtimeService = processEngine.getRuntimeService();
		TaskService taskService = processEngine.getTaskService();

		// 03. Deploy Process Definition
		repositoryService.createDeployment()
						 .addClasspathResource("activiti/deployment/TestMyProcess.bpmn20.xml")
						 .deploy();

		// 04. Start Process Instance
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("testMyProcess");

		// 05. Inquiry Task List
		List<Task> tasklist = taskService.createTaskQuery().list();

		for ( Task task : tasklist )
		{
			System.out.println("[TaskList Data] taskId: " + task.getId() + " / taskName: " + task.getName());
		}

		// 06. Complete First User Task
		Map<String, Object> firstTaskVariable = new HashMap<String, Object>();

		firstTaskVariable.put("name","firstUser");
		firstTaskVariable.put("description","First User Task Completing by firstUser!");

		taskService.complete(tasklist.get(0).getId(), firstTaskVariable);

		// 07. Inquiry Task List
		tasklist = taskService.createTaskQuery().list();

		for ( Task task : tasklist )
		{
			System.out.println("[TaskList Data] taskId: " + task.getId() + " / taskName: " + task.getName());
		}

		// 08. Complete Second User Task
		/*
		Map<String, Object> secondTaskVariable = new HashMap<String, Object>();

		secondTaskVariable.put("name","secondUser");
		secondTaskVariable.put("description","Second User Task Completing by firstUser!");

		taskService.complete(tasklist.get(0).getId(), secondTaskVariable);


		// 09. Inquiry Task
		tasklist = taskService.createTaskQuery().list();

		for ( Task task : tasklist )
		{
			System.out.println("[TaskList Data] taskId: " + task.getId() + " / taskName: " + task.getName());
		}
		*/
	}
}