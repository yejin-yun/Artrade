<%@page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
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
    
    
    <script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script> 
		//https://cublip.tistory.com/326	
		$(document).on("keyup", ".phoneNumber", function() { 
			$(this).val( $(this).val().replace(/[^0-9]/g, "").replace(/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})$/,"$1-$2-$3").replace("--", "-") ); 
		});
		$('#confirmBtn').click(function() { //https://hee-kkk.tistory.com/22 //https://dotheright.tistory.com/165
			var regExpPw = /(?=.*\d{1,20})(?=.*[~`!@#$%\^&*()-+=]{1,20})(?=.*[a-zA-Z]{1,20}).{8,20}$/;
			var result = regExpPw.test($('#pwd').val());
			if(!result) {
				alert('비밀번호는 영문, 숫자, 특수 문자 포함하여 8~20자여야 합니다.');
				return false;
			}
		});
	</script>
	<script>
	function chkPW(){

		 var pw = $("#pwd").val();
		 var num = pw.search(/[0-9]/g);
		 var eng = pw.search(/[a-z]/ig);
		 var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

		 if(pw.length < 8 || pw.length > 20){

		  alert("8자리 ~ 20자리 이내로 입력해주세요.");
		  return false;
		 }else if(pw.search(/\s/) != -1){
		  alert("비밀번호는 공백 없이 입력해주세요.");
		  return false;
		 }else if(num < 0 || eng < 0 || spe < 0 ){
		  alert("영문,숫자, 특수문자를 혼합하여 입력해주세요.");
		  return false;
		 }else {
			console.log("통과"); 
		    return true;
		 }

	}
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

	   if(!chkPW()) {
		   form.password.focus();
		   return false;
	   }
	   
	   
	   if (form.password.value != form.password2.value) {
	      alert("비밀번호가 일치하지 않습니다.");
	      form.password.focus();
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
	
	</script>

	
</head>
<body>

<%@ include file="../main/header.jsp" %>
	<div id="main_div" class="w3-center">
		<h1>회원 가입</h1> (*는 필수)
		<c:if test="${registerFailed}">
	      <font color="red"><c:out value="${exception.getMessage()}" /></font>
	    </c:if>
	    <c:if test="${noDuplication == 1}" >
		      <font color="red"><c:out value="사용가능한 id입니다." /></font>
	    </c:if>
		<form name="form" method="POST" action="" class="form-horizontal" style="margin-top: 10%;"> <!-- form에 action이 있으면 서브밋 타입의 버튼이 아니여도 보내줌. -->
			<div class="form-group form-inline">
    				<label for="userId" style="margin-left: 15%;">ID*: </label>
    				<c:if test="${!(empty user.userId)}">
    					<input type="text" class="form-control" name="userId" id="userId" value="${user.userId }">
    				</c:if>
    				<c:if test="${empty user.userId}">
    					<input type="text" class="form-control" name="userId" id="userId" placeholder="Enter ID">
    				</c:if>
    				
    				<button type="button" class="btn btn-info" style="margin-top: 10px;" onClick="moveTarget('<c:url value='/user/register'><c:param name="submitBtn" value="0" /></c:url>')">중복 확인</button>
    		</div>	
			<div class="form-group form-inline">
    				<label for="pwd">Password*: </label>
    				<input type="password" class="form-control pw" id="pwd" name="password" placeholder="Enter password">
    				<p>(영문, 숫자, 특수 문자 포함 8~20자)</p>
    		</div>	
    		<div class="form-group form-inline">
    				<label for="pwd2">Password 재확인*: </label>
    				<input type="password" class="form-control pw" id="pwd2" name="password2" placeholder="Enter password">
    		</div>	
    		<div class="form-group form-inline">
    				<label for="name">이름*: </label>
    				<c:if test="${!(empty user.name)}">
    					<input type="text" class="form-control" name="name" id="name" value="${user.name }">
    				</c:if>
    				<c:if test="${empty user.name}">
    					<input type="text" class="form-control" name="name" id="name" placeholder="Enter Name">
    				</c:if>
    		</div>	
    		<div class="form-group form-inline">
    				<label for="phoneNum">전화번호*: </label>
    				<c:if test="${!(empty user.phone)}">
    					<input type="tel" class="form-control phoneNumber" name="phone" id="phoneNum" value="${user.phone }">
    				</c:if>
    				<c:if test="${empty user.phone}">
    					<input type="tel" class="form-control phoneNumber" name="phone" id="phoneNum" placeholder="Enter Phone Number">
    				</c:if>
    		</div>	
    		<div class="form-group form-inline">
			    <label for="email">Email*:</label>
			    <c:if test="${!(empty user.email)}">
			   		<input type="email" class="form-control" id="email" value="${user.email }" name="email">
    			</c:if>
    			<c:if test="${empty user.email}">
    				<input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
    			</c:if>
			</div>
			<div class="form-group form-inline" style="margin-top: 0;">
				<button type="reset" class="btn btn-info">취소</button>
				<button class="btn btn-info" id="confirmBtn" onClick="userCreate('<c:url value='/user/register'><c:param name="submitBtn" value="1" /></c:url>')">확인</button>
			</div>
		</form>
	</div>
	<footer class="w3-center" style="margin-top: 500px;">
        <div style="padding: 30px 0;"><p>Copyright (c) Artrade  |    2018년 5월 22일~ </p><p>대표: 윤 예진</p></div>
    </footer>
</body>
</html>