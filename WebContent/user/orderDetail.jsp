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
        <table>
            <tr>
                <td><a href="index.html" title="Home"><img src="img/artrade_logo.png" alt="artrade"></a></td>
                <td colspan="2"  style="font-size: 30px; text-align: center;">구매내역 상세보기</td>
                <td><a href="장바구니.html"><img style="height: 50px;" src="https://t14.pimg.jp/027/281/254/1/27281254.jpg"></a></td>
            </tr>
            <tr>
                <td colspan="4"><hr></td>
            </tr>
            <tr>
                <td>구매한 상품들</td>
                <td colspan="2" style="width: 700px;">상품명</td>
                <td>구매 날짜 : 2020.10.02</td>
            </tr>
            <tr>
                <td><img style="height: 100px;"src="https://image.yes24.com/momo/TopCate2210/MidCate9/220988527.jpg"></td>
                <td colspan="3">상품명어쩌구저ㅉ구</td>
            </tr>
            <tr>
                <td><img style="height: 100px;"src="https://image.yes24.com/momo/TopCate2210/MidCate9/220988527.jpg"></td>
                <td colspan="3">상품명어쩌구저ㅉ구</td>
            </tr>
            <tr>
                <td><img style="height: 100px;"src="https://image.yes24.com/momo/TopCate2210/MidCate9/220988527.jpg"></td>
                <td colspan="3">상품명어쩌구저ㅉ구</td>
            </tr>
            <tr>
                <td><img style="height: 100px;"src="https://image.yes24.com/momo/TopCate2210/MidCate9/220988527.jpg"></td>
                <td colspan="3">상품명어쩌구저ㅉ구</td>
            </tr>
            <tr>
                <td colspan="4"><hr></td>
            </tr>
            <tr>
                <td colspan="4">여기에 주문 주소 등 주문 정보 나오게 수정하기</td>
            </tr>
            <tr>
                <td colspan="4"><hr></td>
            </tr>
            <tr>
                <td colspan="4" style="text-align: center;"><button>BACK</button></td>
            </tr>

        
        </table>
	<footer class="w3-center" style="margin-top: 500px;">
        <div style="padding: 30px 0;"><p>Copyright (c) Artrade  |    2018년 5월 22일~ </p><p>대표: 윤 예진</p></div>
    </footer>
</body>
</html>