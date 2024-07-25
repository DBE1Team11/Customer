<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.net.URLDecoder"%>
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
	    아이디: <input type="text" name="id" size="10" placeholder="8~12자리의 영대소문자와 숫자 조합"><br>
	    비밀번호: <input type="password" name="pw" size="10" placeholder="8~12자리의 영대소문자와 숫자 조합"><br>
	    비밀번호 확인 : <input type="password" name="pw2" size="10" ><br>
		전화번호: <input type="text" name="phone" size="10" placeholder="전화번호"><br>
	    이름: <input type="text" name="name" size="10" placeholder="이름"><br>
	    주소: <input type="text" name="address" size="10"placeholder="주소"><br>
	    남 : <input type="radio" name="sex" size="10" value="남자">
		여 : <input type="radio" name="sex" size="10" value="여자"><br>
	    이메일: <input type="email" name="email" size="10"placeholder="이메일"><br>
	    직업: <input type="text" name="job" size="10"placeholder="직업"><br>
	    <input type="submit" value="JOIN">
	</form>
	<form action="/customer/logout" method="GET">
        <input type="submit" value="LOGIN">
    </form>
</body>
</html>