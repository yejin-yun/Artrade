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
    </style>
    <script src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
    <script src="<c:url value='/js/base.js' />" ></script>

</head>
<body>
	<%@ include file="../main/header.jsp" %>
  	<div class="container" style="width: 100%; height: 100%;">         
 	<table class="table table-bordered w3-center" style="width: 60%; height: 10%;">
	    <tbody>
	    	<tr>
	    		<td>
	    		<a href="<c:url value='/user/update' />">
	    			<img src="<c:url value='/images/bagic/user.png' />" style="width:50%; height:30%;">
	    		</a>
	    		<h3>회원 정보 수정</h3>
	    		</td>
	    		<td>
	    		<a href="<c:url value='/order/list' />">
	    			<img src="<c:url value='/images/bagic/check-list.png' />" style="width:50%; height:30%;">
	    		</a>
	    		<h3>주문조회</h3>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>
		    		<a href="<c:url value='/user/wishlist' />">
		    			<img src="<c:url value='/images/bagic/heart.png' />" style="width:50%; height:30%;">
		    		</a>
		    		<h3>위시리스트</h3>
	    		</td>
	    		<td>
		    		<a href="<c:url value='/user/quit' />">
		    			<img src="<c:url value='/images/bagic/check-out.png' />" style="width:50%; height:30%;">
		    		</a>
		    		<h3>탈퇴하기</h3>
	    		</td>
	    	</tr>
	    </tbody>
	</table>
	</div>
	<footer class="w3-center" style="margin-top: 500px;">
        <div style="padding: 30px 0;"><p>Copyright (c) Artrade  |    2018년 5월 22일~ </p><p>대표: 윤 예진</p></div>
    </footer>
</body>
</html>