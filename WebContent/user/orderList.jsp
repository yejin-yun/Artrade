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

</head>
<body>
	<%@ include file="../main/header.jsp" %>
    <table>
        <tr>
            <td colspan="2" style="width: 250px;"><a href="index.html"><img src="img/artrade_logo.png"></td>
            <td style="width: 600px; font-size: 30px;">구매내역</td>
            <td><a href="장바구니.html"><img style="height: 100px;" src="https://t14.pimg.jp/027/281/254/1/27281254.jpg"></a></td>
        </tr>
        <tr>
            <td colspan="4"><hr></td>
        </tr>
        <tr>
            <td>주문번호</td>
            <td> </td>
            <td>상품명</td>
            <td> </td>
        </tr>   
        <c:set var="flag" value="0" />  
        <c:forEach var="artwork" items="${exhibitions}" varStatus="status">
        
        	<c:if test="${status.count % 5 == 0}">
				<c:set var="flag" value="1" />
				</c:forEach>  
        	</c:if>
        </c:forEach>
        <tr>
            <td>123131142</td>
            <td><img style="height: 100px;"src="https://image.yes24.com/momo/TopCate2210/MidCate9/220988527.jpg"></td>
            <td> 별이 빛나는 밤에</td>
            <td><button>상세정보보기</button></td>
        </tr>
        <tr>
            <td>123131142</td>
            <td><img style="height: 100px;"src="https://image.yes24.com/momo/TopCate2210/MidCate9/220988527.jpg"></td>
            <td> 별이 빛나는 밤에</td>
            <td><button>상세정보보기</button></td>
        </tr>
        <tr>
            <td>123131142</td>
            <td><img style="height: 100px;"src="https://image.yes24.com/momo/TopCate2210/MidCate9/220988527.jpg"></td>
            <td>별이 빛나는 밤에</td>
            <td><button>상세정보보기</button></td>
        </tr>
    
    
    
    </table>
</body>
</html>