<%@ page import="org.example.boardproject.vo.MemberVO" %>
<%@ page import="org.example.boardproject.vo.BoardVO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    MemberVO member = (MemberVO) request.getAttribute("user");
%>
<head>
    <title>게시판</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f5f7fa;
            color: #333;
            display: flex;
            justify-content: center;
            padding: 30px;
        }
        .board-container {
            width: 80%;
            max-width: 1000px;
            background: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            color: #333;
        }
        a.write-btn {
            display: inline-block;
            background: #4CAF50;
            color: #fff;
            padding: 10px 15px;
            text-decoration: none;
            border-radius: 5px;
            font-weight: bold;
            transition: background 0.3s;
            float: right;
            margin-left: 10px;
        }
        a.write-btn:hover {
            background: #45a049;
        }
        .search-form {
            margin: 20px 0;
            text-align: center;
        }
        .search-form input[type="text"] {
            padding: 10px;
            width: 300px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .search-form input[type="submit"] {
            padding: 10px 15px;
            background: #007BFF;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-weight: bold;
            transition: background 0.3s;
        }
        .search-form input[type="submit"]:hover {
            background: #0056b3;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 10px;
            text-align: center;
        }
        th {
            background: #f2f2f2;
        }
        tr:hover {
            background: #f9f9f9;
        }
        td a {
            color: black;
            text-decoration: none;
        }
        td a:hover {
            text-decoration: underline;
        }
        th:nth-child(1), td:nth-child(1) { width: 7%; } /* 번호 */
        th:nth-child(2), td:nth-child(2) { width: 38%; } /* 제목 */
        th:nth-child(3), td:nth-child(3) { width: 10%; } /* 작성자 */
        th:nth-child(4), td:nth-child(4) { width: 15%; } /* 작성일 */
        th:nth-child(5), td:nth-child(5) { width: 10%; }  /* 댓글 수 */
        th:nth-child(6), td:nth-child(6) { width: 10%; }  /* 좋아요 수 */
        th:nth-child(7), td:nth-child(7) { width: 10%; }  /* 조회 수 */
    </style>
</head>
<body>
<div class="board-container">
    <h1>게시판</h1>
    <a class="write-btn" href="write.html">글 작성</a>
    <form class="search-form" action="board" method="POST">
        <input type="text" name="searchKeyword" placeholder="제목 또는 내용을 검색하세요">
        <input type="submit" value="검색">
    </form>
    <table>
        <thead>
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성/수정일</th>
            <th>댓글 수</th>
            <th>좋아요 수</th>
            <th>조회 수</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<BoardVO> boardList = (List<BoardVO>) request.getAttribute("boardList");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            if (boardList != null) {
                for (int i = 0; i < boardList.size(); i++) {
                    BoardVO board = boardList.get(i);
        %>
        <tr>
            <td><%= board.getBoardId() %></td>
            <td>
                <a href="boardDetail?boardId=<%= board.getBoardId() %>">
                    <%= board.getBoardTitle() %>
                </a>
            </td>
            <td><%= board.getBoardWriter() %></td>
            <td><%= (board.getModifyDate() != null) ? formatter.format(board.getModifyDate()) :formatter.format(board.getCreateDate()) %></td>
            <td><%= board.getReplyCount() %></td>
            <td><%= board.getLikeCount() %></td>
            <td><%= board.getViewCount() %></td>
        </tr>
        <%
                }
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>
