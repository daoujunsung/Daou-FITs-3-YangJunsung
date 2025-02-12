package booksearch.controller;

import booksearch.service.BookSearchService;
import booksearch.vo.BookVO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TableUpdateController implements Initializable{

    @FXML private TextField isbn;
    @FXML private TextField newTitle;
    @FXML private TextField newPrice;
    @FXML private TextField newAuthor;
    @FXML private Button updateBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateBtn.setOnAction(event -> {
            BookVO updatedBook = new BookVO(
                    isbn.getText().trim(),
                    newTitle.getText().trim(),
                    Integer.parseInt(newPrice.getText().trim()),
                    newAuthor.getText().trim()
            );

            BookSearchService service = new BookSearchService();
            int affectedRows = service.updateBook(updatedBook);

            if (affectedRows > 0) {
                System.out.println("책 수정 완료");
            } else {
                System.out.println("수정 실패");
            }
        });
    }

    public void setBookData(BookVO book) {
        newTitle.setText(book.getBtitle());
        newPrice.setText(String.valueOf(book.getBprice()));
        newAuthor.setText(book.getBauthor());
        isbn.setText(book.getBisbn());
    }
}
