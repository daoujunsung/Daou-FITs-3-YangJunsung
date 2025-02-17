<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<h1>
  도서검색 프로그램
</h1>
<form action = "http://localhost:8080/BookSearchWebMybatis_war_exploded/bookSearch" method="POST">
  도서명 키워드 :
  <input name = "title" type="text" /> <br/><br/>
  <input name = "price" type="radio" value="10000">
  10,000원 미만
  </input>
  <input name = "price" type="radio" value="20000">
  20,000원 미만
  </input>
  <input name = "price" type="radio" value="30000">
  30,000원 미만
  </input>
  <input name = "price" type="radio" value="40000">
  40,000원 미만
  </input>
  <br/>
  <br/>
  <input type="submit" value="도서 검색!"/>
</form>
</body>
</html>