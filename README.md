# LectureEvaluation
강의 평가를 위한 사이트 개발

## 개발 언어 및 환경
jsp, java, mysql, bootstrap, eclipse

## Model

### likey
- 좋아요 기능 모델링
### evaluation
- 평가 모델링
- 글의 삭제 및 등록 처리
### user
- 사용자 정보 모델링
- 로그인, 회원가입, 이메일 관련 처리
### util
- DB 연동
- Gamil id, pw 값 처리
- sha256을 이용한 해싱처리

## page
### index
- index page
### userLogin
- login page
### userJoin
- Add user page
### userLogout
- user Logout execute
### userRegisterAction
- user Register Action
### userLoginAction
- user login Action
### deleteAction
- delete Evaluation Post
### emailSendAction
- user emailcheck email send page
### emailCheckAction
- regiseted eamil check page
### emailSendConfirm
- unregisted eamil page
### evaluationRegistAtion
- evaluation post add
### likeAction
- add likeCount execute
### reportAction
- report email send execute


## 라이브러리
- javax.mail.jar
https://javaee.github.io/javamail/#Download_JavaMail_Release
- mysql-connector-java
https://dev.mysql.com/downloads/connector/j/5.1.html (최신버전으로)
- activation
https://www.oracle.com/technetwork/java/javase/downloads/index-135046.html
