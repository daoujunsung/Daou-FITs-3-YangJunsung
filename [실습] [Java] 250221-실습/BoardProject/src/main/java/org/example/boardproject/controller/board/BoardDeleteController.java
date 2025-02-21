package org.example.boardproject.controller.board;

import org.example.boardproject.service.BoardService;
import org.example.boardproject.service.ReplyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/boardDelete")
public class BoardDeleteController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int boardId = Integer.parseInt(req.getParameter("boardId"));

        BoardService service = new BoardService();
        int result = service.deleteBoard(boardId);

        if (result > 0) {
            ReplyService rservice = new ReplyService();
            rservice.deleteBoardReply(boardId);
            resp.sendRedirect("/BoardProject_war_exploded/board");
        }
    }

}
