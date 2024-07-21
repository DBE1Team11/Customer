<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>loginForm</title>
</head>
<body>
	<form action="/customer/login" method ="POST">
		>ID: <input type="text" name="id" size="10"><br>
		>PASSWORD: <input type="password" name="pw" size="6" ><br>
		<div id="error" name="comment">${comment}</div>
		<input type="submit" value="LOGIN">
	</form>
	<form action="/customer/join" method ="GET">
		<input type="submit" value="JOIN">
	</form>

</body>
</html>