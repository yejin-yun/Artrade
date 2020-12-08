<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*, java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head> <!-- 이전 코드 css -> 작품보기.html에 있음 -->
    <title>Artrade</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta content="text/html; charset=iso-8859-2" http-equiv="Content-Type">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/base.css' />" >
     <link type="text/css" rel="stylesheet" href="<c:url value='/css/detail.css' />" >
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/view.css' />" >
    
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <style>
    	#main_div {
    		 margin-top: 15%;
    		 width: 80%;
    		 height: 100%;
    		 margin-right: auto;
    		 margin-left: auto;
    	}
    	#pwd {
    		width: 60%;
    		margin-right: auto;
    		margin-left: auto;
    	}
    	#checkBtn {
    		margin-top: 7%;
    	} 
    	#fg {
    		margin-top: 5%;
    	}
    </style>
    <script src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
    <script src="<c:url value='/js/base.js' />" ></script>
	<script>
		function moveTarget(targetUri) {
		   form.action = targetUri;
		   form.submit();
		}
		
		function checkPw() {
			if (form.destination.value == "") { // form은 name속성이다. 
		        alert("비밀번호를 입력해주세요."+form.destination.value);
		        form.destination.focus();
		        return false;
		     } 
		}
		    
	</script>
</head>
<body>
	<%@ include file="../main/header.jsp" %>
	<%-- ${!(empty exception)}는 exception이 없을 경우 false, 있을 경우 true가 나온다.(not을 해줬기 때문)
		 ${deleteFailed} --> 패스워드 입력에 실패한 경운 true가 반환 됨. 
	 --%>
	<div id="main_div" class="w3-center">
		<h1>회원 탈퇴</h1>
		<form method="POST" action="<c:url value='/user/quit' />" name="form" class="form-inline">
			<div class="form-group" id="fg">
    			<label for="pwd">비밀번호 재확인: </label>
    			<input type="password" class="form-control" id="pwd" name="password" placeholder="Enter password">
    			<button type="submit" class="btn btn-info" id="checkBtn">확인</button>
  			</div>
		</form>
	</div>
	<c:if test="${deleteFailed}" >
		<script type="text/javascript">
			alert('비밀번호가 틀렸습니다');
		</script>
	</c:if>
	<%-- 
	<c:if test="${deleteClear}" >
		<script type="text/javascript">
			alert('성공적으로 탈퇴되었습니다.');
		</script>
	</c:if>
	--%>
	<footer class="w3-center" style="margin-top: 500px;">
        <div style="padding: 30px 0;"><p>Copyright (c) Artrade  |    2018년 5월 22일~ </p><p>대표: 윤 예진</p></div>
    </footer>
</body>
</html>