#DB생성
DROP DATABASE IF EXISTS web_practice_demo;
CREATE DATABASE web_practice_demo;
USE web_practice_demo;


#테이블 종류 상관 없이 무조건 만들어야 한다 ==> id, regDate, upgradeDate(r관리차원)
#게시물 생성(id(번호), regDate(등록날짜), updateDate(수정날짜))
CREATE TABLE article(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    title CHAR(100) NOT NULL,
    `body` TEXT NOT NULL  
);
#char(용랼 절약이 없다, 검색이 빠르다) , varchar(용량 절약이 있다) 



#게시물, 테스트 데이터 생성
INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목 1',
`body` = '내용 1';


INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목 2',
`body` = '내용 2';


INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목 3',
`body` = '내용 3';


SELECT * FROM article;

#mysql의 기능 : 가장 마지막에 insert한 번호가 나온다
SELECT LAST_INSERT_ID();


#회원 테아블 생성 
CREATE TABLE `member`(
id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
regDate DATETIME NOT NULL,
updateDate DATETIME NOT NULL,
loginId CHAR(20) NOT NULL, #20자면 충분하다
loginPw CHAR(60) NOT NULL, #비밀번호를 60자러 하는 이유는 나중에 암호회를 해서 저장 해야 하기 때문이다.(우리나라 법으로 비밀번호는 원문으로 저장하면 안되게 되어 있다.)
authLevel SMALLINT(2) UNSIGNED DEFAULT 3 COMMENT '권한레벨(3=일반, 7=관리자)',  #default값을 지정해주고, 잘모르는 것은 comment를 남길수 있다
`name` CHAR(20) NOT NULL,
nickname CHAR(20) NOT NULL,
cellphoneNo CHAR(20) NOT NULL,
email CHAR(50) NOT NULL,
delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '탈퇴여부(0=탈퇴전,1=탈퇴)', #tinyint를 사용하는 이유는 실제 회원가입시 insert하고 탈퇴를 할때 회원정보를 지우는 것이 아니라
delDate DATETIME COMMENT '탈퇴날짜'  #탈퇴들 안했으면 데이터기 없으므로 not null이 없다     #delStatus같은 상태값을 만들어 1에서 0으로 바꾸는 형식으로 숨기는 방식으로 진행이 된다
);

#tinyint(1)을 사용하면 자바에서는 true/fales로 인식을 하기 때문에 작은 것은 smallint를 사용한다

#현재 법상 탈퇴를 하면 번화나 개인정보는 지워야 하나 모든 정보를 지우게 되면 해당 회원의 게시물등 남아 있는 것들을 볼 수가 없다 완전히 사라지기 때문
#delStatus는 삭제 상태로 tinyint(1) unsigned not null을 하면 0,1만 넣을 수 있다는 의미(만약 0을 넣으면 mybatis에서 false로 인식을 해준다)



#회원, 테스트 데이터 생성(관리자 회원)
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'admin',
loginPw = 'admin',
authLevel = 7,
`name` = '관리자',
nickname = '관리자',
cellphoneNo = '01011111111',
email = 'test1@naver.com';



#회원, 테스트 데이터 생성(일반 회원)
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'user1',
loginPw = 'user1',
authLevel = 3, #default 가 3이기 때문에 안넣어도 된다
`name` = '사용자1',
nickname = '사용자1',
cellphoneNo = '01022222222',
email = 'test2@naver.com';



INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'user2',
loginPw = 'user2',
`name` = '사용자2',
nickname = '사용자2',
cellphoneNo = '01033333333',
email = 'test3@naver.com';



SELECT * FROM `member`










