## 사용 버전
+ Spring Framework 5.3.2
+ Spring Security 5.4.4
+ MySQL 8.0.22
+ MyBatis 3.5.6
+ Lombok 1.18.16
+ Log4j2 2.14.1
+ Commons Email 1.5
+ AWS EC2

## 구현된 기능

### 회원 (Spring MVC/Security, Rest API, InterCeptor, Ajax, Modal 적용)
+ 가입 / 탈퇴 / 수정 / 로그인 / 로그아웃
+ 가입 시 - 이메일 중복 체크, 비밀번호 유효성 체크, 비밀번호 암호화
+ 로그인 / 회원가입 시 미기입 정보 체크
+ 미로그인 시 와인 게시판 접근 차단
+ 임시 비밀번호 이메일 전송 (String --> SringBuilder.setLength(initial capacity 100) 수행 시간 단축)
  + 전문: https://yeon-kr.tistory.com/157?category=857330

### 크롤러 (Jsoup, Selenium, Multithreading)
+ 롯데마트, 이마트의 와인들 크롤링 (이미 존재하는 경우 가격만 업데이트)
+ 와인 데이터 저장 배열 (LinkedList 대신 ArrayList inital capacity 변경으로 수행 시간 단축)
  +  전문: yeon-kr.tistory.com/152?category=857330
+ 마트 크롤러 각각 작성 후 쓰레드로 등록해 멀티스레드 실행 (크롤링 속도 향상 위함)

### 본문 내용 (Spring MVC, Paging, Ajax)
+ 크롤링한 와인들을 게시판 형식으로 나열 (Paging)
+ 와인 이름 클릭 시 해당 와인을 판매하는 사이트로 이동
+ 검색창 - 검색어 자동완성 및 검색 기능 (Ajax Autocomplete)
+ 와인 가격 순서대로 정렬 가능

### 관리자
+ 관리자 계정 로그인 시 관리자 버튼 나타냄
+ 와인 검색 기록 통계 확인

### 디자인 (JS, JQuery, BootStrap)
+ 다크 모드 버튼 - 회원의 컴퓨터 설정 값이 다크모드일 경우 자동 ON
+ 전체 디자인 - BootStrap
+ 모바일, PC 화면 사이즈 변화 적용

### 호스팅 (AWS)
+ AWS EC2 
