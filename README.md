# SpringBoot-Study Project 
  - 기존에 3.16 ~ 3.18에 만들었던 프로젝트를 Refactoring 및 기능 추가
    1. 사용자 추가 
    2. Spring Security와 KaKao OAuth를 이용하여 OAuth 로그인 기능 추가 (Spring Security를 추가 학습 후 로그인, 로그아웃 기능, JWT을 통해 구현예정 )
    3. Querydsl을 사용하여 동적 쿼리 생성 ( 검색 기능 )
    4. 기존 Article 기능을 Service와 Controller의 역할 완전 분리하여 구현
    5. 생성자를 Builder 패턴으로 변경(가독성 증가, 데이터의 순서에 상관없이 객체 생성)
    6. xToOne(OneToOne, ManyToOne) 관계 fetch.LAZY 처리 (쿼리시 불필요한 데이터는 사용될 때 가져옴)
  
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
![image](https://user-images.githubusercontent.com/61530368/164738529-6aae5e3d-5a87-49bc-b794-0789ebb4f340.png)


  
  - 사용자(User): id, email, nickname, password, thumbnail, 그리고 Article 리스트를 가진다. 한 사용자는 여러개의 게시글을 작성할 수 있으므로 Article과 일대다 관계다
  - 게시물(Article): 한 게시물 안에 여러개의 댓글이 달리 수 있기 때문에 Comment와 일대다 관계다
  - 댓글(Comment): 댓글을 작성한 사람을 알아야하기 때문에 User의 id와 nickname을 가지고 있다
  - TimeStamp: 객체가 만들어진 시간 또는 변경되는 시간의 값 타임(임베디드 타입)이다. 모든 객체들은 TimeStamp를 가지고 있다
  
# ERD 분석
![image](https://user-images.githubusercontent.com/61530368/164738301-8604ef50-3a1b-4589-9821-0d3c14f715c4.png)



  - user: user 테이블은 PK가 user_id이다
  - article: article 테이블은 PK가 article_id이고 FK는 user_id이다. user테이블과 1:N의 관계를 맺고 있다
  - comment: comment 테이블은 PK가 comment_id이고 FK는 article_id이다. article테이블과 1:N의 관계를 맺고 있다



# UI
  - 메인
![image](https://user-images.githubusercontent.com/61530368/164709566-f0737898-6b9a-40c2-9c33-8369b3bfa83c.png)

  - 게시글
![image](https://user-images.githubusercontent.com/61530368/164709559-33dba311-191c-4c97-b954-2012b7a03ace.png)

  - 댓글 수정
![image](https://user-images.githubusercontent.com/61530368/164709551-060a495b-88dd-4433-9ccc-3dd404ffdbd4.png)

  - 검색 필터링 (title에 "글"이라는 문자가 들어가 있는 것만 가져옴)
![image](https://user-images.githubusercontent.com/61530368/164709542-f8ea1eea-84cc-4b5a-8de9-91c885ad1b54.png)


# 새로 배운 것 
  
  ![image](https://user-images.githubusercontent.com/61530368/164695794-b7947cbe-fa68-468d-b43d-f7cba2f4f7ff.png)

  - OAuth  
    1. 위 그림과 같이 Frontend(사용자)가 KaKao Server로 Redirect와인증 코드를 요청
    2. 인증 코드를 전달하고 Backend(서버)로 Redirect를 통해 인증 코드를 전송한다 
    3. 이후 인증 코드와 Redirect주소를 KaKao에 전송하고 Token발급 
    4. 그림 5번과 6번 사이에 서버에서 토큰을 이용하여 KaKao 서버에 사용자 정보 요청후 회원가입 처리
    5. 사용자를 로그인 처리 후 홈 화면으로 Redirect






