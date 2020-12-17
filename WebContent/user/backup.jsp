<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>로그인</title>

<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <!-- Loding font -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:300,700" rel="stylesheet">

    <!-- Custom Styles -->
    <link rel="stylesheet" type="text/css" href="./styles.css">
    
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/user.css' />" type="text/css">
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
   form.action = targetUri;
   form.submit();
}
</script>
<style>
.login {
   font-size: 30pt;

}


</style>


</head>

<%@ include file="../main/header.jsp" %>


<body bgcolor=#FFFFFF text=#000000 >
<br>
<!-- login form  -->

<br>
<br>
<br>
<br>
<br>
<br>

<br>
<br>
<div class="table" style="text-align:center; margin:0 auto; width:40%; height:70%;">
<center>
<form name="form" method="POST" action="<c:url value='/user/login' />">
  
  <table style="width:40%; text-align:center; font-size:15pt;">
  
  <tr>
  <td colspan="4" style="font-size:20pt;">로그인</td>
  </tr>
  
  <tr>
  <td></td>
  <td>ID</td>
  <td><input type="text" name="userId"> </td>
  <td></td>
  </tr>
  
  <tr>
  <td></td>
  <td>PW</td>
  <td><input type="password" name="password"> </td>
  <td></td>
  </tr>

  
  <tr>
  <td></td>
  <td colspan="2"> <input type="submit" value="로그인" onClick="login()">  <a href="../user/registerForm.jsp"><input type="button" value="회원가입"></a></td>
  <td></td>
  </tr>
  
  
  
  
  
  </table>
  
</form>
</center>
</div>

</body>
</html>