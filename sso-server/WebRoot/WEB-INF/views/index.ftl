<!DOCTYPE html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no, width=device-width">
    <title>统一授权中心</title>
</head>

<body>
<div algin="center">
    <div algin="center">
		<form id="login" action="${path}/login" method="post">
			username:<input type="text" name="username" value="Jason" /> </br>
			password:<input type="password" name="password" value="123456" />
			<input name="clientUrl" value="${clientUrl}" />
			</br>
			<input type="submit" value="Login" />
		</form>
	</div>
</div>
</body>
</html>