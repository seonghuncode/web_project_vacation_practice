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






