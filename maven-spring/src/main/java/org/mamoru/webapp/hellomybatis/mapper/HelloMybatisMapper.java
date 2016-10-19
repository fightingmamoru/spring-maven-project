package org.mamoru.webapp.hellomybatis.mapper;

import java.sql.SQLException;
import java.util.List;

public interface HelloMybatisMapper
{
	List<String> selectUserList() throws SQLException;
}