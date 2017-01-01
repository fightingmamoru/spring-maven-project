<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<title>${title}</title>

		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

		<script type="text/javascript">
			function goParameterTest()
			{
				var form = $("<form/>", {
					"action" : "/hellospring/paramTest/edit",
					"method" : "POST"
				});

				var param = $("<input/>", {
					"id" : "username",
					"name" : "username",
					"type" : "hidden",
					"value" : "mamoru"
				});

				form.append(param).submit().remove();
			}
		</script>
	</head>

	<body>
		<h2>${message}</h2>
		<h4>- This is "HelloSpring" jsp body.</h4>

		<button type="button" onclick="goParameterTest()">파라미터 테스트</button>
	</body>
</html>