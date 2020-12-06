<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
    <!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	
	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	
	<!-- Latest compiled JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<style>
		#exhImage {
			margin-top: 50px;
			width: 100%;
			height: 100%;
		}
		#search {
			margin-top: 200px;
			text-align: center;
			/*width: 80%;
			margin-right: auto;
			margin-left: auto; */
		}
		.mySlides {
			display: none;
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
    	<div id="exhImage" class="w3-center w3-content w3-display-container">
    		<c:if test="${!(empty main_exh)}"> 
    			<a class="trigger" data-toggle="modal" data-target="#myModal"><img class="mySlides" src="<c:url value='${main_exh.image}' />" ></a>
    		</c:if>
    		
    		<c:forEach var="exh" items="${exhibitionList}" varStatus="status" >
    			<a class="trigger" data-toggle="modal" data-target="#myModal">
    			<img class="mySlides" src="<c:url value='${exh.image}' />" ></a>
    			<!-- Modal -->
  				<div class="modal fade" id="myModal" role="dialog">
    			<div class="modal-dialog">
			      <!-- Modal content-->
			      <div class="modal-content">
			        <div class="modal-header">
			          <button type="button" class="close" data-dismiss="modal">&times;</button>
			          <h2 class="modal-title">${exh.title}</h2>
			          <h3>전시회 정보</h3>
			        </div>
			        <div class="modal-body">
			          <p>설명: ${exh.description}</p>
			        </div>
			        <div class="modal-footer">
			          <a href="<c:url value='/exhibition/entrance' />">
			          <button type="button" class="btn btn-default" id="entrance" class="btn btn-primary">입장</button></a>
		              <button type="button" class="btn btn-default" id="buyTicket" class="btn btn-primary">입장권 구매</button>
			        </div>
			      </div>
			    </div>
			  </div>
    		</c:forEach>
    		<button class="w3-button w3-black w3-display-left" onclick="plusDivs(-1)" style="width: 15px;" >&#10094;</button>
  			<button class="w3-button w3-black w3-display-right" onclick="plusDivs(1)" style=" width: 15px;">&#10095;</button>
    	</div>
    	 <script>
        var slideIndex = 1;
        showDivs(slideIndex);

        function plusDivs(n) {
          showDivs(slideIndex += n);
        }

        function showDivs(n) {
          var i;
          var x = document.getElementsByClassName("mySlides");
          if (n > x.length) {slideIndex = 1}
          if (n < 1) {slideIndex = x.length} ;
          for (i = 0; i < x.length; i++) {
            x[i].style.display = "none";
          }
          x[slideIndex-1].style.display = "block";
        }
    </script>
   
    	<div id="search" class="w3-center" style="position:relative; margin-bottom: 10px;">
    		<h1>전시 목록 검색</h1>
    		<form method="GET" id="searchForm" action="<c:url value='/exhibition/search' />">
    			 <input type="text" class="form-control" name="exhSearch" placeholder= "전시회명을 검색하세요." style="width: 30%; margin-right: auto; margin-left: auto; margin-top: 50px;" />
    			 <input type="submit" class="btn btn-info" value="검색" style="margin-bottom: 50px;"/>
    		</form>
    	</div>
    </section>
    <footer class="w3-center" style="margin-top: 500px;">
        <div style="padding: 30px 0;"><p>Copyright (c) Artrade  |    2018년 5월 22일~ </p><p>대표: 윤 예진</p></div>
    </footer>
</body>
</html>