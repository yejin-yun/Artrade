<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*, java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>



<!DOCTYPE html>
<html>
<head lang="en"> <!-- 이전 코드 css -> 작품보기.html에 있음 -->
    <title>Artrade</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta content="text/html; charset=iso-8859-2" http-equiv="Content-Type">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/base.css' />" >
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/detail.css' />" >
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/view.css' />" >

    <style>

		.btns{
			 margin-top: 10%;
		}
		.btns input {
			padding: 5px;
			background-color: white;
			border: 1px solid #646EFF;
			color: #646EFF;
			border-top-left-radius: 5px; 
			border-bottom-left-radius: 5px;
			border-top-right-radius: 5px; 
			border-bottom-right-radius: 5px;
		}
		.btns input:hover
		{ 	
			color:white; 
			background-color: #646EFF; 
		}
		.funcs {
			margin-top: 10%;
		}
		.funcs input 
		{
			margin-right: 10px;
			padding: 5px;
			background-color: white;
			border: 1px solid #646EFF;
			color: #646EFF;
			border-top-left-radius: 5px; 
			border-bottom-left-radius: 5px;
			border-top-right-radius: 5px; 
        	border-bottom-right-radius: 5px;
      }
		.funcs input:hover
		{ 	
			color:white; 
			background-color: #646EFF; 
		}
		
		.checkWish {
			margin-left: 10px;
			margin-top: 10px;
		}
    </style>
    <script src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
    <script src="<c:url value='/js/base.js' />" ></script>
	<script>
	$(document).ready(function() { 
		$('#allCheck').click(function() {
		  $("input[name=checkArtwork]:checkbox").attr("checked", true);
		});
		$('#allReset').click(function() {
		  $("input[name=checkArtwork]:checkbox").attr("checked", false);
		});
	});
	</script>
    <script>
        //위시리스트
         function moveTarget(targetUri) {
       		//alert(targetUri);
           form.action = targetUri; // 삭제면 삭제, 카트 이동이면 카드 이동
           form.submit();
        }
        
       function checkConfirm(targetUri) {
    	   //alert(targetUri);
	       var isChk = false;
	       var products = document.getElementsByName("checkArtwork");
	       console.log('console' + products[0]);
	       for(var i=0;i<products.length;i++){
	           if(products[i].checked == true) {
	               isChk = true;
	               break;
	           }
	       }
	     if(!isChk) {
	    	 alert("상품을 한개 이상 선택해주세요.");
	         return false;
	     }
	     
	     moveTarget(targetUri);
       }
        
       function delWish(paraValue, targetUri) {
	        if($("input:checkbox[id='" + paraValue + "']").is(':checked') == true) {
	        	 moveTarget(targetUri);
	        } else {
	        	alert('해당 상품이 선택되어 있지 않습니다.');
	        }
	    }
       
       function moveToCart(paraValue, targetUri) {
	        if($("input:checkbox[id='" + paraValue + "']").is(':checked') == true) {
	        	 moveTarget(targetUri);
	        } else {
	        	alert('해당 상품이 선택되어 있지 않습니다.');
	        }
	    }
       
      //function deleteWish(paraValue) {
    	 
      //}
     /*
     
      alert("상품" + paraValue);
    	  var flag = false;
    	  var artwork = document.getElementsByName("checkArtwork");
    	  
    	  for(var i=0;i<artwork.length;i++){
    		  alert("상품" + artwork[i].value);
	           if(artwork[i].value == paraValue) {
	        	   if(artwork.valuechecked == true) {
	        		   flag = true;
	        		   break;
	        	   }
	           }	
	       }
    	  if(flag == false) {
    		  retrun false;
    	  }
    	  moveTarget('/artrade/user/deletewishlist'); 
     */
    </script>
</head>
<body>
  <%@ include file="../main/header.jsp" %>
    <div class="section">
    <section>
    	<form method="POST" action="<c:url value='/user/wishlist' />" id="payment_form" name="form">
           <%
           		out.println("<table>");
                 System.out.println("here is....view.jsp");
                 List<Artwork> wishlist = (List<Artwork>)request.getAttribute("wishlist");
                 
                 //System.out.println("size = " + wishlist.size());
                 
                 if(wishlist == null || wishlist.size() == 0) {
                    out.println("<p style='text-align:center;'>위시리스트가 없습니다.</p>");
                    return;
                 }
                 int total = wishlist.size();
                 int rpp = 9; // 한 페이지에 보이는 작품의 수
                 int allPage = 0; //전체 몇 페이지인지.
                 int curPage = 0;
                 int startIndex = 0; 
                 int lastIndex = 0;
                 int ratioIndex;
                 
                 if(request.getParameter("sIndex") != null && !request.getParameter("sIndex").equals("")) {
                    curPage = Integer.parseInt(request.getParameter("sIndex"));
                    System.out.println("sIndex = " + curPage);
                 } else { //널이라면 페이지를 처음 방문한 것임.
                    curPage = 1;
                 }
                 
                 //page=1, rpp=9라면 시작인덱스는 0이고, 마지막인덱스는 8이어야 함.
                 startIndex = rpp * (curPage - 1);
                 lastIndex = rpp * curPage - 1;
                 
                 ratioIndex = (curPage - 1) * rpp; 
                 
                 allPage = (wishlist.size() / rpp) + (total % rpp == 0 ? 0 : 1); // 상품이 18개면 2페이지가 필요하고, 20개면 3페이지가 필요함.
                 for(int i = startIndex; i <= lastIndex && i <total; i++) {
                    Artwork wishArtwork = wishlist.get(i);
                    if((i + 1) % 3 == 1) {
                       out.println("<tr style='margin-bottom: 30px;'>");
                    }
           %>
                 <td>
                  <label name> <%-- detail.jsp?artworkNo=${ads.artwork.artworkNo}&isLogined=1&userNo=${ads.artwork(심플아트워크 담는 객체 변수의 이름).userNo} --%>   
                    <div class="w3-card-4 work card">
                   		<c:set var="artworkNo" value="<%= wishArtwork.getArtworkNo() %>" />    
                       	<c:set var="userNo" value="<%= request.getAttribute(\"userNo\") %>" /> 
                    	<input type="checkbox" name="checkArtwork" value="${artworkNo}" id="${artworkNo}" class="checkWish allCheckbox"/> 	
                           <div class="img_div">
                           	<a href="<c:url value='/artwork/detail'>
	            				<c:param name='artworkNo' value='${artworkNo}' />
	            				<c:param name='isLogined' value='${isLogined}' /></c:url>"> 
                               <img class="main_img" src="<c:url value='<%= wishArtwork.getImage() %>' />" /></a>
                           </div>
                        	<div class="content">
                        	<a href="<c:url value='/artwork/detail'>
	            				<c:param name='artworkNo' value='${artworkNo}' />
	            				<c:param name='isLogined' value='${isLogined}' /></c:url>">
                            <h2><%= wishArtwork.getTitle() %></h2>
                            <p><%= wishArtwork.getArtistName() %></p>
                            <p><%= wishArtwork.getPrice() %></p></a> 
                           <div class="btns">
                           		<input type="button" value="삭제" onClick="delWish('${artworkNo}', '<c:url value='/user/deletewishlist' />')" >
                           		<input type="button" value="장바구니로 이동" onClick="moveToCart('${artworkNo}', '<c:url value='/user/fromWishToCart' />')" >

                           </div>
                        </div>
                    </div>
                    </label>
                  </td>
         <%
         
	         int temp = ratioIndex % rpp;
			    if((temp) < 2) {
				  	for(int t = 0; t < 2 - (temp); t++) {
				  		System.out.println("temp = " + temp + "ratioIndex = " + ratioIndex + "size = " + wishlist.size());
				  		if(wishlist.size() > (ratioIndex + (2 - temp)) || (wishlist.size() - ratioIndex >= 2)) break;
				  		//artworkList.size() - ratioIndex >= 2이면 한 페이지에 작품 2개 이상이 나온다는 건데, 2개 이상 나올 때는 1개일때 <td>를 수행하면 안됨.  
				  		out.println("<td></td>");
				  	}
		  	   }	
               if((i + 1) % 3 == 0) {
                     out.println("</tr>");
                  }
               
               ratioIndex++;
               }
                 out.println("</table>");
         %>
         <div class="w3-center funcs">
         	<input type="button" value="전체 선택" id="allCheck" > 
         	<input type="button" value="전체 해제" id="allReset">
			<input type="button" value="선택 상품 모두 삭제" onClick="checkConfirm('<c:url value='/user/deletewishlist' />')">
			<input type="button" value="선택 상품 장바구니 이동" onClick="checkConfirm('<c:url value='/user/fromWishToCart' />')">
		 </div>
         <%
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
                             out.println("<a href='/artrade/user/wishlist?sIndex=" + i + "'>" + i + "</a>");
                        
                          } else {   
                             out.println("<a href='/artrade/user/wishlist?sIndex=" + i + "'>" + i + "</a>|");
                        
                          }
                        }
                    }
                    out.println("</div>");
                 }
                 
          //out.println("<a href=\"addr_list.jsp?sindex=" + i + "\">" + i + "</a>|");
                 //out.println("<a href='<c:url value=\'/artwork/list\' var=\'artlist\'>
               //      <c:param name=\'sindex\' value=\'i\' /></c:url>'>" + i + "</a>");
         %>
         </form>
    </section>
    </div>
    <div class="footer">
        <footer class="w3-center">
            <div style="padding: 30px 0;"><p>Copyright (c) Artrade  |    2018년 5월 22일~ </p><p>대표: 윤 예진</p></div>
        </footer>
    </div>
    </body>
</html>