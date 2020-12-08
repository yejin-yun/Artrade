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
        //위시리스트 
        $(document).ready(function(){
            $('.heart').click(function() {
                    if( $(this).attr('src') == "/artrade/images/bagic/heart-thin.png"){
                        $(this).attr('src','/artrade/images/bagic/heart-full.png'); 
                    }else {
                        $(this).attr('src','/artrade/images/bagic/heart-thin.png'); 
                    }
            });
        });
    </script>
    <script>
	    function moveTarget(targetUri) {
	   		//alert(targetUri);
	       form.action = targetUri; // 삭제면 삭제, 카트 이동이면 카드 이동
	       form.submit();
	    }
    
	   	function clickLike() {
				//var like = document.getElementById("like_img");
				//console.log("test: " + like.src);
				//if(like.src = "/artrade/images/bagic/heart-full.png")
				//	like.src = "/artrade/images/bagic/heart-thin.png";
				//else
				//	like.src = "/artrade/images/bagic/heart-full.png";
				//alert(targetUri);
				//moveTarget(targetUri);
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
           		List<SimpleArtworkInfo> artworkList = (List<SimpleArtworkInfo>)request.getAttribute("artworkList");
           		List<Exhibition> exhList = (List<Exhibition>)request.getAttribute("exhibitionList");
           		
           		if(artworkList.size() < 1 && exhList.size() < 1) {
           			out.println("<p style='text-align:center;'>검색결과가 없습니다.</p>");
           			return;           			
           		}
           		int total = -1;
           		int rpp = 9; // 한 페이지에 보이는 작품의 수
           		int allPage = 0; //전체 몇 페이지인지.
           		int curPage = 0;
           		int startIndex = 0; 
           		int lastIndex = 0;
           		int i = -1;
           		
           		if(artworkList.size() > 0) {
					System.out.println("artworkList is not null");

	           		total = artworkList.size();
	           		
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

	           		for(i = startIndex; i <= lastIndex && i <total; i++) {
	           			SimpleArtworkInfo artwork = artworkList.get(i);
	           			if((i + 1) % 3 == 1) {
	           				out.println("<tr style='margin-bottom: 30px;'>");
	           			}

           %>
           		<td>
           			<div class="w3-card-4 work">
           				<c:set var="wish_val" value="<%= artwork.getIsInWishlist() %>" />  
                        <c:set var="artworkNo" value="<%= artwork.getArtworkNo() %>" />
                       <%--
                        <a href="<c:url value="/artwork/detail" var="detail">
                        		<c:param name="artworkNo" value="${artworkNo}" />
                        	</c:url>"> --%>
	                        <div class="img_div">
	                        	<a href="detail.jsp?artworkNo=${artworkNo}&isLogined=${isLogined}&userNo=${userNo}" >
	                            <img class="main_img" src="<c:url value='<%= artwork.getImage() %>' />" /></a>
	                        </div>
                        	<div class="content">
                        		<a href="detail.jsp?artworkNo=${artworkNo}&isLogined=${isLogined}&userNo=${userNo}" > 
	                            <h2><%= artwork.getTitle() %></h2></a>
	                            <c:if test="${wish_val == 1}" >
	        						<c:set var="like_src" value="/images/bagic/heart-full.png" />
	        					</c:if>
	        					<c:if test="${wish_val == 0}" >
	        						<c:set var="like_src" value="/images/bagic/heart-thin.png" />
	        					</c:if>
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
	                            	<img src="<c:url value='${like_src}' />" id="like_img"
	                                	alt="하트(좋아요)" class="heart"
	                                	style="padding-bottom: 10px; float: right; padding-right: 10px;"/>
	                            </a>
	                            <p><%= artwork.getArtistName() %></p>
	                            <p><%= artwork.getPrice() %>원</p>
                        </div>
                    </div>
                  </td>
			<%
						if((i + 1) % 3 == 0) {
		   					out.println("</tr>");
		   				}
           			}
           		}
           		if(exhList.size() > 0) {
           			System.out.println("exhList is not null");
           			if(i == -1) {
    	           		if(request.getAttribute("sIndex") != null && !request.getAttribute("sIndex").equals("")) {
    	           			curPage = Integer.parseInt((String)request.getAttribute("sIndex"));
    	           		} else { //널이라면 페이지를 처음 방문한 것임.
    	           			curPage = 1;
    	           		}
    	           		
    	           		i = rpp * (curPage - 1);
    	           		lastIndex = rpp * curPage - 1;
           			}
           			total = exhList.size();
	           		allPage = (exhList.size() / rpp) + (total % rpp == 0 ? 0 : 1); // 상품이 18개면 2페이지가 필요하고, 20개면 3페이지가 필요함.
	           		for(int j = i; j <= lastIndex && j <total; j++) {
	           			Exhibition exhibition = exhList.get(j);
	           			if((j + 1) % 3 == 1) {
	           				out.println("<tr style='margin-bottom: 30px;'>");
	           			}		
           %>
           	<td>
           			<div class="w3-card-4 work">
                        <c:set var="exhibitionNo" value="<%= exhibition.getExhibitionNo() %>" />
	                        <div class="img_div">
	                        	<a class="trigger" data-toggle="modal" data-target="#myModal" href="detail.jsp?artworkNo=${artworkNo}&isLogined=${isLogined}&userNo=${userNo}" >
	                            <img class="main_img" src="<c:url value='<%= exhibition.getImage() %>' />" /></a>
	                        </div>
                        	<div class="content">
                        		<a class="trigger" data-toggle="modal" data-target="#myModal" href="detail.jsp?artworkNo=${artworkNo}&isLogined=${isLogined}&userNo=${userNo}" > 
	                            <h2><%= exhibition.getTitle() %></h2></a>
	                            <p><%= exhibition.getPrice() %>원</p>
                       		</div>
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
                    </div>
                  </td>
           <%
			       		if((j + 1) % 3 == 0) {
			  				out.println("</tr>");
			  			}
			  		}
           		}
           		out.println("</table>");
           		if(total != 0) {
           			out.println("<div class='w3-center' style='margin-top: 50px;'>");
           			for(i = 1; i <= allPage; i++) {
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
           					} else {	
           						out.println("<a href='/artrade/artwork/list?sIndex=" + i + "'>" + i + "</a>|");
           					}
          			  	}
           			}
           			out.println("</div>");
           		}
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

