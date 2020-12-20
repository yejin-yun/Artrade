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
    <!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	
	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	
	<!-- Latest compiled JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
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
           		int resultNum;
           		int ratioIndex = 0;
           		
           		resultNum = artworkList.size() + exhList.size();
           		System.out.println("resultNum = " + resultNum);
           		
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
	           		
	           		ratioIndex = (curPage - 1) * rpp; 
	           		
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
	                        	<a href="<c:url value='/artwork/detail'>
	            				<c:param name='artworkNo' value='${artworkNo}' />
	            				<c:param name='isLogined' value='${isLogined}' /></c:url>">
	                            <img class="main_img" src="<c:url value='<%= artwork.getImage() %>' />" /></a>
	                        </div>
                        	<div class="content">
                        		<a href="<c:url value='/artwork/detail'>
	            				<c:param name='artworkNo' value='${artworkNo}' />
	            				<c:param name='isLogined' value='${isLogined}' /></c:url>">
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
						int temp = ratioIndex % rpp;
					    if((temp) < 2) {
						  	for(int t = 0; t < 2 - (temp); t++) {
						  		System.out.println("temp = " + temp + "ratioIndex = " + ratioIndex + "size = " + resultNum);
						  		if(resultNum > (ratioIndex + (2 - temp)) || (resultNum - ratioIndex >= 2)) break;
						  		//artworkList.size() - ratioIndex >= 2이면 한 페이지에 작품 2개 이상이 나온다는 건데, 2개 이상 나올 때는 1개일때 <td>를 수행하면 안됨.  
						  		out.println("<td></td>");
						  	}
				  	   }
					   ratioIndex++;
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
    	           		       		
           			}
           			startIndex = rpp * (curPage - 1);
	           		lastIndex = rpp * curPage - 1;
           			total = exhList.size();
	           		allPage = (exhList.size() / rpp) + (total % rpp == 0 ? 0 : 1); // 상품이 18개면 2페이지가 필요하고, 20개면 3페이지가 필요함.
	           		System.out.println("current Ratio = " + ratioIndex + " StatIndex = " + startIndex);
	           		for(int j = startIndex; j <= lastIndex && j <total; j++) {
	           			Exhibition exhibition = exhList.get(j);	
	           			System.out.println("(ratioIndex - 1) % rpp = " + (ratioIndex - 1) % rpp + " (j + 1) % 3 = " + (j + 1) % 3);
	           			if((ratioIndex - 1) % rpp == 2){  // 위에서 후위 연산자라 1 증가 돼서 오니까 -1해줌. 
	           				out.println("<tr style='margin-bottom: 30px;'>");
	           			}
	           			if((j + 1) % 3 == 0 && (ratioIndex - 1) % rpp > 2){ 
	           				out.println("<tr style='margin-bottom: 30px;'>");
	           			}
           %>
           	<td>
           		<div class="w3-card-4 work">
                   <c:set var="exhibitionNo" value="<%= exhibition.getExhibitionNo() %>" />
	                    <div class="img_div">
	                       <a class="trigger" data-toggle="modal" data-target="#myModal">
	                       <%--<a class="trigger" data-toggle="modal" data-target="#myModal" href="detail.jsp?artworkNo=${artworkNo}&isLogined=${isLogined}&userNo=${userNo}" > --%>
	                       <img class="main_img" src="<c:url value='<%= exhibition.getImage() %>' />" /></a>
	                     </div>
                         <div class="content">
                        	<a class="trigger" data-toggle="modal" data-target="#myModal"> 
	                        <h2><%= exhibition.getTitle() %></h2></a>
	                        <p style="margin-top: 12%;"><%= exhibition.getPrice() %>원</p>
                       	</div>
                  <!-- Modal -->
  				<div class="modal fade" id="myModal" role="dialog"> <%-- 사용하려면 부트스트랩 링크 넣기 --%>
    			<div class="modal-dialog">
			      <!-- Modal content-->
			      <div class="modal-content">
			        <div class="modal-header">
			          <button type="button" class="close" data-dismiss="modal">&times;</button>
			          <h2 class="modal-title"><%= exhibition.getTitle() %></h2>
			        </div>
			        <div class="modal-body">
			          <p>설명: <%= exhibition.getDescription() %></p>
			        </div>
			        <div class="modal-footer">
			          <a href="<c:url value='/exhibition/entrance'><c:param name='exhibitionNo' value='${exhibitionNo}'/></c:url>">
                   <button type="button" class="btn btn-default" id="entrance" class="btn btn-primary">입장</button></a>
                   <a href="<c:url value='/exhibition/buyTicket'><c:param name='exhibitionNo' value='${exhibitionNo}'/></c:url>">
                    <button type="button" class="btn btn-default" id="buyTicket" class="btn btn-primary">입장권 구매</button></a>
			        </div>
			      </div>
			    </div>
			  </div>
          	</div>
                  </td>
           <%
			           int temp = ratioIndex % rpp;
					    if((temp) < 2) {
						  	for(int t = 0; t < 2 - (temp); t++) {
						  		System.out.println("temp = " + temp + "ratioIndex = " + ratioIndex + "size = " + resultNum);
						  		if(resultNum > (ratioIndex + (2 - temp)) || (resultNum - ratioIndex >= 2)) break;
						  		//artworkList.size() - ratioIndex >= 2이면 한 페이지에 작품 2개 이상이 나온다는 건데, 2개 이상 나올 때는 1개일때 <td>를 수행하면 안됨.  
						  		out.println("<td></td>");
						  	}
				  	   }

	           			if((j + 1) % 3 == 0 && (ratioIndex - 1) % rpp > 2){ 
	           				out.println("</tr>");
	           			}
			       
			       		ratioIndex++;
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

