package org.mamoru.activiti.test;

import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

/**
 * Spring을 연동하지 않고 Java Source 작성
 * - ActivitiRule은 별도 초기화
 * - @Deployment를 사용하여, TestMyProcess.bpmn20.xml 파일 배포 지정
 * - activiti.cfg.xml 파일에서 processInstanceConfiguration 설정
 * - 작업 완료 시, 테스트 데이터가 DB에서 delete 됨
 */
public class TestMyProcessTest
{
	@Rule
	public ActivitiRule activitiRule = new ActivitiRule();

	@Test
	@Deployment(resources = {"activiti/deployment/TestMyProcess.bpmn20.xml"})
	public void test()
	{
		// 01. Start Process
		ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey("testMyProcess");
		assertNotNull(processInstance);

		// 02. Inquiry Task
		TaskService taskService = activitiRule.getTaskService();
		List<Task> tasklist = taskService.createTaskQuery().list();

		for ( Task task : tasklist )
		{
			System.out.println("[TaskList Data] taskId: " + task.getId() + " / taskName: " + task.getName());
		}

		// 03. Complete First User Task
		Map<String, Object> firstTaskVariable = new HashMap<String, Object>();

		firstTaskVariable.put("name","firstUser");
		firstTaskVariable.put("description","First User Task Completing by firstUser!");

		taskService.complete(tasklist.get(0).getId(), firstTaskVariable);

		// 04. Inquiry Task
		tasklist = taskService.createTaskQuery().list();

		for ( Task task : tasklist )
		{
			System.out.println("[TaskList Data] taskId: " + task.getId() + " / taskName: " + task.getName());
		}

		// 05. Complete Second User Task
		Map<String, Object> secondTaskVariable = new HashMap<String, Object>();

		secondTaskVariable.put("name","firstUser");
		secondTaskVariable.put("description","First User Task Completing by firstUser!");

		taskService.complete(tasklist.get(0).getId(), secondTaskVariable);

		// 06. Inquiry Task
		tasklist = taskService.createTaskQuery().list();

		for ( Task task : tasklist )
		{
			System.out.println("[TaskList Data] taskId: " + task.getId() + " / taskName: " + task.getName());
		}
	}
}