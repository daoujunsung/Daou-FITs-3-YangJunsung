package org.example.boardproject.controller.board;

import org.example.boardproject.service.BoardService;
import org.example.boardproject.service.LikeService;
import org.example.boardproject.service.ReplyService;
import org.example.boardproject.vo.BoardVO;
import org.example.boardproject.vo.LikeVO;
import org.example.boardproject.vo.MemberVO;
import org.example.boardproject.vo.ReplyVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/boardDetail")
public class BoardDetailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int boardId = Integer.parseInt(req.getParameter("boardId"));

        HttpSession session = req.getSession();
        MemberVO member = (MemberVO) session.getAttribute("user");

        BoardService service = new BoardService();
        BoardVO board = service.selectByBoardId(boardId);
        int result = service.updateBoardView(board);
        if (result < 1) {
            resp.sendRedirect("/BoardProject_war_exploded/board");
        }

        LikeService lservice = new LikeService();
        LikeVO like = new LikeVO(boardId, member.getId());
        boolean isLike = lservice.checkLike(like);

        ReplyService rservice = new ReplyService();
        List<ReplyVO> replyList = rservice.selectByBoardId(boardId);

        RequestDispatcher rd =
                req.getRequestDispatcher("boardDetail.jsp");
        req.setAttribute("user", member);
        req.setAttribute("board", board);
        req.setAttribute("isLike", isLike);
        req.setAttribute("replyList", replyList);
        rd.forward(req, resp);
    }
}
