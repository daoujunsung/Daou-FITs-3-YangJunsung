<%@ page import="org.example.boardproject.vo.MemberVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
  request.setCharacterEncoding("UTF-8");
  response.setCharacterEncoding("UTF-8");
  MemberVO member = (MemberVO) request.getAttribute("user");
%>
<head>
  <title>환영합니다!</title>
  <style>
    body {
      font-family: 'Noto Sans', sans-serif;
      background-color: #e9f1f7;
      margin: 0;
      padding: 0;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      color: #333;
    }
    .container {
      text-align: center;
      background-color: #fff;
      padding: 40px 50px;
      border-radius: 8px;
      box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
      width: 100%;
      max-width: 500px;
    }
    h1 {
      font-size: 32px;
      color: #4CAF50;
      margin-bottom: 30px;
    }
    .btn {
      display: inline-block;
      padding: 12px 25px;
      font-size: 16px;
      text-decoration: none;
      margin: 10px;
      border-radius: 30px;
      color: #fff;
      background-color: #007bff;
      transition: background-color 0.3s ease;
    }
    .btn:hover {
      background-color: #0056b3;
    }
    .logout-btn {
      background-color: #f44336;
    }
    .logout-btn:hover {
      background-color: #d32f2f;
    }
  </style>
</head>
<body>
<div class="container">
  <h1><%= member.getName() %>님 환영합니다!</h1>
  <a class="btn logout-btn" href="logout">로그아웃</a>
  <a class="btn" href="board">게시판 이동</a>
</div>
</body>
</html>
