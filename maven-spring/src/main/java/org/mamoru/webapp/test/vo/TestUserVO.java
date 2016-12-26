package org.mamoru.webapp.test.vo;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.AssertTrue;
import java.io.Serializable;

public class TestUserVO implements Serializable
{
	private static final long serialVersionUID = -3566482800584695090L;

	@NotEmpty
	private String userType;

	// userType = 'A' 일 경우 검증할 필드
	@AssertTrue
	private boolean isUserIdOK()
	{
		return !(this.userType.equals("USER")) || StringUtils.isNotEmpty(this.userId);
	}

	private String userId;
	private boolean userIdOK;

//	@AssertTrue
//	public boolean isUserName()
//	{
//		return this.userType==null || !(this.userType.equals("USER")) || StringUtils.isNotEmpty(this.userType);
//	}

	private String userName;

	// userType = 'B' 일 경우 검증할 필드
//	@AssertTrue
//	public boolean isCompanyId()
//	{
//		return this.userType==null || !(this.userType.equals("COMPANY")) || StringUtils.isNotEmpty(this.userType);
//	}

	private String companyId;


//	@AssertTrue
//	public boolean isCompanyName()
//	{
//		return this.userType==null || !(this.userType.equals("COMPANY")) || StringUtils.isNotEmpty(this.userType);
//	}

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