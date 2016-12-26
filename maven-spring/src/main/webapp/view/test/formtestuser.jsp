<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<title>TestUser Edit Test</title>
	</head>

	<body>
		<h2>TestUser Edit Test</h2>
		<h4>- TestUser edit and checking validation.</h4>

		<form:form commandName="testUserVO" id="editForm" name="editForm" method="post" action="updateTestUser">
			사용자 타입 :
			<form:select path="userType">
				<form:option value="USER">개인사용자</form:option>
				<form:option value="COMPANY">회사사용자</form:option>
			</form:select>

			<br/>

			사용자 아이디 :
			<form:input path="userId"/>
			<form:errors path="userId"/>
			<form:errors path="userIdOK"/>

			<br/>

			사용자 이름 :
			<form:input path="userName"/>
			<form:errors path="userName"/>

			<br/>

			회사 아이디 :
			<form:input path="companyId"/>
			<form:errors path="companyId"/>

			<br/>

			회사 이름 :
			<form:input path="companyName"/>
			<form:errors path="companyName"/>

			<form:button value="저장"/>
		</form:form>
	</body>
</html>