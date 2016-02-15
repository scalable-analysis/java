<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./resources/CSS/common.css" type="text/css"/>
<title>Insert title here</title>
</head>
<body class="bg">
 
	<div class="title">dmpDemo</div>
	<div Class="lg">
		<form action="common/login.action" method="POST">
			<div class="lg_top"></div>
			<div class="lg_main">
				<div class="lg_m_1">
					<input name="username" class="ur" autofocus="autofoucs"/> <input name="password"
						type="password" class="pw" />
				</div>
			</div>
			<div class="lg_foot">
				<input type="submit" value="Login In" class="bn" />
			</div>
		</form>
	</div>

</body>
</html>