<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    
     <!--JQuery -->
    <script src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
    <script src="<c:url value='/js/base.js' />" ></script>
    <script src="<c:url value='/js/img_slide.js' />"></script>

    <style>
        section {
            padding-top: ;
        }
        .img_slides {
           width: 100%;
           height: 100%;
          /* padding-top: 40px;   
           padding-bottom: 100px; */
           display: none;
        }
        .img_slides2 {
           width: 100%;
           height: 100%;
          /* padding-top: 40px;   
           padding-bottom: 100px; */
           display: none;
        }
        .img_back {
            width: 100%;
        }
        
        
    </style>
    
   
</head>
<body>
   <%@ include file="header.jsp" %>
    <div id="container" >
    <section>
    <div class="w3-content w3-section" style="padding-bottom: 50px; padding-top: 30px; max-width:100%; height: 600px;"><!--main image-->
        <!-- 사진이 주기적으로 바뀌면 좋을 듯 -> 컨트롤러에서 사진 주소 리스트를 여기로 전달하면 여기서 EL로 꺼내면 되지 않나? -->
        <c:forEach var="artwork" items="${artworks}">
        	<c:if test="${!(empty artwork)}">
        	 	<img class="img_slides" src="<c:url value='${artwork.image}' />" alt="artwork_slides" />
        	 </c:if>
        </c:forEach>
    </div>
    <h1 class="w3-center">전시회 정보</h1>
    <div class="w3-content w3-section w3-display-container w3-text-black" style="padding-bottom: 0px; max-width:100%; height: 600px;"><!--main image-->
        <c:forEach var="exhibition" items="${exhibitions}">
        	<c:if test="${!(empty exhibition)}">
        		<a href="<c:url value='/exhibition/list'/>">
        	  <%-- <a href="<c:url value='/exhibition/list'><c:param name='main_exh' value='${exhibition}' /></c:url>"> --%>
        	<img class="img_slides2 w3-hover-opacity" onmouseover="odc_mouseover()" src="<c:url value='${exhibition.image}' />" alt="exhibition" /></a>
        	</c:if>
        </c:forEach>
        <h1 class="w3-display-middle w3-large info" style="visibility:hidden;">전시회 페이지 가기</h1>
        <%-- <h1 class="w3-display-middle w3-large info" style="visibility:hidden;">전시회 <br> 자세히 보러가기</h1> --%>
    </div>

    </section>
    <footer class="w3-center">
        <div style="padding: 30px 0;"><p>Copyright (c) Artrade  |    2018년 5월 22일~ </p><p>대표: 윤 예진</p></div>
    </footer>
    </div>
</body>
</html>
