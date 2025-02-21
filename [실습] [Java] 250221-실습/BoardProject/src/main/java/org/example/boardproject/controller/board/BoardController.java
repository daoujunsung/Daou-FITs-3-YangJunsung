package org.example.boardproject.controller.board;

import org.example.boardproject.service.BoardService;
import org.example.boardproject.vo.BoardVO;
import org.example.boardproject.vo.MemberVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/board")
public class BoardController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        MemberVO member = (MemberVO) session.getAttribute("user");

        BoardService service = new BoardService();
        List<BoardVO> boardList = service.selectAllBoards();

        RequestDispatcher rd =
                req.getRequestDispatcher("board.jsp");
        req.setAttribute("user", member);
        req.setAttribute("boardList", boardList);
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        HttpSession session = req.getSession();
        MemberVO member = (MemberVO) session.getAttribute("user");
        String keyword = req.getParameter("searchKeyword");

        BoardService service = new BoardService();
        List<BoardVO> boardList = service.selectByTitleOrContent(keyword);

        RequestDispatcher rd =
                req.getRequestDispatcher("board.jsp");
        req.setAttribute("user", member);
        req.setAttribute("boardList", boardList);
        rd.forward(req, resp);
    }
}
