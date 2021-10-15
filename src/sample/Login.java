package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static sample.Main.jdbcClass;

public class Login implements Initializable {
    @FXML
    public TextField loginCollegeEmail;
    @FXML
    public PasswordField loginPassword;
    @FXML
    public ComboBox loginSelectUser;
    @FXML
    public TextField registrationFirstName;
    @FXML
    public TextField registrationMiddleName;
    @FXML
    public TextField registrationLastName;
    @FXML
    public ComboBox registrationDepartmentName;
    @FXML
    public TextField registrationDepartmentId;
    @FXML
    public TextField registrationCollegeEmail;
    @FXML
    public PasswordField registrationPassword;
    @FXML
    public PasswordField registrationConfirmPassword;
    @FXML
    public Label loginDatabase;
    /*In Database-facultyMain table-column AccessFlag if that value is 0 then Retrival forms are shown after login
    otherwise Registration Forms.
    This AccessFlag value is set when User will Fill All first Three Forms at same time.
     */
    //VARIABLES WHICH WE WILL USE IN OTHER CLASSES
    public static int REGISTRATION_NO = 0;
    public static String Department = "";
    public static String email = "";
    public static int accessFlag = 0;
    public static int adminFlag = 0; //TO RECOGNIZED THAT ADMIN HAS LOGIN

    ObservableList<String> selectList  = FXCollections.observableArrayList("College Admin","Department Admin","Faculty");
    ObservableList<String> departmentList  = FXCollections.observableArrayList("Bio-Technology","Chemical","Civil","Computer","E&TC","Information Technolgy","Mechanical","Production",
            "First Year - Mathematics","First Year - Physics","First Year - Chemistry","First Year - Electrical","MBA","Library");
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            Connection connection = jdbcClass.getConnection();
            if (connection == null) {
                System.out.println("h");
                loginDatabase.setText("Database is not Connected");
            } else {
                System.out.println("hl");
                loginDatabase.setText("Database is Connected");
            }
        loginSelectUser.setItems(selectList);
    }
    @FXML
    public void signInButton(ActionEvent event) {
        Validation.dialogFlag = 0;
        PreparedStatement preparedStatement, departmentPreparedStatement;
        ResultSet resultSet, departmentResultSet;
        String collageEmail = loginCollegeEmail.getText();
        String password = loginPassword.getText();
        String user = loginSelectUser.getSelectionModel().isEmpty() ? "" : loginSelectUser.getValue().toString();
        Validation.isEmpty(user, "User");
        Validation.isEmpty(collageEmail, "UserName");
        Validation.isEmpty(password, "Password");
        String signInQuery = "select * from facultymain";
        String departmentInQuery = "select * from department";
        try {
            if (Validation.dialogFlag == 0) {
                int dialog = 0;
                preparedStatement = jdbcClass.connection.prepareStatement(signInQuery);
                resultSet = jdbcClass.fireQuery(preparedStatement);
                departmentPreparedStatement = jdbcClass.connection.prepareStatement(departmentInQuery);
                departmentResultSet = jdbcClass.fireQuery(departmentPreparedStatement);
                if (loginSelectUser.getValue() == "College Admin") {
                    if (password.equals("Admin") && collageEmail.equals("Admin")) {
                        //COLLAGE ADMIN
                        adminFlag = 1;
                        changeFXML("FXML/AdminMain.fxml", event, 1280, 700);
                    }
                    else
                        JOptionPane.showMessageDialog(null,"UserName And Password is Incorrect For Admin");
                } else if (loginSelectUser.getValue() == "Department Admin") {
                    //DEPARTMENT ADMIN
                    adminFlag = 0;
                    while (departmentResultSet.next()) {
                        if (collageEmail.equals(departmentResultSet.getString("UserName")) && password.equals(departmentResultSet.getString("Password"))) {
                            Department = departmentResultSet.getString("DepartmentName");
                            dialog = 0;
                            changeFXML("FXML/AdminMain.fxml", event, 1280, 700);
                            break;
                        }
                        else
                            dialog = 1;
                    }
                    if(dialog==1)
                        JOptionPane.showMessageDialog(null,"UserName And Password is Incorrect For Department Admin,Please Try Again");
                } else if (loginSelectUser.getValue() == "Faculty") {
                    while (resultSet.next()) {
                        if (collageEmail.equals(resultSet.getString("UserName")) && password.equals(resultSet.getString("Password"))) {
                            REGISTRATION_NO = Integer.parseInt(resultSet.getString("RegistrationNo"));
                            Department = resultSet.getString("DepartmentName");
                            email = resultSet.getString("UserName");
                            accessFlag = Integer.parseInt(resultSet.getString("AccessFlag"));
                            System.out.println(REGISTRATION_NO);
                            if (accessFlag == 1) {
                                dialog = 0;
                                JOptionPane.showMessageDialog(null,"Fill First Three Form Details Completely After that your response will be Generate.");
                                if(JOptionPane.OK_OPTION==0)
                                    changeFXML("FXML/RegistrationForms.fxml", event, 1280, 700);
                            } else {
                                dialog = 0;
                                changeFXML("FXML/RetrivalForms.fxml", event, 1280, 700);
                            }
                            break;
                        }
                        else
                            dialog = 1;
                    }
                    if(dialog ==1)
                        JOptionPane.showMessageDialog(null,"UserName And Password is Incorrect For Faculty,Please Try Again !!");
                }
            }
        }
        catch (NullPointerException e){
            JOptionPane.showMessageDialog(null,"Database is Not Connected");
        }
        catch(SQLException e){
                e.printStackTrace();
        }
    }
    @FXML
    public void signUpButton(ActionEvent event){
      changeFXML("FXML/Registration.fxml",event,720,720);
    }
    public void changeFXML(String FXMLName,ActionEvent event,int v,int v1){
        Stage primaryStage = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource(FXMLName));
            primaryStage.setTitle("Faculty IMS");
            primaryStage.setScene(new Scene(root, v, v1));
            primaryStage.show();
            ((Node)event.getSource()).getScene().getWindow().hide();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    private void forgotPassword(MouseEvent event){

    }
}