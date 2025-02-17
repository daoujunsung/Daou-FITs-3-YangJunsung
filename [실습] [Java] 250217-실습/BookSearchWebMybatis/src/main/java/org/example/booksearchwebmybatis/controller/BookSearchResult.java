package org.example.booksearchwebmybatis.controller;

import org.example.booksearchwebmybatis.service.BookService;
import org.example.booksearchwebmybatis.vo.BookVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/bookSearch")
public class BookSearchResult extends HttpServlet {
//    @Override
//    public void init() throws ServletException {
//        super.init();
//    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("doget");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");

        String title = "";
        int price = 0;

        if (req.getParameter("title") != null) {
            title = req.getParameter("title");
        }
        if (req.getParameter("price") != null) {
            price = Integer.parseInt(req.getParameter("price"));
        }

        BookService service = new BookService();
        List<BookVO> list = service.searchBooks(title, price);

        resp.setContentType("text/html; charset=utf-8");

        PrintWriter out = resp.getWriter();

        out.println("<html>");
        out.println("<head></head>");
        out.println("<body><h1>검색결과입니다.</h1>");
        out.println("<h2>검색키워드: " + title + "</h2>");
        out.println("<h2>검색가격: " + price + "</h2>");
        out.println("<ul>");

        for(BookVO b: list) {
            out.println("<a href = 'http://localhost:8080/BookSearchWebMybatis_war_exploded/bookDetail?isbn=" + b.getBisbn() + "'><li>" + b.getBtitle() + "," + b.getBprice() + "</li></a>");
        }

        out.println("</ul></body>");
        out.println("</html>");
        out.flush();
        out.close();
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}