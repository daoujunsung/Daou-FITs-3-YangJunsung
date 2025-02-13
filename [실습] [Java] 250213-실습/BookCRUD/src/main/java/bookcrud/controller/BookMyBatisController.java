package bookcrud.controller;

import bookcrud.service.BookCRUDService;
import bookcrud.service.BookCRUDServiceOracleImpl;
import bookcrud.vo.BookVO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class BookMyBatisController implements Initializable {

    @FXML private TextField newISBN;
    @FXML private TextField newTitle;
    @FXML private TextField newPrice;
    @FXML private TextField newAuthor;
    @FXML private TextField searchKeyword;
    @FXML private TableView<BookVO> tableView;
    @FXML private TableColumn<BookVO, String> isbnCol;
    @FXML private TableColumn<BookVO, String> titleCol;
    @FXML private TableColumn<BookVO, Integer> priceCol;
    @FXML private TableColumn<BookVO, String> authorCol;
    @FXML private Button registerBtn;
    @FXML private Button searchBtn;
    @FXML private Button updateBtn;
    @FXML private Button deleteBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // FXML 이 로드될 때 실행
        // 버튼이 클릭되면 TableView 에 데이터를 넣어야 함
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("bisbn"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("btitle"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("bprice"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("bauthor"));

        registerBtn.setOnAction(event -> {
            String isbn = newISBN.getText().trim();
            String title = newTitle.getText().trim();
            String price = newPrice.getText().trim();
            String author = newAuthor.getText().trim();

            BookVO newBook = new BookVO(isbn, title, Integer.parseInt(price), author);
            BookCRUDService service = new BookCRUDServiceOracleImpl();
            int result = service.createBook(newBook);

            if (result > 0) {
                System.out.println("등록되었습니다");
            } else {
                System.out.println("등록에 실패했습니다");
            }
        });

        searchBtn.setOnAction(event -> {
            String keyword = searchKeyword.getText().trim();
            if (keyword.isEmpty()) return;

            BookCRUDService service = new BookCRUDServiceOracleImpl();
            tableView.setItems(service.searchBooks(keyword));
            });

        updateBtn.setOnAction(event -> {
            BookVO selectedBook = tableView.getSelectionModel().getSelectedItem();
            if (selectedBook == null) {
                System.out.println("수정할 책을 선택하세요.");
                return;
            }
            BookCRUDService service = new BookCRUDServiceOracleImpl();
            service.openEditForm(selectedBook);
        });

        deleteBtn.setOnAction(event -> {
            BookVO selectedBook = tableView.getSelectionModel().getSelectedItem();
            if (selectedBook == null) {
                System.out.println("삭제할 책을 선택하세요.");
                return;
            }

            BookCRUDService service = new BookCRUDServiceOracleImpl();

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