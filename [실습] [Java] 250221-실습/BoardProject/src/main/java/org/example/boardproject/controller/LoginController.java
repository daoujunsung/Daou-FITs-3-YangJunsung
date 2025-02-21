package org.example.boardproject.controller;

import org.example.boardproject.service.MemberService;
import org.example.boardproject.vo.MemberVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String id = req.getParameter("id");
        String password = req.getParameter("password");

        MemberService service = new MemberService();

        MemberVO member = service.login(id, password);

        if (member != null) {
            HttpSession session = req.getSession();

            session.setAttribute("user", member);

            resp.sendRedirect("/BoardProject_war_exploded/first");
        } else {
            // 로그인 실패
            resp.sendRedirect("loginError.html");
        }

    }
}
