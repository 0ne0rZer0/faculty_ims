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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import static sample.Main.jdbcClass;


public class AdminRetrieve extends RetrivalForms implements Initializable {
    /**/
    @FXML private Label PersonalMotherName;
    @FXML private Label PersonalDateOfBirth;
    @FXML private Label personalEmail;
    @FXML private Label personalEmail2;
    @FXML private Label PersonalPAN;
    @FXML private Label PersonalAadhar;
    @FXML private Label PersonalAddress;
    @FXML private Label PersonalGender;

    @FXML private Label stesCurrentDesignation;
    @FXML private Label stesTypeOfAppointment;
    @FXML private Label stesDateOfAppointmentOfCD;
    @FXML private Label SppuDateOfApprovalOfCD;
    @FXML private Label stesAppointmentOrderReferenceNo;
    @FXML private Label stesCurrentApprovalCategory;
    @FXML private Label stesApprovalReferenceNo;

    @FXML private TableView eduTable;
    @FXML private TableColumn<Column,String > eduDegree;
    @FXML private TableColumn<Column,String > eduSpecialization;
    @FXML private TableColumn<Column,String > eduClass;
    @FXML private TableColumn<Column,String > eduUniversity;
    @FXML private TableColumn<Column,String > eduPercentage;
    @FXML private TableColumn<Column,String > eduYearOfPassing;
    @FXML private TableColumn<Column,String > eduCompletionStatus;

    @FXML private TableView eduPHDTable;
    @FXML private TableColumn<Column,String > phdDomain;
    @FXML private TableColumn<Column,String > phdSpecialization;
    @FXML private TableColumn<Column,String > phdUniversity;
    @FXML private TableColumn<Column,String > phdYearOfAddmission;
    @FXML private TableColumn<Column,String > phdYearOfPassing;
    @FXML private TableColumn<Column,String > phdCompletionStatus;

    @FXML private TableView educationalWorkingTable;
    @FXML private TableColumn<Column,String > eduRFieldColumn;
    @FXML private TableColumn<Column,String > eduRNameColumn;
    @FXML private TableColumn<Column,String > eduRPositionColumn;
    @FXML private TableColumn<Column,String > eduRDateColumn;
    @FXML private TableColumn<Column,String > eduRExperience;

    @FXML
    private Label personalName;
    @FXML private Label PersonalMobileNumber;
    @FXML private Label SPPUPGTeacherApprovalDate;
    @FXML private Label SPPUPhdTeacherApprovalDate;
    @FXML private Label SPPUPGTeacherApprovalReferenceNumber;
    @FXML private Label SPPUPhdTeacherApprovalReferenceNumber;
    @FXML private Label currentDesignation;
    @FXML private Label previousAppointment;
    @FXML private Label stesDateOfJoiningSCOE;

    static int flagUpdate = 0;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        jdbcClass.getConnection();
        showPersonalData();
        showSTESDetails();

        eduDegree.setCellValueFactory(new PropertyValueFactory<>("firstColumn"));
        eduSpecialization.setCellValueFactory(new PropertyValueFactory<>("secondColumn"));
        eduClass.setCellValueFactory(new PropertyValueFactory<>("thirdColumn"));
        eduUniversity.setCellValueFactory(new PropertyValueFactory<>("fourthColumn"));
        eduYearOfPassing.setCellValueFactory(new PropertyValueFactory<>("fifthColumn"));
        eduPercentage.setCellValueFactory(new PropertyValueFactory<>("sixthColumn"));
        eduCompletionStatus.setCellValueFactory(new PropertyValueFactory<>("sevenColumn"));

        phdDomain.setCellValueFactory(new PropertyValueFactory<>("firstColumn"));
        phdSpecialization.setCellValueFactory(new PropertyValueFactory<>("secondColumn"));
        phdUniversity.setCellValueFactory(new PropertyValueFactory<>("thirdColumn"));
        phdYearOfAddmission.setCellValueFactory(new PropertyValueFactory<>("fourthColumn"));
        phdYearOfPassing.setCellValueFactory(new PropertyValueFactory<>("fifthColumn"));
        phdCompletionStatus.setCellValueFactory(new PropertyValueFactory<>("sixthColumn"));

        eduRFieldColumn.setCellValueFactory(new PropertyValueFactory<>("firstColumn"));
        eduRNameColumn.setCellValueFactory(new PropertyValueFactory<>("secondColumn"));
        eduRPositionColumn.setCellValueFactory(new PropertyValueFactory<>("thirdColumn"));
        eduRDateColumn.setCellValueFactory(new PropertyValueFactory<>("fourthColumn"));
        eduRExperience.setCellValueFactory(new PropertyValueFactory<>("fifthColumn"));

        publicationRTableTypeColumn.setCellValueFactory(new PropertyValueFactory<>("firstColumn"));
        publicationRTableTitleColumn.setCellValueFactory(new PropertyValueFactory<>("secondColumn"));
        publicationRTableDateColumn.setCellValueFactory(new PropertyValueFactory<>("thirdColumn"));
        publicationRTableYearColumn.setCellValueFactory(new PropertyValueFactory<>("fourthColumn"));

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

        interactionRTableParticulars.setCellValueFactory(new PropertyValueFactory<>("firstColumn"));
        interactionRTableRole.setCellValueFactory(new PropertyValueFactory<>("secondColumn"));
        interactionRTableDate.setCellValueFactory(new PropertyValueFactory<>("thirdColumn"));
        interactionRTableYear.setCellValueFactory(new PropertyValueFactory<>("fourthColumn"));

        tableGenerate(2,eduTable);
        tableGenerate(3,eduPHDTable);
        tableGenerate(4,educationalWorkingTable);
        tableGenerate(5,publicationRetriveTable);
        tableGenerate(6,attendedRetriveTable);
        tableGenerate(7,organizedRetriveTable);
        tableGenerate(9,interactionRetriveTable);
        tableGenerate(8,fundedRetriveTable);
        tableGenerate(10,guestRetriveTable);
    }
    void tableGenerate(int flag,TableView tableName){
        try {
            tableName.setItems(getColumn(flag));
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
                    AdminRetrivalFormsPane adminRetrivalFormsPane = new AdminRetrivalFormsPane();
                    /*Accourding to flag particular method is called of FORM */
                    switch (flag){
                        case 4:
                            System.out.println("Working table Generate");
                            //adminRetrivalFormsPane.generateData(flag,firstColumn);
                            break;
                        case 5:
                            System.out.println("Publication table Generate");
                            adminRetrivalFormsPane.generateData(flag,firstColumn);
                            break;
                        case 6:
                            System.out.println("Attended table Generate");
                            adminRetrivalFormsPane.generateData(flag,firstColumn);
                            break;
                        case 7:
                            System.out.println("Organized table Generate");
                            adminRetrivalFormsPane.generateData(flag,firstColumn);
                            break;
                        case 8:
                            System.out.println("Funded table Generate");
                            adminRetrivalFormsPane.generateData(flag,firstColumn);
                            break;
                        case 9:
                            System.out.println("Interaction table Generate");
                            adminRetrivalFormsPane.generateData(flag,firstColumn);
                            break;
                        case 10:
                            System.out.println("Guest table Generate");
                            adminRetrivalFormsPane.generateData(flag,firstColumn);
                            break;
                    }
                }
            });
            return row;
        });
    }
    //In this Method Query will be Fired According to Flag
    public ObservableList<Column> getColumn(int flag) throws SQLException {
        ObservableList<Column> columns = FXCollections.observableArrayList();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        String query = "";
        switch (flag) {
            case 2:
                query = "select Degree,Specialization,Class,UniversityName,Percentage,MonthYear,CompletionStatus from educationalugpg where  RegistrationNo = '" + AdminMain.RegistrationNo + "'";
                break;
            case 3:
                query = "select Domain,Specialization,UniversityName,YearAdmission,YearCompletion,Status from educationalphd where  RegistrationNo = '" + AdminMain.RegistrationNo + "'";
                break;
            case 4:
                query = "select Field,NameOfInstitute,Position,DateOfJoining,TIMESTAMPDIFF(YEAR,DateOfJoining,DateOfLeaving) from experiencebeforejoiningstes where  RegistrationNo = '" + AdminMain.RegistrationNo + "'";
                break;
            case 5:
                query = "select TitleOfPaper,PublicationType,FromDateofConferencePublication,AcademicYear from publication where  RegistrationNo = '" + AdminMain.RegistrationNo + "'";
                break;
            case 6:
                query = "select TitleOfProgramme,TypeOfProgramme,DateFrom,AcademicYear from attended where RegistrationNo = '" +AdminMain.RegistrationNo+ "'";
                break;
            case 7:
                query = "select TitleOfProgramme,TypeOfProgramme,DateFrom,AcademicYear from organized where  RegistrationNo = '" +AdminMain.RegistrationNo + "'";
                break;
            case 8:
                query = "select TitleofResearchProduct,SponsoringAgency,FromDate,AcademicYear from fundedresearchproduct where  RegistrationNo = '" +AdminMain.RegistrationNo + "'";
                 break;
            case 9:
                query = "select Particulars , RoleofFaculty ,ProgramDate,AcademicYear from interaction where RegistrationNo = '" + AdminMain.RegistrationNo + "'";
                break;
            case 10:
                query = "select Topics,NameOfResourcePerson,DateOfConduction,AcademicYear from guestlecture where RegistrationNo = '" + AdminMain.RegistrationNo+ "'";
                break;
        }
        preparedStatement = jdbcClass.connection.prepareStatement(query);
        resultSet = jdbcClass.fireQuery(preparedStatement);
        while(resultSet.next()) {
            if(flag == 2)
                columns.add(new Column(resultSet.getString(1), resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getDate(6).toString(),resultSet.getString(7)));
            else if(flag == 3)
                columns.add(new Column(resultSet.getString(1), resultSet.getString(2),resultSet.getString(3),resultSet.getDate(4).toString(),resultSet.getDate(5).toString(),resultSet.getString(6)));
            else if(flag == 4)
                columns.add(new Column(resultSet.getString(1), resultSet.getString(2),resultSet.getString(3),resultSet.getDate(4).toString(),resultSet.getLong(5)));
            else
                columns.add(new Column(resultSet.getString(1), resultSet.getString(2),resultSet.getDate(3).toString(),resultSet.getString(4)));
        }
        return columns;
    }
    private void showPersonalData() {
            PreparedStatement personal;
            ResultSet personalResultSet;
            String personalQuery = "select * from personal where  RegistrationNo = '"+AdminMain.RegistrationNo+"'";
            String bankQuery = "select * from bankdetails where  RegistrationNo = '"+AdminMain.RegistrationNo+"'";
            try {
                personal = jdbcClass.connection.prepareStatement(personalQuery);
                personalResultSet = jdbcClass.fireQuery(personal);
                while(personalResultSet.next()) {
                    personalName.setText("Prof " + personalResultSet.getString(2) + " " + personalResultSet.getString(3) + " " + personalResultSet.getString(4));
                    PersonalGender.setText(personalResultSet.getString(5));
                    PersonalDateOfBirth.setText(String.valueOf(LocalDate.parse(personalResultSet.getString(6))));
                    PersonalMotherName.setText(personalResultSet.getString(7));
                    PersonalMobileNumber.setText(personalResultSet.getString(11));
                    personalEmail.setText(personalResultSet.getString(12));
                    personalEmail2.setText(personalResultSet.getString(12));
                    PersonalAddress.setText(personalResultSet.getString(13));
                }
                personal = jdbcClass.connection.prepareStatement(bankQuery);
                personalResultSet = jdbcClass.fireQuery(personal);
                while (personalResultSet.next()){
                    PersonalPAN.setText(personalResultSet.getString(3));
                    PersonalAadhar.setText(personalResultSet.getString(2));

                }
            } catch(SQLException e) {
                e.printStackTrace();
            }
    }
    private void showSTESDetails() {
           PreparedStatement stes;
           ResultSet stesResultSet;
           String stesQuery = "select * from stes where RegistrationNo = '" +AdminMain.RegistrationNo+ "'";
           String sppuQuery = "select * from sppu where RegistrationNo = '" +AdminMain.RegistrationNo+ "'";
           try {
               stes = jdbcClass.connection.prepareStatement(stesQuery);
               stesResultSet = jdbcClass.fireQuery(stes);
               while(stesResultSet.next()) {
                   stesTypeOfAppointment.setText(stesResultSet.getString(3));
                   stesDateOfAppointmentOfCD.setText(stesResultSet.getString(4));
                   stesAppointmentOrderReferenceNo.setText(stesResultSet.getString(5));
                   stesCurrentApprovalCategory.setText(stesResultSet.getString(6));
                   previousAppointment.setText(stesResultSet.getString(7));
                   SppuDateOfApprovalOfCD.setText(stesResultSet.getDate(8).toString());
                   stesApprovalReferenceNo.setText(stesResultSet.getString(9));
                   stesDateOfJoiningSCOE.setText(stesResultSet.getDate(10).toString());
               }
               stes = jdbcClass.connection.prepareStatement(sppuQuery);
               stesResultSet = jdbcClass.fireQuery(stes);
               while(stesResultSet.next()) {
                   if(stesResultSet.getString(2).equals("PG")) {
                       SPPUPGTeacherApprovalDate.setText(stesResultSet.getString(3));
                       SPPUPGTeacherApprovalReferenceNumber.setText(stesResultSet.getString(4));
                   }
                   if(stesResultSet.getString(2).equals("PHD")){
                       SPPUPhdTeacherApprovalDate.setText(stesResultSet.getString(3));
                       SPPUPhdTeacherApprovalReferenceNumber.setText(stesResultSet.getString(4));
                   }
                        stesCurrentDesignation.setText(stesResultSet.getString(5));
                        currentDesignation.setText(stesResultSet.getString(5));
               }
           } catch (SQLException e) {
               e.printStackTrace();
           }
    }
    @FXML
    private void updateData(ActionEvent event){
        flagUpdate = 1;
        Stage primaryStage = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXML/RetrivalForms.fxml"));
            primaryStage.setTitle("Faculty IMS");
            primaryStage.setScene(new Scene(root, 1280, 700));
            primaryStage.show();
            ((Node)event.getSource()).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void generatePDF(ActionEvent event){
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Save File");
        FileChooser fileChooser = new FileChooser();
        //Show save file dialog
        File file = fileChooser.showSaveDialog(primaryStage);
        try {
            String path = tokenizePath(file.toString());
            PDF.pdfConverter(AdminMain.RegistrationNo, path + ".pdf");
        }catch (NullPointerException e){
            System.out.println("Path is Not Selected");
        }
    }
    public String tokenizePath(String path){
        String actualPath = new String("");
        String delim = "\\\\\\\\";
        StringTokenizer token = new StringTokenizer(path,"\\");
        do {
            actualPath += token.nextToken();
            if(token.hasMoreTokens())
                actualPath += delim;
        }
        while(token.hasMoreTokens());
        System.out.println(actualPath);
        return path;
    }
}
