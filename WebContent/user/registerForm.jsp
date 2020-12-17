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
    
     <!--JQuery -->
    <script src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
    <script src="<c:url value='/js/base.js' />" ></script>
    <script src="<c:url value='/js/img_slide.js' />"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/user.css' />" type="text/css">
<script>
function userCreate() {
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
   form.submit();
}

function userList(targetUri) {
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

<%@ include file="../main/header.jsp" %>

<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>   
<!-- 화면 로드 시 서버로부터 커뮤니티 목록을 가져와 commSelect 메뉴 생성 -->
<br>
<!-- registration form  -->
<form name="form" method="POST" action="<c:url value='/user/register' />">
  <table style="width: 100%;">
    <tr>
      
     <td>
             
       <!-- 회원가입이 실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
        <c:if test="${registerFailed}">
         <font color="red"><c:out value="${exception.getMessage()}" /></font>
       </c:if>
       <br>   
        <tr>

            
            <td colspan="2"> </td>
            
            
            

        </tr>
        <tr>
            <td rowspan="11"> </td>
            <td colspan="2" style="text-align: center; font-size: 30px;">회원가입</td>        
            <td> </td>
        </tr>

        <tr>
            <td>ID</td>
            <td><input type="text" name="userId"></td>
            <td><button onclick="">중복확인</button></td>
        </tr>
        <tr>
            <td>PW </td>
            <td><input type="password" name="password"></td>
            <td> 영문, 숫자, 특수 문자 포함 8~20자</td>
        </tr>
        <tr>
            <td>PW 재확인 </td>
            <td><input type="password" name="password2"></td>
            <td></td>
        </tr>
        <tr>
            <tr>
            <td>이름 </td>
            <td><input type="text" name="name"></td>
            <td rowspan="11"> </td>
        </tr>
        <tr>
            <td>닉네임</td>
            <td><input type="text" name="nickname"></td>
        <tr>
        <tr>
            <td>전화번호</td>
            <td><input type="text" name="phone"></td>
        <tr>
        <tr>
            <td>이메일</td>
            <td><input type="text" name="email"></td>
        </tr>
        <tr>
        <td colspan="4" style="text-align: center;"><button onclick="userCreate()">회원가입</button></td>
        </tr>
    </table>
</form>
</body>
</html>