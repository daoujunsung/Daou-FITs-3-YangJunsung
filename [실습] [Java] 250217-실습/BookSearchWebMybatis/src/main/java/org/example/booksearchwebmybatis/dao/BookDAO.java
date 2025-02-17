package org.example.booksearchwebmybatis.dao;

import org.apache.ibatis.session.SqlSession;
import org.example.booksearchwebmybatis.vo.BookVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookDAO {
    private SqlSession sqlSession;

    public BookDAO(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<BookVO> selectByTitleAndPrice(String btitle, int bprice) {
        List<BookVO> list = null;
        Map<String, Object> params = new HashMap<>();
        params.put("btitle", btitle);
        params.put("bprice", bprice);
        try {
            list = sqlSession.selectList("booksearch.MyBook.selectByTitleAndPrice", params);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public BookVO selectByISBN(String bisbn) {
        BookVO result = null;

        try {
            result = sqlSession.selectOne("booksearch.MyBook.selectByISBN", bisbn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
