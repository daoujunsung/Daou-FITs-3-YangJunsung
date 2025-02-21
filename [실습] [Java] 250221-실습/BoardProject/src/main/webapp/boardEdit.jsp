<%@ page import="org.example.boardproject.vo.BoardVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");

    BoardVO board = (BoardVO)request.getAttribute("board");
%>
<head>
    <meta charset="UTF-8">
    <title>글 수정</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #ffffff;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
            color: #333;
        }
        .write-container {
            background: #f5f5f5;
            border-radius: 10px;
            padding: 30px;
            width: 500px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            margin-bottom: 20px;
            font-size: 28px;
            color: #333;
        }
        input[type="text"],
        textarea {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
            resize: none;
            font-size: 16px;
            color: #333;
        }
        textarea {
            height: 200px;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background: #007BFF;
            border: none;
            border-radius: 5px;
            color: #fff;
            font-weight: bold;
            cursor: pointer;
            transition: background 0.3s ease;
            font-size: 16px;
        }
        input[type="submit"]:hover {
            background: #0056b3;
        }
        label {
            font-size: 16px;
            color: #333;
        }
    </style>
</head>
<body>
<div class="write-container">
    <h1>글 수정</h1>
    <form action="boardEdit" method="POST">
        <input type="hidden" name="boardId" value=<%= board.getBoardId()%> />
        <label for="title">제목</label>
        <input type="text" name="title" id="title" placeholder="제목을 입력하세요" value="<%=board.getBoardTitle()%>" required>
        <label for="content">내용</label>
        <textarea name="content" id="content" placeholder="내용을 입력하세요" required><%=board.getBoardContent()%></textarea>
        <input type="submit" value="수정">
    </form>
</div>
</body>
</html>

