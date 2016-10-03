<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" href="../resources/css/qr.css" type="text/css"></link>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>微信登录--扫描二维码</title>
	</head>
	<body>
	    
		<div class='qr-wrapper'>
			<span class='qr-title'>用微信扫一扫进行登录</span>
			<img class='qr-img' src='${sessionScope.qrUrl}'/>
		</div>
	</body>
</html>