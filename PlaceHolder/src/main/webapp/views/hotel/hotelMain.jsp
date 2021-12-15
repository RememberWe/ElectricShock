<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"
	integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm"
	crossorigin="anonymous">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet" href="/semi-css/hotelMain.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<body>
    <!-- 로고 / 검색 / 햄버거  -->
    <div class="container" id="head">
        <div class="row">
            <div class="col-3 align-self-cente">
                <a href=""><img src="semi-img/logos.png" id="logo"></a>
            </div>
            <div class="col-8 align-self-center" id="head2">
                <form class="d-flex">
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                    <button><i class="fas fa-search"></i></button>
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
													<div class="col-4"><a href="/logout.user"><button class="logOut">로그아웃</button></a></div>
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
																<a href="/signupPage.user"><button type="button" class="signBtns signUp">회원가입</button></a>
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
									<li class="nav-item">
										<a href="/main.user"><button type="button" class="sideBanner">홈으로</button></a>
									</li>
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
        <div class="row">
            <a hrfe="/list.hotel"><div class="nav1"><button type="button" class="btn" style=" background-color: rgba(0, 255, 255, 0.24);" id="nav-1">예약</button></div></a>
            <div class="nav1"><button type="button" class="btn" style=" background-color: rgba(0, 255, 255, 0.24);" id="nav-1">커뮤니티</button></div>
            <div class="nav1"><button type="button" class="btn" style=" background-color: rgba(0, 255, 255, 0.24);" id="nav-1">고객센터</button></div>
            <div class="nav1"><button type="button" class="btn" style=" background-color: rgba(0, 255, 255, 0.24);" id="nav-1">마이페이지</button></div>
        </div>
    </div>
    <!-- nav 바 두번째 -->
    <div class="container">
        <div class="row">
            <div class="nav2 col-4"><button type="button" class="btn" style="background-color: rgba(216, 112, 147, 0.397);"
                    id="nav-2"><i class="fas fa-city"></i>&nbsp;&nbsp;도시</button></div>
            <div class="nav2 col-2"><button type="button" class="btn" style="background-color: rgba(216, 112, 147, 0.397);"
                    id="nav-2"><i class="fas fa-calendar-check"></i>&nbsp;&nbsp;CHECK-IN</button></div>
            <div class="nav2 col-2"><button type="button" class="btn" style="background-color: rgba(216, 112, 147, 0.397);"
                    id="nav-2"><i class="far fa-calendar-check">&nbsp;&nbsp;CHECK-OUT</i></button></div>
            <div class="nav2 col-2"><button type="button" class="btn" style="background-color: rgba(216, 112, 147, 0.397);"
                    id="nav-2"><i class="fas fa-user-friends"></i>&nbsp;&nbsp;인원수</button></div>
            <div class="nav2 col-2"><button type="button" class="btn" style="background-color: rgba(216, 112, 147, 0.397);"
                    id="nav-2"><i class="fas fa-clipboard-list"></i>&nbsp;&nbsp;예약</button></div>
        </div>
    </div>
    <!-- 메인 캐러셀 -->
    <div class="container">
        <div id="carouselExampleFade" class="carousel slide carousel-fade" data-bs-ride="carousel">
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <a href="">
                        <img src="semi-img/hotel1.jpg" class="d-block w-100" alt="...">
                    </a>
                </div>
                <div class="carousel-item">
                    <a href="">
                        <img src="semi-img/hotel2.jpg" class="d-block w-100" alt="...">
                    </a>
                </div>
                <div class="carousel-item">
                    <a href="">
                        <img src="semi-img/hotel3.jpg" class="d-block w-100" alt="...">
                    </a>
                </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleFade"
                data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleFade"
                data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>
    </div>
    <br><br>
    <!-- Today's Hot! -->
    <div class="container" id="today">
        <div class="thTitle">
            <h1>Today's Hot</h1>
            <br>
        </div>
        <div class="thPicture">
            <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <div class="row">
                            <div class="col-4">
                                <div class="card text-dark mb-3" style="max-width: 18rem; background-color: rgba(0, 255, 255, 0.24);">
                                    <a href="">
                                        <div class="card-body" id="cardlist1">
                                        </div>
                                    </a>
                                    <div class="card-header">
                                        <h5>현우님한테 드리는 호텔</h5>
                                    </div>
                                </div>
                            </div>
                            <div class="col-4">
                                <div class="card text-dark mb-3" style="max-width: 18rem; background-color: rgba(0, 255, 255, 0.24);">
                                    <a href="">
                                        <div class="card-body" id="cardlist2">
                                        </div>
                                    </a>
                                    <div class="card-header">
                                        <h5>진규선물</h5>
                                    </div>

                                </div>
                            </div>
                            <div class="col-4">
                                <div class="card text-dark mb-3" style="max-width: 18rem; background-color: rgba(0, 255, 255, 0.24);">
                                    <a href="">
                                        <div class="card-body" id="cardlist3">
                                        </div>
                                    </a>
                                    <div class="card-header">
                                        <h5>소현선물</h5>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <div class="row">
                            <div class="col-4">
                                <div class="card text-dark mb-3" style="max-width: 18rem; background-color: rgba(0, 255, 255, 0.24);">
                                    <a href="">
                                        <div class="card-body" id="cardlist4">
                                        </div>
                                    </a>
                                    <div class="card-header">
                                        <h5>병주야 받으렴</h5>
                                    </div>
                                </div>
                            </div>
                            <div class="col-4">
                                <div class="card text-dark mb-3" style="max-width: 18rem; background-color: rgba(0, 255, 255, 0.24);">
                                    <a href="">
                                        <div class="card-body" id="cardlist5">
                                        </div>
                                    </a>
                                    <div class="card-header">
                                        <h5>강사님 선물</h5>
                                    </div>
                                </div>
                            </div>
                            <div class="col-4">
                                <div class="card text-dark mb-3" style="max-width: 18rem; background-color: rgba(0, 255, 255, 0.24);">
                                    <a href="">
                                        <div class="card-body" id="cardlist6">
                                        </div>
                                    </a>
                                    <div class="card-header">
                                        <h5>나에게 주는 선물</h5>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls"
                    data-bs-slide="prev">

                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls"
                    data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>

        </div>
    </div>
    <hr>
    <br><br>
    <!-- Review Zone  -->
    <div class="container" id="review">
        <div class="rzTitle">
            <h1>- Review Zone -</h1>
            <br>
        </div>
        <div class="rzPicture">
            <div class="row row-cols-1 row-cols-md-3 g-4">
                <div class="col">
                    <div class="card h-100">
                        <a href="">
                            <p class=card-img id="card-img-1"></p>
                            <div class="card-body">
                                <h5 class="card-title">천현우 호텔</h5>
                                <p class="card-text">조장이여 영원하라</p>
                            </div>
                        </a>
                        <div class="card-footer">
                            <small class="text-muted">Last updated 3 mins ago</small>
                        </div>

                    </div>
                </div>
                <div class="col">
                    <div class="card h-100">
                        <a href="">
                            <p class=card-img id="card-img-2"></p>
                            <div class="card-body">
                                <h5 class="card-title">진규의 따듯한 호텔</h5>
                                <p class="card-text">찜질방보단 진규호텔으로</p>
                            </div>
                        </a>
                        <div class="card-footer">
                            <small class="text-muted">Last updated 3 mins ago</small>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card h-100">
                        <a href="">
                            <p class=card-img id="card-img-3"></p>
                            <div class="card-body">
                                <h5 class="card-title">소현의 니노막시무스 호텔</h5>
                                <p class="card-text">아샷추 Let's get it!</p>
                        </a>
                    </div>
                    <div class="card-footer">
                        <small class="text-muted">Last updated 3 mins ago</small>
                    </div>
                </div>
            </div>
        </div>
        <br>
        <!-- ------------------------------------------------------------------------------------------------------ -->
        <div class="row row-cols-1 row-cols-md-3 g-4">
            <div class="col">
                <div class="card h-100">
                    <a href="">
                        <p class=card-img id="card-img-4"></p>
                        <div class="card-body">
                            <h5 class="card-title">병주의 병주고 약주고</h5>
                            <p class="card-text">데이터베이스 세계로 들어호텔</p>
                    </a>
                </div>
                <div class="card-footer">
                    <small class="text-muted">Last updated 3 mins ago</small>
                </div>
            </div>
        </div>
        <div class="col">
            <div class="card h-100">
                <a href="">
                    <p class=card-img id="card-img-5"></p>
                    <div class="card-body">
                        <h5 class="card-title">김동현92호텔</h5>
                        <p class="card-text">킹받을 때 들릴수 있는 호텔입니다.</p>
                    </div>
                </a>
                <div class="card-footer">
                    <small class="text-muted">Last updated 3 mins ago</small>
                </div>
            </div>
        </div>
        <div class="col">
            <div class="card h-100">
                <a href="">
                    <p class=card-img id="card-img-6"></p>
                    <div class="card-body">
                        <h5 class="card-title">조성태 강사님 이용호텔</h5>
                        <p class="card-text">인생이 코딩할 수있는 호텔</p>
                </a>
            </div>
            <div class="card-footer">
                <small class="text-muted">Last updated 3 mins ago</small>
            </div>
        </div>
    </div>
    </div>
    </div>
    </div>
    <br><br><br>

    <!-- 추가 버튼 -->
    <div class="container">
        <div class="plus">
            <button type="button" class="btn btn-warning" style="border-radius:10px;"><i
                    class="fas fa-plus"></i></button>
        </div>
    </div>
    <br><br><br>
    <!-- FOOTER -->
    <div class="container-fluid footBack">
        <div class="container">
            <div id="footer">
                <div id="footerIn">
                    <div class="row">
                        <div class="col-3 footer-left">

                            <a href="index.html" class="fot_logo"></a>

                        </div>
                        <div class="col-3 footer-center">

                        </div>
                        <div class="col-6 footer-right">
                            <ul class="foot-ul-blog">
                                <li><a href="">블로그</a></li>
                                <li><a href="">이용약관</a></li>
                                <li><a href="">개인정보처리방침</a></li>
                                <li><a href="">운영 정책</a></li>
                                <li><a href="">고객 문의</a></li>
                            </ul>
                        </div>
                    </div>

                    <div class="row">

                        <div class="col-4">
                            <ul class="foot-sangho">
                                <li>상호명 : <a href="index.html" class="foot-sangho-a">PlaceHolder</a></li>
                                <li>공동대표 : 기억해조</li>
                                <li>사업자등록번호 : 230-00-12345</li>
                                <li>동신판매업신고번호 : 2021-서울종로-05000</li>
                            </ul>
                        </div>

                        <div class="col-5">
                            <ul class="foot-sangho">
                                <li>&nbsp;</li>
                                <li>대표전화 : 02-1234-5678</li>
                                <li>이메일 : PlaceHolder@LoremIpsum.com</li>
                                <li>영업소재지 : 서울특별시 중구 남대문로 120 대일빌딩 2F, 3F</li>
                            </ul>
                        </div>

                        <div class="col-3">
                            <ul class="foot-logos">

                                <li><a href="" class="nblog"></a></li>
                                <li><a href="" class="twitter"></a></li>
                                <li><a href="" class="instargram"></a></li>
                                <li><a href="" class="facebook"></a></li>

                            </ul>
                        </div>

                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-9">
                            <p class="foot_caution">PlaceHolder는 통신판매중개자이자 통신판매 당사자 입니다. 따라서 PlaceHolder는 공간 거래정보 및
                                거래내역을 소유할 수
                                있습니다.</p>
                        </div>
                        <div class="col-3">
                            <p class="foot_caution">Copyright PLACEHOLDER Corp. All Rights Reserved.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
</body>
</html>