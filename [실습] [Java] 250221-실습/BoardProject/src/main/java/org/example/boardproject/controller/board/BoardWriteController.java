package org.example.boardproject.controller.board;

import org.example.boardproject.service.BoardService;
import org.example.boardproject.vo.BoardVO;
import org.example.boardproject.vo.MemberVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/write")
public class BoardWriteController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        HttpSession session = req.getSession();
        String userId = ((MemberVO)session.getAttribute("user")).getId();
        String title = req.getParameter("title");
        String content = req.getParameter("content");

        BoardVO board = new BoardVO();
        board.setBoardTitle(title);
        board.setBoardContent(content);
        board.setBoardWriter(userId);

        BoardService service = new BoardService();
        int result = service.insertBoard(board);

        if (result > 0) {
            resp.sendRedirect("/BoardProject_war_exploded/board");
        }
    }
}
