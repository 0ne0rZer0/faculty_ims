package sample;

import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static JDBCClass jdbcClass;
    @FXML
    public JFXTextField connectionIP;
    @FXML
    public JFXTextField connectionPort;

    @Override
    public void start(Stage primaryStage) throws Exception{
        jdbcClass = new JDBCClass("com.mysql.cj.jdbc.Driver","jdbc:mysql://192.168.0.103/facultyims","root","root");
        Parent root = FXMLLoader.load(getClass().getResource("FXML/Login.fxml"));
        primaryStage.setTitle("Faculty IMS");
        primaryStage.setScene(new Scene(root, 640,640 ));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

}

