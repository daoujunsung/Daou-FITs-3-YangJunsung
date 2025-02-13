package bookcrud.service;

import bookcrud.dao.BookDAO;
import bookcrud.mybatis.MyBatisSessionFactory;
import bookcrud.view.TableUpdateStage;
import bookcrud.vo.BookVO;

import javafx.collections.ObservableList;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class BookCRUDServiceOracleImpl implements BookCRUDService {

    public BookCRUDServiceOracleImpl() {

    }

    // Create
    @Override
    public int createBook(BookVO newBook) {
        SqlSessionFactory factory =
                MyBatisSessionFactory.getSqlSessionFactory();
        SqlSession sqlSession = factory.openSession();

        BookDAO dao = new BookDAO(sqlSession);
        int result = dao.insertBook(newBook);
        if (result > 0) {
            sqlSession.commit();
        } else {
            sqlSession.rollback();
        }
        sqlSession.close();
        return result;
    }

    // Read
    @Override
    public ObservableList<BookVO> searchBooks(String keyword) {
        SqlSessionFactory factory =
                MyBatisSessionFactory.getSqlSessionFactory();
        SqlSession sqlSession = factory.openSession();

        BookDAO dao = new BookDAO(sqlSession);
        ObservableList<BookVO> olist = dao.selectByTitle(keyword);

        sqlSession.close();
        return olist;
    }

    // Update
    @Override
    public int updateBook(BookVO updatedbook) {
        if (updatedbook.getBtitle() == null || updatedbook.getBauthor() == null || updatedbook.getBprice() <= 0) {
            System.out.println("모든 필드를 올바르게 입력되지 않았습니다");
            return 0;
        }

        SqlSessionFactory factory =
                MyBatisSessionFactory.getSqlSessionFactory();
        SqlSession sqlSession = factory.openSession();

        BookDAO dao = new BookDAO(sqlSession);
        int result = dao.updateBook(updatedbook);
        if (result > 0) {
            sqlSession.commit();
        } else {
            sqlSession.rollback();
        }
        sqlSession.close();
        return result;
    }

    // Delete
    @Override
    public int deleteBook(BookVO selectedBook) {
        SqlSessionFactory factory =
                MyBatisSessionFactory.getSqlSessionFactory();
        SqlSession sqlSession = factory.openSession();

        BookDAO dao = new BookDAO(sqlSession);
        int result = dao.deleteBook(selectedBook);
        if (result > 0) {
            sqlSession.commit();
        } else {
            sqlSession.rollback();
        }
        sqlSession.close();
        return result;
    }

    public void openEditForm(BookVO selectedBook) {
        // TableView 에서 선택된 행 가져오기
        TableUpdateStage tableUpdateStage = new TableUpdateStage(selectedBook);
        tableUpdateStage.setTitle("도서 정보 수정");
        tableUpdateStage.show();
    }

}
