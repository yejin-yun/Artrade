<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.*, model.dao.*" %>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
    <title>Artrade</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta content="text/html; charset=iso-8859-2" http-equiv="Content-Type">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
     <link type="text/css" rel="stylesheet" href="<c:url value='/css/base.css' />" >
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/view.css' />" >
  	<link type="text/css" rel="stylesheet" href="<c:url value='/css/detail.css' />" >
    <script src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
    <script src="<c:url value='/js/base.js' />" ></script>

    <style>
    	section {
    		width: 100%;
    		height: 100%;
    		padding:0;
    		margin:0;
    	}
		#left {
			/*border: 1px solid black; */
			padding: 0;
			width: 100%;
			height: auto;
		}
		#right {
			/*border: 1px solid black; */
			
			width: 80%;
			height: 80%;
			margin-right: auto;
			margin-left: auto;
			margin-top: 5%;
			background-color: white;
		}
		#full_img {
			width: 100%;
			height: auto;
			
		}
		.btns {
			width: 10%;
			margin-left: auto;
			margin-right: auto;
			margin-top: 5%;
		}
		
		.btns button 
		{
			margin-right: 10px;
			padding: 5px;
			background-color: white;
			border: 1px solid #646EFF;
			color: #646EFF;
			border-top-left-radius: 5px; 
			border-bottom-left-radius: 5px;
			border-top-right-radius: 5px; 
        	border-bottom-right-radius: 5px;
      }
		.btns button:hover
		{ 	
			color:white; 
			background-color: #646EFF; 
		}
    </style>
    <script src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
    <script src="<c:url value='/js/base.js' />" ></script>
</head>
<body>
	<%@ include file="../main/header.jsp" %>
    <section>
        <div class="img_div" id="left">
                 <img class="full_img" src="<c:url value='${artwork.image}'/>" />
              </div>
              <div class="content" id="right">
              <h2>작가: ${artwork.artistName}</h2>
              <p>제목: ${artwork.title}</p>
              <p style="margin-top: 15px;">설명: ${artwork.description}</p>
        </div>
        <div class="btns">
        <button onClick="history.go(-1)">되돌아 가기</button>
        </div>
    </section>
    <footer class="w3-center" style="margin-top: 80%;">
        <div style="padding: 30px 0;"><p>Copyright (c) Artrade  |    2018년 5월 22일~ </p><p>대표: 윤 예진</p></div>
    </footer>
</body>
</html>