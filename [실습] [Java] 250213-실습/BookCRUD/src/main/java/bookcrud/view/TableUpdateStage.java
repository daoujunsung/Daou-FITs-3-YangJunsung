package bookcrud.view;

import bookcrud.controller.TableUpdateController;
import bookcrud.vo.BookVO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TableUpdateStage extends Stage {

    public TableUpdateStage(BookVO book) {
        Parent root = null;

        FXMLLoader fxmlLoader =
                new FXMLLoader(getClass().getResource("/fxml/updateform.fxml"));

        try {
            root = fxmlLoader.load();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // FXML 로드 후, 컨트롤러를 가져오기
        TableUpdateController controller = fxmlLoader.getController();

        // Book 객체의 값들을 controller의 TextField에 전달
        controller.setBookData(book);  // 컨트롤러에 데이터 전달

        // Scene 설정
        Scene scene = new Scene(root);
        this.setScene(scene);
    }
}
