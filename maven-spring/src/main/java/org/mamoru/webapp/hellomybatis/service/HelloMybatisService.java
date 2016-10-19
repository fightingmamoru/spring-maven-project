package org.mamoru.webapp.hellomybatis.service;

import java.sql.SQLException;
import java.util.List;

public interface HelloMybatisService
{
	List<String> selectUserList() throws SQLException;
}