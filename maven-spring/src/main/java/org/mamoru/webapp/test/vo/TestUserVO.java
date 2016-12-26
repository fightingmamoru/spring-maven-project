package org.mamoru.webapp.test.vo;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

public class TestUserVO implements Serializable
{
	private static final long serialVersionUID = -3566482800584695090L;

	@NotEmpty
	private String userType;

	private String userId;
	private String userName;

	private String companyId;
	private String companyName;

	public String getUserType()
	{
		return userType;
	}

	public void setUserType(String userType)
	{
		this.userType = userType;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getCompanyId()
	{
		return companyId;
	}

	public void setCompanyId(String companyId)
	{
		this.companyId = companyId;
	}

	public String getCompanyName()
	{
		return companyName;
	}

	public void setCompanyName(String companyName)
	{
		this.companyName = companyName;
	}
}