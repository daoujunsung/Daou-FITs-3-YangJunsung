package org.example.boardproject.controller.like;

import org.example.boardproject.service.LikeService;
import org.example.boardproject.vo.LikeVO;
import org.example.boardproject.vo.MemberVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/like")
public class LikeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        MemberVO member = (MemberVO) session.getAttribute("user");
        int boardId = Integer.parseInt(req.getParameter("boardId"));

        LikeVO like = new LikeVO(boardId, member.getId());
        LikeService service = new LikeService();

        int result = 0;

        if (service.checkLike(like)) {
            result = service.deleteLike(like);
        } else {
            result = service.insertLike(like);
        }

        if (result > 0) {
            System.out.println("수정 성공");
        } else {
            System.out.println("수정 실패");
        }
    }
}
