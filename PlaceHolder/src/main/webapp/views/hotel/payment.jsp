<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Payment</title>
<link rel="shortcut icon" type="image/x-icon" href="/semi-img/favicon.ico" />
<!-- 제이쿼리CDN -->
      <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>

#container {   
   margin: auto;
   box-sizing: border-box;
   background-color: rgb(215, 232, 250);}

table{width:320px; height:400px; margin:auto;}
h1{text-align:center; background-color: #C6E2FF; border-radius:10px;}

.title{font-weight:600; text-align:right; width:100px; background-color:#c6e1ff6b; border-radius:12px;}
.content{width:160px; overflow:auto; font-style: initial; font-size:13px;}

.btns{text-align:center; background-color: #C6E2FF; border-radius:10px;}
#rev{width:90px; border-radius:12px; background-color:white; border:0px;}
#cancle{width:90px; border-radius:12px; background-color:white; border:0px;}
</style>
</head>
<body>
   <c:forEach var="info" items="${paymentInfo}">
      <form action="/paid.book" method="post" id="form">         
            <table>
               <tr>
                  <td colspan=2><h1>결제정보</h1></td>
               </tr>
               <tr>
                  <td class=title>호텔 이름 ▷</td>
                  <td class="content"id=hotelName name="hotelName">${info.hotelName }</td>
               </tr>
               <tr>
                  <td class=title>회원 아이디 ▷</td>
                  <td class="content" id="userId" name="userId">${info.userId }</td>
               </tr>
               <tr>
                  <td class=title>예약 번호 ▷</td>
                  <td class="content" id="revId" name="reservvId">${info.revId }</td>
               </tr>
               <tr>
                  <td class=title><input type=text name="revId" value="${info.revId }"
                     style="display: none"></td>
                     <td></td>
               </tr>
               <tr>
                  <td class=title>체크인 ▷</td>
                  <td class="content" id="checkIn" name="checkIn">${info.checkIn }</td>
               </tr>
               <tr>
                  <td class=title>체크아웃 ▷</td>
                  <td class="content" class="content" id="checkOut" name="checkOut">${info.checkOut }</td>
               </tr>
               <tr>
                  <td class=title>방 타입 ▷</td>
                  <td class="content" id="revRoomType" name="revRoomType">${info.revRoomType }</td>
               </tr>
               <tr>
                  <td class=title>방 개수 ▷</td>
                  <td class="content" id="revQuantity" name="revQuantity">${info.revQuantity }</td>
               </tr>
               <tr>
                  <td class=title>총 가격 ▷</td>
                  <td class="content" id="revPrice" name="revPrice">${info.revPrice }</td>
               </tr>
               <tr>
                  <td class=title>결제 방법 ▷</td>
                  <td class="content" id="pay" name="pay">현장 결제</td>
               </tr>
               <tr>
                  <td class="btns" colspan=2>
                     <input type="submit" value="결제하기" id="rev">
                     <a href="javascript:history.back();"><input type="button" value="취소" id="cancle"></a>                     
                  </td>                  
               </tr>
            </table>         
      </form>
   </c:forEach>
   
   <script>
      $("#rev").on("click",function(){
         if(!confirm("예약 수정 후 재결제하시는 경우\n체크인 당일 호텔에서 차액을 정산받으실 수 있습니다.")){
            return false;   
         }
         
      })
      
      $("#cancle").on("click",function(){
         if(!confirm("취소하시겠습니까? (이전화면으로 돌아갑니다.)")){
            return false;
         }
      }) 
   </script>
</body>
</html>