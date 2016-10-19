package org.mamoru.webapp.hellomybatis.service.impl;

import org.mamoru.webapp.hellomybatis.mapper.HelloMybatisMapper;
import org.mamoru.webapp.hellomybatis.service.HelloMybatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class HelloMybatisServiceImpl implements HelloMybatisService
{
	private final HelloMybatisMapper helloMybatisMapper;

	@Autowired
	public HelloMybatisServiceImpl(HelloMybatisMapper helloMybatisMapper)
	{
		this.helloMybatisMapper = helloMybatisMapper;
	}

	public List<String> selectUserList() throws SQLException
	{
		return helloMybatisMapper.selectUserList();
	}
}