<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Placeholder 회원정보수정</title>
<link rel="shortcut icon" type="image/x-icon" href="/semi-img/favicon.ico" />
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
<!-- modify css -->
<link rel="stylesheet" href="/semi-css/modify.css">
</head>
<body>
   <div class="container">
      <div class="logo">
         <img src="/semi-img/logos.png" id="signupLogo">
      </div>
      <br> <br>
      <hr>
      <div class="title">
         <br>
         <h1 style="font-weight: 600;">회원정보 수정</h1>
         <br>
      </div>

      <!-- 회원가입 시작 -->
      <form action="/modify.user" method="post" id="form">
         <div class="signup">
            <div class="signupBox">
               <div class=signupName>아이디</div>
               <div class=signupVal id="idVal">
                  <input type=text placeholder="아이디 입력" id="id" name="id"
                     value="${userDTO.userId}" readonly>
               </div>
               <span class=signupRegex id="idRegex"></span>
            </div>
            <br> <br>
            <div class="signupBox">
               <div class=signupName>이름</div>
               <div class=signupVal id="nameVal">
                  <input type=text placeholder="이름" value="${userDTO.userName}"
                     id="name" name="name">
               </div>
               <span class=signupRegex id="nameRegex"></span>
            </div>
            <br> <br>
            <div class="signupBox">
               <div class=signupName>이전 비밀번호</div>
               <div class=signupVal id="bpwVal">
                  <div class="bpwVal1">
                     <input type=password placeholder="비밀번호 입력" id="bpw" name="bpw">
                  </div>
                  <div class="bpwVal2">
                     <input type=button value="확인" id="bpwBtn">
                  </div>
               </div>
               <span class=signupRegex id="bpwRegex"></span>
            </div>
            <br>
            <div class="signupBox">
               <div class=signupName>비밀번호</div>
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
                  <input type=text placeholder="닉네임 입력" id="nickname"
                     name="nickname" value="${userDTO.userNickname}">
               </div>
               <span class=signupRegex id="nicknameRegex"></span>
            </div>
            <br> <br>
            <div class="signupBox">
               <div class=signupName>이메일</div>
               <div class=signupVal id="emailVal">
                  <input type=text placeholder="메일 입력" id="email" name="email"
                     value="${userDTO.userEmail}">
               </div>
               <span class=signupRegex id="emailRegex"></span>
            </div>
            <br> <br>
            <div class="signupBox">
               <div class=signupName>생년월일</div>
               <div class=signupVal id="birthVal">
                  <input type=text placeholder="주민등록번호 앞자리 입력" id="birth"
                     name="birth" value="${userDTO.userBirth}">
               </div>
               <span class=signupRegex id="birthRegex"></span>
            </div>
            <br> <br>
            <div class="signupBox">
               <div class=signupName>전화번호</div>
               <div class=signupVal id="phoneVal">
                  <input type=text placeholder="전화번호 입력" id="phone" name="phone"
                     value="${userDTO.userPhone}">
               </div>
               <span class=signupRegex id="phoneRegex"></span>
            </div>
            <br> <br>
            <div class="signupBox">
               <div class=signupName>우편번호</div>
               <div class=signupVal id="test5">
                  <div id="test1">
                     <input type=text placeholder="아이디입력" id="ad" name="post"
                        value="${userDTO.userPost}">
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
                  <input type=text placeholder="주소" id="ad1" name="roadAddress"
                     value="${userDTO.userRoadAddress}">
               </div>
            </div>
            <br>

            <div class="signupBox">
               <div class=signupName>상세주소</div>
               <div class=signupVal id="ad2Val">
                  <input type=text placeholder="상세주소" id="ad2" name="roadAddress2"
                     value="${userDTO.userRoadAddress2}">
               </div>
            </div>
            <br>

            <!-- 가입하기  -->
            <div class="signupBox">
               <input type=button value="수정완료" id="signup2-btn">
            </div>
            <div class="signupBox">
               <input type=submit value="수정완료" id="signup-btn">
            </div>
            
            <div class="signupBox">
               <input type=button value="회원탈퇴" id="lieve-btn">
            </div>
            <br>
      </form>
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
        let beforePw = "${userDTO.userPw}";
        console.log(beforePw);
       

        // input받는창 아래에 유효성 검사

        // ID 검사
        $("#id").on("input", function () {
            let idRegex = /[a-z][a-zA-Z\d]{4,10}/;
            let idResult = idRegex.test($("#id").val());
            if (!idResult) {
                $("#idRegex").html("첫글자 : 영어소문자 / 영어,숫자(5~10글자)로 입력바랍니다.");
                $("#idRegex").css("color", "red");
            } else {
                $("#idRegex").html("확인완료")
                $("#idRegex").css("color", "green");
            }

            if ($("#id").val() == "") {
                $("#idRegex").html("");
            }
        })

        // 이름 검사
        $("#name").on("input", function () {
            let nameRegex = /[가-힣 a-z A-Z]{2,}/;
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
        
        // 예전 비밀번호 검사 코드
         $("#bpwBtn").on("click",function(){
            $.ajax({
             url:"/pw.user",
             data:{pw:$("#bpw").val()}
             }).done(function(resp){
                console.log(resp);
                if(resp==beforePw){                   
                   $("#bpwRegex").html("예전 비밀번호와 동일합니다.");
                       $("#bpwRegex").css("color", "Blue");
                       $("#signup-btn").css("display","inline");
                    $("#signup2-btn").css("display","none");
                }else{
                   $("#bpwRegex").html("예전 비밀번호와 불일치.");
                       $("#bpwRegex").css("color", "red");
                    $("#signup-btn").css("display","none");
                       $("#signup2-btn").css("display","inline");                      
                   }
               })
        });

        // 패스워드 검사
        rpw.on("input", function () {
            let pwRegex = /[a-z0-9]{4,12}/;
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

        // 이메일 검사

        email.on("input", function () {
            let emailRegex = /.+?@.+?\.com/;
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
      // *** 윤년 관련해 보완 필요
        $("#birth").on("input", function () {
           let birthRegex = /\d{2}([0]\d|[1][0-2])([0][1-9]|[1-2]\d|[3][0-1])$/;
            let birthResult = birthRegex.test($("#birth").val());
            if (!birthResult) {
                $("#birthRegex").html("유효한 생년월일(주민번호 앞자리)을 입력해주세요");
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
                phone_Result.html("010******** ('-'제외하고 입력, 총 11자리)");
                phone_Result.css("color", "red");
            } else {
                phone_Result.html("확인완료")
                phone_Result.css("color", "green");
            }
            if (phone.val() == "") {
                phone_Result.html("");
            }
        })
        
        $("#signup2-btn").on("click",function(){
        	alert("이전 비밀번호를 확인해주세요")
        	$("#bpw").val("");
        	$("#bpw").focus();
        })

        // <FORM> 태그 페이지 이동 막기
        form.on("submit", function () {            
            
            let nameRegex = /[가-힣a-z]{2,}/g;
            let nameResult = nameRegex.test(name.val());
            if (!nameResult) {
                alert("이름을 입력해주세요.")
                name.val("");
                name.focus();
                return false;
            }

            let pwRegex = /[a-z0-9]{4,12}/g;
            let pwResult = pwRegex.test(pw.val());
            if (!pwResult) {
                alert("패스워드를 4~12자리로입력해주세요.")
                pw.val("");
                pw.focus();
                return false;
            }
            let rpwRegex = /[a-z0-9]{4,12}/g;
            let rpwResult = rpwRegex.test(rpw.val());
            if (!rpwResult) {
                alert("패스워드확인을 입력해주세요.")
                rpw.val("");
                rpw.focus();
                return false;
            }

            let emailRegex = /.+?@.+?\.com/g;
            let emailResult = emailRegex.test(email.val());
            if (!emailResult) {
                alert("이메일을 확인해주세요.")
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
                alert("전화번호를 확인해 주세요.")
                phone.val("");
                phone.focus();
                return false;
            }

            if(document.getElementById("nickname").value == ''){
               alert("닉네임을 입력해 주세요.");
               return false;
            }

            if(document.getElementById("ad").value == ''){
               alert("주소를 입력해 주세요.");
               return false;
            }

            if(document.getElementById("ad1").value == ''){
               alert("주소를 입력해 주세요.");
               return false;
            }

            if(document.getElementById("ad2").value == ''){
               alert("주소를 입력해 주세요.");
               return false;
            }
            
            
            $.ajax({
                url:"/pw.user",
                data:{pw:$("#bpw").val()}
                }).done(function(resp){
                   console.log(resp);
                   if(resp==beforePw){
                	   alert("수정이 완료되었습니다.")                	   
                   }
               	})
        })

        document.getElementById("find").onclick = function () {
            new daum.Postcode({
                oncomplete: function (data) {
                    document.getElementById('ad').value = data.zonecode;
                    document.getElementById("ad1").value = data.roadAddress;                   
                }
            }).open();
        }
        
        $("#lieve-btn").on("click",function(){
           if(confirm("정말로 탈퇴하시겠습니까?")){
              alert("탈퇴되었습니다.");
              location.href="/secession.user"
           }
        })
    </script>
</body>
</html>