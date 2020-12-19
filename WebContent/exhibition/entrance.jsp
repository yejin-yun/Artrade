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
    <style>

    </style>
    <script src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
    <script src="<c:url value='/js/base.js' />" ></script>
    <script>

    </script>
    <script>
	    function moveTarget(targetUri) {
	   		//alert(targetUri);
	       form.action = targetUri; // 삭제면 삭제, 카트 이동이면 카드 이동
	       form.submit();
	    }
    
    </script>
</head>
<body>
	<%@ include file="../main/header.jsp" %>
    <div class="section">
    <section>
           <table>
           <%
           		System.out.println("here is....view.jsp");
           		List<Artwork> artworkList = (List<Artwork>)request.getAttribute("artworkInExh");

           		if(artworkList == null) {
           			out.println("<p style='text-align:center;'>상품이 없습니다.</p>");
           			return;
           		}
           		int total = artworkList.size();
           		int rpp = 9; // 한 페이지에 보이는 작품의 수
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
           			if((i + 1) % 3 == 1) {
           				out.println("<tr style='margin-bottom: 30px;'>");
           			}
           %>
           		<td>
           			<div class="w3-card-4 work">
                        <c:set var="artworkNo" value="<%= artwork.getArtworkNo() %>" />
	                        <div class="img_div">
	                        	<a href="<c:url value='/exhibition/detail'>
	            				<c:param name='artworkNo' value='${artworkNo}' />
	            				<c:param name='isLogined' value='${isLogined}' /></c:url>">
	                            <img class="main_img" src="<c:url value='<%= artwork.getImage() %>' />" /></a>
	                        </div>
                        	<div class="content">
                        		<a href="<c:url value='/exhibition/detail'>
	            				<c:param name='artworkNo' value='${artworkNo}' />
	            				<c:param name='isLogined' value='${isLogined}' /></c:url>"> 
	                            <h2><%= artwork.getTitle() %></h2></a>
	                            <p><%= artwork.getArtistName() %></p>
                        </div>
                    </div>
                  </td>
			<%
					if(artworkList.size() < 3) {
			        	   if((3 - artworkList.size() - 1) != i) { //i는 0부터 시작하니까 -1해준 거. 
					  		   for(int t = 0; t < 3 - artworkList.size(); t++) {
					  			   out.println("<td></td>");
					  		   }
				  		   }
				  	   }	
					if((i + 1) % 3 == 0) {
		   				out.println("</tr>");
		   			}
           		}
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
           						out.println("<a href='/artrade/artwork/list?sIndex=" + i + "'>" + i + "</a>");
           						//out.println("<a href=\"/artrade/artwork/view.jsp?sIndex=" + i + "\">" + i + "</a>");
           						//out.println("<a href=\"<c:url value='/artwork/list' var='artlist'><c:param name='sIndex' value='" + i + "' ></c:url>\">" + i + "</a>");
           						//out.println("<a href=\"<c:url value='/artwork/list' var='artlist'></c:url>\">" + i + "</a>|");
           					} else {	
           						out.println("<a href='/artrade/artwork/list?sIndex=" + i + "'>" + i + "</a>|");
           						//out.println("<a href='/artrade/artwork/view.jsp?sIndex'>" + i + "</a>|");
           						//out.println("<a href=\"/view.jsp?sIndex=" + i + "\">" + i + "</a>|");
           						//out.println("<a href=\"<c:url value='/artwork/list' var='artlist'><c:param name='sIndex' value='" + i + "' ></c:url>\">" + i + "</a>|");
           						//out.println("<a href=\"<c:url value='/artwork/list' var='artlist'></c:url>\">" + i + "</a>|");
           					}
          			  	}
           			}
           			out.println("</div>");
           		}
           		
          //out.println("<a href=\"addr_list.jsp?sindex=" + i + "\">" + i + "</a>|");
           		//out.println("<a href='<c:url value=\'/artwork/list\' var=\'artlist\'>
					//		<c:param name=\'sindex\' value=\'i\' /></c:url>'>" + i + "</a>");
			%>
    </section>
    </div>
    <div class="footer">
        <footer class="w3-center">
            <div style="padding: 30px 0;"><p>Copyright (c) Artrade  |    2018년 5월 22일~ </p><p>대표: 윤 예진</p></div>
        </footer>
    </div>
    </body>
</html>

