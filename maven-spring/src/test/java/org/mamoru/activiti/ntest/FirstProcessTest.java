package org.mamoru.activiti.ntest;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
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
public class FirstProcessTest
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
	public void startProcess()
	{
		// 01. Deploy by Spring Configuration

		// 02. Set Variable
		Map<String, Object> processVariable = new HashMap<String, Object>();
		processVariable.put("gender", "M");

		// 03. Start Process Instance
		String processDefinitionKey = "firstProcess";
		String processInstanceId = runtimeService.startProcessInstanceByKey(processDefinitionKey, processVariable).getId();

		System.out.println("processInstanceId : " + processInstanceId);
	}

	@Test
	public void selectActivityList()
	{
		// HistoryService를 통한 조회
		List result = historyService.createHistoricProcessInstanceQuery()
				.finished()
				.processInstanceId("155001")
				.list();

		System.out.println("[result size] " + result.size());


		List result2 = historyService.createHistoricActivityInstanceQuery()
				.finished()
				.processInstanceId("155001")
				.list();

		System.out.println("[result2 size] " + result2.size());
	}

	@Test
	public void emptyTest()
	{
		// todo : 테스트 필요
		Task newTask = taskService.newTask();

		taskService.saveTask(newTask);


		// todo : 이미 작성 된 중간에 결재를 위한 task를 끼워 넣을 수 있는지 확인 필요

		// todo : 현재 확인 결과 단일 task를 생성하여 처리 불가능 해보임.
	}
}

/* Check List
	- Spring 설정 중, deploymentMode를 통해서, deploy 범위 설정 (파일별 deploy를 할것이냐 폴더 단위로 할것이냐 등)
	- deploymentMode 기본 설정 사용 시,SpringAutoDeployment 라는 NAME_ 으로 deploy 됨
	- 개별 Process Definition에 대한 정보는 act_re_procdef 테이블에서 확인 가능
	  (파일 2개가 deploy 됐다면, 2개의 definition이 생김)
	- Process Definition 별로 개별 instance 실행 가능
	-
*/
