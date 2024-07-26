<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<head>
    <title>error</title>
</head>
<body>
${msg} 에러 발생<br>
에러 내용 : ${pageContext.exception}<br>
<c:if test="true">
   <img src="img/${msg}.png">
</c:if>

</body>
</html>
