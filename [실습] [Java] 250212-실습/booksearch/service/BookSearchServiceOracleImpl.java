package booksearch.service;

import booksearch.dao.BookDAO;
import booksearch.dao.ConnectionMaker;
import booksearch.dao.KConnectionMaker;
import booksearch.view.TableUpdateStage;
import booksearch.vo.BookVO;
import javafx.collections.ObservableList;

public class BookSearchServiceOracleImpl implements BookSearchService{
    ConnectionMaker connectionMaker = new KConnectionMaker();

    public BookSearchServiceOracleImpl() {

    }

    public ObservableList<BookVO> searchBooks(String keyword) {
        BookDAO bookDAO = new BookDAO(connectionMaker);
        return bookDAO.select(keyword);
    }

    public void openEditForm(BookVO selectedBook) {
        // TableView 에서 선택된 행 가져오기
        TableUpdateStage tableUpdateStage = new TableUpdateStage(selectedBook);
        tableUpdateStage.setTitle("도서 정보 수정");
        tableUpdateStage.show();
    }

    public int deleteBook(BookVO selectedBook) {
       BookDAO bookDAO = new BookDAO(connectionMaker);
       return bookDAO.delete(selectedBook);
    }

    public int updateBook(BookVO updatedbook) {
        BookDAO bookDAO = new BookDAO(connectionMaker);

        if (updatedbook.getBtitle() == null || updatedbook.getBauthor() == null || updatedbook.getBprice() <= 0) {
            System.out.println("모든 필드를 올바르게 입력되지 않았습니다");
            return 0;
        }
        return bookDAO.update(updatedbook);
    }

}
