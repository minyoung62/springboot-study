# SpringBoot-Study Project 
  - 인프런에서 QueryDsl수업과 JPA를 듣고 기존에 4.13~4.123에 만들었던 프로젝트를 Refactoring 
  1. 사용자 추가 
  2. Spring Security와 KaKao OAuth를 이용하여 로그인 기능 추가 (추후 Security를 추가 학습 후 Roles와 로그아웃 기능 그리고 JWT 구현예정 )
  3. Querydsl을 사용하여 동적 쿼리 생성 ( 검색 기능 )
  4. 기존 Article 기능을 Service와 Controller의 역할 완전 분리하여 구현
  5. 생성자를 Builder 패턴으로 변경(가독성 증가, 데이터의 순서에 상관없이 객체 생성) 
  
# Front End
  - Mustache, HTML, CSS, JS
 
# Back End
  - Spring Boot
  - Spring MVC
  - Spring Data Jpa
  - JPA
  - Querydsl
  - Spring Security
  - OAuth(KaKao)
  - Junit5
  - Gradle

# 기능 목록
  - 로그인 기능
    - KaKao OAuth 로그인

  - 게시글 기능
    - 게시글 쓰기
    - 게시글 읽기
    - 게시글 수정
    - 게시글 삭제

  - 댓글 기능
    - 댓글 쓰기
    - 댓글 읽기
    - 댓글 수정
    - 댓글 삭제

  - Search 기능
    - 검색어를 이용해 게시글 가져오기


# Entity 분석
![image](https://user-images.githubusercontent.com/61530368/164683558-69293bd4-3118-44ec-9356-bcc3e2ea6a7b.png)

  
  - 사용자(User): id, email, nickname, password, thumbnail
  
# ERD 분석
![image](https://user-images.githubusercontent.com/61530368/164689444-d0795c48-609f-412b-9da5-6a0b6d4f1e73.png)




# 새로 배운 것 
  
  ![image](https://user-images.githubusercontent.com/61530368/164695794-b7947cbe-fa68-468d-b43d-f7cba2f4f7ff.png)

  - OAuth  
    1. 위 그림과 같이 Frontend(사용자)가 KaKao Server로 Redirect와인증 코드를 요청
    2. 인증 코드를 전달하고 Backend(서버)로 Redirect를 통해 인증 코드를 전송한다 
    3. 이후 인증 코드와 Redirect주소를 KaKao에 전송하고 Token발급 
    4. 그림 5번과 6번 사이에 서버에서 토큰을 이용하여 KaKao에 사용자 정보 요청후 회원가입 처리
    5. 사용자를 로그인 처리 후 홈 화면으로 Redirect






