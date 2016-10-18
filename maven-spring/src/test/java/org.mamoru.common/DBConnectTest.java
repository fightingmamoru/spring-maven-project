package org.mamoru.common;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnectTest
{
	@Test
	public void selectTable() throws SQLException
	{
		BasicDataSource basicDataSource = new BasicDataSource();

		basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		basicDataSource.setUrl("jdbc:mysql://124.50.85.159:3307/fightingmamoru");
		basicDataSource.setUsername("fightingmamoru");
		basicDataSource.setPassword("mncd0218!");

		Connection connection = basicDataSource.getConnection();

		String selectSql = "SELECT USER_ID FROM TEST_USERS";

		PreparedStatement pstmt = connection.prepareStatement(selectSql);
		ResultSet rs = pstmt.executeQuery();

		while ( rs.next() )
		{
			System.out.println("[Data Item] " + rs.getString("USER_ID"));
		}

		pstmt.close();
		connection.close();
		basicDataSource.close();
	}
}