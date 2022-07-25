<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- pom.xml에 있는 JSTL을 사용할수 있게 import 해주는 것.
기존에 jstl을 의존성 주입을 해주었기 때문에 이렇게 코드만 작성하면 바로 사용 가능 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 리스트</title>
<link rel="stylesheet" href="/resource/common.css" />
<script src="/resource/common.js" defer="defer"></script> 
</head>
<body>


  <hr />

  <!-- <div>${articles}</div>  -->
  <!-- jstl사용 하기 -->
  <c:forEach var="article" items="${articles}">

  </c:forEach>
  <!-- 
  forEach는 반복문으로 articles에 있는 것을 article에 담아 반복 한다
   -->


  <h1>게시물 리스트 페이지</h1>

  <header>
    <a href="/">로고</a>

    <!-- ul>li*3>a[href="#]  -->

    <ul>
      <li>
        <a href="/">홈</a>
      </li>
      <li>
        <a href="/usr/article/list">리스트</a>
      </li>
    </ul>

  </header>

  <hr />

  <table border="1">
    <thead>
      <tr>
        <th>번호</th>
        <th>작성 날짜</th>
        <th>수정 날짜</th>
        <th>작성자</th>
        <th>제목</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="article" items="${articles}">
        <tr>
          <td>${article.id }</td>
          <td>${article.regDate.substring(2, 16) }</td>
          <td>${article.updateDate.substring(2, 16) }</td>
          <td>${article.memberId}</td>
          <td>
            <a href="../article/detail?id=${article.id }"> ${article.title } </a>
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>



</body>
</html>