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
    <link type="text/css" rel="stylesheet" href="../css/base.css" >
    <link type="text/css" rel="stylesheet" href="../css/detail.css" >
    <style>
    	section {
    		width: 100%;
    		height: 100%;
    	}
		#left {
			float: left;
			width: 70%;
			height: 100%;
		}
		#right {
			float: right;
			width: 30%;
			height: 100%;
			padding-left: 3%;
			background-color: white;
		}
		#full_img {
			width: 100%;
			height: 100%;
		}
    </style>
    <script src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
    <script src="<c:url value='/js/base.js' />" ></script>
</head>
<body>
	<%@ include file="../main/header.jsp" %>
    <section>
              <div class="img_div" id="left">
                 <img class="full_img" src="<c:url value='/images/artwork/모네_수련_연작.jpg'/>" />
              </div>
              <div class="content" id="right">
              <h2>작가: 모네</h2>
              <p>제목: 산책</p>
              <p style="margin-top: 15px;">설명: ${artwork.description}</p>
        </div>
    </section>
    <footer class="w3-center" style="margin-top: 500px;">
        <div style="padding: 30px 0;"><p>Copyright (c) Artrade  |    2018년 5월 22일~ </p><p>대표: 윤 예진</p></div>
    </footer>
</body>
</html>