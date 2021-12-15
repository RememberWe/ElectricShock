<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Placeholder 회원정보수정</title>
<!-- 지도 API -->
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<!-- JQUERY CDN -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- SlickSlide CDN -->
<link rel="stylesheet" type="text/css"
	href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
<!-- Fontawesome CDN -->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"
	integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm"
	crossorigin="anonymous">
<!-- Bootstrap CDN -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Google font -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&display=swap"
	rel="stylesheet">
<!-- signup CSS -->
<link rel="stylesheet" href="/semi-css/modify.css">
</head>
<body>
	<div class="container">

		<!-- header -->
		<div class="row header">
			<div class="col-3 align-self-center">
				<a href="index.jsp"><img src="/semi-img/logos.png" id="logo"></a>
			</div>
			<div class="col-8 align-self-center" id="head2">
				<form class="d-flex">
					<input class="form-control me-2" type="search" placeholder="Search"
						aria-label="Search">
					<button class="top-search">
						<i class="fas fa-search"></i>
					</button>
				</form>
			</div>
			<!-- 햄버거메뉴 -->
			<div class="col-1  align-self-center justify-content-end">
				<nav class="navbar navbar-light">
					<button class="navbar-toggler" type="button"
						data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="offcanvas offcanvas-end" tabindex="-1"
						id="offcanvasNavbar" aria-labelledby="offcanvasNavbarLabel">
						<div class="offcanvas-header">
							<h5 class="offcanvas-title" id="offcanvasNavbarLabel">PlaceHolder</h5>

							<button type="button" class="btn-close text-reset"
								data-bs-dismiss="offcanvas" aria-label="Close"></button>
						</div>
						<hr>
						<div class="offcanvas-body">

							<ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
								<c:choose>
									<c:when test="${loginId != null}">
										<!-- 로그인 이후 보일 내용 -->
										<li class="nav-item" id="loginAcc">
											<div class="row">
												<div class="col-12 loginAcc"></div>
											</div>
											<div class="row">
												<div class="col-8 loginMent">${loginId}님안녕하세요.</div>
												<div class="col-4">
													<a href="/logout.user"><button class="logOut">로그아웃</button></a>
												</div>
											</div>
											<div class="row loginAccBannerH">
												<div class="col-3">
													<a href="/list.hotel"><button class="loginAccBanner">예약</button></a>
												</div>
												<div class="col-3">
													<a href=""><button class="loginAccBanner">후기</button></a>
												</div>
												<div class="col-3">
													<a href=""><button class="loginAccBanner">찜목록</button></a>
												</div>
												<div class="col-3">
													<a href="/userInfo.user"><button class="loginAccBanner">MyPage</button></a>
												</div>
											</div>
										</li>
									</c:when>
									<c:otherwise>
										<!-- 로그인 폼 -->
										<li class="nav-item">
											<div class="row signBox">
												<form action="/login.user" method="post">
													<div class="col-12 signInput">
														<input type="text" placeholder="Input ID" class="inputId"
															name="id"> <input type="password"
															placeholder="Input PW" class="inputPw" name="pw">
													</div>
													<div class="row">
														<div class="col-6 sign">
															<button class="signBtns">로그인</button>
														</div>
														<div class="col-6 sign">
															<button type="button" class="signBtns signUp">회원가입</button>
														</div>
													</div>
												</form>
											</div>


										</li>
									</c:otherwise>
								</c:choose>
								<li class="nav-item">
									<button type="button" class="sideBanner">이 달의 이벤트</button>
								</li>
								<li class="nav-item"><a href="/main.user"><button
											type="button" class="sideBanner">홈으로</button></a></li>
								<li class="nav-item">
									<button type="button" class="sideBanner">자유게시판</button>
								</li>
							</ul>
							<form class="d-flex">
								<input class="form-control me-2" type="search"
									placeholder="Search" aria-label="Search">
								<button class="btn btn-outline-success" type="submit">Search</button>
							</form>
						</div>
					</div>
				</nav>
			</div>
		</div>
	</div>

	<!-- nav 바 첫번째 -->
	<div class="container">
		<div class="nav">
			<div class="nav1">
				<a href="">
					<button type="button" class="navBtn">예약</button>
				</a>
			</div>
			<div class="nav1">
				<a href="">
					<button type="button" class="navBtn">커뮤니티</button>
				</a>
			</div>
			<div class="nav1">
				<a href="">
					<button type="button" class="navBtn">고객센터</button>
				</a>
			</div>
			<div class="nav1">
				<a href="">
					<button type="button" class="navBtn">MY PAGE</button>
				</a>
			</div>
		</div>
	</div>
	<br>
	<br>

	<div class="container">
		<h1>MY PAGE</h1>
		<h3>정보 수정</h3>
		<hr>
	</div>

	<!-- 회원가입 시작 -->
	<form action="/modify.user" method="post" id="form">
		<div class="signup">
			<div class="signupBox">
				<div class=signupName>아이디</div>
				<div class=signupVal>
					<input type=text placeholder="아이디 입력" id="id" name="id"
						value="${userDTO.userId}" readonly>
				</div>
				<span class=signupRegex id="idRegex"></span>
			</div>
			<br> <br>
			<div class="signupBox">
				<div class=signupName>이름</div>
				<div class=signupVal>
					<input type=text placeholder="이름" value="${userDTO.userName}"
						id="name" name="name">
				</div>
				<span class=signupRegex id="nameRegex"></span>
			</div>
			<br> <br>
			<div class="signupBox">
				<div class=signupName>이전 비밀번호</div>
				<div class=signupVal>
					<input type=password placeholder="비밀번호 입력" id="bpw" name="bpw">
				</div>
				<span class=signupRegex id="bpwRegex"></span>
			</div>
			<br>
			<div class="signupBox">
				<div class=signupName>새로운 비밀번호</div>
				<div class=signupVal id="pwVal">
					<input type=password placeholder="비밀번호 입력" id="pw" name="pw">
				</div>
				<span class=signupRegex id="pwRegex"></span>
			</div>
			<br>
			<div class="signupBox">
				<div class=signupName>비밀번호 확인</div>
				<div class=signupVal id="rpwVal">
					<input type=password placeholder="비밀번호 재확인" id="rpw" name="rpw">
				</div>
				<div class=signupRegex id="rpwRegex"></div>
			</div>
			<br> <br>
			<div class="signupBox">
				<div class=signupName>닉네임</div>
				<div class=signupVal id="nicknameVal">
					<input type=text placeholder="닉네임 입력"
						value="${userDTO.userNickname}" id="nickname" name="nickname">
				</div>
				<span class=signupRegex id="nicknameRegex"></span>
			</div>
			<br> <br>
			<div class="signupBox">
				<div class=signupName>이메일</div>
				<div class=signupVal id="emailVal">
					<input type=text placeholder="메일 입력" value="${userDTO.userEmail}"
						id="email" name="email">
				</div>
				<span class=signupRegex id="emailRegex"></span>
			</div>
			<div class="signupBox">
                    <div class=signupName>생년월일</div>
                    <div class=signupVal id="birthVal">
                    	<input type=text placeholder="주민등록번호 앞자리 입력" value="${userDTO.userBirth}" id="birth" name="birth">
                    </div>
                    <span class=signupRegex id="birthRegex"></span>
                </div><br><br>
			<br> <br>
			<div class="signupBox">
				<div class=signupName>전화번호</div>
				<div class=signupVal id="phoneVal">
					<input type=text placeholder="전화번호 입력" value="${userDTO.userPhone}"
						id="phone" name="phone">
				</div>
				<span class=signupRegex id="phoneRegex"></span>
			</div>
			<br> <br>
			<div class="signupBox">
				<div class=signupName>우편번호</div>
				<div class=signupVal id="test5">
					<div id="test1">
						<input type=text placeholder="우편번호 입력" value="${userDTO.userPost}"
							id="ad" name="post">
					</div>
					<div id="test2">
						<input type=button value="찾기" id="find">
					</div>
				</div>
			</div>
			<br>
			<div class="signupBox">
				<div class=signupName>도로명주소</div>
				<div class=signupVal id="ad1Val">
					<input type=text placeholder="주소"
						value="${userDTO.userRoadAddress}" id="ad1" name="roadAddress">
				</div>
			</div>
			<br>

			<div class="signupBox">
				<div class=signupName>상세주소</div>
				<div class=signupVal id="ad2Val">
					<input type=text placeholder="나머지 주소를 입력해주세요" value="${userDTO.userRoadAddress2}" id="ad2"
						name="roadAddress2">
				</div>
			</div>
			<br>

			<!-- 수정완료  -->
			<div class="signupBox">
				<input type=submit value="수정완료" id="signup-btn">
			</div>
			<div class="signupBox">
				<button type=button id=delUser>회원탈퇴</button>
			</div>
			<br>
	</form>

	<!-- FOOTER -->
	<div class="signupBox" id="footer-background">
		<div class="row">
			<div class="col-2 footers">
				<a href="">이용약관</a>
			</div>
			<div class="col-3 footers">
				<a href="">개인정보처리방침</a>
			</div>
			<div class="col-4 footers">
				<a href="">책임의 한계와 법적고지</a>
			</div>
			<div class="col-3 footers">
				<a href="">회원정보 고객센터</a>
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-12 footer">
				<img src="semi-img/logos.png" id="footer-img"> &nbsp;&nbsp;
				Copyright PLACEHOLDER Corp. All Rights Reserved.
			</div>
		</div>
		<br>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<script type="text/javascript"
		src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>

	<script>
        let form = $("#form");
        let pw = $("#pw");
        let pw_result = $("#pwRegex");
        let rpw = $("#rpw");
        let rpw_result = $("#rpwRegex");
        let id = $("#id");
        let idResult = $("#idRegex");
        let name = $("#name");
        let name_result = $("#nameRegex");
        let phone = $("#phone");
        let phone_Result = $("#phoneRegex");
        let email = $("#email");
        let email_Result = $("#emailRegex");

        // input받는창 아래에 유효성 검사

        // 이름 검사
        $("#name").on("input", function () {
            let nameRegex = /^[가-힣 a-z A-Z]{2,8}$/;
            let nameResult = nameRegex.test($("#name").val());
            if (!nameResult) {
                $("#nameRegex").html("한글 또는 영어 2글자 이상 필요합니다");
                $("#nameRegex").css("color", "red");
            } else {
                $("#nameRegex").html("확인완료")
                $("#nameRegex").css("color", "green");
            }
            if ($("#name").val() == "") {
                $("#nameRegex").html("");
            }
        })

        // 이전 비밀번호검사
        $("#bpw").on("input", function () {
            let bpwRegex = /${dto.pw}/;
            let bpwResult = bpwRegex.test($("#bpw").val());
            if (!bpwResult) {
                $("#bpwRegex").html("이전비밀번호를 입력해주세요");
                $("#bpwRegex").css("color", "red");
            } else {
                $("#bpwRegex").html("일치");
                $("#bpwRegex").css("color", "blue");
            }

            if ($("#bpw").val() == "") {
                $("#bpwRegex").html("");
            }
        })


        // 패스워드 검사
        pw.on("input", function () {
            let pwRegex = /[a-z0-9]{4,12}$/;
            let pwResult = pwRegex.test(pw.val());
            if (!pwResult) {
                pw_result.html("4~12 자리로 입력바랍니다");
                pw_result.css("color", "red");
            } else {
                pw_result.html("확인완료");
                pw_result.css("color", "green");

                if (pw.val() == "" && rpw.val() == "") {
                    pw_result.html("");
                    rpw_result.html("");
                }
            }
        })
        rpw.on("input", function () {
            if (pw.val() != rpw.val()) {
                rpw_result.html("비밀번호가 일치하지 않습니다.");
                rpw_result.css("color", "red");

            } else {
                rpw_result.html("비밀번호가 일치합니다.");
                rpw_result.css("color", "blue");
            }

            if (pw.val() == "" && rpw.val() == "") {
                pw_result.html("");
                rpw_result.html("");
            }
        })

        pw.on("input", function () {
            if (pw.val() != rpw.val()) {
                rpw_result.html("비밀번호가 일치하지 않습니다.");
                rpw_result.css("color", "red");
            } else {
                rpw_result.html("비밀번호가 일치합니다.");
                rpw_result.css("color", "blue");
            }

            if (pw.val() == "" && rpw.val() == "") {
                pw_result.html("");
                rpw_result.html("");
            }
        })

        // 닉네임 검사

        $("#nickname").on("input", function () {
            let nicknameRegex = /^[a-zA-Z가-힣\d]{2,12}$/;
            let nicknameResult = nicknameRegex.test($("#nickname").val());
            if (!nicknameResult) {
                $("#nicknameRegex").html(" 2~12 자리로 입력바랍니다");
                $("#nicknameRegex").css("color", "red");
            } else {
                $("#nicknameRegex").html("확인완료")
                $("#nicknameRegex").css("color", "green");
            }
            if ($("#nickname").val() == "") {
                $("#nicknameRegex").html("");
            }
        })


        // 이메일 검사

        email.on("input", function () {
            let emailRegex = /.+?@.+?\.com$/;
            let emailResult = emailRegex.test(email.val());
            if (!emailResult) {
                email_Result.html("예시> ____ @ ____ .com");
                email_Result.css("color", "red");
            } else {
                email_Result.html("확인완료")
                email_Result.css("color", "green");
            }
            if (email.val() == "") {
                email_Result.html("");
            }
        })
        
        // 생년월일 검사

        $("#birth").on("input", function () {
            let birthRegex = /^\d{6}$/;
            let birthResult = birthRegex.test($("#birth").val());
            if (!birthResult) {
                $("#birthRegex").html("주민등록번호 앞자리");
                $("#birthRegex").css("color", "red");
            } else {
                $("#birthRegex").html("확인완료")
                $("#birthRegex").css("color", "green");
            }
            if (birth.val() == "") {
                $("#birthRegex").html("");
            }
        })

        // 전화번호 검사

        phone.on("input", function () {
            let phoneRegex = /010[0-9]{8}$/;
            let phoneResult = phoneRegex.test(phone.val());
            if (!phoneResult) {
                phone_Result.html("양식 : 010 - xxxx - xxxx");
                phone_Result.css("color", "red");
            } else {
                phone_Result.html("확인완료")
                phone_Result.css("color", "green");
            }
            if (phone.val() == "") {
                phone_Result.html("");
            }
        })


        // <FORM> 태그 페이지 이동 막기
        form.on("submit", function () {
            
            let nameRegex = /[가-힣a-z]{2,}/g;            
            let nameResult = nameRegex.test(name.val());
            if (!nameResult) {
                alert("이름을 확인해 주세요.")
                name.val("");
                name.focus();
                return false;
            }            

            let bpwRegex = /${dto.userPw}/;
            let bpwResult = bpwRegex.test($("#bpw").val());
            if (!bpwResult) {
                alert("이전 비밀번호랑 일치하지 않습니다.")
                $("#bpw").val("");
                $("#bpw").focus();
                return false;
            }            

            let pwRegex = /[a-z0-9]{4,12}$/g;
            let pwResult = pwRegex.test(pw.val());
            if (!pwResult) {
                alert("패스워드를 8자리로입력해주세요.")
                pw.val("");
                pw.focus();
                return false;
            }
            
            let rpwRegex = /[a-z0-9]{4,12}$/g;
            let rpwResult = rpwRegex.test(rpw.val());
            if (!rpwResult) {
                alert("패스워드확인을 제대로 입력해주세요.")
                rpw.val("");
                rpw.focus();
                return false;
            }
            
            let emailRegex = /.+?@.+?\.com/g;
            let emailResult = emailRegex.test(email.val());
            if (!emailResult) {
                alert("이메일을 제대로 입력하세요.")
                email.val("");
                email.focus();
                return false;
            }
            
            let birthRegex = /^\d{6}$/;
            let birthResult = birthRegex.test($("#birth").val());
            if (!birthResult) {
                alert("주민등록번호 앞자리를 입력하세요.")
                $("#birth").val("");
                $("#birth").focus();
                return false;
            }             

            let phoneRegex = /010[0-9]{8}$/;
            let phoneResult = phoneRegex.test(phone.val());
            if (!phoneResult) {
                alert("전화번호를 제대로 입력해주세요.")
                phone.val("");
                phone.focus();
                return false;
            }
            
        document.getElementById("find").onclick = function () {
            new daum.Postcode({
                oncomplete: function (data) {
                    document.getElementById('ad').value = data.zonecode;
                    document.getElementById("ad1").value = data.roadAddress;
                }
            }).open();
        }
    </script>
</body>
</html>