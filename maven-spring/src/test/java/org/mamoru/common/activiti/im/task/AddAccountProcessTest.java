package org.mamoru.common.activiti.im.task;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mamoru.common.service.ImService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
	계정 추가 신청 테스트
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =  {
		"classpath*:spring/root-context.xml",
		"classpath*:spring/servlet/servlet-common-context.xml",
		"classpath*:spring/servlet/servlet-datasource-context.xml",
		"classpath*:spring/servlet/servlet-mybatis-context.xml",
		"classpath*:spring/servlet/servlet-activiti-context.xml"
})
public class AddAccountProcessTest
{
	// processDefinitionKey 지정
	private String processDefinitionKey = "addAccProcess";

	@Autowired
	@Rule
	public ActivitiRule activitiRule;

	@Autowired
	RuntimeService runtimeService;

	@Autowired
	HistoryService historyService;

	@Autowired
	RepositoryService repositoryService;

	@Autowired
	ImService imService;

	@Test
	public void startProcess()
	{
		// 01. 신청정보를 파라미터로 넘겨받음 (임의의 정보를 맵으로 세팅)
		Map<String, Object> applicationData = new HashMap<String, Object>();

		// 계정 신청 정보 세팅 (test01)
		/*
		applicationData.put("accountName", "test01");
		applicationData.put("requestUser", "admin");
		applicationData.put("description", "계정신청을 요청합니다.");
		*/

		// 계정 신청 정보 세팅 (test02)
		/*
		applicationData.put("accountName", "test03");
		applicationData.put("requestUser", "user00");
		applicationData.put("description", "계정신청을 요청합니다.");
		*/

		// 계정 신청 정보 세팅 (test03)
		/*
		applicationData.put("accountName", "test03");
		applicationData.put("requestUser", "user10");
		applicationData.put("description", "계정신청을 요청합니다.(빠른요청)");
		*/

		// 계정 신청 정보 세팅 (test05)
		applicationData.put("accountName", "test05");
		applicationData.put("requestUser", "user50");
		applicationData.put("description", "계정신청을 요청합니다.");

		// 02. ProcessInstance 시작
		String processInstanceId = runtimeService.startProcessInstanceByKey(processDefinitionKey, applicationData).getId();

		System.out.println("(Check ProcessInstanceId : " + processInstanceId + ") ");
	}

	@Test
	public void retrieveProcessInformation()
	{
		/*
			현재진행중인 목록 조회
			- 종료된 processInstance : finished()
			- 종료되지 않은 processInstance : unfinished()
		 */
		List<HistoricProcessInstance> unfinishProcessInstanceList = historyService.createHistoricProcessInstanceQuery()
				.processDefinitionKey(processDefinitionKey)
				.unfinished()
				.list();

		// 조회 내용 조회
		for ( HistoricProcessInstance processInstance : unfinishProcessInstanceList )
		{
			String currentProcessInstanceId = processInstance.getId();
			String currentActiveActivityId = runtimeService.getActiveActivityIds(currentProcessInstanceId).get(0);

			System.out.println("[Process Instance Data (unfinished)] processInstanceId : " + currentProcessInstanceId + " / activeActivityId : " + currentActiveActivityId);
		}

		List<HistoricProcessInstance> finishProcessInstanceList = historyService.createHistoricProcessInstanceQuery()
				.processDefinitionKey(processDefinitionKey)
				.finished()
				.list();

		// 조회 내용 조회
		for ( HistoricProcessInstance processInstance : finishProcessInstanceList )
		{
			String currentProcessInstanceId = processInstance.getId();

			System.out.println("[Process Instance Data (finished)] processInstanceId : " + currentProcessInstanceId);
		}
	}

	@Test
	public void completeApproval()
	{
		// 승인할 결재 processInstanceId
		String processInstanceId = "257501";

		// 결재 승은 완료 처리 (실제로는 로직이 포함 된 필요지점에서 호출되어야 함)
		imService.completeApproval(processInstanceId, "Y");
	}
}