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
public class CheckTypeServiceTaskTest
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
		// createDeployment(repositoryService);

		// 04. Start Process Instance
		Map<String, Object> serviceTaskVariable = new HashMap<String, Object>();
		serviceTaskVariable.put("type","B");

		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("testJavaProcess", serviceTaskVariable);

		// 05. Inquiry Task List
		List<Task> tasklist = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();

		for ( Task task : tasklist )
		{
			System.out.println("[TaskList Data] taskId: " + task.getId() + " / taskName: " + task.getName());
		}
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
				.name("testJavaProcess")
				.addClasspathResource("activiti/deployment/TestJavaTaskProcess.bpmn20.xml")
				.deploy();
	}
}