package booksearch.controller;

import booksearch.TableUpdateStage;
import booksearch.vo.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class JavaFXTableViewController implements Initializable {

    @FXML private TextField searchKeyword;
    @FXML private TableView<Book> tableView;
    @FXML private TableColumn<Book, String> isbnCol;
    @FXML private TableColumn<Book, String> titleCol;
    @FXML private TableColumn<Book, Integer> priceCol;
    @FXML private TableColumn<Book, String> authorCol;
    @FXML private Button searchBtn;
    @FXML private Button updateBtn;
    @FXML private Button deleteBtn;

    private final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private final String ID = "C##DEV";
    private final String PW = "4587";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // FXML 이 로드될 때 실행
        // 버튼이 클릭되면 TableView 에 데이터를 넣어야 함
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("bisbn"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("btitle"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("bprice"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("bauthor"));

        searchBtn.setOnAction(event -> searchBooks());
        updateBtn.setOnAction(event -> openEditForm());
        deleteBtn.setOnAction(event -> deleteBook());
    }

    private void searchBooks() {
        String keyword = searchKeyword.getText().trim();
        if (keyword.isEmpty()) return;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        ObservableList<Book> books = FXCollections.observableArrayList();
        String query = "SELECT bisbn, btitle, bprice, bauthor FROM book " +
                "WHERE btitle LIKE ?";

        try {
            conn = DriverManager.getConnection(JDBC_URL, ID, PW);
            pstmt = conn.prepareStatement(query);

            pstmt.setString(1, "%" + keyword + "%");

            rs = pstmt.executeQuery();

            while (rs.next()) {
                books.add(new Book(
                        rs.getString("bisbn"),
                        rs.getString("btitle"),
                        rs.getInt("bprice"),
                        rs.getString("bauthor")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("정상 종료되지 않았습니다.");
            }
        }

        // TableView 업데이트
        tableView.setItems(books);
    }

    private void openEditForm() {
        // TableView에서 선택된 행 가져오기
        Book selectedBook = tableView.getSelectionModel().getSelectedItem();
        if (selectedBook == null) {
            System.out.println("수정할 책을 선택하세요.");
            return;
        }

        TableUpdateStage tableUpdateStage = new TableUpdateStage(selectedBook);
        tableUpdateStage.show();

    }

    private void deleteBook() {
        Book selectedBook = tableView.getSelectionModel().getSelectedItem();
        if (selectedBook == null) {
            System.out.println("삭제할 책을 선택하세요.");
            return;
        }
        System.out.println(selectedBook.getBtitle());

        Connection conn = null;
        PreparedStatement pstmt = null;

        String query = "DELETE FROM book WHERE bisbn = ?";

        try {
            conn = DriverManager.getConnection(JDBC_URL, ID, PW);
            pstmt = conn.prepareStatement(query);

            pstmt.setString(1, selectedBook.getBisbn());

            int result = pstmt.executeUpdate();

            if (result > 0) {
                System.out.println(selectedBook.getBtitle() + " 삭제하였습니다");
                tableView.getItems().remove(selectedBook);
            } else {
                System.out.println("삭제 실패했습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("정상 종료되지 않았습니다.");
            }
        }
    }
}