package org.mamoru.activiti.test;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.List;

/*
	Activiti에 기본으로 테스트를 위해 작성된 Process 테스트 코드
	- Spring에 의존하지 않음. 단독 Java Program.
	- 실행설정에서 .bpmn20.xml 파일 로드를 위한 classpath 지정
 */
public class FinancialReportProcessTest
{
	@Test
	public void test()
	{
		// Create Activiti process engine (don't user H2 database)
		ProcessEngine processEngine = ProcessEngineConfiguration
				.createStandaloneProcessEngineConfiguration()
				.setDatabaseSchemaUpdate("false")
				.setJdbcDriver("com.mysql.jdbc.Driver")
				.setJdbcUrl("jdbc:mysql://localhost:3306/activiti01?sessionVariables=storage_engine=InnoDB&useUnicode=true&characterEncoding=utf8")
				.setJdbcUsername("activiti01")
				.setJdbcPassword("kimjb132!")
				.buildProcessEngine();

		// Get Activiti services (repositoryService, runtimeService, taskService, historyService)
		RepositoryService repositoryService = processEngine.getRepositoryService();
		RuntimeService runtimeService = processEngine.getRuntimeService();
		TaskService taskService = processEngine.getTaskService();
		HistoryService historyService = processEngine.getHistoryService();

		// Deploy process definition
		repositoryService.createDeployment()
				.addClasspathResource("activiti/diagram/FinancialReportProcess.bpmn20.xml")
				.deploy();

		// start process instance
		String processInstanceId = runtimeService.startProcessInstanceByKey("financialReport").getId();

		// Get the first task
		List<Task> taskList = taskService.createTaskQuery().taskCandidateGroup("accountancy").list();

		for ( Task task : taskList )
		{
			System.out.println("Task for accountancy group : " + task.getName());

			// claim task
			taskService.claim(task.getId(), "fozzie");
		}

		// Verify Fozzie can now retrieve the task
		taskList = taskService.createTaskQuery().taskAssignee("fozzie").list();

		for ( Task task : taskList )
		{
			System.out.println("Task for fozzie : " + task.getName());

			// complete task
			taskService.complete(task.getId());
		}

		// Count taks for fozzie
		long cnt = taskService.createTaskQuery().taskAssignee("fozzie").count();

		System.out.println("Number of task for fozzie : " + cnt);


		// Retrieve and claim the second task
		taskList = taskService.createTaskQuery().taskCandidateGroup("management").list();

		for ( Task task : taskList )
		{
			System.out.println("Task for management group : " + task.getName());

			// claim task
			taskService.claim(task.getId(), "kermit");
		}

		// complete second task and process end
		for ( Task task : taskList )
		{
			taskService.complete(task.getId());
		}

		// process actually finished
		HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
				.processInstanceId(processInstanceId).singleResult();

		System.out.println("Process instance end time : " + historicProcessInstance.getEndTime());
	}
}
