package booksearch.controller;

import booksearch.service.BookSearchService;
import booksearch.vo.BookVO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class JavaFXTableViewController implements Initializable {

    @FXML private TextField searchKeyword;
    @FXML private TableView<BookVO> tableView;
    @FXML private TableColumn<BookVO, String> isbnCol;
    @FXML private TableColumn<BookVO, String> titleCol;
    @FXML private TableColumn<BookVO, Integer> priceCol;
    @FXML private TableColumn<BookVO, String> authorCol;
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

        searchBtn.setOnAction(event -> {
            String keyword = searchKeyword.getText().trim();
            if (keyword.isEmpty()) return;

            BookSearchService service = new BookSearchService();
            tableView.setItems(service.searchBooks(keyword));
            });

        updateBtn.setOnAction(event -> {
            BookVO selectedBook = tableView.getSelectionModel().getSelectedItem();
            if (selectedBook == null) {
                System.out.println("수정할 책을 선택하세요.");
                return;
            }
            BookSearchService service = new BookSearchService();
            service.openEditForm(selectedBook);
        });

        deleteBtn.setOnAction(event -> {
            BookVO selectedBook = tableView.getSelectionModel().getSelectedItem();
            if (selectedBook == null) {
                System.out.println("삭제할 책을 선택하세요.");
                return;
            }

            BookSearchService service = new BookSearchService();

            int result = service.deleteBook(selectedBook);

            if (result > 0) {
                System.out.println(selectedBook.getBtitle() + " 삭제하였습니다");
                tableView.getItems().remove(selectedBook);
            } else {
                System.out.println("삭제 실패했습니다.");
            }
        });
    }
}