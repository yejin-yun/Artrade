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
    
    </style>
    <script src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
    <script src="../js/base.js"></script>
</head>
<body>
     <%@ include file="../main/header.jsp" %>        
    <div class="container">
    <aside id="right">
        <h2>작가: ${artwork.artworkNo} </h2>
        <p>작품 명: ${artwork.title} </p>
        <p>가격: ${artwork.price}원</p>
        <p>사이즈: ${artwork.workSize} </p>
        <c:set var="wish_val" value="${artwork.isInWishList }" />
        <form class="btn">
            <a href="<c:url value='/order/payment'>
            	<c:param name="isInCart" value="0" />
            	<c:param name="artworkNo" value="${artwork.artworkNo}" />
            	<c:param name="servletPath" value="<%= request.getServletPath() %>" />
            </c:url>">
            <button type="button" id="buy">주문하기</button></a>
            
            <c:set var="artworkNo" value="${artwork.artworkNo}" /> 
            <a href="<c:url value='/user/cartAdd'>
            	<c:param name="isLogined" value="1" />
            	<c:param name="artworkNo" value="${artworkNo}" />
            	
            	
            </c:url>">
            <button type="button" id="cartlist">장바구니에 담기 </button>
            </a>
            
            <!-- 백업 
            <a href="<c:url value="/user/cartAdd" var="cart">
            	<c:param name="artworkNo" value="${artworkNo}" />
            </c:url>">
            <button type="button" id="cart">장바구니에 담기</button>
            </a>
            
             -->
            
            <a href=""><button type="button" id="cart">장바구니에 담기</button></a>
            <%--  <c:set var="wish_val" value="<%= artwork.getIsInWishlist() %>" /> --%> 
            <c:set var="artworkNo" value="${artwork.artworkNo}" /> 
             <a href="<c:url value='/user/wishlistLike'>
	                            	<c:if test="${wish_val == 0}" >
	        							<c:param name="like" value="1" />
	        						</c:if>
	        						<c:if test="${wish_val == 1}" >
	        							<c:param name="like" value="0" />
	        						</c:if>
	        						<c:param name="isLogined" value="1" /> <%-- 이걸 쓰려면 controller에서 isLogined를 쓰면 안됨 --%>
	        						<c:param name="artworkNo" value="${artworkNo}" />
	        						</c:url>">
        	<button type="button" id="wishlist">위시리스트에 담기</button></a>
        </form>
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