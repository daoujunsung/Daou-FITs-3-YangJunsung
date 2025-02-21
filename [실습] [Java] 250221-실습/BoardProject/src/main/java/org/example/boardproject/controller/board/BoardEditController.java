package org.example.boardproject.controller.board;

import org.example.boardproject.service.BoardService;
import org.example.boardproject.vo.BoardVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/boardEdit")
public class BoardEditController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int boardId = Integer.parseInt(req.getParameter("boardId"));
        HttpSession session = req.getSession();

        BoardService service = new BoardService();
        BoardVO board = service.selectByBoardIdForEdit(boardId);

        RequestDispatcher rd =
                req.getRequestDispatcher("boardEdit.jsp");
        req.setAttribute("board", board);
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        HttpSession session = req.getSession();
        int boardId = Integer.parseInt(req.getParameter("boardId"));
        String title = req.getParameter("title");
        String content = req.getParameter("content");

        BoardVO board = new BoardVO();
        board.setBoardId(boardId);
        board.setBoardTitle(title);
        board.setBoardContent(content);

        BoardService service = new BoardService();
        int result = service.updateBoard(board);

        if (result > 0) {
            resp.sendRedirect("/BoardProject_war_exploded/boardDetail?boardId=" + boardId);
        }
    }
}
