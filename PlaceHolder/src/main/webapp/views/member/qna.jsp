<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Q&A</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
	#table{
		width:700px;
		height:200px;
	}
	
	#subject{
		width:85%;
	}
	#title{
		width:99%;
	}
	#body{
		width:100%;
		height:100px;
	}
	#contents{
		width:99%;
		height:150px;
	}
	button{
		margin-right:5px;
	}
	#qna{
		padding-left:15px;
	}
	
</style>
</head>
<body>

	<table border=1 align=center id="table">
		<tr>
			<th colspan=2 align=left id=qna>Q&A
		</tr>
		<!-- <tr>
			<td id="subject"><input type="text" id="title" name="title" placeholder="제목을 입력하세요.">
		</tr> -->
		<tr>
			<td colspan=2 id="body"><textarea placeholder="내용을 입력하세요." id="contents" name="inquiryContent"></textarea>
		</tr>
		<tr>
			<td colspan=2 align=right><a href="/write.qna"><button>작성완료</button></a>
					
		</tr>
	</table>
	<script>
		
	</script>
</body>
</html>