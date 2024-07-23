<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title>INFO</title>
</head>
<body>
  ${id}님 정보 <br>
  이름 : ${name} <br>
  전화번호 : ${phone} <br>
  주소 : ${address} <br>
  성별 : ${sex} <br>
  이메일 : ${email} <br>
  직업 : ${job} <br>
  생성날짜 : ${date} <br>
  <form action="/customer/welcome" method="GET">
    <input type="hidden" name="id" value="${id}">
    <input type="submit" value="BACK">
  </form>
  <form action="/customer/updateCustomer" method="GET">
      <input type="submit" value="UPDATE">
  </form>
</body>
</html>
