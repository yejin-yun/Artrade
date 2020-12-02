<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*, java.util.List" %>
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
    <link type="text/css" rel="stylesheet" href="../css/view.css" >
    <style>
		#left {
			float: left;
		}
		#right {
			float: right;
			background-color: white;
		}
		#full_img {
			width: 100%;
			height: 100%;
		}
    </style>
    <script src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
    <script src="../js/base.js"></script>
</head>
<body>
	<%@ include file="../main/header.jsp" %>
    <section>
    	<div class="w3-card-4 work">
              <a href="">
              <div class="img_div">
                 <img class="full_img" src="../img/artwork/모네_수련_연작.jpg" />
              </div>
              <div class="content">
              <h2>모네</h2></a>
              <p>산책</p>
              <p>${artwork.description}</p>
              </div>
        </div>
    </section>
</body>
</html>