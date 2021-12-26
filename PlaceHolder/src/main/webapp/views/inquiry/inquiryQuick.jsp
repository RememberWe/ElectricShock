<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객센터에 문의하기</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="shortcut icon" type="image/x-icon" href="/semi-img/favicon.ico" />
<link rel="stylesheet" href="/semi-css/inquiryQuick.css">
</head>
<body>
		<form action ="/write.qna" method="get" id="form">
        	<div class = "container">
        	<div class = "row title">
        		<div class = "col title" style = "text-align:center;">
        			<br>
        			<br>
        			<label>질문 또는 요청사항을 입력해주세요</label>
        			<input type=text name=hotelId id="cHotelId" style="display:none">
        			<input type=text name=userId id="cUserId" style="display:none">
        			<input type=text name=inquiryStat value="답변 대기" style="display:none">
        		</div>
        	</div>
        	<br>
        	<div class = "row content">
        		<div class = "col content" style = "text-align:center;">
        			<textarea rows = "10" cols = "55" id = "inquiryContent" name = "inquiryContent"></textarea>
        		</div>
        	</div>
        	<div class = "row button">
        		<div class = "col button" style = "text-align:right;">
        			<input type = reset value = "초기화" id="reset">
        			<input type = button value = "작성완료" id="submit">
        		</div>
        	</div>
        </div>
        </form>
        
        <script>
        	
        //글자수 제한
        $("#inquiryContent").keyup(function(){
       	 let content = $(this).val();
       	 if(content.length > 1000){
       		 alert("최대 1000자 입력가능합니다.")
       		 $(this).val(content.substring(0,1000));
       	 }
        });
        
        $("#inquiryTitle").keyup(function(){
       	 let title = $(this).val();
        	if(title.length > 100){
        		alert("최대 100자 입력가능합니다.")
        		$(this).val(content.substring(0,100));
        	 }
        });
        
        // 글 제출 후 팝업창 닫힘
        $("#submit").on("click", function(){
        	let content = $("#inquiryContent").val();
        	if(content.length < 10){
        		alert("최소 10글자 이상 입력해주세요.");
        		return false;
        	}
        	
        	
        	let inquiryContent = $("#inquiryContent").val();
            let hotelId = $("#cHotelId").val();
        	console.log(inquiryContent + hotelId);
        	$.ajax({
        		url:"/write.qna",
        		data:{
        			"inquiryContent" : inquiryContent,
        			"hotelId" : hotelId
        		}
        	}).done(function(){
        		alert("제출이 완료되었습니다.\n빠른 시일 내에 답변 드리겠습니다.\n감사합니다.");
            	window.close();
        	})
        })
        
        function getParentText(){
        	
        }
        
        </script>
</body>
</html>