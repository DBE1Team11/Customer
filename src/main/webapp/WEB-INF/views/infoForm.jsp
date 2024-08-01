<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.net.URLDecoder"%>
<html>
<head>
    <title>INFO</title>
</head>
<body>
  ${id}님 정보 <br>

  이름 : ${sessionScope.name} <br>
  전화번호 : ${customer.phone} <br>
  주소 : ${customer.address} <br>
  성별 : ${customer.sex} <br>
  이메일 : ${customer.email} <br>
  직업 : ${customer.job} <br>
  생성날짜 : ${customer.date} <br>
  <form action="/customer/board/list" method="GET">
    <input type="hidden" name="id" value="${id}">
    <input type="submit" value="BACK">
  </form>
  <form action="/customer/updateCustomer" method="GET">
      <input type="submit" value="UPDATE">
  </form>
</body>
</html>
