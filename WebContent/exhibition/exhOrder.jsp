<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% request.setCharacterEncoding("UTF-8"); %>


<!DOCTYPE html>
<html>
<head>
    <title>Arteade</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta content="text/html; charset=iso-8859-2" http-equiv="Content-Type">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
   <link type="text/css" rel="stylesheet" href="<c:url value='/css/base.css' />" >
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/detail.css' />" >

    <!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	
	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	
	<!-- Latest compiled JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<style>
        .menu a {cursor:pointer;}
        .menu .submenu {display:none;}
        .pm_nobackbook_apperance, .pm_account_apperance {display:none;}
        #container{
        	display: inline-block;
        	width: 100%;
        	margin-top: 5%;
        }
        .artwork_img {
        	width: 50px;
        	height: 30px;
        }
        .menu li {
        	margin: 10px;
        	
        }
        label{ /*251p 이거 덕분에 입력창이 예쁘게 맞춰짐.*/ 
			display: inline-block;
			width: 120px;
		}
		
		.pay{
			margin: 0 10px;
		}
		.submenu{
			border-top: solid 1px black;
		}
		.submenu li {
			padding: 10px;
			border-bottom: solid 1px black;
		}
    </style>
    <script src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
     <script src="<c:url value='/js/base.js' />" ></script>

   <script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script>
        $(document).ready(function(){
            $(".menu > a").click(function(){
                var submenu = $(this).next("ul");
                if( submenu.is(":visible") ){
                    submenu.slideUp();
                }else{
                    submenu.slideDown();
                }
            });
        });

        /* http://blog.naver.com/PostView.nhn?blogId=schatz1234&logNo=220908487291&parentCategoryNo=&categoryNo=16&viewDate=&isShowPopularPosts=true&from=search */
        $(function() {
            $(".pm_nobackbook_click").click(function(){
                $(".pm_account_apperance").css("display", "none");
                if( $(".pm_nobackbook_apperance").is(":visible") ){
                    $(".pm_nobackbook_apperance").slideUp();
                }else{
                    $(".pm_nobackbook_apperance").slideDown();
                }
            });
            $(".pm_account_click").click(function(){
                $(".pm_nobackbook_apperance").css("display", "none");
                if( $(".pm_account_apperance").is(":visible") ){
                    $(".pm_account_apperance").slideUp();
                }else{
                    $(".pm_account_apperance").slideDown();
                }
            });
            
            $(".pm_card_click").click(function(){
            	$(".pm_account_apperance").css("display", "none");
                $(".pm_nobackbook_apperance").css("display", "none");

            });
            $(".pm_phone_click").click(function(){
            	$(".pm_account_apperance").css("display", "none");
                $(".pm_nobackbook_apperance").css("display", "none");

            });
        });
    </script>
    <script>
    function buy() {
	    
	    var payment = document.getElementsByName("pm_means");
	    if(payment[1].checked == true) {
	    	 if (form.provider.value == "") {
	 	        alert("입금자명을 입력하십시오."+form.provider.value);
	 	        form.provider.focus();
	 	        return false;
	 	     }
	    	 if (form.bank.value == "") {
		 	        alert("입금은행을 입력하십시오."+form.bank.value);
		 	        form.bank.focus();
		 	        return false;
		 	 }
	    }
	    if(payment[3].checked == true) {
	    	 if (form.depositor.value == "") {
	 	        alert("예금주명을 입력하십시오."+form.depositor.value);
	 	        form.depositor.focus();
	 	        return false;
	 	     }
	    }
	 

	     var isChk = false;
	        var products = document.getElementsByName("payment_product");
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
	     
	    confirm('주문이 완료되었습니다.');
	    form.submit();
    }
    
    </script>
</head>
<body>
    <%@ include file="../main/header.jsp" %>
	<section>
		<div id="container" class="w3-center">
        <%-- <form method="POST" action="<c:url value='/order/payment'>
        	<c:param name="isInCart" value="${isInCart}" />
        	<c:param name="userNo" value="${userNo}" />
        	<c:if test="${isInCart == 0}" >
        		<c:param name="artworkNo" value="${artworkNo}" />
        	</c:if>
        	<c:if test="${isInCart == 1}" >
        		<% request.setAttribute("artistList", request.getAttribute("artworkList"));--%>
        	<%--	<c:param name="artworkList" value="${artworkList}" /> --%>
        	<%-- </c:if>
        	</c:url>" id="payment_form">
            <div class="menu"> --%>
            <form method="POST" action="<c:url value='/exhibition/buyTicket'><c:param name='exhibitionNo' value='${exhibition.exhibitionNo}'/></c:url>" id="payment_form" name="form">
            	
            <div class="menu">
                    <h3>상품 정보</h3>
                    <a><button type="button" class="btn btn-info" id="submenu_click" style="margin-bottom: 30px; margin-top: 10px;">주문 상품 보기</button></a>
                   
                    <%-- 	
                    
                     <a><img src="submenu_click.jpg" alt="상품정보 바" id="submenu_click" /></a>
                    <a><button type="button" class="btn btn-info" id="submenu_click">주문 상품 보기</button></a>  --%>
                    
                    <ul class="submenu"> <!--해당 리스트의 내용을 display:none;을 통해 안보이게 처리를 한 후 -->
                    	<li>
                            <div class="product">
                                <label> <%-- detail.jsp?artworkNo=${ads.artwork.artworkNo}&isLogined=1&userNo=${ads.artwork(심플아트워크 담는 객체 변수의 이름).userNo} --%>
                                    <input type="checkbox" name="payment_product" value="${exhibition.exhibitionNo}" checked />    
                                    <%-- <a href="<c:url value='/${frontServletPath}/detail.jsp?artworkNo=${artworkNo}&isLogined=1&userNo=${userNo}' />"> --%>
                                    	<img src="<c:url value='${exhibition.image}' />" class="artwork_img" />
                                    <span><br>${exhibition.title}<br>가격: ${exhibition.price}원</span></a>
                                </label> <!-- 라벨은 있는 게 좋은지 없는 게 좋은지 모르겠다... -->
                                <c:set var="totalCost" value="${exhibition.price}" />
                            </div>
                        </li>
                        </ul>
            </div>
            <div id="payment_means" class="menu" style="margin-top: 20px;">
                <input type="radio" name="pm_means" value="pm_card" class="pay pm_card_click" checked />카드 결제
                <input type="radio" name="pm_means" value="pm_nobackbook" class="pm_nobackbook_click pay" />무통장 입금
                    
                <input type="radio" name="pm_means" value="pm_phone" class="pay pm_phone_click" />휴대폰 결제
                <input type="radio" name="pm_means" value="pm_account" class="pm_account_click pay" />실시간 계좌이체
                <div>
                    <ul class="pm_nobackbook_apperance" >
                        <li>
                            <span>입금자명     </span><input type="text" name="provider"/>
                        </li>
                        <li>
                            <span>입금은행     </span><input type="text" name="bank"/>
                        </li>
                    </ul>  
                    <ul class="pm_account_apperance">
                        <li>
                            <span>예금주명     </span><input type="text" name="depositor"/>
                        </li>
                    </ul>
                </div>
            </div>
            <div style="margin: 20px;">
                <span>최종 결제 금액: </span> ${totalCost}원
            </div>
            <div class="container" style="margin-top:0;">
	  			<button type="button" class="btn btn-default" style="padding-right: 10px;">취소</button>
	  			<button type="button" class="btn btn-default" id="btn_buy" onClick="buy()" >구매</button>
  			</div>
   
        </form>
        </div>
    </section>
	<footer class="w3-center" style="margin-top: 500px;">
        <div style="padding: 30px 0;"><p>Copyright (c) Artrade  |    2018년 5월 22일~ </p><p>대표: 윤 예진</p></div>
    </footer>
</body>
</html>