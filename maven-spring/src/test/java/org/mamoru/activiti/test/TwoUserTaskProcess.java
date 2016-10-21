package org.mamoru.activiti.test;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
// @WebAppConfiguration (over Spring 4.3.3.RELEASE)
@ContextConfiguration(locations = {
		"classpath*:spring/root-context.xml",
		"classpath*:spring/servlet/servlet-common-context.xml",
		"classpath*:spring/servlet/servlet-datasource-context.xml",
		"classpath*:spring/servlet/servlet-mybatis-context.xml",
		"classpath*:spring/servlet/servlet-activiti-context.xml"
})
public class TwoUserTaskProcess
{
	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private HistoryService historyService;

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
		String processInstanceId = runtimeService.startProcessInstanceByKey("TwoUserTaskProcess").getId();

		// Set Assignee for usertask1
		String taskId = getActiveTaskId(processInstanceId);
		String assigneeUserId = "user01";

		taskService.setAssignee(taskId, assigneeUserId);

		// Print Process Instance List
		printProcessInstanceList(processInstanceId);

		// Print Activity List
		printActivityList(processInstanceId);


		System.out.println(	"================================================================================================================================================\n" +
							"| Complete usertask1                                                                                                                           |\n" +
							"================================================================================================================================================\n");

		// complete usertask1
		taskService.complete(taskId);

		// Print Process Instance List
		printProcessInstanceList(processInstanceId);

		// Print Activity List
		printActivityList(processInstanceId);


		System.out.println(	"================================================================================================================================================\n" +
							"| Complete usertask2                                                                                                                           |\n" +
							"================================================================================================================================================\n");


		// Set Assignee for usertask2
		taskId = getActiveTaskId(processInstanceId);
		assigneeUserId = "user02";

		taskService.setAssignee(taskId, assigneeUserId);

		// complete usertask2
		taskService.complete(taskId);

		// Print Process Instance List
		printProcessInstanceList(processInstanceId);

		// Print Activity List
		printActivityList(processInstanceId);
	}

	private void printProcessInstanceList(String processInstanceId)
	{
		List<HistoricProcessInstance> processInstanceList = StringUtils.isEmpty(processInstanceId) ?
				historyService.createHistoricProcessInstanceQuery().list()
				:
				historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).list();

		System.out.println(	"================================================================================================================================================\n" +
							"| ProcessInstance List                                                                                                                         |\n" +
							"================================================================================================================================================\n" +
							"| processDefinitionName          | processInstanceId | startActivityId      | startTime           | endTime             | deleteReason        |\n" +
							"================================================================================================================================================");

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		for ( HistoricProcessInstance processInstance : processInstanceList )
		{
			String pProcessDefinitionName = String.format("%30s", processInstance.getProcessDefinitionName());
			String pProcessInstanceId = String.format("%17s", processInstance.getId());
			String pStartActivityId = String.format("%20s", processInstance.getStartActivityId());

			Date startTime = processInstance.getStartTime();
			String pStartTime = String.format("%19s", startTime==null ? null : dateFormat.format(startTime));

			Date endTime = processInstance.getEndTime();
			String pEndTime = String.format("%19s", endTime==null ? null : dateFormat.format(endTime));

			String pDeleteReason = String.format("%20s", processInstance.getDeleteReason());

			System.out.println(	"| " + pProcessDefinitionName + " | " + pProcessInstanceId + " | " +  pStartActivityId + " | " + pStartTime + " | " + pEndTime + " | " + pDeleteReason + " |\n"+
								"------------------------------------------------------------------------------------------------------------------------------------------------");
		}

		System.out.println("");
	}

	private void printActivityList(String processInstanceId)
	{
		List<HistoricActivityInstance> activityList = StringUtils.isEmpty(processInstanceId) ?
				historyService.createHistoricActivityInstanceQuery().list()
				:
				historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).list();

		System.out.println(	"========================================================================================================================================================\n" +
							"| Activity List                                                                                                                                        |\n" +
							"========================================================================================================================================================\n" +
							"| id         | activityId           | activityType | activityName         | processInstanceId | asignee    | startTime           | endTime             |\n" +
							"========================================================================================================================================================");

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		for ( HistoricActivityInstance activity : activityList )
		{
			String pId = String.format("%10s", activity.getId());
			String pActivityId = String.format("%20s", activity.getActivityId());
			String pActivityType = String.format("%12s", activity.getActivityType());
			String pActivityName = String.format("%20s", activity.getActivityName());
			String pProcessInstanceId = String.format("%17s", activity.getProcessInstanceId());
			String pAssigne = String.format("%10s", activity.getAssignee());

			Date startTime = activity.getStartTime();
			String pStartTime = String.format("%19s", startTime==null ? null : dateFormat.format(startTime));

			Date endTime = activity.getEndTime();
			String pEndTime = String.format("%19s", endTime==null ? null : dateFormat.format(endTime));

			System.out.println(	"| " + pId + " | " + pActivityId + " | " + pActivityType + " | " + pActivityName + " | " + pProcessInstanceId + " | " + pAssigne + " | " + pStartTime + " | " + pEndTime + " |\n"+
								"--------------------------------------------------------------------------------------------------------------------------------------------------------");
		}

		System.out.println("");
	}

	private void printTaskInstanceList(String processInstanceId)
	{
		List<HistoricTaskInstance> taskInstanceList = StringUtils.isEmpty(processInstanceId) ?
				historyService.createHistoricTaskInstanceQuery().list()
				:
				historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstanceId).list();

		for ( HistoricTaskInstance taskInstance : taskInstanceList )
		{
			System.out.println("[TaskInstance] id: " + taskInstance.getId() + " / asignee: " + taskInstance.getAssignee());
		}
	}

	private String getActiveTaskId(String processInstanceId)
	{
		return taskService.createTaskQuery().processInstanceId(processInstanceId)
				.active().list().get(0).getId();
	}
}