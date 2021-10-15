package sample;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoadingLogo implements Initializable {
    public static JDBCClass jdbcClass;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Stage primaryStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FXML/Login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("Faculty IMS");
        primaryStage.setScene(new Scene(root, 640,640 ));
        primaryStage.show();
    }
}
