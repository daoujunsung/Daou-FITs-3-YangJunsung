package booksearch.service;

import booksearch.dao.BookDAO;
import booksearch.view.TableUpdateStage;
import booksearch.vo.BookVO;
import javafx.collections.ObservableList;

public interface BookSearchService {
    ObservableList<BookVO> searchBooks(String keyword);

    void openEditForm(BookVO selectedBook);

    int deleteBook(BookVO selectedBook);

    int updateBook(BookVO updatedbook);
}
