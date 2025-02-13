package bookcrud.dao;

import bookcrud.vo.BookVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

// connection 을 전달하지 않고 SqlSessionFactory 를 전달
public class BookDAO {
    private SqlSession sqlSession;

    public BookDAO(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    // Read
    public ObservableList<BookVO> selectByTitle(String btitle) {
        ObservableList<BookVO> olist = null;

        try {
            List<BookVO> list = sqlSession.selectList("bookcrud.MyBook.selectByTitle", btitle);
            olist = FXCollections.observableArrayList(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return olist;
    }

    // Create
    public int insertBook(BookVO book) {
        int result = 0;

        try {
            result = sqlSession.insert("bookcrud.MyBook.insertBook", book);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    // Update
    public int updateBook(BookVO book) {
        int result = 0;

        try {
            result = sqlSession.insert("bookcrud.MyBook.updateBook", book);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    // Delete
    public int deleteBook(BookVO book) {
        int result = 0;

        try {
            result = sqlSession.insert("bookcrud.MyBook.deleteBook", book);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
