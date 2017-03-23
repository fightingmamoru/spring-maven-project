package org.mamoru.activiti.test;

import org.activiti.engine.*;
import org.activiti.engine.runtime.Execution;
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
public class TestMyReceiveTest
{
	@Test
	public void testActivitiProcess()
	{
		// 01. Create Activiti Process Engine (Use mysql)
		ProcessEngine processEngine = getProcessEngine();

		// 02. Set Acitiviti Services
		RepositoryService repositoryService = processEngine.getRepositoryService();
		RuntimeService runtimeService = processEngine.getRuntimeService();
		TaskService taskService = processEngine.getTaskService();

		// 03. Deploy Process Definition (Only one execute)
		createDeployment(repositoryService);

		// 04. Start Process Instance
		Map<String, Object> serviceTaskVariable = new HashMap<String, Object>();
		serviceTaskVariable.put("type","A");

		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("testReceiveProcess", serviceTaskVariable);

		// 05. Inquiry Execution Id
		Execution execution = runtimeService.createExecutionQuery().processInstanceId(processInstance.getId())
																   .activityId("servicetask1")
																   .singleResult();

		// 06. Inquiry Task List
		List<Task> tasklist = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();

		for ( Task task : tasklist )
		{
			System.out.println("[TaskList Data] taskId: " + task.getId() + " / taskName: " + task.getName());
		}

		// 07. Signal to Receive Task
		runtimeService.signal(execution.getId());

		// 08. Inquiry Task List
		tasklist = taskService.createTaskQuery().list();

		for ( Task task : tasklist )
		{
			System.out.println("[TaskList Data] taskId: " + task.getId() + " / taskName: " + task.getName());
		}
	}

	@Test
	public void testToSignal()
	{
		// 01. Create Activiti Process Engine (Use mysql)
		ProcessEngine processEngine = getProcessEngine();

		// 02. Set Acitiviti Services
		RepositoryService repositoryService = processEngine.getRepositoryService();
		RuntimeService runtimeService = processEngine.getRuntimeService();
		TaskService taskService = processEngine.getTaskService();

		// 03. Set Process Instance Id
		String processInstanceId = "130005";

		// 04. Inquiry Execution Id
		Execution execution = runtimeService.createExecutionQuery().processInstanceId(processInstanceId)
				.activityId("receivetask")
				.singleResult();

		// 06. Signal to Receive Task
		runtimeService.signal(execution.getId());
	}

	private ProcessEngine getProcessEngine()
	{
		return ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration()
				.setDatabaseSchemaUpdate("false")
				.setJdbcDriver("com.mysql.jdbc.Driver")
				.setJdbcUrl("jdbc:mysql://localhost:3306/activiti01")
				.setJdbcUsername("activiti01")
				.setJdbcPassword("activiti01")
				.buildProcessEngine();
	}

	private void createDeployment(RepositoryService repositoryService)
	{
		repositoryService.createDeployment()
				.name("testReceiveProcess")
				.addClasspathResource("activiti/deployment/TestReceiveTaskProcess.bpmn20.xml")
				.deploy();
	}
}