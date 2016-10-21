package org.mamoru.common.activiti.test;

import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PrintActivitiInformation
{
	public static void printProcessInstanceList(List<HistoricProcessInstance> processInstanceList)
	{
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

	public static void printActivityList(List<HistoricActivityInstance> activityList)
	{
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
}
