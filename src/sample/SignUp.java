package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static sample.Main.jdbcClass;

public class SignUp extends Login implements Initializable{
    @FXML
    public void submit(ActionEvent event){
        Validation.dialogFlag = 0;
        ArrayList<String> signUp = new ArrayList<>();
        String collageEmail = registrationCollegeEmail.getText();
        String firstName = registrationFirstName.getText();
        String middleName = registrationMiddleName.getText();
        String lastName = registrationLastName.getText();
        String departmentName = registrationDepartmentName.getSelectionModel().isEmpty() ? "" : registrationDepartmentName.getValue().toString();
        String departmentID = registrationDepartmentId.getText();
        String password = registrationPassword.getText();
        String confirmPassword = registrationConfirmPassword.getText();
        new Validation().isEmpty(firstName,"First Name");
        new Validation().isEmpty(middleName,"Middle Name");
        new Validation().isEmpty(lastName,"Last Name");
        new Validation().isEmpty(departmentName,"Department Name");
        new Validation().isEmpty(departmentID,"Department ID");
        new Validation().isEmpty(collageEmail,"Collage Email");
        new Validation().isEmpty(password,"Password");
        new Validation().isEmpty(confirmPassword,"Confirm Password");
        if(Validation.dialogFlag == 0) {
            new Validation().validateAlphabets(departmentID,"Department ID");
            /*if (new Validation().validateAlphabets(departmentID)) {
                JOptionPane.showMessageDialog(null, "Please Enter Valid Department ID");*/
            if (!new Validation().validateSinhgadEmail(collageEmail)) {
                JOptionPane.showMessageDialog(null, "Please Enter Valid Collage Email Address");
            } else if (!password.contentEquals(confirmPassword)) {
                JOptionPane.showMessageDialog(null, "Password And Confirm Password Not Equal");
            } else if (new Validation().validateSinhgadEmail(collageEmail)) {
                String query = "select * from facultymain";
                int flag = 0;
                try {
                    PreparedStatement preparedStatement = jdbcClass.connection.prepareStatement(query);
                    ResultSet resultSet = jdbcClass.fireQuery(preparedStatement);
                    while (resultSet.next()) {
                        if (resultSet.getString("UserName").equals(collageEmail)) {
                            JOptionPane.showMessageDialog(null, "Collage Email Address is Already Present Please Login To Continue");
                            flag = 1;
                            break;
                        }
                    }
                    if (flag == 0) {
                        String facultyName = firstName + " " + middleName + " " + lastName;
                        signUp.add(collageEmail);
                        signUp.add(password);
                        signUp.add(departmentName);
                        signUp.add(departmentID);
                        signUp.add(firstName + middleName + lastName);
                        signUp.add("Active");
                        signUp.add("1");
                        System.out.println(signUp);
                        String InsertQuery = "insert into facultymain (UserName,Password,DepartmentName,DepartmentID,FacultyName,Status,AccessFlag) values('" + collageEmail + "','" + password + "','" + departmentName + "','" + departmentID + "','" + facultyName + "','" + "Active" + "','" + "1" + "')";
                        preparedStatement = jdbcClass.connection.prepareStatement(InsertQuery);
                        System.out.println(preparedStatement.toString());
                        jdbcClass.fireQuery(preparedStatement);
                        JOptionPane.showMessageDialog(null, "Sign Up SuccessFully");
                        changeFXML("../FXML/Login.fxml", event, 640, 640);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,"Database is Not Connected");
                } catch (SQLException e) {

                }
            }
        }
    }
    @FXML
    private void backTO(MouseEvent event){
        Stage primaryStage = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXML/Login.fxml"));
            primaryStage.setTitle("Faculty IMS");
            primaryStage.setScene(new Scene(root,640,640));
            primaryStage.show();
            ((Node)event.getSource()).getScene().getWindow().hide();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        jdbcClass.getConnection();
        registrationDepartmentName.setItems(departmentList);
    }
}
