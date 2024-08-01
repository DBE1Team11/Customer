<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>fastcampus</title>
    <link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>
    <div id="menu">
        <ul>
            <li id="logo">fastcampus</li>
            <li><a href="<c:url value='/'/>">Home</a></li>
            <li><a href="<c:url value='/board/list'/>">Board</a></li>
            <li><a href="<c:url value='/login/login'/>">login</a></li>
            <li><a href="<c:url value='/join'/>">Sign in</a></li>
            <li><a href=""><i class="fas fa-search small"></i></a></li>
        </ul>
    </div>
    <script>
        let msg = "${msg}";
        if(msg == "WRT_ERR") alert("작성 실패하였습니다.");
    </script>
    <div style = "">
    <h2>게시물 ${mode == "new" ? "글쓰기" : "읽기"}</h2>
    <form action = "" id ="form">
        <input type = "hidden" name = "bno" value="1" placeholder="제목을 입력해 주세요." ${mode == "new" ? '' : 'readonly="readonly"'}>
        <input type = "text" name ="title" placeholder="내용을 입력해 주세요." value="${boardDto.title}" ${mode == "new" ? '' : 'readonly="readonly"'}><br>
        <textarea name = "content" id = "" cols = "30" rows = "10" ${mode == "new" ? '' : 'readonly="readonly"'}>${boardDto.content}</textarea><br>
        <button type="button" id="writeBtn" class="btn">등록</button>
        <button type="button" id="modifyBtn" class="btn">수정</button>
        <button type="button" id="removeBtn" class="btn">삭제</button>
        <button type="button" id="listBtn" class="btn">목록</button>
    </form>
    </div>
<script>
    $(document).ready(function (){
        $("#removeBtn").on("click", function(){
            if(!confirm("정말로 삭제하시겠습니까?")) return;

            let form = $("#form");
            form.attr("action", "<c:url value='/board/remove?page=${page}&pageSize=${pageSize}'/>");
            form.attr("method", "post");
            form.submit();
        });

        $("#listBtn").on("click", function(){
            location.href="<c:url value='/board/list?page=${page}&pageSize=${pageSize}'/>";
        });
        $("#writeBtn").on("click", function(){
            let form = $("#form");
            form.attr("action", "<c:url value='/board/write'/>");
            form.attr("method", "post");
            form.submit();
        });
        $("#modifyBtn").on("click", function(){
            let form = $("#form");
            let isReadOnly = $("input[name=title]").attr('readonly');
            if(isReadOnly == true){
                $("input[name=title]").attr('readonly', false);
                $("textarea").attr('readonly', false);
                $('#modifyBtn').html("등록")
            }
            form.attr("action", "<c:url value='/board/modify'/>");
            form.attr("method", "post");
            form.submit();
        });
        $("input[name=title]", "#from").attr('readonly', false);
        $("textarea", "#form").attr('readonly',false);
    });
</script>
</body>
</html>