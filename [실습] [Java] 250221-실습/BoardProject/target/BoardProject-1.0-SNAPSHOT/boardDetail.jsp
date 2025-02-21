<%@ page import="org.example.boardproject.vo.MemberVO" %>
<%@ page import="org.example.boardproject.vo.BoardVO" %>
<%@ page import="org.example.boardproject.vo.ReplyVO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    MemberVO member = (MemberVO) request.getAttribute("user");
    BoardVO board = (BoardVO) request.getAttribute("board");
    boolean isLike = (boolean) request.getAttribute("isLike");
    List<ReplyVO> replyList = (List<ReplyVO>) request.getAttribute("replyList");
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
%>
<head>
    <title>게시글</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <script src="js/boardDetail.js"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f5f7fa;
            display: flex;
            justify-content: center;
            padding: 30px;
            flex-direction: column; /* 세로로 레이아웃 설정 */
            align-items: center; /* 가운데 정렬 */
            min-height: 100vh; /* 최소 높이를 100vh로 설정하여 화면에 꽉 차도록 */
        }

        .post-container {
            width: 70%;
            max-width: 800px;
            background: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            height: auto; /* 자식 콘텐츠에 맞춰서 높이 조정 */
        }
        h1 {
            font-size: 28px;
            color: #333;
        }
        .post-info {
            color: #888;
            font-size: 14px;
            margin-bottom: 20px;
        }
        .post-content {
            line-height: 1.6;
            color: #444;
            margin-bottom: 30px;
        }
        .post-content p {
            margin: 10px 0;
        }
        .action-buttons {
            text-align: right;
        }
        .action-buttons a {
            display: inline-block;
            background: #e74c3c;
            color: #fff;
            padding: 10px 15px;
            text-decoration: none;
            border-radius: 5px;
            font-weight: bold;
            transition: background 0.3s;
        }
        .action-buttons a:hover {
            background: #c0392b;
        }
        .likeBtn {
            border: none;
            background: transparent;
            font-size: 24px;
            color: #e74c3c;
            cursor: pointer;
            transition: transform 0.2s, color 0.3s;
            margin-left: 10px;
            padding: 5px;
            border-radius: 5px;
            width: 40px; /* 고정된 너비 */
            height: 40px; /* 고정된 높이 */
            text-align: center;
            line-height: 30px; /* 버튼 내 텍스트 수직 정렬 */
        }

        .likeBtn:hover {
            transform: scale(1.2);
            color: #c0392b;
        }

        .likeBtn:active {
            transform: scale(1);
        }

        /* 댓글 리스트 스타일 */
        .reply-section {
            margin-top: 30px;
        }

        .reply-form {
            display: flex;
            gap: 10px;
            margin-bottom: 5px;
            padding-bottom: 15px;
            border-bottom : 1px solid #ddd;
        }

        .reply-form input[type="text"] {
            width: 80%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .reply-form button {
            background: #3498db;
            color: #fff;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            font-weight: bold;
            cursor: pointer;
            transition: background 0.3s;
        }

        .reply-form button:hover {
            background: #2980b9;
        }

        /* 댓글 리스트 스타일 */
        .reply-list {
            list-style: none;
            padding: 0;
        }

        .reply-item {
            padding: 15px;
            border-bottom : 1px solid #ddd;
            margin-bottom: 5px;
            display: flex;
            flex-direction: column; /* 두 줄로 배치되도록 */
        }

        .reply-header {
            display: flex;
            justify-content: space-between; /* 작성자와 작성일을 양 끝에 배치 */
            align-items: center;
        }

        .reply-content {
            margin-top: 10px; /* 댓글 내용과 구분 */
            color: #555;
        }

        .reply-delete {
            background: #e74c3c;
            color: #fff;
            padding: 5px 10px;
            border: none;
            border-radius: 5px;
            font-size: 14px;
            cursor: pointer;
            transition: background 0.3s, transform 0.2s;
            margin-left: auto; /* 오른쪽 끝에 배치 */
            margin-top: 10px; /* 내용과 삭제 버튼 사이의 간격 */
        }

        .reply-delete:hover {
            background: #c0392b;
            transform: scale(1.1);
        }

        .reply-delete:active {
            transform: scale(1);
        }
    </style>
</head>
<body>
<div class="post-container">
    <input type="hidden" id="hiddenBoardId" value=<%= board.getBoardId() %>>
    <h1><%= board.getBoardTitle() %></h1>
    <div class="post-info">
        작성자: <%= board.getBoardWriter() %> | 작성일: <%= (board.getModifyDate() != null) ? formatter.format(board.getModifyDate()) + " 수정됨" :formatter.format(board.getCreateDate()) %>
    </div>
    <hr/>
    <div class="post-content">
        <%= board.getBoardContent() %>
    </div>
    <div class="action-buttons">
        <a href="board">목록으로</a>
        <% if (board.getBoardWriter().equals(member.getId())) { %>
        <a href="boardEdit?boardId=<%= board.getBoardId() %>">수정</a>
        <a href="boardDelete?boardId=<%= board.getBoardId() %>">삭제</a>
        <% } else if (isLike){%>
        <button class="likeBtn">♥</button>
        <% } else { %>
        <button class="likeBtn">♡</button>
        <% } %>
    </div>
    <hr/>
    <div class="reply-section">
        <form class="reply-form">
            <input type="text" id="newReply" placeholder="댓글을 작성하세요">
            <button type="button" id="reply-register">등록</button>
        </form>
        <ul class="reply-list">
            <% if (replyList != null) {
                for (ReplyVO reply : replyList) {
            %>
            <li class="reply-item">
                <div class="reply-header">
                    <div class="reply-writer"><%= reply.getReplyWriter() %></div>
                    <div class="reply-date"><%= formatter.format(reply.getCreateDate()) %></div>
                </div>
                <div class="reply-content"><%= reply.getReplyContent() %></div>
                <% if (reply.getReplyWriter().equals(member.getId())) { %>
                <button class="reply-delete" data-reply-id="<%= reply.getReplyId() %>">삭제</button>
                <% } %>
            </li>
            <%
                    }
                } %>
        </ul>
    </div>

</div>
</body>
</html>
