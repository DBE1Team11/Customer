<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<head>
    <title>Title</title>
</head>
<body>
  ${id}님 로그인 완료
    <form action="/customer/logout" method="GET">
        <input type="submit" value="LOGOUT">
    </form>
  <form action="/customer/info" method ="GET">
      <input type="hidden" name="id" value="${id}">
      <input type="submit" value="INFO">
  </form>
</body>
</html>
