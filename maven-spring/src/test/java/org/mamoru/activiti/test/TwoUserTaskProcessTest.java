package org.mamoru.activiti.test;

import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mamoru.common.activiti.service.ActivitiCommonService;
import org.mamoru.common.activiti.test.PrintActivitiInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
// @WebAppConfiguration (over Spring 4.3.3.RELEASE)
@ContextConfiguration(locations = {
		"classpath*:spring/root-context.xml",
		"classpath*:spring/servlet/servlet-common-context.xml",
		"classpath*:spring/servlet/servlet-datasource-context.xml",
		"classpath*:spring/servlet/servlet-mybatis-context.xml",
		"classpath*:spring/servlet/servlet-activiti-context.xml"
})
public class TwoUserTaskProcessTest
{
	@Autowired
	private ActivitiCommonService activitiCommonService;

	@Autowired
	@Rule
	public ActivitiRule activitiSpringRule;

	@Test
	public void test()
	{
		System.out.println(	"================================================================================================================================================\n" +
							"| Start ProcessInstance                                                                                                                        |\n" +
							"================================================================================================================================================\n");

		// Start Process Instance
		String processDefinitionKey = "TwoUserTaskProcess";
		String processInstanceId = activitiCommonService.startProcessInstanceByKey(processDefinitionKey);

		// Set Assignee for usertask1
		String taskId = activitiCommonService.getActiveTaskId(processInstanceId);
		String assigneeUserId = "user01";

		activitiCommonService.setTaskAssignee(taskId, assigneeUserId);

		// Print Process Instance List
		PrintActivitiInformation.printProcessInstanceList(activitiCommonService.getProcessInstanceList(processInstanceId));

		// Print Activity List
		PrintActivitiInformation.printActivityList(activitiCommonService.getActivityList(processInstanceId));


		System.out.println(	"================================================================================================================================================\n" +
							"| Complete usertask1                                                                                                                           |\n" +
							"================================================================================================================================================\n");

		// complete usertask1
		activitiCommonService.completeTask(taskId);

		// Print Process Instance List
		PrintActivitiInformation.printProcessInstanceList(activitiCommonService.getProcessInstanceList(processInstanceId));

		// Print Activity List
		PrintActivitiInformation.printActivityList(activitiCommonService.getActivityList(processInstanceId));


		System.out.println(	"================================================================================================================================================\n" +
							"| Complete usertask2                                                                                                                           |\n" +
							"================================================================================================================================================\n");


		// Set Assignee for usertask2
		taskId = activitiCommonService.getActiveTaskId(processInstanceId);
		assigneeUserId = "user02";

		activitiCommonService.setTaskAssignee(taskId, assigneeUserId);

		// complete usertask2
		activitiCommonService.completeTask(taskId);

		// Print Process Instance List
		PrintActivitiInformation.printProcessInstanceList(activitiCommonService.getProcessInstanceList(processInstanceId));

		// Print Activity List
		PrintActivitiInformation.printActivityList(activitiCommonService.getActivityList(processInstanceId));
	}
}