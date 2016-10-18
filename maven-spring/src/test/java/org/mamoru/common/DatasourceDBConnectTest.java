package org.mamoru.common;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// spring-test annotation
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {
		"classpath*:spring/root-context.xml",
		"classpath*:spring/servlet/servlet-common-context.xml",
		"classpath*:spring/servlet/servlet-datasource-context.xml"
})
public class DatasourceDBConnectTest
{
	// Logger
	private static Logger LOGGER = LogManager.getLogger(DatasourceDBConnectTest.class);

	@Autowired
	ApplicationContext applicationContext;

	@Test
	public void connectTest() throws SQLException
	{
		BasicDataSource basicDataSource = (BasicDataSource) applicationContext.getBean("basicDataSource");

		Connection connection = basicDataSource.getConnection();

		String selectSql = "SELECT USER_ID FROM TEST_USERS";

		PreparedStatement pstmt = connection.prepareStatement(selectSql);
		ResultSet rs = pstmt.executeQuery();

		while ( rs.next() )
		{
			LOGGER.debug("[Data Item] {}", rs.getString("USER_ID"));
		}

		pstmt.close();
		connection.close();
		basicDataSource.close();
	}
}