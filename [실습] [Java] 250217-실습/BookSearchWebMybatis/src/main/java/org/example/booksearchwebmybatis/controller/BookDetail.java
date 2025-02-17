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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(value = "/bookDetail")
public class BookDetail extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String isbn = req.getParameter("isbn");

        BookService service = new BookService();
        BookVO book = service.searchBookDetail(isbn);

        resp.setContentType("text/html; charset=utf-8");

        PrintWriter out = resp.getWriter();

        out.println("<html>");
        out.println("<head></head>");
        out.println("<body><h1>" + book.getBtitle() + "</h1>");
        out.println("<ul>");
        out.println("<li>출판일자: " + book.getBdate() + "</li>");
        out.println("<li>페이지 수: " + book.getBpage() + "</li>");
        out.println("<li>가격: " + book.getBprice() + "</li>");
        out.println("<li>저자: " + book.getBauthor() + "</li>");
        out.println("<li>역자: " + book.getBtranslator() + "</li>");
        out.println("<li>출판사: " + book.getBpublisher() + "</li>");
        out.println("</ul></body>");
        out.println("</html>");
        out.flush();
        out.close();
    }
}
