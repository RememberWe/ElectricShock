<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<table border="1" align="center">
			<tr>
				<td colspan=2 id="title">나의 정보</td>
			</tr>
			<tr>
				<td>아이디:</td>
				<td> ${loginId } </td>
			</tr>
			<tr>
				<td>패스워드:</td>
				<td>${dto.userPw }</td>
			</tr>
			<tr>
				<td>닉네임:</td>
				<td>${dto.userNickname }</td>
			</tr>
			<tr>
				<td>이름:</td>
				<td>${dto.userName }</td>
			</tr>
			<tr>
				<td>전화번호:</td>
				<td>${dto.userPhone }</td>
			</tr>
			<tr>
				<td>이메일:</td>
				<td>${dto.userEmail }</td>
			</tr>
			<tr>
				<td>우편번호:</td>
				<td>${dto.userPost }</td>
			</tr>
			<tr>
				<td>주소1</td>
				<td>${dto.userRoadAddress }</td>
			</tr>
			<tr>
				<td>주소2</td>
				<td>${dto.userRoadAddress2 }</td>
			</tr>
			<div class="row">
		<div class="col-12 loginMenu">
			<button id="update">회원정보수정</button>
			<button id="logOut">로그아웃</button>
			<button id="signOut">회원탈퇴</button>
		</div>
	</div>
	<script>
		
	 document.getElementById("update").addEventListener("click",function(){
	      location.href="/views/member/updateInfo.jsp";
	    })
	    
	 document.getElementById("logOut").addEventListener("click",function(){
	      location.href="/logout.user";
	    })
	    
	 document.getElementById("signOut").addEventListener("click",function(){
	      location.href="/secession.user";
	    })
		
	</script>
			
		</table>
</body>
</html>