package org.example.boardproject.controller.reply;

import org.example.boardproject.service.ReplyService;
import org.example.boardproject.vo.MemberVO;
import org.example.boardproject.vo.ReplyVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

@WebServlet("/replyRegister")
public class ReplyController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        HttpSession session = req.getSession();
        String userId = ((MemberVO)session.getAttribute("user")).getId();

        String replyContent = req.getParameter("replyContent");
        int boardId = Integer.parseInt(req.getParameter("boardId"));

        ReplyVO reply = new ReplyVO();
        reply.setReplyContent(replyContent);
        reply.setReplyWriter(userId);
        reply.setBoardId(boardId);

        ReplyService service = new ReplyService();
        int result = service.insertReply(reply);

        // 응답 형식 지정 (JSON)
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        // JSON 문자열을 직접 작성
        PrintWriter out = resp.getWriter();

        if (result > 0) {
            reply = service.selectByReplyId(service.getLastReplyId());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // JSON 문자열 작성 (이때 문자열 내에 이스케이프 처리 주의)
            String jsonResponse = String.format(
                    "{\"replyId\": %d, \"replyWriter\": \"%s\", \"replyContent\": \"%s\", \"createDate\": \"%s\"}",
                    reply.getReplyId(), reply.getReplyWriter(), reply.getReplyContent(), formatter.format(reply.getCreateDate())
            );
            out.print(jsonResponse);  // 응답 전송
        } else {
            out.print("{\"error\": \"댓글 등록 실패\"}");
        }

        out.flush();  // 버퍼 비우기
    }
}
