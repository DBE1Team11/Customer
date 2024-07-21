<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JoinForm</title>
</head>
<body>
	<form action="/customer/join" method="POST">
	    <div id="error" name="comment">${comment}</div>
	    아이디: <input type="text" name="id" size="10"><br>
	    비밀번호: <input type="password" name="pw" size="10"><br>
	    비밀번호 확인 : <input type="password" name="pw2" size="10"><br>
	    이름: <input type="text" name="name" size="10"><br>
	    주소: <input type="text" name="address" size="10"><br>
	    성별: <input type="text" name="sex" size="10"><br>
	    이메일: <input type="email" name="email" size="10"><br>
	    직업: <input type="text" name="job" size="10"><br>
	    <input type="submit" value="JOIN">
	</form>
	<form action="/customer/logout" method="GET">
        <input type="submit" value="LOGIN">
    </form>
</body>
</html>