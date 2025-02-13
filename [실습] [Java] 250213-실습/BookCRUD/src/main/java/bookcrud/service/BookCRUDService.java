package bookcrud.service;

import bookcrud.vo.BookVO;
import javafx.collections.ObservableList;

public interface BookCRUDService {
    int createBook(BookVO newBook);

    ObservableList<BookVO> searchBooks(String keyword);

    void openEditForm(BookVO selectedBook);

    int deleteBook(BookVO selectedBook);

    int updateBook(BookVO updatedbook);
}
