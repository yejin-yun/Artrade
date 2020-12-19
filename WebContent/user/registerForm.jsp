<%@page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import="model.*" %>
<html>
<head>
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
    		width: 50%;
    		margin-right: auto;
    		margin-left: auto;
    		margin-top: 10%;
    	}
    	form > div {
    		margin-top: 1%;
    		width: 100%;
    		margin-right: auto;
    		margin-left: auto;
    	}
    	
		div > input {
			display: inline-block;
			width: 500px;
			margin-left: auto;
			margin-right: auto;
		}
		
		label {
			display: inline-block;
		}
		
    </style>
     <!--JQuery -->
    <script src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
    <script src="<c:url value='/js/base.js' />" ></script>
    <script src="<c:url value='/js/img_slide.js' />"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/user.css' />" type="text/css">
<script>
function userCreate(targetUri) {
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
   if (form.password.value != form.password2.value) {
      alert("비밀번호가 일치하지 않습니다.");
      form.name.focus();
      return false;
   }
   if (form.name.value == "") {
      alert("이름을 입력하십시오.");
      form.name.focus();
      return false;
   }
   var emailExp = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
   if(emailExp.test(form.email.value)==false) {
      alert("이메일 형식이 올바르지 않습니다.");
      form.email.focus();
      return false;
   }
   var phoneExp = /^\d{2,3}-\d{3,4}-\d{4}$/;
   if(phoneExp.test(form.phone.value)==false) {
      alert("전화번호 형식이 올바르지 않습니다.");
      form.phone.focus();
      return false;
   }
   moveTarget(targetUri);
}

function moveTarget(targetUri) {
	   form.action = targetUri;
	   form.submit();
	}

function check() {
	if(existingUser(form.userId.value) == 1)
		alert("해당 아이디는 사용중입니다.");
	else
		alert("해당 아이디는 사용 가능 합니다.");
	
}

</script>
</head>
<body>

<%@ include file="../main/header.jsp" %>
	<div id="main_div" class="w3-center">
		<h1>회원 가입</h1> (*는 필수)
		<c:if test="${registerFailed}">
	      <font color="red"><c:out value="${exception.getMessage()}" /></font>
	    </c:if>
		<form name="form" method="POST" action="" class="form-horizontal" style="margin-top: 10%;"> <!-- form에 action이 있으면 서브밋 타입의 버튼이 아니여도 보내줌. -->
			<div class="form-group form-inline">
    				<label for="userId" style="margin-left: 15%;">ID*: </label>
    				<input type="text" class="form-control" name="userId" id="userId" placeholder="Enter ID">
    				<button type="submit" class="btn btn-info" style="margin-top: 10px;">중복 확인</button>
    		</div>	
			<div class="form-group form-inline">
    				<label for="pwd">Password*: </label>
    				<input type="password" class="form-control" id="pwd" name="password" placeholder="Enter password">
    				<p>(영문, 숫자, 특수 문자 포함 8~20자)</p>
    		</div>	
    		<div class="form-group form-inline">
    				<label for="pwd2">Password 재확인*: </label>
    				<input type="password" class="form-control" id="pwd2" name="password2" placeholder="Enter password">
    		</div>	
    		<div class="form-group form-inline">
    				<label for="name">이름*: </label>
    				<input type="text" class="form-control" name="name" id="name" placeholder="Enter Name">
    		</div>	
    		<div class="form-group form-inline">
    				<label for="phoneNum">전화번호*: </label>
    				<input type="tel" class="form-control" name="phone" id="phoneNum" placeholder="Enter Phone Number">
    		</div>	
    		<div class="form-group form-inline">
			    <label for="email">Email*:</label>
		    	<input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
			</div>
			<div class="form-group form-inline" style="margin-top: 0;">
				<button type="reset" class="btn btn-info">취소</button>
				<button class="btn btn-info" onClick="userCreate('<c:url value='/user/register' />')">확인</button>
			</div>
		</form>
	</div>
	<footer class="w3-center" style="margin-top: 500px;">
        <div style="padding: 30px 0;"><p>Copyright (c) Artrade  |    2018년 5월 22일~ </p><p>대표: 윤 예진</p></div>
    </footer>
</body>
</html>