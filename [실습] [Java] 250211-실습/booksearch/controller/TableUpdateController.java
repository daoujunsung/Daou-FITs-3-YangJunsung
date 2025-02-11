package booksearch.controller;

import booksearch.vo.Book;
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
        updateBtn.setOnAction(event -> saveChanges());
    }

    public void setBookData(Book book) {
        newTitle.setText(book.getBtitle());
        newPrice.setText(String.valueOf(book.getBprice()));
        newAuthor.setText(book.getBauthor());
        isbn.setText(book.getBisbn());
    }

    private void saveChanges() {
        String updateTitle = newTitle.getText().trim();
        String updateAuthor = newAuthor.getText().trim();
        String updatePriceStr = newPrice.getText().trim();
        String bisbn = isbn.getText().trim();

        if (updateTitle.isEmpty() || updateAuthor.isEmpty() || updatePriceStr.isEmpty()) {
            System.out.println("모든 필드를 입력하세요.");
            return;
        }

        try {
            int newPrice = Integer.parseInt(updatePriceStr);  // 가격을 숫자로 변환

            // DB 업데이트 쿼리
            String query = "UPDATE book SET btitle = ?, bprice = ?, bauthor = ? WHERE bisbn = ?";

            try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "C##DEV", "4587");
                 PreparedStatement pstmt = conn.prepareStatement(query)) {

                pstmt.setString(1, updateTitle);
                pstmt.setInt(2, newPrice);
                pstmt.setString(3, updateAuthor);
                pstmt.setString(4, bisbn);  // 기존 ISBN으로 해당 책을 수정

                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("책 수정 완료");
                } else {
                    System.out.println("수정 실패");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (NumberFormatException e) {
            System.out.println("가격은 숫자여야 합니다.");
        }
    }


}
