package org.example.boardproject.controller.reply;

import org.example.boardproject.service.ReplyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/replyDelete")
public class ReplyDeleteController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int boardId = Integer.parseInt(req.getParameter("replyId"));

        ReplyService service = new ReplyService();
        int result = service.deleteReply(boardId);

        if (result > 0) {
            System.out.println("댓글 삭제 성공");
        } else {
            System.out.println("댓글 삭제 실패");
        }
    }
}
