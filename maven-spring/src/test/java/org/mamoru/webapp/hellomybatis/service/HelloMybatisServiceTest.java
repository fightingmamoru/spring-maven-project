package org.mamoru.webapp.hellomybatis.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.SQLException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {
		"classpath*:spring/root-context.xml",
		"classpath*:spring/servlet/servlet-common-context.xml",
		"classpath*:spring/servlet/servlet-datasource-context.xml",
		"classpath*:spring/servlet/servlet-mybatis-context.xml"
})
public class HelloMybatisServiceTest
{
	@Autowired
	private HelloMybatisService helloMybatisService;

	@Test
	public void getUserIds() throws SQLException
	{
		List<String> userIds = helloMybatisService.selectUserList();

		for ( String userId : userIds )
		{
			System.out.println("[UserId] " + userId);
		}
	}
}