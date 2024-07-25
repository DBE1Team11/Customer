<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<form action='/customer/login' method ="POST" onsubmit="return formCheck(this);">
		>ID: <input type="text" name="id" size="10" value=${cookie.idcheck.value}><br>
		>PASSWORD: <input type="password" name="pw" size="6" ><br>
		<div id="error" name="comment">${comment}</div>
		<div id="msg">
			<c:if test="${not empty param.msg}">
				<i class="fa fa-exclamation-circle"> ${param.msg}</i>
			</c:if>
		</div>
		<input type="checkbox" name="rememberID" value="true" ${empty cookie.id.value ? "":"checked"}> ID저장<br>
		<input type="submit" value="LOGIN">
	</form>

	<form action="/customer/join" method ="GET">
		<input type="submit" value="JOIN">
	</form>
	<script>
		function formCheck(frm) {
			let msg ='';
			if(frm.id.value.length==0) {
				setMessage('id를 입력해주세요.', frm.id);
				return false;
			}

			if(frm.pw.value.length==0) {
				setMessage('password를 입력해주세요.', frm.pw);
				return false;
			}

			return true;
		}

		function setMessage(msg, element){
			document.getElementById("msg").innerHTML = ` ${'${msg}'}`;

			if(element) {
				element.select();
			}
		}
	</script>
</body>
</html>