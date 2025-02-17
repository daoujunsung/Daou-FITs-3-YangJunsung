package org.example.booksearchwebmybatis.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.booksearchwebmybatis.dao.BookDAO;
import org.example.booksearchwebmybatis.mybatis.MyBatisSessionFactory;
import org.example.booksearchwebmybatis.vo.BookVO;

import java.util.List;

public class BookService {

    public BookService() {

    }
    public List<BookVO> searchBooks(String keyword, int price) {
        SqlSessionFactory factory =
                MyBatisSessionFactory.getSqlSessionFactory();
        SqlSession sqlSession = factory.openSession();

        BookDAO dao = new BookDAO(sqlSession);
        List<BookVO> list = dao.selectByTitleAndPrice(keyword, price);

        sqlSession.close();
        return list;
    }

    public BookVO searchBookDetail(String isbn) {
        SqlSessionFactory factory =
                MyBatisSessionFactory.getSqlSessionFactory();
        SqlSession sqlSession = factory.openSession();

        BookDAO dao = new BookDAO(sqlSession);
        BookVO book = dao.selectByISBN(isbn);

        sqlSession.close();
        return book;
    }
}
