<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% request.setCharacterEncoding("UTF-8"); %>


<!DOCTYPE html>
<html>
<head>
    <title>Artrade</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta content="text/html; charset=iso-8859-2" http-equiv="Content-Type">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/base.css' />" >
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/detail.css' />" >
    <style>
       #regiForm {
          width: 80%;
          margin: 0, auto;
       }
       
       
       .name {
          text-align: left;
          width: 15%;
       }
    </style>
    <script src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
    <script src="<c:url value='/js/base.js' />" ></script>
    <script>
    
    
    function formSumbit() {
       if (form.title.value == "") {
          alert("작품명을 입력하십시오."+form.title.value);
          form.title.focus();
          return false;
       } 
       
       if (form.artistName.value == "") {
          alert("작가명을 입력하십시오.");
          form.artistName.focus();
          return false;
       } 
       
       if (form.workSize.value == "") {
          alert("작품 크기를 입력하십시오.");
          form.workSize.focus();
          return false;
       } 
       if (form.description.value == "") {
          alert("상품설명을 입력하십시오.");
          form.description.focus();
          return false;
       } 
       if (form.price.value == "") {
          alert("가격을 입력하십시오.");
          form.price.focus();
          return false;
       } 
       
       if (Character.isDigit(form.price.value) == false) {
          alert("정수값을 입력하십시오.");
          form.price.focus();
          return false;
       } 
       
       
       
       
      form.submit();
       
       
       
       
    }

    function userList(targetUri) {
       form.action = targetUri;
       form.submit();
    }
    
    
    </script>
    
      <script> 
        function setThumbnail(event) { 
            var reader = new FileReader();  //파일 읽어오기
            reader.onload = function(event) {  // 로드...
                var img = document.createElement("img");
                img.setAttribute("src", event.target.result);
                
                
                    document.querySelector("div#image_container").innerHTML = '';
                
                
                document.querySelector("div#image_container").appendChild(img); 
                
               
                
                
                
                //원래 있던 사진은 지워져야하는데...
                
                
                
            }; 
            reader.readAsDataURL(event.target.files[0]); 
        } 
    </script>
    
</head>
<body>
<%@ include file="../main/header.jsp" %>
    <%
       Artwork art = new Artwork();
    %>
    
    <div class="container" align="center">
    <h1>작품 등록</h1>
    
    <form name="form" id="regiForm" method="POST" enctype="multipart/form-data" action="<c:url value='/artwork/register' />">    
        <table  style="font-size:15pt;">
            <tr>
                 <td rowspan="6" style="background-color: gray; width: 600px" class="name">
                    <div id="image_container" style="width: 600px; height: 600px; overflow: hidden"></div>
                 </td>
                <td class="name">작품명</td>
                <td><input type="text" name="title" ></td>
            </tr>  
            <tr>
                <td class="name">작가명</td>
                <td><input type="text" name="artistName"></td>
            </tr>
            <tr>
                <td class="name">작품 크기</td>
                <td><input type="text" name="workSize"></td>
            </tr>
            <tr>
                <td class="name">키워드</td>
                <td><input type="text" name="keywordString"></td>
            </tr>
            <tr>
                <td class="name">상품설명</td>
                <td><input type="text" style="height: 60pt;" name="description"></td>
            </tr>
            <tr>
                <td class="name">가격</td>
                <td><input type="text" name="price"></td>
            </tr>
            <tr>
                 <td style="text-align: center;">
                <input type="file" name="picture" id="image" accept="image/*" onchange="setThumbnail(event);"/>
            </td>
                <td colspan="2" style="text-align: center;"><input type="submit" value="등록하기" name="sumbit "onClick="formSumbit()"></td>
            </tr>
        </table>
        
        </form>
    
    </div>
    <footer class="w3-center">
        <div style="padding: 30px 0;"><p>Copyright (c) Artrade  |    2018년 5월 22일~ </p><p>대표: 윤 예진</p></div>
    </footer>
    </body>
</html>