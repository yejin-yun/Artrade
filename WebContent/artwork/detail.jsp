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
    .btns{
			 margin-top: 10%;
		}
		.btns button {
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
    <script src="../js/base.js"></script>
</head>
<body>
     <%@ include file="../main/header.jsp" %>        
    <div class="container">
    <aside id="right">
        <h2>작가: ${artwork.artistName} </h2>
        <p>작품명: ${artwork.title} </p>
        <p>가격: ${artwork.price}원</p>
        <p>사이즈: ${artwork.workSize} </p>
        <c:set var="wish_val" value="${artwork.isInWishList }" />
        <c:set var="cart_val" value="${artwork.isInCart }" />
        
        <div class="w3-center">
        <div class="btns">
        <form class="btn">
            <a href="<c:url value='/order/payment'>
            	<c:param name="isInCart" value="0" />
            	<c:param name="artworkNo" value="${artwork.artworkNo}" />
            	<c:param name="servletPath" value="<%= request.getServletPath() %>" />
            </c:url>">
            <button type="button" id="buy">주문하기</button></a>
            
            
            <c:set var="artworkNo" value="${artwork.artworkNo}" /> 
            
            <c:set var="isInCart" value="${artwork.isInCart}" /> 
            <c:choose>
            	<c:when test="${isInCart eq 0}">
            		<a href="<c:url value='/user/cartAdd'>
            	<c:param name="isLogined" value="1" />
            	<c:param name="artworkNo" value="${artworkNo}" />
           			 </c:url>">
            		<button type="button" id="cartlist">장바구니에 담기</button>
            </a>
            	</c:when>
            
            <c:when test="${isInCart eq 1}">
            
            <button type="button" id="cartlist" onClick="alert('장바구니에 같은 상품이 이미 존재합니다.')">장바구니에 담기</button>
            </c:when>
            
            
            </c:choose>
            
            
            <%-- <a href="<c:url value='/user/cartAdd'>
            	<c:param name="isLogined" value="1" />
            	<c:param name="artworkNo" value="${artworkNo}" />
            </c:url>">
            <button type="button" id="cartlist">장바구니에 담기</button>
            </a> --%>
  			
  			<c:choose>
  				<c:when test="${wish_val == 0}">
  					<a href="<c:url value='/user/wishlistLike'>
	        						<c:param name="like" value="1" />
	        						<c:param name="isLogined" value="1" /> <%-- 이걸 쓰려면 controller에서 isLogined를 쓰면 안됨 --%>
	        						<c:param name="artworkNo" value="${artworkNo}" />
	        						</c:url>">
        	<button type="button" id="wishlist">위시리스트에 담기</button></a>
  				</c:when>
  				<c:when test="${wish_val == 1}">
  					<button type="button" id="wishlist" onClick="alert('이미 위시리스트에 담긴 상품입니다.')">위시리스트에 담기</button>
  				</c:when>
  			</c:choose>
 
        </form>
        </div>
        </div>
    </aside>   

   <section id="section1">
        <article id="main_image">    
	        <img src="<c:url value='${artwork.image}' />" alt="모네_수련_연작"/><br>
        </article>
        <article id="explanation">
            <br><hr>
            <p><b>♥상세 설명♥ </b><br></p>
            <!-- <div class="ex_img">
                <img src="../img/artwork/모네_수련_연작.jpg" alt="모네_수련_연작"/>
            </div> -->
            <P>
		        ${artwork.description}
            </P>
        </article>
    </section>
    <div>
    <footer class="w3-center">
        <div style="padding: 30px 0;"><p>Copyright (c) Artrade  |    2018년 5월 22일~ </p><p>대표: 윤 예진</p></div>
    </footer>
    </body>
</html>