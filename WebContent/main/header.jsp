<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="controller.user.UserSessionUtils" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<% request.setCharacterEncoding("UTF-8"); %>
<%
	if (!UserSessionUtils.hasLogined(request.getSession())) {
		request.setAttribute("isLogined", 0);		
	} else {
		request.setAttribute("isLogined", 1);
	}

%>
<!DOCTYPE html>
<html>
<head>

     <!--JQuery -->
    <script src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
    <script src="<c:url value='/js/base.js' />" ></script>
    <script src="<c:url value='/js/img_slide.js' />"></script>
    
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
</head>
<body>
<header>
        <div class="search_header">
            <a href="#"><img id="serch_btn" src="<c:url value='/images/index/magnifier-32.png' />"  style="padding-left: 40px; padding-top: 10px;"></a>
            <form method="get" action="/search" style="display: inline-block; margin-left: 20px; margin-top:20px; width:80%; height: 50%;">
                <input type="text" class="keyword" placeholder="작가 또는 작품명을 검색하세요" style="width:100%; height: 100%; border:0; background:#f3f3f3 " >
            </form>
        </div>
        <div class="wrapper">
        <h1 id="logo"><a href="<c:url value='/main' />" title="Home"><img src="<c:url value='/images/index/artrade_logo.PNG' />" alt="artrade" ></a></h1>
        <nav>
            <h2><strong>
            <ul class="main_menu">
                <li id="work"><a>작품</a>
                    <ul class="work_sub_menu sub_menu">
                          <li><a href="<c:url value='/artwork/list' />">작품보기</a></li> <%-- 꼭 context path(/artrade)가 있어야 됨.  --%>
                        <li><a href="<c:url value='/artwork/register' />">등록하기</a></li>
                    </ul>
                </li>
                <li><a href="<c:url value='/exhibition/list' />">전시회</a></li>
                <li id="trade"><a>교환</a>
                    <ul class="trade_sub_menu sub_menu">
                        <li><a href="<c:url value='/trade/list' />">교환품보기</a></li>
                        <li><a href="<c:url value='/trade/list' />">교환품제시</a></li>
                    </ul> 
                </li>
                <li><a href="<c:url value='/onedayclass/list' />">클래스</a></li>
            </ul></strong>
            </h2>
        </nav>
       <div class="search_btn"><a href="#"><img id="search_img" src="<c:url value='/images/index/magnifier-32.png' />"></a></div>
        <div class="menu_header">
            <!-- https://ddorang-d.tistory.com/104 -->
            <div class="menubar_btn"><a href="#">
                <img id="menubar" src="<c:url value='/images/index/menubar.PNG' />" border="0">
            </a></div>
        </div>
        <div class="menu_bg"></div>
        <div class="sidebar_menu">
            <div class="close_btn"><a href="#">
                <img src="<c:url value='/images/index/x-mark.png' />" border="0" width="10px" height="10px">
            </a></div>
            <ul class="menu_wrap">
            	<c:if test="${isLogined == 0}" >
                	<li><a href="<c:url value='/user/login' />">Login</a></li>
                </c:if>
                <c:if test="${isLogined == 1}" >
                	<li><a href="<c:url value='/user/logout' />">LogOut</a></li>
                </c:if>
                <li><a href="../user/cart.jsp">Cart</a></li>
                <li><a href="<c:url value='/user/wishlist' />">Wishlist</a></li>
                <li><a href="../user/mypage.jsp">Mypage</a></li>
            </ul>
        </div>
        
    </div>
    </header>
</body>
</html>