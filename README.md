# PlaceHolder 프로젝트
## _KH 3조 "기억해조"_

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

원활한 협업을 위해 만든 github 저장소입니다.
충돌을 방지하기 위해 지켜야 할 사항은 다음과 같습니다.
- 자신의 작업은 자신의 개인 브랜치에서만 작업
- 틈틈히 최신의 main브랜치 소스 pull하기
- push 전에는 main으로 push가 아닌 자신의 브랜치로 push 후, pull request요청

# 프로젝트 제목 : Placeholder

* 프로젝트 개요
1. 요약 : Placeholder는 사용자에게 고급 호텔 정보 조회 및 예약 기능을 제공하는 웹서비스다.
2. 핵심 서비스 : 위드 코로나, 2030 세대가 주도하는 트렌드를 고려해 간편하고(Comfortable), 사랑스러우면서(Adorable), 굉장한(Terrific) 호텔 예약 서비스를 제공한다.
3. 사용자 특성
 2030대의 연령대로 PC, 모바일 환경에 익숙하다.
실내 공간에서 모이는 것을 좋아하고 사람들과 어울리는 것을 좋아한다.
4. 구현 방법 : Java / Javascript에 기반을 두고 MVC2의 형태로 웹서비스를 구현한다.


# 주요 구현 기능 목록

- 회원 가입 및 정보 수정 : 서비스 웹페이지에 방문한 사용자가 회원가입을 하고 정보를 수정할 수 있다.
- 호텔 정보 제공 : 사용자에게 placeholder가 보유한 호텔 리스트를 보여주고 상세한 정보를 제공할 수 있다.
- 검색 : 사용자가 검색창을 활용하여 이름 / 위치를 기준으로 호텔 리스트를 조회할 수 있다.
- 예약 : 사용자가 각 호텔 상세 페이지에 접속해서 원하는 조건에 맞추어 호텔 객실을 예약할 수 있다.
- 빠른 예약 : 모든 페이지에 존재하는 사이드바를 활용해서 원하는 호텔 객실을 빠르게 예약할 수 있다.
- 찜 기능 : 사용자가 마음에 드는 호텔을 찜하고 목록을 따로 조회할 수 있다.
- 커뮤니티 게시판 : 사용자가 자유 주제(호텔 후기 포함)로 게시글을 작성, 수정, 삭제할 수 있다.
- 고객센터 게시판 : 고객센터에 글을 작성하여 관리자에게 호텔 등에 관해 질문할 수 있으며 글을 수정하고 삭제할 수 있다.
- 관리자 페이지 : 사용자 / 등록된 호텔 / 게시글의 기본 정보를 조회하고 필요한 경우 사이트에서 삭제할 수 있다.

## 설계의 주안점 ( 프로젝트를 제작하며 가장 신경 쓴 부분 )

1. 핵심 기능을 정확하게 구현한다
- 핵심 기능인 호텔 정보제공 / 예약 / 회원가입 및 관리 기능이 정의서에 맞게 정확하게 동작하도록 구현한다.

2. 서비스가 안정적으로 동작할 수 있게 구현한다
- 웹서비스를 제공할 때 해당 기능에서 에러가 발생하지 않도록 안정적으로 설계한다.
- 사용자가 서비스를 이용하면서 발생시킬 수 있는 논리적 예외 사항들을 고려하여 서버를 설계한다.

3. UI를 간편하고 직관적으로 구현한다
- 처음 접속하는 사용자도 바로 웹서비스에 적응할 수 있도록 직관적인 UI를 구현한다.
- 각각의 기능이 한 페이지 내에서 완벽히 실행하도록 하여 사용자가 서비스를 편리하게 이용하도록 한다.

## 사용기술 및 개발환경

HTML / CSS / JAVASCRIPT / JQUERY / BOOTSTRAP
JAVA jdk 11 / ojdbc8

## 개발환경
eclipse / Visual Studio Code / Oracle 11g / AWS, Apache Tomcat 8.5
 
## 협업툴
Notion / ERDCloud
<hr>

##  팀원별 이름 및 역할

○ 김동현(92) : 회원 / 디자인
- 회원 담당 : 회원 가입 / 정보 수정 기능 (Javascript/정규표현식) 
- 메인 페이지 : 호텔 정보 제공 / 추천상품 제공 (HTML/CSS/BOOTSTRAP)
- UI 디자인 : 담당 페이지 디자인 (HTML/CSS/BOOTSTRAP)
- 기획안 발표

○ 박소현 : 게시판 / 와이어프레임 / 발표
- 게시판 담당 : 호텔 리스트 게시판, 커뮤니티 게시판, 관리자 게시판의 CRUD, 페이징 (Java/ojdbc/Javascript/HTML/CSS/BOOTSTRAP)
- UI 디자인 : 담당 페이지 디자인 (HTML/CSS/BOOTSTRAP)
- 검색 기능 : 이름 / 위치 기준 호텔 검색 기능 (Java)
- 와이어프레임 설계
- 프로젝트 발표 및 웹서비스 시연

○ 유병주 : Database / ERD 설계
- ERD 설계 : 프로젝트 전 데이터베이스 설계(ERDcloud)
- DB 생성 및 관리 : ERD에 맞는 테이블 설계 및 데이터베이스 관리(sql developer/ojdbc/Java)
- 게시판 : 고객센터 게시판 CRUD(Java)

○ 천현우 : 예약 / API / 기록 및 팀 운영
- 예약 : 호텔 객실 예약, 결제, 수정, 취소 기능 (Java/ojdbc/Javascript)
- 마이페이지 : 개인정보, 예약, 게시글 등 정보 조회 및 수정 (Java/ojdbc/Javascript/HTML/CSS/BOOTSTRAP)
- API  : 데이터 크롤링 및 장소 정보 제공(Kakao Local API, Map API)
- 기록관리 : 회의록 및 업무 기록 (Notion)
- 팀 운영

○ 홍진규 : Frontend / API / AWS
- 예약 : 호텔 객실 예약, 결제, 수정, 취소 기능 (Javascript)
- 검색 : 이름 / 위치 기준 호텔 검색 기능 (Javascript)
- 상품 리스트 및 상세 페이지 : 호텔 리스트 / 상세 정보 제공 페이지 (Javascript/HTML/CSS/BOOTSTRAP)
- 찜목록 : 회원이 찜한 호텔 조회 및 수정 (Javascript/Java/ojdbc)
- UI 디자인 : 담당 페이지 디자인 (HTML/CSS/BOOTSTRAP)
- AWS : Amazon Web Service 객체 생성 및 운영 (AWS)
- API : 호텔 장소 정보 제공 (Kakao Map API)