<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
#table{
	width:750px;
	height:100px;
}
#space{
	width:5%;
}
#title{
	width:55%;
}
#writer{
	width:15%;
}
#date{
	width:15%;
}
#view{
	width:10%;
}
#contents{
	width:100%;
	height:300px;	
}
#top{
	height:30px;
}

</style>
</head>
<body>
	<table border=1 align=center id="table">
		<tr>
			<th colspan=5 align=center id=top>Q&A
		</tr>
		<tr align =center>
			<td id="space">
			<td id="title" name=inquiryContent>내용
			<td id="writer" name=userId>작성자
			<td id="date" name=inquiryCreated>날짜
			<td id="view" name=inquiryStat>상태
		</tr>
		<tr>
			<td colspan=5 align=center id="contents">
			<c:forEach var="qna_List" items="${qna_List }">
					<tr align="center">
						<td>${qna_List.inquiry_seq }
						<td>${qna_List.inquiryContent }
						<td>${qna_List.userId }
						<td>${qna_List.inquiryCreated }
						<td>${qna_List.inquiryStat }
					</tr>
				</c:forEach>
		</tr>
		<tr>
			<td colspan=5 align=center>${navi}</td>
		</tr>
		<tr>
			<td colspan=5 align=right id=bottom>
			<a href="/qna.jsp"><button id="write">문의하기</button></a>
			</td>
		</tr>
	</table>
</body>
</html>