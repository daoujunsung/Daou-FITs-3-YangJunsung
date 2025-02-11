package booksearch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;

public class JavaFXTableViewEx extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // 화면 초기화
        // 화면 구성을 FXML 을 이용
        Parent root = null;
        FXMLLoader loader =
                new FXMLLoader(getClass().getResource("tableviewexample.fxml"));

        try {
            root = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
