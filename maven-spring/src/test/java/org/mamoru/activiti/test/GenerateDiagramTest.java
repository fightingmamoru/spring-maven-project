package org.mamoru.activiti.test;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.util.ArrayList;
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
public class GenerateDiagramTest
{
	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	@Rule
	private ActivitiRule activitiSpringRule;

	@Test
	public void test() throws IOException
	{
		ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity)repositoryService.getProcessDefinition("jstprocess:1:67506");

		if ( processDefinitionEntity != null && processDefinitionEntity.isGraphicalNotationDefined() )
		{
			BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionEntity.getId());

			DefaultProcessDiagramGenerator generator = new DefaultProcessDiagramGenerator();
			List<String> activeActivities = new ArrayList<String>();

			InputStream resource = generator.generateDiagram(bpmnModel, "png", activeActivities);
			OutputStream outputStream = new FileOutputStream(new File("c:/test/" + processDefinitionEntity.getName() + ".png"));

			int read=0;
			byte[] bytes = new byte[1024];

			while ((read = resource.read(bytes)) > 0 )
			{
				outputStream.write(bytes, 0, read);
			}

			outputStream.close();
			resource.close();
		}
		else
		{
			throw new ActivitiIllegalArgumentException("Process Definition Id \"" + processDefinitionEntity.getId() + "\" is not having BPMN DI" );
		}
	}
}
