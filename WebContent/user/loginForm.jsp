<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.*, model.dao.*" %>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<title>로그인</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/user.css' />" type="text/css">
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta content="text/html; charset=iso-8859-2" http-equiv="Content-Type">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/base.css' />" >
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/detail.css' />" >
	<link rel=stylesheet href="<c:url value='/css/user.css' />" type="text/css">
     <!--JQuery -->
    <script src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
    <script src="<c:url value='/js/base.js' />" ></script>
    <script src="<c:url value='/js/img_slide.js' />"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	
	<style>
		section {
			width: 90%;
			margin-left: auto;
			margin-right: auto;
		}
		form {
			width: 50%;
			margin-left: auto;
			margin-right: auto;
			margin-top: 10%;
		}
		#container {
			width: 70%;
			margin-left: auto;
			margin-right: auto;
		}
		#inputForms {
			margin-top: 10%;
		}
		#btns {
			margin-top: 10%;
		}
	</style>
	
<script>
function login() {
   if (form.userId.value == "") {
      alert("사용자 ID를 입력하십시오.");
      form.userId.focus();
      return false;
   } 
   if (form.password.value == "") {
      alert("비밀번호를 입력하십시오.");
      form.password.focus();
      return false;
   }      
   //form.submit();
}

function userCreate(targetUri) {
   form.action = targetUri; // action을 바꾸고 제출하는 거라 method는 안바꾸면 post로 감.
   form.method = 'GET'; //get으로 넘겨야 jsp가 뜸.
   form.submit();
}
</script>

</head>
<body bgcolor=#FFFFFF text=#000000 >
<%@ include file="../main/header.jsp" %>

<!-- login form  -->
<section>
<form name="form" method="POST" action="<c:url value='/user/login' />">
	<div id="container" >
	  	<h2>로그인</h2>
	  	<div id="inputForms">
		  	ID: <input name="userId" class="w3-input w3-border w3-animate-input w3-round-large" type="text" style="width:50%">
		  	PW: <input name="password" class="w3-input w3-border w3-animate-input w3-round-large" type="password" style="width:50%">
	  	</div>
	  	<div id="btns">
	  		<button type="submit" onClick="login()"class="w3-button w3-black">로그인</button>
	  		
	  		<button class="w3-button w3-black" onClick="userCreate('<c:url value='/user/register' />')">회원가입</button>
	  	</div>
 	</div>
</form>
</section>
	<div class="footer">
     	<footer class="w3-center">
            <div style="padding: 30px 0;"><p>Copyright (c) Artrade  |    2018년 5월 22일~ </p><p>대표: 윤 예진</p></div>
        </footer>
    </div>
</body>
</html>
