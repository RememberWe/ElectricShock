<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${dto.userId }</title>
 <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
 <style>
 #table{
		width:700px;
		height:200px;
	}
	#space{
		width:10%;
	}
	#subject{
		width:85%;
	}
	#title{
		width:99%;
	}
	#body{
		width:100%;
		height:150px;
	}
	#contents{
		width:99%;
		height:150px;
	}
	button{
		margin-right:5px;
	}
 </style>
</head>

<body>
	<form action="/modify.qna" method="post" id="frmDetail">
	<table border=1 align=center id="table">
		<tr>
			<th colspan=2 align=center>Q&A
		</tr>
		<tr>
			<td id="subject">
			<input type="hidden" value="${dto.inquiry_seq}" name="seq">
			<input type="text" size="80" placeholder="제목을 입력하세요." id="title" name="title" readonly value="${dto.userId }">
		</tr>
		<tr>
			<td colspan=2 id="body"><textarea placeholder="내용을 입력하세요." id="contents" name="contents" readonly value="${dto.inquiryContent }"></textarea>
		</tr>
		<tr>
			<td colspan=2 align=right>
			<a href="javascript:history.back()"><button type=button>목록으로</button></a>
			<c:if test="${dto.userId==userId }">
				<a href="/delete.qna"><button type="button" id=del>삭제하기</button></a>
				<button type="button" id=mod>수정하기</button>
				<button type="button" id="modOk" style="display:none;">수정완료</button>
				<button type="button" id="modCancel" style="display:none;">취소</button>
			</c:if>
			</td>
		</tr>
	</table>
	</form>
	<script>
	${"#del"}.on("click",function()){
		if(confirm("정말 삭제하시겠습니까?")){
			location.href="/delete.qna?inquiry_seq=${dto.inquiry_seq}";
		}
	}
	
	const bkTitle="";
	const bkContents="";
	
	${"#mod"}.on("click",function()){
		
		bkTitle=${"#title"}.val();
		bkContents=${"#contents"}.val();
		
		${"#title"}.removeAttr("readonly");
		${"#contents"}.removeAttr("readonly");
		${"#mod"}.css("display","none");
		${"#del"}.css("display","none");
		${"#modOk"}.css("display","inline");
		${"#modCancel"}.css("display","inline");
	}
	
	${"modOk"}.on("click",function(){
		if(confirm("이대로 수정하시겠습니까?")){
			$("#frmDetail").submit();
		}
	});
	
	${"#modCancel"}.on("click",function()){
		if(confirm("정말 취소하시겠습니까?")){
			${"#title"}.val(bktitle);
			${"#contents"}.val(bkContents);
			${"#title"}.attr("readonly","");
			${"#contents"}.attr("readonly","");
			${"#mod"}.css("display","inline");
			${"#del"}.css("display","inline");
			${"#modOk"}.css("display","none");
			${"#modCancel"}.css("display","none");
		}
	}
	</script>
</body>
</html>