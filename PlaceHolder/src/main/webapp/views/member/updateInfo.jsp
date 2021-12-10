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
	<form action="/update.user" method="post">
		<table border="1" align="center">
			<tr>
				<td colspan=2 id="title">나의 정보</td>
			</tr>
			<tr>
				<td>아이디:</td>
				<td>${loginId }</td>
			</tr>
			<tr>
				<td>패스워드:</td>
				<td><input type=password name="pw" id="pw"></td>
			</tr>
			<tr>
				<td>닉네임:</td>
				<td><input type=text name=nickname id="nickname"></td>
			</tr>
			<tr>
				<td>이름:</td>
				<td><input type=text name=name id="name"></td>
			</tr>
			<tr>
				<td>전화번호:</td>
				<td><input type=text name=phone id="phone"></td>
			</tr>
			<tr>
				<td>이메일:</td>
				<td><input type=text name=email id="email"></td>
			</tr>
			<tr>
				<td>생년월일:</td>
				<td><input type=text name=birth id="birth"></td>
			</tr>
			<tr>
				<td>우편번호:</td>
				<td><input type=text name=post id="post"></td>
			</tr>
			<tr>
				<td>주소1</td>
				<td><input type=text name=roadAddress id="roadAddress"></td>
			</tr>
			<tr>
				<td>주소2</td>
				<td><input type=text name=roadAddress2 id="roadAddress2"></td>
			</tr>
			<tr>
				<td colspan=2><input type=submit id="submit"></td>
		</table>
	</form>
</body>
</html>