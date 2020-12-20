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
   	  <div class="container">         
	  <table class="table table-hover" style="caption-side: top;">
	  	<caption style="text-align: center; margin-top: 0; margin-bottom: 5%; color: #148CFF;"><h2>구매 내역 상세보기</h2></caption>
	    <thead>
	      <tr>
	        <th>구매상품</th>
	        <th>상품명</th>
	        <th>가격</th>
	        <th><button onClick='history.go(-1)' style='display: inline;'>BACK</button></th>
	      </tr>
	    </thead>
	    <tbody>
    	 <%
    			List<Artwork> artworkList = (List<Artwork>)request.getAttribute("artworkList");
           		int total = artworkList.size();
           		int rpp = 4; // 한 페이지에 보이는 작품의 수
           		int allPage = 0; //전체 몇 페이지인지.
           		int curPage = 0;
           		int startIndex = 0; 
           		int lastIndex = 0;
           		System.out.println("test_curPage = " + curPage);
           		System.out.println("test_sIndex = " + request.getAttribute("sIndex"));
           		if(request.getAttribute("sIndex") != null && !request.getAttribute("sIndex").equals("")) {
           			System.out.println("if pass");
           			curPage = Integer.parseInt((String)request.getAttribute("sIndex"));
           			System.out.println("sIndex = " + curPage);
           		} else { //널이라면 페이지를 처음 방문한 것임.
           			curPage = 1;
           		}
           		
           		//page=1, rpp=9라면 시작인덱스는 0이고, 마지막인덱스는 8이어야 함.
           		startIndex = rpp * (curPage - 1);
           		lastIndex = rpp * curPage - 1;
           		
           		allPage = (artworkList.size() / rpp) + (total % rpp == 0 ? 0 : 1); // 상품이 18개면 2페이지가 필요하고, 20개면 3페이지가 필요함.
           		for(int i = startIndex; i <= lastIndex && i <total; i++) {
           			Artwork artwork = artworkList.get(i);
           %>
           <c:set var="artworkNo" value="<%= artwork.getArtworkNo() %>" />
           
	        <tr>
	            <td>
	            <a href="<c:url value='/artwork/detail'>
	            				<c:param name='artworkNo' value='${artworkNo}' />
	            				<c:param name='isLogined' value='${isLogined}' /></c:url>">
	            <img style="height: 50px;" src="<c:url value='<%= artwork.getImage() %>' />"></a>
	            </td>
	            <td><a href="<c:url value='/artwork/detail'>
	            				<c:param name='artworkNo' value='${artworkNo}' />
	            				<c:param name='isLogined' value='${isLogined}' /></c:url>">
	            				<%= artwork.getTitle() %></a></td>
	            <td><%= artwork.getPrice() %></td>
	        </tr>
	        <%-- --%>
    		<%
           		}
           		out.println("</tbody>");
           		out.println("</table>");
           		if(total != 0) {
           			out.println("<div class='w3-center' style='margin-top: 50px;'>");
           			for(int i = 1; i <= allPage; i++) {
           				if(curPage == i) { //현재 페이지는 눌리면 안됨.. 
           					if(i == allPage) {
           						out.println(i);
           					} else {
           						out.println(i + "|");
           					}
           				} else { //현재 페이지가 아닌 페이지의 경우 링크를 달아 클릭이 가능하게 해야함. 
           					//request.setAttribute("sIndex", i);
           					if(i == allPage) {
           						out.println("<a href='/artrade/order/list?sIndex=" + i + "'>" + i + "</a>");  
           					} else {	
           						out.println("<a href='/artrade/order/list?sIndex=" + i + "'>" + i + "</a>|");
           					}
          			  	}
           			}
           			out.println("</div>");
           		}
           		
			%>    
        </div>
	<footer class="w3-center" style="margin-top: 500px;">
        <div style="padding: 30px 0;"><p>Copyright (c) Artrade  |    2018년 5월 22일~ </p><p>대표: 윤 예진</p></div>
    </footer>
</body>
</html>