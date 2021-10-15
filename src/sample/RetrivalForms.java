package sample;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static sample.Main.jdbcClass;

public class RetrivalForms extends RegistrationForms implements Initializable {

    @FXML
    public JFXButton interactionAddMore;
    @FXML
    public JFXButton publicationAddMore;
    @FXML
    public JFXButton attendedAddMore;
    @FXML
    public JFXButton organizedAddMore;
    @FXML
    public JFXButton fundedAddMore;
    @FXML
    public JFXButton guestAddMore;
    @FXML
    public JFXButton workAddMore;
    @FXML
    public Button logoutCallButton;
    @FXML
    public javafx.scene.control.TableView attendedRetriveTable;
    @FXML
    public javafx.scene.control.TableView organizedRetriveTable;
    @FXML
    public TableView guestRetriveTable;
    @FXML
    public TableView fundedRetriveTable;
    @FXML
    public TableView educationalRetriveTable;
    @FXML
    public TableView publicationRetriveTable;
    @FXML
    protected TableColumn<Column,String> attendedRTableTypeColumn;
    @FXML
    protected TableColumn<Column,String> attendedRTableTitleColumn;
    @FXML
    protected TableColumn<Column,String> attendedRTableDateColumn;
    @FXML
    protected TableColumn<Column,String> attendedRTableYearColumn;
    @FXML
    protected TableColumn<Column,String> organizedRTableTypeColumn;
    @FXML
    protected TableColumn<Column,String> organizedRTableTitleColumn;
    @FXML
    protected TableColumn<Column,String> organizedRTableDateColumn;
    @FXML
    protected TableColumn<Column,String> organizedRTableYearColumn;
    @FXML
    protected TableColumn<Column,String> guestRTableTopic;
    @FXML
    protected TableColumn<Column,String> guestRTableName;
    @FXML
    protected TableColumn<Column,String> guestRTableDate;
    @FXML
    protected TableColumn<Column,String> guestRTableYearColumn;
    @FXML
    protected TableColumn<Column,String> fundedRTableTitleColumn;
    @FXML
    protected TableColumn<Column,String> fundedRTableSponsoringColumn;
    @FXML
    protected TableColumn<Column,String> fundedRTableDateColumn;
    @FXML
    protected TableColumn<Column,String> fundedRTableYearColumn;
    @FXML
    protected TableColumn<Column,String> eduRFieldColumn;
    @FXML
    protected TableColumn<Column,String> eduRPositionColumn;
    @FXML
    protected TableColumn<Column,String> eduRNameColumn;
    @FXML
    protected TableColumn<Column,String> eduRDateColumn;
    @FXML
    protected TableColumn<Column,String> publicationRTableTitleColumn;
    @FXML
    protected TableColumn<Column,String> publicationRTableTypeColumn;
    @FXML
    protected TableColumn<Column,String> publicationRTableDateColumn;
    @FXML
    protected TableColumn<Column,String> publicationRTableYearColumn;
    @FXML
    protected TableView interactionRetriveTable;
    @FXML
    protected TableColumn<Column,String> interactionRTableParticulars;
    @FXML
    protected TableColumn<Column,String> interactionRTableRole;
    @FXML
    protected TableColumn<Column,String> interactionRTableDate;
    @FXML
    protected TableColumn<Column,String> interactionRTableYear;
    @FXML
    protected AnchorPane personalAnchorPane;
    @FXML
    protected AnchorPane educationalAnchorPane;
    @FXML
    protected AnchorPane sppuAnchorPane;
    @FXML
    protected Button personalCallButton;

    protected String firstColumn = null;
    public  static String thirdColumn = null;
    public static  String fourthColumn = null;
    public static int addMoreFlag = 0;

    //FLAG TO DETERMINE WHICH PANE IS SELECTED
    private static int flag =0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        jdbcClass.getConnection();
        if(AdminRetrieve.flagUpdate == 1) {
            Login.REGISTRATION_NO = AdminMain.RegistrationNo;
            attendedAddMore.setDisable(true);
            publicationAddMore.setDisable(true);
            fundedAddMore.setDisable(true);
            guestAddMore.setDisable(true);
            organizedAddMore.setDisable(true);
            interactionAddMore.setDisable(true);
            workAddMore.setDisable(true);
            logoutCallButton.setDisable(true);
        }
        else{
            attendedAddMore.setDisable(false);
            publicationAddMore.setDisable(false);
            fundedAddMore.setDisable(false);
            guestAddMore.setDisable(false);
            organizedAddMore.setDisable(false);
            interactionAddMore.setDisable(false);
            workAddMore.setDisable(false);
            logoutCallButton.setDisable(false);
        }

        //Edit Pane
        personalAnchorPane.setDisable(true);
        educationalAnchorPane.setDisable(true);
        sppuAnchorPane.setDisable(true);

        attendedRTableTitleColumn.setCellValueFactory(new PropertyValueFactory<>("firstColumn"));
        attendedRTableTypeColumn.setCellValueFactory(new PropertyValueFactory<>("secondColumn"));
        attendedRTableDateColumn.setCellValueFactory(new PropertyValueFactory<>("thirdColumn"));
        attendedRTableYearColumn.setCellValueFactory(new PropertyValueFactory<>("fourthColumn"));

        organizedRTableTitleColumn.setCellValueFactory(new PropertyValueFactory<>("firstColumn"));
        organizedRTableTypeColumn.setCellValueFactory(new PropertyValueFactory<>("secondColumn"));
        organizedRTableDateColumn.setCellValueFactory(new PropertyValueFactory<>("thirdColumn"));
        organizedRTableYearColumn.setCellValueFactory(new PropertyValueFactory<>("fourthColumn"));

        guestRTableTopic.setCellValueFactory(new PropertyValueFactory<>("firstColumn"));
        guestRTableName.setCellValueFactory(new PropertyValueFactory<>("secondColumn"));
        guestRTableDate.setCellValueFactory(new PropertyValueFactory<>("thirdColumn"));
        guestRTableYearColumn.setCellValueFactory(new PropertyValueFactory<>("fourthColumn"));

        fundedRTableTitleColumn.setCellValueFactory(new PropertyValueFactory<>("firstColumn"));
        fundedRTableSponsoringColumn.setCellValueFactory(new PropertyValueFactory<>("secondColumn"));
        fundedRTableDateColumn.setCellValueFactory(new PropertyValueFactory<>("thirdColumn"));
        fundedRTableYearColumn.setCellValueFactory(new PropertyValueFactory<>("fourthColumn"));

        eduRFieldColumn.setCellValueFactory(new PropertyValueFactory<>("firstColumn"));
        eduRNameColumn.setCellValueFactory(new PropertyValueFactory<>("secondColumn"));
        eduRPositionColumn.setCellValueFactory(new PropertyValueFactory<>("fourthColumn"));
        eduRDateColumn.setCellValueFactory(new PropertyValueFactory<>("thirdColumn"));

        publicationRTableTypeColumn.setCellValueFactory(new PropertyValueFactory<>("firstColumn"));
        publicationRTableTitleColumn.setCellValueFactory(new PropertyValueFactory<>("secondColumn"));
        publicationRTableDateColumn.setCellValueFactory(new PropertyValueFactory<>("thirdColumn"));
        publicationRTableYearColumn.setCellValueFactory(new PropertyValueFactory<>("fourthColumn"));

        interactionRTableParticulars.setCellValueFactory(new PropertyValueFactory<>("firstColumn"));
        interactionRTableRole.setCellValueFactory(new PropertyValueFactory<>("secondColumn"));
        interactionRTableDate.setCellValueFactory(new PropertyValueFactory<>("thirdColumn"));
        interactionRTableYear.setCellValueFactory(new PropertyValueFactory<>("fourthColumn"));

        personalCategory.setItems(personalCategoryList);

        eduUGDegree.setItems(UG_degreelist);
        eduPGDegree.setItems(PG_degreelist);
        eduPGClass.setItems(classlist);
        eduUGClass.setItems(classlist);
        eduPGCompletionStatus.setItems(completionStatuslist);
        eduPHDCompletionStatus.setItems(completionStatuslist);
        eduUGSpecialization.setItems(specializationlist);
        eduPGSpecialization.setItems(specializationlist);

        sppuTypeOfAppointment.setItems(typeOfAppointmentList);
        sppuStesCurrentApprovalCatagory.setItems(stesCurrentApprovalCatagoryList);
        sppuCurrentDesignation.setItems(currentDesignationList);

        showPersonalData();
        showBankData();
    }
    public void windowClosed(WindowEvent e) {
        System.out.println("A has closed");
    }
    //Generate the TABLE
    void tableGenerate(TableView tableName){
        try {
            tableName.setItems(getColumn());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tableName.setRowFactory( tv -> {
            TableRow<Column> row = new TableRow<>();
            row.setOnMouseClicked(event ->  {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Column rowData = row.getItem();
                    firstColumn = rowData.getFirstColumn();
                    thirdColumn = rowData.getThirdColumn();
                    fourthColumn = rowData.getFourthColumn();
                    System.out.println(thirdColumn);
                    RetrivalFormsPane retriveFormController = new RetrivalFormsPane();
                    /*Accourding to flag particular method is called of FORM */
                    switch (flag){
                        case 8:
                            System.out.println("Working table Generate");
                            retriveFormController.generateData(flag,firstColumn);
                            break;
                        case 10:
                            System.out.println("Publication table Generate");
                            retriveFormController.generateData(flag,firstColumn);
                            break;
                        case 11:
                            System.out.println("Attended table Generate");
                            retriveFormController.generateData(flag,firstColumn);
                            break;
                        case 12:
                            System.out.println("Organized table Generate");
                            retriveFormController.generateData(flag,firstColumn);
                            break;
                        case 13:
                            System.out.println("Interaction table Generate");
                            retriveFormController.generateData(flag,firstColumn);
                            break;
                        case 14:
                            retriveFormController.generateData(flag,firstColumn);
                            break;
                        case 15:
                            System.out.println("Guest table Generate");
                            retriveFormController.generateData(flag,firstColumn);
                            break;
                    }
                }
            });
            return row;
        });
    }
    //In this Method Query will be Fired According to Flag
    public ObservableList<Column> getColumn() throws SQLException {
        ObservableList<Column> columns = FXCollections.observableArrayList();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        String query = "";
        System.out.println(flag);
        query = "select TitleOfProgramme,TypeOfProgramme,DateFrom,AcademicYear from attended where RegistrationNo = '"+Login.REGISTRATION_NO+"'";
        if(flag == 8)
            query = "select Field,NameOfInstitute,DateOfJoining,Position from experiencebeforejoiningstes where  RegistrationNo = '"+Login.REGISTRATION_NO+"'";
        if(flag == 10)
            query = "select TitleOfPaper,PublicationType,FromDateofConferencePublication,AcademicYear from publication where  RegistrationNo = '"+Login.REGISTRATION_NO+"'";
        if(flag == 12)
            query = "select TitleOfProgramme,TypeOfProgramme,DateFrom,AcademicYear from organized where  RegistrationNo = '"+Login.REGISTRATION_NO+"'";
        if(flag == 13)
            query = "select Particulars , RoleofFaculty ,ProgramDate,AcademicYear from interaction where RegistrationNo = '"+Login.REGISTRATION_NO+"'";
        if(flag ==14)
            query = "select TitleofResearchProduct,SponsoringAgency,FromDate,AcademicYear from fundedresearchproduct where  RegistrationNo = '"+Login.REGISTRATION_NO+"'";
        if(flag == 15)
            query = "select Topics,NameOfResourcePerson,DateOfConduction,AcademicYear from guestlecture where RegistrationNo = '"+Login.REGISTRATION_NO+"'";
        preparedStatement = jdbcClass.connection.prepareStatement(query);
        resultSet = jdbcClass.fireQuery(preparedStatement);
        while(resultSet.next()) {
            columns.add(new Column(resultSet.getString(1), resultSet.getString(2),resultSet.getDate(3).toString(),resultSet.getString(4)));
        }
        return columns;
    }

    ////////////////////////"UPDATE Of DATA"/////////////////////////

    //Personal update
    @Override
    protected void personalSaveButtonHandle(ActionEvent event) {
        PreparedStatement personalQuery;
        PreparedStatement bankQuery;
        ArrayList<String> personalData = getPersonalData();
        ArrayList<String> bankData = getBankData();
        String personalStringQuery = "update personal set FirstName = ? ,MiddleName = ?, LastName = ? ,Gender = ?,DOB = ?,MotherName = ?,Category = ?,Religion = ? ," +
                "Caste = ?,PhoneNo = ?,Email = ? ,Address = ? where RegistrationNo = ?";
        String bankStringQuery = "update bankdetails set AadharCardNo = ? , PanCardNo = ?,BankAccountNo = ?,IFSC = ?,MICR = ?,PF = ?,PassportNo = ? where  RegistrationNo = ?";
        System.out.println(bankData);
        try {
            personalQuery =  jdbcClass.connection.prepareStatement(personalStringQuery);
            personalQuery.setInt(13, Login.REGISTRATION_NO);
            for(int i = 1; i < 13 ; ++i ) {
                if(i != 10)
                    personalQuery.setString(i,personalData.get(i-1));
                else
                    personalQuery.setLong(i,Long.parseLong(personalData.get(i-1)));
            }
            System.out.println(personalQuery.toString());
            bankQuery = jdbcClass.connection.prepareStatement(bankStringQuery);
            bankQuery.setInt(8,Login.REGISTRATION_NO);
            for(int i = 1; i < 8 ; ++i) {
                if(i == 1 || i == 5) {
                    bankQuery.setLong(i, Long.parseLong(bankData.get(i-1)));
                } else {
                    bankQuery.setString(i,bankData.get(i-1));
                }
            }
            System.out.println(bankQuery.toString());
            jdbcClass.fireQuery(personalQuery);
            jdbcClass.fireQuery(bankQuery);

            JOptionPane.showMessageDialog(null,"Data is Updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //SPPU Update
    @Override
    protected void sppuSaveButton(ActionEvent event){
        PreparedStatement sppuPGQuery;
        PreparedStatement sppuPhdQuery;
        PreparedStatement stesQuery;
        PreparedStatement updatephdQuery = null;
        nameOfAssociatedResearchCentre = sppuNameOfAssociatedResearchCentre.getText();
        String sppuPGStringQuery = "update sppu set DateOfApproval = ?, ReferenceNo = ?, CurrentDesignation = ? where RegistrationNo = ? and ApprovalCategory = '"+"PG"+"'";
        String sppuPhdStringQuery = "update sppu set DateOfApproval = ?, ReferenceNo = ?, CurrentDesignation = ? where RegistrationNo = ? and ApprovalCategory = '"+"PHD"+"'";
        String stesStringQuery = "update stes set EmployeeNo = ?, TypesOfAppointment = ?, DateAppointmentCurrentDesignation = ?, AppointmentOrderReferenceNumber = ?, SPPUCurrentApprovalCategory = ?, PreviousAppointment = ?, SPPUDateOfApprovalOfCurrentDesignation = ?, SPPUApprovalReferenceNumber = ? where RegistrationNo = ?";
        String updateNameResearchCentre = "update educationalphd set NameResearch ='" + nameOfAssociatedResearchCentre + "' where RegistrationNo=1;";
        String employeeNo = sppuEmployeeNumber.getText();
        String referenceNo = sppuReferenceNumber.getText();
        String currentDesignation = sppuCurrentDesignation.getValue().toString();
        LocalDate sppuPgApprovalDate = sppuPgTeacherApprovalDate.getValue();
        LocalDate sppuPhdGuideRecognitionApprovalDateVar = sppuPhdGuideRecognitionApprovalDate.getValue();
        String phdGuideApprovalReferenceNo = sppuPhdGuideApprovalReferenceNumber.getText();
        String typeOfAppointment = sppuTypeOfAppointment.getValue().toString();
        LocalDate sppuDateOfApprovalOfCurrentDesignationVar = sppuDateOfApprovalOfCurrentDesignation.getValue();
        String stesAppointmentOrderReferenceNo = sppuStesAppointmentOrderReferenceNumber.getText();
        LocalDate stesDateOfAppointmentOfCurrentDesignationVar = stesDateOfAppointmentOfCurrentDesignation.getValue();
        String stesCurrentApprovalCatagory = sppuStesCurrentApprovalCatagory.getValue();
        String sppuApprovalReferenceNumberVar = sppuApprovalReferenceNumber.getText();
        try {
            sppuPGQuery = jdbcClass.connection.prepareStatement(sppuPGStringQuery);
            sppuPhdQuery = jdbcClass.connection.prepareStatement(sppuPhdStringQuery);
            updatephdQuery = jdbcClass.connection.prepareStatement(updateNameResearchCentre);
            stesQuery = jdbcClass.connection.prepareStatement(stesStringQuery);

            sppuPGQuery.setInt(4, Login.REGISTRATION_NO);
            sppuPGQuery.setDate(1, java.sql.Date.valueOf(sppuPgApprovalDate));
            sppuPGQuery.setString(2, referenceNo);
            sppuPGQuery.setString(3, currentDesignation);


            sppuPhdQuery.setInt(4, Login.REGISTRATION_NO);
            sppuPhdQuery.setDate(1, java.sql.Date.valueOf(sppuPhdGuideRecognitionApprovalDateVar));
            sppuPhdQuery.setString(2, phdGuideApprovalReferenceNo);
            sppuPhdQuery.setString(3, currentDesignation);

            stesQuery.setInt(9, Login.REGISTRATION_NO);
            stesQuery.setString(1, employeeNo);
            stesQuery.setString(2, typeOfAppointment);
            stesQuery.setDate(3, java.sql.Date.valueOf(stesDateOfAppointmentOfCurrentDesignationVar));
            stesQuery.setString(4, stesAppointmentOrderReferenceNo);
            stesQuery.setString(5, stesCurrentApprovalCatagory);
            stesQuery.setString(6, radioButtonHandler());
            stesQuery.setDate(7, java.sql.Date.valueOf(sppuDateOfApprovalOfCurrentDesignationVar));
            stesQuery.setString(8, sppuApprovalReferenceNumberVar);
            //stesQuery.setDate(9, Date.valueOf(SCOEJoiningDate));

            jdbcClass.fireQuery(sppuPGQuery);
            jdbcClass.fireQuery(sppuPhdQuery);
            jdbcClass.fireQuery(stesQuery);
            jdbcClass.fireQuery(updatephdQuery);
            JOptionPane.showMessageDialog(null,"Data is Updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Educational Update
    @Override
    protected void educationalSaveButton(ActionEvent event) {
        PreparedStatement educationalUG = null;
        PreparedStatement educationalPHD = null;
        PreparedStatement educationalPG = null;
        ArrayList <String> UGData = getUGEducationalData();
        ArrayList <String> PGData = getPGEducationalData();
        ArrayList<String> PHDData = getPHDEducationalData();
        String educationalStringUGQuery = "update educationalugpg set GraduationType = ?,Degree = ?,Specialization = ?,UniversityName = ?,Percentage = ?, Class = ?, MonthYear = ?, CompletionStatus = ? where RegistrationNo = ?;";
        String educationalStringPGQuery = "update educationalugpg set GraduationType = ?,Degree = ?,Specialization = ?,UniversityName = ?,Percentage = ?, Class = ?, MonthYear = ?, CompletionStatus = ? where RegistrationNo = ?;";
        String educationalStringPHDQuery = "update educationalphd set Domain = ?, Specialization = ?, UniversityName = ?, Status = ?, YearAdmission = ?, YearCompletion = ?, NameResearch= ?, where RegistrationNo = ?;";
        try {
            educationalUG = jdbcClass.connection.prepareStatement(educationalStringUGQuery);
            educationalPG = jdbcClass.connection.prepareStatement(educationalStringPGQuery);
            educationalPHD = jdbcClass.connection.prepareStatement(educationalStringPHDQuery);
            educationalUG.setInt(9, Login.REGISTRATION_NO);
            educationalPG.setInt(9, Login.REGISTRATION_NO);
            educationalPHD.setInt(8, Login.REGISTRATION_NO);

            for (int i = 1; i <= 8; i++) {
                if (i != 5)
                    educationalUG.setString(i, UGData.get(i - 1));
                else
                    educationalUG.setFloat(i, Float.parseFloat(UGData.get(i - 1)));
            }
            for (int i = 1; i <= 8; i++) {
                if (i != 5)
                    educationalPG.setString(i, PGData.get(i - 1));
                else
                    educationalPG.setFloat(i, Float.parseFloat(PGData.get(i - 1)));
            }
            if (eduEnabledPHD.isSelected()) {
                for (int i = 1; i <= 7; i++) {
                    educationalPHD.setString(i, PHDData.get(i - 1));
                }
            } else {
                for (int i = 1; i <= 7; i++)
                    educationalPHD.setString(i, "NULL");
            }

            jdbcClass.fireQuery(educationalUG);
            jdbcClass.fireQuery(educationalPG);
            jdbcClass.fireQuery(educationalPHD);
            JOptionPane.showMessageDialog(null, "Data is Updated");
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null,"Database error in Educational Form");
            e.printStackTrace();
        }
    }
    //SPPU
    private void showSppuData(){
        PreparedStatement sppupg = null;
        PreparedStatement sppuphd = null;
        PreparedStatement researchCenter = null;
        PreparedStatement stes = null;

        ResultSet pgresultSet,phdresultSet,nameresultSet,stesresultSet;

        String sppupgQuery = "select * from sppu where RegistrationNo = 1 and ApprovalCategory='PG'";
        String sppuphdQuery = "select * from sppu where RegistrationNo = 1 and ApprovalCategory='PHD'";
        String nameOfResearchCenterQuery = "select NameResearch from educationalphd where RegistrationNo = '"+Login.REGISTRATION_NO+"'";
        String stesQuery = "select * from stes where  RegistrationNo = '"+Login.REGISTRATION_NO+"'";

        try {
            sppupg = jdbcClass.connection.prepareStatement(sppupgQuery);
            sppuphd = jdbcClass.connection.prepareStatement(sppuphdQuery);
            researchCenter = jdbcClass.connection.prepareStatement(nameOfResearchCenterQuery);
            stes = jdbcClass.connection.prepareStatement(stesQuery);

            pgresultSet = jdbcClass.fireQuery(sppupg);
            phdresultSet = jdbcClass.fireQuery(sppuphd);
            nameresultSet = jdbcClass.fireQuery(researchCenter);
            stesresultSet = jdbcClass.fireQuery(stes);
            while(pgresultSet.next() && phdresultSet.next() && nameresultSet.next() && stesresultSet.next()) {
                sppuPgTeacherApprovalDate.setValue(LocalDate.parse(pgresultSet.getString("DateOfApproval")));
                sppuPhdGuideRecognitionApprovalDate.setValue(LocalDate.parse(phdresultSet.getString("DateOfApproval")));
                sppuReferenceNumber.setText(pgresultSet.getString("ReferenceNo"));
                sppuPhdGuideApprovalReferenceNumber.setText(phdresultSet.getString("ReferenceNo"));
                sppuNameOfAssociatedResearchCentre.setText(nameresultSet.getString("NameResearch"));
                sppuCurrentDesignation.getSelectionModel().select(pgresultSet.getString("CurrentDesignation"));
                sppuEmployeeNumber.setText(stesresultSet.getString("EmployeeNo"));
                sppuTypeOfAppointment.getSelectionModel().select(stesresultSet.getString("TypesOfAppointment"));
                stesDateOfAppointmentOfCurrentDesignation.setValue(LocalDate.parse(stesresultSet.getString("DateAppointmentCurrentDesignation")));
                sppuStesAppointmentOrderReferenceNumber.setText(stesresultSet.getString("AppointmentOrderReferenceNumber"));
                sppuStesCurrentApprovalCatagory.getSelectionModel().select(stesresultSet.getString("SPPUCurrentApprovalCategory"));
                sppuDateOfApprovalOfCurrentDesignation.setValue(LocalDate.parse(stesresultSet.getString("SPPUDateOfApprovalOfCurrentDesignation")));
                sppuApprovalReferenceNumber.setText(stesresultSet.getString("SPPUApprovalReferenceNumber"));

                String previousAppointment = stesresultSet.getString("PreviousAppointment");
                if(previousAppointment.equals("Assistant Professor")){
                    sppuHBOXAssistnatProfessor.setSelected(true);
                }else if(previousAppointment.equals("Associate Professor")) {
                    sppuHBOXAssociateProfessor.setSelected(true);
                } else if(previousAppointment.equals("No")){
                    sppuHBOXNo.setSelected(true);
                }
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Database error in SPPU Form");
            e.printStackTrace();
        }
    }
    //PERSONAL
    private void showPersonalData() {
        PreparedStatement personal;
        ResultSet personalResultSet;
        String personalQuery = "select * from personal where  RegistrationNo = '"+Login.REGISTRATION_NO+"'";
        try {
            personal = jdbcClass.connection.prepareStatement(personalQuery);
            personalResultSet = jdbcClass.fireQuery(personal);
            while(personalResultSet.next()) {
                personalFirstName.setText(personalResultSet.getString(2));
                personalMiddleName.setText(personalResultSet.getString(3));
                personalLastName.setText(personalResultSet.getString(4));
                String gender = personalResultSet.getString(5);
                switch (gender) {
                    case "Male":
                        Male.setSelected(true);
                        break;
                    case "Female":
                        Female.setSelected(true);
                        break;
                    case "Other":
                        Other.setSelected(true);
                        break;
                }
                personalDateOfBirth.setValue(LocalDate.parse(personalResultSet.getString(6)));
                personalMothersName.setText(personalResultSet.getString(7));
                personalCategory.getSelectionModel().select(personalResultSet.getString(8));
                personalReligion.setText(personalResultSet.getString(9));
                personalCaste.setText(personalResultSet.getString(10));
                personalMobileNumber.setText(personalResultSet.getString(11));
                personalPersonalEmail.setText(personalResultSet.getString(12));
                personalAddress.setText(personalResultSet.getString(13));
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null,"Database error in Personal Form");
            e.printStackTrace();
        }
    }
    //BANK
    private void showBankData() {
        PreparedStatement bank;
        ResultSet bankResultSet;
        String bankQuery = "select * from bankdetails where  RegistrationNo = '"+Login.REGISTRATION_NO+"'";
        try {
            bank = jdbcClass.connection.prepareStatement(bankQuery);
            bankResultSet = jdbcClass.fireQuery(bank);
            while(bankResultSet.next()) {
                personalAadharNumber.setText(bankResultSet.getString(2));
                personalPANNumber.setText(bankResultSet.getString(3));
                personalBankAccountNumber.setText(bankResultSet.getString(4));
                personalIFSCCode.setText(bankResultSet.getString(5));
                personalMICRCode.setText(bankResultSet.getString(6));
                personalPFNumber.setText(bankResultSet.getString(7));
                personalPassportNumber.setText(bankResultSet.getString(8));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Database error in Personal Form");
            e.printStackTrace();
        }
    }
    //UG
    private void showeducationalUGData(){
        PreparedStatement educationalUG = null;
        ResultSet resultSet;
        String educationalUGQuery = "select * from educationalugpg where  RegistrationNo = '"+Login.REGISTRATION_NO+"' and GraduationType='"+"UG"+"'";
        try {
            educationalUG = Main.jdbcClass.connection.prepareStatement(educationalUGQuery);
            resultSet = Main.jdbcClass.fireQuery(educationalUG);
            while(resultSet.next()) {

                eduUGDegree.getSelectionModel().select(resultSet.getString("Degree"));
                eduUGSpecialization.getSelectionModel().select(resultSet.getString("Specialization"));
                eduUGClass.getSelectionModel().select(resultSet.getString("Class"));
                eduUGUniversity.setText(resultSet.getString("UniversityName"));
                eduUGPercentage.setText(resultSet.getString("Percentage"));
                eduUGYearPassing.setValue(LocalDate.parse(resultSet.getString("MonthYear")));

            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Database error in Educational Form");
            e.printStackTrace();
        }
    }
    //PG
    private void showeducationalPGData(){
        PreparedStatement educationalPG = null;
        ResultSet resultSet;
        String educationalPGQuery = "select * from educationalugpg where RegistrationNo ='"+Login.REGISTRATION_NO+"' and GraduationType='"+"PG"+"'";
        try {
            educationalPG = Main.jdbcClass.connection.prepareStatement(educationalPGQuery);
            resultSet = Main.jdbcClass.fireQuery(educationalPG);
            while(resultSet.next()) {

                eduPGDegree.getSelectionModel().select(resultSet.getString("Degree"));
                eduPGSpecialization.getSelectionModel().select(resultSet.getString("Specialization"));
                eduPGClass.getSelectionModel().select(resultSet.getString("Class"));
                eduPGUniversity.setText(resultSet.getString("UniversityName"));
                eduPGPercentage.setText(resultSet.getString("Percentage"));
                eduPGYearPassing.setValue(LocalDate.parse(resultSet.getString("MonthYear")));
                eduPGCompletionStatus.getSelectionModel().select(resultSet.getString("CompletionStatus"));
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Database error in Educational Form");
            e.printStackTrace();
        }
    }
    //PHD
    private void showeducationalPHDData(){
        PreparedStatement educationalPHD = null;
        ResultSet resultSet;
        String educationalPHDQuery = "select * from educationalphd where RegistrationNo ='"+Login.REGISTRATION_NO+"'";
        String getJoiningDate = "select STESJoiningDate from stes where RegistrationNo ='"+Login.REGISTRATION_NO+"'";
        try {
            educationalPHD = Main.jdbcClass.connection.prepareStatement(educationalPHDQuery);
            resultSet = Main.jdbcClass.fireQuery(educationalPHD);
            while(resultSet.next()) {
                String temp = resultSet.getString("Domain");
                System.out.println(temp);
                if(temp!="0000-00-00") {
                    System.out.println("in");
                    eduPHDSpecialization.setText(resultSet.getString("Specialization"));
                    eduPHDDomain.setText(resultSet.getString("Domain"));
                    eduPHDUniversity.setText(resultSet.getString("UniversityName"));
                    eduPHDYearAdmission.setValue(LocalDate.parse(resultSet.getString("YearAdmission")));
                    eduPHDYearCompletion.setValue(LocalDate.parse(resultSet.getString("YearCompletion")));
                    eduPHDCompletionStatus.getSelectionModel().select(resultSet.getString("Status"));
                }
                else {
                    eduPHDSpecialization.setText("None");
                    eduPHDDomain.setText("None");
                    eduPHDUniversity.setText("None");
                    eduPHDCompletionStatus.setValue("None");
                }
            }
            educationalPHD = jdbcClass.connection.prepareStatement(getJoiningDate);
            resultSet = jdbcClass.fireQuery(educationalPHD);
            while (resultSet.next()){
                eduSCOEJoiningDate.setValue(LocalDate.parse(resultSet.getString("STESJoiningDate")));
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Database error in Educational Form");
            e.printStackTrace();
        }
    }
    @FXML
    public void paneChange(ActionEvent event) {
        educationalPane.setPrefHeight(1100);
        personalPane.setPrefHeight(1100);
        SPPUPane.setPrefHeight(1100);
        vBox.setPrefHeight(1100);
        Button button = (Button) event.getSource();
        if(personalCallButton.equals(button)) {
            flag = 7;
            showPersonalData();
            showBankData();
            personalPane.toFront();
            personalPane.setPrefHeight(1300);
            vBox.setPrefHeight(1300);
        } else if(educationalCallButton.equals(button)) {
            flag = 8;
            showeducationalUGData();
            showeducationalPGData();
            showeducationalPHDData();
            tableGenerate(educationalRetriveTable);
            educationalPane.toFront();
            educationalPane.setPrefHeight(2300);
            vBox.setPrefHeight(2300);
            System.out.println(" Educational ");
        } else if(sppuCallButton.equals(button)) {
            flag = 9;
            showSppuData();
            SPPUPane.toFront();
            SPPUPane.setPrefHeight(1200);
            vBox.setPrefHeight(1200);
            System.out.println(" Sppu ");
        } else if(publicationCallButton.equals(button)) {
            flag = 10;
            tableGenerate(publicationRetriveTable);
            publicationPane.toFront();
            System.out.println(" Publication ");
        } else if(attendedCallButton.equals(button)) {
            flag = 11;
            tableGenerate(attendedRetriveTable);
            attendedPane.toFront();
            System.out.println(" Attended VBOX");
        } else if(organizedCallButton.equals(button)) {
            flag = 12;
            tableGenerate(organizedRetriveTable);
            organizedPane.toFront();
            System.out.println(" Organized VBOX ");
        } else if(outsideWorldCallButton.equals(button)) {
            flag = 13;
            tableGenerate(interactionRetriveTable);
            interactionPane.toFront();
            System.out.println("Interaction VBOX ");
        } else if(fundedCallButton.equals(button)) {
            flag = 14;
            tableGenerate(fundedRetriveTable);
            fundedPane.toFront();
            System.out.println(" Funded ");
        } else if(guestLectureCallButton.equals(button)) {
            flag = 15;
            tableGenerate(guestRetriveTable);
            guestPane.toFront();
            System.out.println(" Guest ");
        } else if (logoutCallButton.equals(button)) {
            Stage primaryStage = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("FXML/Login.fxml"));
                primaryStage.setTitle("Faculty IMS");
                primaryStage.setScene(new Scene(root, 640, 640));
                primaryStage.show();
                ((Node)event.getSource()).getScene().getWindow().hide();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        vBox.toFront();
    }
    ///////////////////////////////////////////////////ADD MORE BUTTONS////////////////////////////////////////////////

    @FXML
    protected void workAddMore(ActionEvent event){
        addMoreFlag = 8;
        openForm(event,980,1000);
    }
    @FXML
    protected void publicationAddMore(ActionEvent event){
        addMoreFlag = 10;
        openForm(event,980,700);
    }
    @FXML
    protected void attendedAddMore(ActionEvent event){
        addMoreFlag = 11;
        openForm(event,980,700);
    }
    @FXML
    protected void organizedAddMore(ActionEvent event){
        addMoreFlag = 12;
        openForm(event,980,700);
    }
    @FXML
    protected void interactionAddMore(ActionEvent event){
        addMoreFlag = 13;
        openForm(event,980,700);
    }
    @FXML
    protected void fundedAddMore(ActionEvent event){
        addMoreFlag = 14;
        openForm(event,980,700);
    }
    @FXML
    protected void guestAddMore(ActionEvent event){
        addMoreFlag = 15;
        openForm(event,980,700);
    }

    ///////////////////////////////////////////////////////Edit Button////////////////////////////////////////////////
    private int personalEditFlag = 1;
    private int educationalEditFlag = 1;
    private int sppuEditFlag = 1;
    @FXML
    private void personalEdit(ActionEvent event){
        if(personalEditFlag == 1){
            personalAnchorPane.setDisable(false);
            personalEditFlag = 0;
        }
        else{
            personalAnchorPane.setDisable(true);
            personalEditFlag = 1;
        }
    }
    @FXML
    private void educationalEdit(ActionEvent event){
        if(educationalEditFlag == 1){
            educationalAnchorPane.setDisable(false);
            educationalEditFlag = 0;
        }
        else{
            educationalAnchorPane.setDisable(true);
            educationalEditFlag = 1;
        }
    }
    @FXML
    private void sppuEdit(ActionEvent event){
        if(sppuEditFlag == 1){
            sppuAnchorPane.setDisable(false);
            sppuEditFlag = 0;
        }
        else{
            sppuAnchorPane.setDisable(true);
            sppuEditFlag = 1;
        }
    }

    private void openForm(ActionEvent event,int v,int v1){
        Stage primaryStage = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXML/AddMore.fxml"));
            primaryStage.setTitle("Faculty IMS");
            primaryStage.setScene(new Scene(root, v, v1));
            primaryStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
