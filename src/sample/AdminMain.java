package sample;

import com.jfoenix.controls.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.Year;
import java.util.*;

import static sample.Main.jdbcClass;

public class AdminMain implements Initializable {
    //Personal Fields Declared Here
    @FXML
    private JFXCheckBox personalSelectAll;
    @FXML
    private JFXCheckBox personalDob;
    @FXML
    private JFXCheckBox personalMomName;
    @FXML
    private JFXCheckBox personalCategory;
    @FXML
    private JFXCheckBox personalReligion;
    @FXML
    private JFXCheckBox personalCaste;
    @FXML
    private JFXCheckBox personalEmail;
    @FXML
    private JFXCheckBox personalMobNo;
    @FXML
    private JFXCheckBox personalAddress;
    @FXML
    private JFXDrawer personalDrawer;
    @FXML
    private JFXHamburger personalHamburger;
    @FXML
    private JFXDrawer educationalDrawer;
    @FXML
    private JFXHamburger educationalHamburger;

    //Attended Fields Declared here
    @FXML
    private JFXDrawer attendedDrawer;
    @FXML
    private JFXCheckBox attendedSelectAll;
    @FXML
    private JFXCheckBox attendedTypeOfProgramme;
    @FXML
    private JFXCheckBox attendedTitleOfProgramme;
    @FXML
    private JFXCheckBox attendedLevelOfProgramme;
    @FXML
    private JFXCheckBox attendedDuration;
    @FXML
    private JFXCheckBox attendedVenue;
    @FXML
    private JFXCheckBox attendedNameOfFundingAgency;
    @FXML
    private JFXCheckBox attendedFundsReceived;
    @FXML
    private JFXCheckBox attendedNameOfApprovingAgency;
    @FXML
    private JFXHamburger attendedHamburger;

    //Organized Fields Decalartion
    @FXML
    private JFXHamburger organizedHamburger;
    @FXML
    private JFXCheckBox organizedSelectAll;
    @FXML
    private JFXDrawer organizedDrawer;
    @FXML
    private JFXCheckBox organizedTypeOfProgramme;
    @FXML
    private JFXCheckBox organizedTitleOfProgramme;
    @FXML
    private JFXCheckBox organizedLevelOfProgramme;
    @FXML
    private JFXCheckBox organizedDuration;
    @FXML
    private JFXCheckBox organizedNoOfParticipants;
    @FXML
    private JFXCheckBox organizedTargetAudience;
    @FXML
    private JFXCheckBox organizedNameOfResourcePerson;
    @FXML
    private JFXCheckBox organizedContactNumber;
    @FXML
    private JFXCheckBox organizedEmailId;

    //Interaction Fields Declared Here
    @FXML
    private JFXHamburger interactionHamburger;
    @FXML
    private JFXDrawer interactionDrawer;
    @FXML
    private JFXCheckBox interactionSelectAll;
    @FXML
    private JFXCheckBox interactionRoleOfFaculty;
    @FXML
    private JFXCheckBox interactionParticulars;
    @FXML
    private JFXCheckBox interactionTargetAudiance;
    @FXML
    private JFXCheckBox interactionNoofParticipants;
    @FXML
    private JFXCheckBox interactionProgramDate;
    @FXML
    private JFXCheckBox interactionVenue;
    @FXML
    private JFXCheckBox interactionNoOfDays;

    //Publication fields Declaration

    @FXML
    private JFXHamburger publicationalHamburger;
    @FXML
    private JFXDrawer publicationalDrawer;
    @FXML
    private JFXCheckBox publicationSelectAll;
    @FXML
    private JFXCheckBox publicationAuthorsName;
    @FXML
    private JFXCheckBox publicationCorrespondanceAuthor;
    @FXML
    private JFXCheckBox publicationType;
    @FXML
    private JFXCheckBox publicationTitleOfPaper;
    @FXML
    private JFXCheckBox publicationNameOfJournalConference;
    @FXML
    private JFXCheckBox publicationNameOfPublisherOrganizer;
    @FXML
    private JFXCheckBox publicationLink;
    @FXML
    private JFXCheckBox publicationVenue;
    @FXML
    private JFXCheckBox publicationISSNISBNDOI;
    @FXML
    private JFXCheckBox publicationVolumeAndIssueNumber;
    @FXML
    private JFXCheckBox publicationMonthAndYearOfJournalPublication;
    @FXML
    private JFXCheckBox publicationDuration;
    @FXML
    private JFXCheckBox publicationImpactFactor;
    @FXML
    private JFXCheckBox publicationCitiationCount;
    @FXML
    private JFXCheckBox publicationScopusIndex;
    @FXML
    private JFXCheckBox publicationSNIP;
    @FXML
    private JFXCheckBox publicationSJRRank;
    @FXML
    private JFXCheckBox publicationIndexedBy;
    @FXML
    private JFXCheckBox publicationFundsRecieved;
    @FXML
    private JFXCheckBox publicationNameOfFundingAgency;

    //Funded fields Declaration
    @FXML
    private JFXHamburger fundedHamburger;
    @FXML
    private JFXDrawer fundedDrawer;
    @FXML
    private JFXCheckBox fundedSelectAll;
    @FXML
    private JFXCheckBox fundedRole;
    @FXML
    private JFXCheckBox fundedInCollaborationWith;
    @FXML
    private JFXCheckBox fundedTitleofResearchProduct;
    @FXML
    private JFXCheckBox fundedSponsoringAgency;
    @FXML
    private JFXCheckBox fundedStatus;
    @FXML
    private JFXCheckBox fundedSanctionedAmount;
    @FXML
    private JFXCheckBox fundedReceivedAmount;
    @FXML
    private JFXCheckBox fundedUtilizedAmount;
    @FXML
    private JFXCheckBox fundedOutcome;
    @FXML
    private JFXCheckBox fundedFromDate;


    //Guest fields Declaration
    @FXML
    private JFXHamburger guestHamburger;
    @FXML
    private JFXDrawer guestDrawer;
    @FXML
    private JFXCheckBox guestSelectAll;
    @FXML
    private JFXCheckBox guestTopics;
    @FXML
    private JFXCheckBox guestNameOfResourcePerson;
    @FXML
    private JFXCheckBox guestDesignationOfResourcePerson;
    @FXML
    private JFXCheckBox guestResourcePersonOrganization;
    @FXML
    private JFXCheckBox guestResourcePersonMobileNo;
    @FXML
    private JFXCheckBox guestResourcePersonEmailId;
    @FXML
    private JFXCheckBox guestTargetAudience;
    @FXML
    private JFXCheckBox guestNumberOfParticipants;
    @FXML
    private JFXCheckBox guestRemuneration;
    @FXML
    private JFXCheckBox guestDateOfConduction;

    @FXML
    private JFXHamburger sppuHamburger;
    @FXML
    private JFXDrawer sppuDrawer;
    @FXML
    private JFXHamburger workingHamburger;
    @FXML
    private JFXDrawer workingDrawer;
    @FXML
    private JFXCheckBox workFieldTeaching;
    @FXML
    private JFXCheckBox workExperience;
    @FXML
    private JFXCheckBox workFieldIndustrial;
    @FXML
    private JFXCheckBox workFieldBoth;
    @FXML
    private JFXCheckBox edudegreePG;
    @FXML
    private JFXCheckBox edudegreeUG;
    @FXML
    private JFXCheckBox edudegreeBoth;
    @FXML
    private JFXCheckBox sppuDomainPG;
    @FXML
    private JFXCheckBox sppuDomainPHD;
    @FXML
    private JFXCheckBox sppuDomainBoth;

    @FXML
    private JFXCheckBox eudcationSelectAll;
    @FXML
    private JFXCheckBox eduDegree;
    @FXML
    private JFXCheckBox eduSpecialization;
    @FXML
    private JFXCheckBox eduUniversityName;
    @FXML
    private JFXCheckBox eduPercentage;
    @FXML
    private JFXCheckBox eduClass;
    @FXML
    private JFXCheckBox eduMonthYear;
    @FXML
    private JFXCheckBox eduCompletionStatus;
    @FXML
    private JFXCheckBox eduPhdDomain;
    @FXML
    private JFXCheckBox eduPhdSpecialization;
    @FXML
    private JFXCheckBox eduPhdUniversityName;
    @FXML
    private JFXCheckBox eduPhdStatus;
    @FXML
    private JFXCheckBox eduPhdYearCompletion;
    @FXML
    private JFXCheckBox sppuApprovalCategory;
    @FXML
    private JFXCheckBox sppuDateOfApproval;
    @FXML
    private JFXCheckBox sppuReferenceNo;
    @FXML
    private JFXCheckBox sppuCurrentDesignation;
    @FXML
    private JFXCheckBox STESJoiningDate;
    @FXML
    private JFXCheckBox SPPUEmployeeNo;
    @FXML
    private JFXCheckBox SPPUDateAppointmentCurrentDesignation;
    @FXML
    private JFXCheckBox SPPUAppointmentOrderReferenceNumber;
    @FXML
    private JFXCheckBox SPPUTypesOfAppointment;
    @FXML
    private JFXCheckBox SPPUCurrentApprovalCategory;
    @FXML
    private JFXCheckBox SPPUDateOfApprovalOfCurrentDesignation;
    //select facultymain.FacultyName,attended.AcademicYear,count(AcademicYear) from facultymain INNER join attended on facultymain.RegistrationNo = attended.RegistrationNo where attended.AcademicYear = '2014-2015' group by attended.AcademicYear, facultymain.FacultyName;
    @FXML
    private JFXCheckBox SPPUApprovalReferenceNumber;
    @FXML
    private JFXCheckBox workSelectAll;
    @FXML
    private HBox workingFieldHbox;
    @FXML
    private JFXCheckBox workNameOfInstitute;
    @FXML
    private JFXCheckBox workPosition;
    @FXML
    private JFXCheckBox workDateOfJoining;
    @FXML
    private JFXCheckBox workDateOfLeaving;
    @FXML
    private JFXCheckBox FacultyName;
    @FXML
    private JFXCheckBox UserName;
    @FXML
    private JFXCheckBox DepartmentName;
    @FXML
    private JFXCheckBox DepartmentID;
    @FXML
    private JFXCheckBox Status;
    @FXML
    private JFXCheckBox sppuSelectAll;
    @FXML
    private JFXCheckBox SPPUPreviousAppointment;
    //Common
    @FXML
    private Label departmentName;
    @FXML
    private Label departmentName2;
    @FXML
    public JFXButton callFun;
    @FXML
    private VBox selectFormVbox;
    @FXML
    private Pane reportPane;
    @FXML
    private Pane professorPane;

    @FXML
    private Pane dashboardPane;
    @FXML
    private VBox dashboardVbox;
    @FXML
    private Button adminDashboardButton;
    @FXML
    private Button adminReportButton;
    @FXML
    private Button adminLogOutButton;
    @FXML
    public TextField searchBox;
    @FXML
    public ListView<String> searchList;
    @FXML
    public TableView commonTable;
    @FXML
    public TableColumn<Column, String> facultyName;
    @FXML
    public TableColumn<Column, String> department;
    @FXML
    public TableColumn<Column, String> tableEmail;
    @FXML
    public TableColumn<Column, String> status;
    @FXML
    public TableColumn<Column, String> currentDesignation;
    @FXML
    public JFXComboBox<String> academicYearFrom;
    @FXML
    public JFXComboBox<String> academicYearTo;
    @FXML
    private JFXCheckBox attendedAcademicYear;
    @FXML
    private JFXCheckBox organizedAcademicYear;
    @FXML
    private JFXCheckBox fundedAcademicYear;
    @FXML
    private JFXCheckBox publicationAcademicYear;
    @FXML
    private JFXCheckBox interactionAcademicYear;
    @FXML
    private JFXCheckBox guestAcademicYear;

    //SUMMARY
    @FXML
    private JFXCheckBox publicationCheck;
    @FXML
    private JFXCheckBox attendedCheck;
    @FXML
    private JFXCheckBox organizedCheck;
    @FXML
    private JFXCheckBox fundedCheck;
    @FXML
    private JFXCheckBox interactionCheck;
    @FXML
    private JFXCheckBox guestCheck;
    @FXML
    private Pane summaryPane;
    @FXML
    private JFXButton adminSummaryButton;
    @FXML
    public JFXComboBox<String> academicYearFromSummary;
    @FXML
    public JFXComboBox<String> academicYearToSummary;

    static ArrayList<String> facultylist = new ArrayList<String>();
    static ArrayList<String> personallist = new ArrayList<String>();
    static ArrayList<String> educationallist = new ArrayList<String>();
    static ArrayList<String> educationalPHDlist = new ArrayList<String>();
    static ArrayList<String> sppulist = new ArrayList<String>();
    static ArrayList<String> guestlist = new ArrayList<String>();
    static ArrayList<String> workinglist = new ArrayList<String>();
    static ArrayList<String> steslist = new ArrayList<String>();
    static ArrayList<String> fundedlist = new ArrayList<String>();
    static ArrayList<String> publicationlist = new ArrayList<String>();
    static ArrayList<String> attendedlist = new ArrayList<String>();
    static ArrayList<String> organizedlist = new ArrayList<String>();
    static ArrayList<String> interactionlist = new ArrayList<String>();

    private ObservableList<String> entries = FXCollections.observableArrayList();
    private ObservableList<String>  academicYearComboBox = FXCollections.observableArrayList();

    static String selectedItem = null;
    static int RegistrationNo = 0;
    static int summaryFlag = 0;
    static String eduDomain = null;
    static String sppuDomain = null;
    static String workField = null;
    static String academicYearF = null;
    static String academicYearT = null;
    static String academicYearFSummary = null;
    static String academicYearTSummary = null;

    //References for publication

    boolean personalSelectAllButtoninitiallySelected = false;
    boolean attendedSelectAllButtoninitiallySelected = false;
    boolean organizedSelectAllButtoninitiallySelected = false;
    boolean interactionSelectAllButtoninitiallySelected = false;
    boolean educationalSelectAllButtoninitiallySelected = false;
    boolean sppuSelectAllButtoninitiallySelected = false;
    boolean guestSelectAllButtoninitiallySelected = false;
    boolean fundedSelectAllButtoninitiallySelected = false;
    boolean publicationSelectAllButtoninitiallySelected = false;
    boolean workingSelectAllButtoninitiallySelected = false;
    private static String thirdColumn = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            jdbcClass.getConnection();
            dashboardPane.toFront();
            departmentName.setText("Department : "+Login.Department+"");
            departmentName2.setText(Login.Department);
            professorPane.toFront();
            searchBox();
            List<String> academicYearList = new ArrayList<>();
            for(int i = Year.now().getValue(); i >= 1995 ; i--) {
                academicYearList.add(""+i+"");
             }
            academicYearList.add(0,"None");
            academicYearComboBox.setAll(academicYearList);
            academicYearFrom.setItems(academicYearComboBox);
            academicYearTo.setItems(academicYearComboBox);
            academicYearFromSummary.setItems(academicYearComboBox);
            academicYearToSummary.setItems(academicYearComboBox);
             try {
                initializeTable();
                contextMenu();
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }

            //table
            facultyName.setCellValueFactory(new PropertyValueFactory<>("firstColumn"));
            department.setCellValueFactory(new PropertyValueFactory<>("secondColumn"));
            tableEmail.setCellValueFactory(new PropertyValueFactory<>("thirdColumn"));
            currentDesignation.setCellValueFactory(new PropertyValueFactory<>("fourthColumn"));
            status.setCellValueFactory(new PropertyValueFactory<>("fifthColumn"));

    }
    /////////////////////////////////////////////Dynamic Selection/////////////////////////////////////////////////////
    public void getGeneralCheckboxData() {
        //Personal Drawer
        if (personalDrawer.isOpened()) {
            if (!personalSelectAllButtoninitiallySelected && personalSelectAll.isSelected()) {
                personalSelectAllButtoninitiallySelected = true;
                personalDob.setSelected(true);
                personalDob.setDisable(true);
                personalMomName.setSelected(true);
                personalMomName.setDisable(true);
                personalCategory.setSelected(true);
                personalCategory.setDisable(true);
                personalReligion.setSelected(true);
                personalReligion.setDisable(true);
                personalCaste.setSelected(true);
                personalCaste.setDisable(true);
                personalMobNo.setSelected(true);
                personalMobNo.setDisable(true);
                personalEmail.setSelected(true);
                personalEmail.setDisable(true);
                personalAddress.setSelected(true);
                personalAddress.setDisable(true);
            }
            if (personalSelectAllButtoninitiallySelected && !personalSelectAll.isSelected()) {
                personalSelectAllButtoninitiallySelected = false;
                personalDob.setSelected(false);
                personalDob.setDisable(false);
                personalMomName.setSelected(false);
                personalMomName.setDisable(false);
                personalCategory.setSelected(false);
                personalCategory.setDisable(false);
                personalReligion.setSelected(false);
                personalReligion.setDisable(false);
                personalCaste.setSelected(false);
                personalCaste.setDisable(false);
                personalMobNo.setSelected(false);
                personalMobNo.setDisable(false);
                personalEmail.setSelected(false);
                personalEmail.setDisable(false);
                personalAddress.setSelected(false);
                personalAddress.setDisable(false);
            }
        }
        //educational drawer
        if (educationalDrawer.isOpened()) {
            if (!educationalSelectAllButtoninitiallySelected && eudcationSelectAll.isSelected()) {
                educationalSelectAllButtoninitiallySelected = true;
                eduClass.setSelected(true);
                eduCompletionStatus.setSelected(true);
                eduDegree.setSelected(true);
                //edudegreeBoth.setSelected(true);
                //edudegreePG.setSelected(true);
                eduUniversityName.setSelected(true);
                eduSpecialization.setSelected(true);
                eduPercentage.setSelected(true);
                eduMonthYear.setSelected(true);

                eduPhdStatus.setSelected(true);
                eduPhdDomain.setSelected(true);
                eduPhdYearCompletion.setSelected(true);
                eduPhdSpecialization.setSelected(true);
                eduPhdUniversityName.setSelected(true);

                eduPhdStatus.setDisable(true);
                eduPhdDomain.setDisable(true);
                eduPhdYearCompletion.setDisable(true);
                eduPhdSpecialization.setDisable(true);
                eduPhdUniversityName.setDisable(true);

                eduClass.setDisable(true);
                eduCompletionStatus.setDisable(true);
                eduDegree.setDisable(true);
                //edudegreeBoth.setDisable(true);
                //edudegreePG.setDisable(true);
                eduUniversityName.setDisable(true);
                eduSpecialization.setDisable(true);
                eduPercentage.setDisable(true);
                eduMonthYear.setDisable(true);

            }
            if (educationalSelectAllButtoninitiallySelected && !eudcationSelectAll.isSelected()) {
                educationalSelectAllButtoninitiallySelected = false;
                eduClass.setSelected(false);
                eduCompletionStatus.setSelected(false);
                eduDegree.setSelected(false);
                //edudegreeBoth.setSelected(false);
                //edudegreePG.setSelected(false);
                eduUniversityName.setSelected(false);
                eduSpecialization.setSelected(false);
                eduPercentage.setSelected(false);
                eduMonthYear.setSelected(false);

                eduClass.setDisable(false);
                eduCompletionStatus.setDisable(false);
                eduDegree.setDisable(false);
                //edudegreeBoth.setDisable(false);
                //edudegreePG.setDisable(false);
                eduUniversityName.setDisable(false);
                eduSpecialization.setDisable(false);
                eduPercentage.setDisable(false);
                eduMonthYear.setDisable(false);

                eduPhdStatus.setSelected(false);
                eduPhdDomain.setSelected(false);
                eduPhdYearCompletion.setSelected(false);
                eduPhdSpecialization.setSelected(false);
                eduPhdUniversityName.setSelected(false);

                eduPhdStatus.setDisable(false);
                eduPhdYearCompletion.setDisable(false);
                eduPhdDomain.setDisable(false);
                eduPhdSpecialization.setDisable(false);
                eduPhdUniversityName.setDisable(false);

            }
        }
        if(workingDrawer.isOpened()){
            if(!workingSelectAllButtoninitiallySelected && workSelectAll.isSelected()){
                workingSelectAllButtoninitiallySelected = true;
                workNameOfInstitute.setSelected(true);
                workPosition.setSelected(true);
                workDateOfJoining.setSelected(true);
                workDateOfLeaving.setSelected(true);
                workExperience.setSelected(true);
                workNameOfInstitute.setDisable(true);
                workPosition.setDisable(true);
                workDateOfLeaving.setDisable(true);
                workDateOfJoining.setDisable(true);
                workExperience.setDisable(true);
            }
            if(workingSelectAllButtoninitiallySelected && !workSelectAll.isSelected()){
                workingSelectAllButtoninitiallySelected = false;
                workNameOfInstitute.setSelected(false);
                workPosition.setSelected(false);
                workDateOfJoining.setSelected(false);
                workDateOfLeaving.setSelected(false);
                workExperience.setSelected(false);

                workNameOfInstitute.setDisable(false);
                workPosition.setDisable(false);
                workDateOfLeaving.setDisable(false);
                workDateOfJoining.setDisable(false);
                workExperience.setDisable(false);
            }
        }
        //sppu drawer
        if (sppuDrawer.isOpened()) {
            if (!sppuSelectAllButtoninitiallySelected) {
                //-------------------------------setSelected----------------------------------------------//
                sppuApprovalCategory.setSelected(sppuSelectAll.isSelected());
                sppuDateOfApproval.setSelected(sppuSelectAll.isSelected());
                sppuReferenceNo.setSelected(sppuSelectAll.isSelected());
                sppuCurrentDesignation.setSelected(sppuSelectAll.isSelected());

                // STES
                SPPUEmployeeNo.setSelected(sppuSelectAll.isSelected());
                SPPUTypesOfAppointment.setSelected(sppuSelectAll.isSelected());
                SPPUDateAppointmentCurrentDesignation.setSelected(sppuSelectAll.isSelected());
                SPPUDateAppointmentCurrentDesignation.setSelected(sppuSelectAll.isSelected());
                SPPUPreviousAppointment.setSelected(sppuSelectAll.isSelected());
                SPPUDateOfApprovalOfCurrentDesignation.setSelected(sppuSelectAll.isSelected());
                SPPUApprovalReferenceNumber.setSelected(sppuSelectAll.isSelected());
                STESJoiningDate.setSelected(sppuSelectAll.isSelected());
                SPPUCurrentApprovalCategory.setSelected(sppuSelectAll.isSelected());
                SPPUAppointmentOrderReferenceNumber.setSelected(sppuSelectAll.isSelected());

                //-------------------------------setDisable----------------------------------------------//
                sppuApprovalCategory.setDisable(sppuSelectAll.isSelected());
                sppuDateOfApproval.setDisable(sppuSelectAll.isSelected());
                sppuReferenceNo.setDisable(sppuSelectAll.isSelected());
                sppuCurrentDesignation.setDisable(sppuSelectAll.isSelected());

                // STES
                SPPUEmployeeNo.setDisable(sppuSelectAll.isSelected());
                SPPUTypesOfAppointment.setDisable(sppuSelectAll.isSelected());
                SPPUDateAppointmentCurrentDesignation.setDisable(sppuSelectAll.isSelected());
                SPPUDateAppointmentCurrentDesignation.setDisable(sppuSelectAll.isSelected());
                SPPUPreviousAppointment.setDisable(sppuSelectAll.isSelected());
                SPPUDateOfApprovalOfCurrentDesignation.setDisable(sppuSelectAll.isSelected());
                SPPUApprovalReferenceNumber.setDisable(sppuSelectAll.isSelected());
                STESJoiningDate.setDisable(sppuSelectAll.isSelected());
                SPPUCurrentApprovalCategory.setDisable(sppuSelectAll.isSelected());
                SPPUAppointmentOrderReferenceNumber.setDisable(sppuSelectAll.isSelected());

            }
        }
        //Attended Drawer
        if (attendedDrawer.isOpened()) {
            if (!attendedSelectAllButtoninitiallySelected && attendedSelectAll.isSelected()) {
                attendedSelectAllButtoninitiallySelected = true;

                attendedTypeOfProgramme.setSelected(true);
                attendedTypeOfProgramme.setDisable(true);

                attendedTitleOfProgramme.setSelected(true);
                attendedTitleOfProgramme.setDisable(true);

                attendedLevelOfProgramme.setSelected(true);
                attendedLevelOfProgramme.setDisable(true);

                attendedDuration.setSelected(true);
                attendedDuration.setDisable(true);

                attendedVenue.setSelected(true);
                attendedVenue.setDisable(true);

                attendedNameOfFundingAgency.setSelected(true);
                attendedNameOfFundingAgency.setDisable(true);

                attendedFundsReceived.setSelected(true);
                attendedFundsReceived.setDisable(true);

                attendedNameOfApprovingAgency.setSelected(true);
                attendedNameOfApprovingAgency.setDisable(true);

                attendedAcademicYear.setSelected(true);
                attendedAcademicYear.setDisable(true);
            }

            if (attendedSelectAllButtoninitiallySelected && !attendedSelectAll.isSelected()) {
                attendedSelectAllButtoninitiallySelected = false;
                attendedTypeOfProgramme.setDisable(false);
                attendedTypeOfProgramme.setSelected(false);
                attendedTitleOfProgramme.setDisable(false);
                attendedTitleOfProgramme.setSelected(false);
                attendedLevelOfProgramme.setDisable(false);
                attendedLevelOfProgramme.setSelected(false);
                attendedDuration.setDisable(false);
                attendedDuration.setSelected(false);
                attendedVenue.setDisable(false);
                attendedVenue.setSelected(false);
                attendedNameOfFundingAgency.setDisable(false);
                attendedNameOfFundingAgency.setSelected(false);
                attendedFundsReceived.setDisable(false);
                attendedFundsReceived.setSelected(false);
                attendedNameOfApprovingAgency.setDisable(false);
                attendedNameOfApprovingAgency.setSelected(false);
                attendedAcademicYear.setSelected(false);
                attendedAcademicYear.setDisable(false);
            }

        }
        //Organized Drawer
        if (organizedDrawer.isOpened()) {
            if (!organizedSelectAllButtoninitiallySelected && organizedSelectAll.isSelected()) {
                organizedSelectAllButtoninitiallySelected = true;
                organizedTypeOfProgramme.setDisable(true);
                organizedTitleOfProgramme.setDisable(true);
                organizedLevelOfProgramme.setDisable(true);
                organizedDuration.setDisable(true);
                organizedNoOfParticipants.setDisable(true);
                organizedTargetAudience.setDisable(true);
                organizedNameOfResourcePerson.setDisable(true);
                organizedContactNumber.setDisable(true);
                organizedEmailId.setDisable(true);
                organizedAcademicYear.setDisable(true);

                organizedTypeOfProgramme.setSelected(true);
                organizedTitleOfProgramme.setSelected(true);
                organizedLevelOfProgramme.setSelected(true);
                organizedDuration.setSelected(true);
                organizedNoOfParticipants.setSelected(true);
                organizedTargetAudience.setSelected(true);
                organizedNameOfResourcePerson.setSelected(true);
                organizedContactNumber.setSelected(true);
                organizedEmailId.setSelected(true);
                organizedAcademicYear.setSelected(true);
            }

            if (organizedSelectAllButtoninitiallySelected && !organizedSelectAll.isSelected()) {
                organizedSelectAllButtoninitiallySelected = false;

                organizedTypeOfProgramme.setDisable(false);
                organizedTitleOfProgramme.setDisable(false);
                organizedLevelOfProgramme.setDisable(false);
                organizedDuration.setDisable(false);
                organizedNoOfParticipants.setDisable(false);
                organizedTargetAudience.setDisable(false);
                organizedNameOfResourcePerson.setDisable(false);
                organizedContactNumber.setDisable(false);
                organizedEmailId.setDisable(false);
                organizedAcademicYear.setDisable(false);

                organizedTypeOfProgramme.setSelected(false);
                organizedTitleOfProgramme.setSelected(false);
                organizedLevelOfProgramme.setSelected(false);
                organizedDuration.setSelected(false);
                organizedNoOfParticipants.setSelected(false);
                organizedTargetAudience.setSelected(false);
                organizedNameOfResourcePerson.setSelected(false);
                organizedContactNumber.setSelected(false);
                organizedEmailId.setSelected(false);
                organizedAcademicYear.setSelected(false);
            }
        }
        //Interaction Drawer
        if (interactionDrawer.isOpened()) {
            if (!interactionSelectAllButtoninitiallySelected && interactionSelectAll.isSelected()) {
                interactionSelectAllButtoninitiallySelected = true;

                interactionRoleOfFaculty.setSelected(true);
                interactionParticulars.setSelected(true);
                interactionTargetAudiance.setSelected(true);
                interactionNoofParticipants.setSelected(true);
                interactionProgramDate.setSelected(true);
                interactionVenue.setSelected(true);
                interactionNoOfDays.setSelected(true);
                interactionAcademicYear.setSelected(true);

                interactionRoleOfFaculty.setDisable(true);
                interactionParticulars.setDisable(true);
                interactionTargetAudiance.setDisable(true);
                interactionNoofParticipants.setDisable(true);
                interactionProgramDate.setDisable(true);
                interactionVenue.setDisable(true);
                interactionNoOfDays.setDisable(true);
                interactionAcademicYear.setDisable(true);

            }
            if (interactionSelectAllButtoninitiallySelected && !interactionSelectAll.isSelected()) {
                interactionSelectAllButtoninitiallySelected = false;

                interactionRoleOfFaculty.setSelected(false);
                interactionParticulars.setSelected(false);
                interactionTargetAudiance.setSelected(false);
                interactionNoofParticipants.setSelected(false);
                interactionProgramDate.setSelected(false);
                interactionVenue.setSelected(false);
                interactionNoOfDays.setSelected(false);
                interactionAcademicYear.setDisable(false);
                interactionAcademicYear.setSelected(false);

                interactionRoleOfFaculty.setDisable(false);
                interactionParticulars.setDisable(false);
                interactionTargetAudiance.setDisable(false);
                interactionNoofParticipants.setDisable(false);
                interactionProgramDate.setDisable(false);
                interactionVenue.setDisable(false);
                interactionNoOfDays.setDisable(false);
            }
        }
        //Publication Drawer
        if (publicationalDrawer.isOpened()) {
            if (!publicationSelectAllButtoninitiallySelected && publicationSelectAll.isSelected()) {
                publicationSelectAllButtoninitiallySelected = true;

                publicationAuthorsName.setSelected(true);
                publicationCorrespondanceAuthor.setSelected(true);
                publicationType.setSelected(true);
                publicationTitleOfPaper.setSelected(true);
                publicationNameOfJournalConference.setSelected(true);
                publicationNameOfPublisherOrganizer.setSelected(true);
                publicationLink.setSelected(true);
                publicationVenue.setSelected(true);
                publicationISSNISBNDOI.setSelected(true);
                publicationVolumeAndIssueNumber.setSelected(true);
                publicationMonthAndYearOfJournalPublication.setSelected(true);
                publicationDuration.setSelected(true);
                publicationImpactFactor.setSelected(true);
                publicationCitiationCount.setSelected(true);
                publicationScopusIndex.setSelected(true);
                publicationSNIP.setSelected(true);
                publicationSJRRank.setSelected(true);
                publicationIndexedBy.setSelected(true);
                publicationFundsRecieved.setSelected(true);
                publicationNameOfFundingAgency.setSelected(true);
                publicationAcademicYear.setSelected(true);
                publicationAcademicYear.setDisable(true);

                publicationAuthorsName.setDisable(true);
                publicationCorrespondanceAuthor.setDisable(true);
                publicationType.setDisable(true);
                publicationTitleOfPaper.setDisable(true);
                publicationNameOfJournalConference.setDisable(true);
                publicationNameOfPublisherOrganizer.setDisable(true);
                publicationLink.setDisable(true);
                publicationVenue.setDisable(true);
                publicationISSNISBNDOI.setDisable(true);
                publicationVolumeAndIssueNumber.setDisable(true);
                publicationMonthAndYearOfJournalPublication.setDisable(true);
                publicationDuration.setDisable(true);
                publicationImpactFactor.setDisable(true);
                publicationCitiationCount.setDisable(true);
                publicationScopusIndex.setDisable(true);
                publicationSNIP.setDisable(true);
                publicationSJRRank.setDisable(true);
                publicationIndexedBy.setDisable(true);
                publicationFundsRecieved.setDisable(true);
                publicationNameOfFundingAgency.setDisable(true);

            }
            if (publicationSelectAllButtoninitiallySelected && !publicationSelectAll.isSelected()) {
                publicationSelectAllButtoninitiallySelected = false;

                publicationAuthorsName.setSelected(false);
                publicationCorrespondanceAuthor.setSelected(false);
                publicationType.setSelected(false);
                publicationTitleOfPaper.setSelected(false);
                publicationNameOfJournalConference.setSelected(false);
                publicationNameOfPublisherOrganizer.setSelected(false);
                publicationLink.setSelected(false);
                publicationVenue.setSelected(false);
                publicationISSNISBNDOI.setSelected(false);
                publicationVolumeAndIssueNumber.setSelected(false);
                publicationMonthAndYearOfJournalPublication.setSelected(false);
                publicationDuration.setSelected(false);
                publicationImpactFactor.setSelected(false);
                publicationCitiationCount.setSelected(false);
                publicationScopusIndex.setSelected(false);
                publicationSNIP.setSelected(false);
                publicationSJRRank.setSelected(false);
                publicationIndexedBy.setSelected(false);
                publicationFundsRecieved.setSelected(false);
                publicationNameOfFundingAgency.setSelected(false);
                publicationAcademicYear.setSelected(false);
                publicationAcademicYear.setDisable(false);

                publicationAuthorsName.setDisable(false);
                publicationCorrespondanceAuthor.setDisable(false);
                publicationType.setDisable(false);
                publicationTitleOfPaper.setDisable(false);
                publicationNameOfJournalConference.setDisable(false);
                publicationNameOfPublisherOrganizer.setDisable(false);
                publicationLink.setDisable(false);
                publicationVenue.setDisable(false);
                publicationISSNISBNDOI.setDisable(false);
                publicationVolumeAndIssueNumber.setDisable(false);
                publicationMonthAndYearOfJournalPublication.setDisable(false);
                publicationDuration.setDisable(false);
                publicationImpactFactor.setDisable(false);
                publicationCitiationCount.setDisable(false);
                publicationScopusIndex.setDisable(false);
                publicationSNIP.setDisable(false);
                publicationSJRRank.setDisable(false);
                publicationIndexedBy.setDisable(false);
                publicationFundsRecieved.setDisable(false);
                publicationNameOfFundingAgency.setDisable(false);

            }
        }
        //Funded Drawer
        if (fundedDrawer.isOpened()) {
            if (!fundedSelectAllButtoninitiallySelected && fundedSelectAll.isSelected()) {
                fundedSelectAllButtoninitiallySelected = true;

                fundedRole.setSelected(true);
                fundedInCollaborationWith.setSelected(true);
                fundedTitleofResearchProduct.setSelected(true);
                fundedSponsoringAgency.setSelected(true);
                fundedStatus.setSelected(true);
                fundedSanctionedAmount.setSelected(true);
                fundedReceivedAmount.setSelected(true);
                fundedUtilizedAmount.setSelected(true);
                fundedOutcome.setSelected(true);
                fundedFromDate.setSelected(true);

                fundedRole.setDisable(true);
                fundedInCollaborationWith.setDisable(true);
                fundedTitleofResearchProduct.setDisable(true);
                fundedSponsoringAgency.setDisable(true);
                fundedStatus.setDisable(true);
                fundedSanctionedAmount.setDisable(true);
                fundedReceivedAmount.setDisable(true);
                fundedUtilizedAmount.setDisable(true);
                fundedOutcome.setDisable(true);
                fundedFromDate.setDisable(true);
                fundedAcademicYear.setDisable(true);
                fundedAcademicYear.setSelected(true);
            }
            if (fundedSelectAllButtoninitiallySelected && !fundedSelectAll.isSelected()) {
                fundedSelectAllButtoninitiallySelected = false;

                fundedRole.setSelected(false);
                fundedInCollaborationWith.setSelected(false);
                fundedTitleofResearchProduct.setSelected(false);
                fundedSponsoringAgency.setSelected(false);
                fundedStatus.setSelected(false);
                fundedSanctionedAmount.setSelected(false);
                fundedReceivedAmount.setSelected(false);
                fundedUtilizedAmount.setSelected(false);
                fundedOutcome.setSelected(false);
                fundedFromDate.setSelected(false);
                fundedAcademicYear.setDisable(false);
                fundedAcademicYear.setSelected(false);

                fundedRole.setDisable(false);
                fundedInCollaborationWith.setDisable(false);
                fundedTitleofResearchProduct.setDisable(false);
                fundedSponsoringAgency.setDisable(false);
                fundedStatus.setDisable(false);
                fundedSanctionedAmount.setDisable(false);
                fundedReceivedAmount.setDisable(false);
                fundedUtilizedAmount.setDisable(false);
                fundedOutcome.setDisable(false);
                fundedFromDate.setDisable(false);
            }
        }
        //Guest Drawer
        if (guestDrawer.isOpened()) {
            if (!guestSelectAllButtoninitiallySelected && guestSelectAll.isSelected()) {
                guestSelectAllButtoninitiallySelected = true;

                guestTopics.setSelected(true);
                guestNameOfResourcePerson.setSelected(true);
                guestDesignationOfResourcePerson.setSelected(true);
                guestResourcePersonOrganization.setSelected(true);
                guestResourcePersonMobileNo.setSelected(true);
                guestResourcePersonEmailId.setSelected(true);
                guestTargetAudience.setSelected(true);
                guestNumberOfParticipants.setSelected(true);
                guestRemuneration.setSelected(true);
                guestDateOfConduction.setSelected(true);
                guestAcademicYear.setSelected(true);
                guestAcademicYear.setDisable(true);

                guestTopics.setDisable(true);
                guestNameOfResourcePerson.setDisable(true);
                guestDesignationOfResourcePerson.setDisable(true);
                guestResourcePersonOrganization.setDisable(true);
                guestResourcePersonMobileNo.setDisable(true);
                guestResourcePersonEmailId.setDisable(true);
                guestTargetAudience.setDisable(true);
                guestNumberOfParticipants.setDisable(true);
                guestRemuneration.setDisable(true);
                guestDateOfConduction.setDisable(true);

            }
            if (guestSelectAllButtoninitiallySelected && !guestSelectAll.isSelected()) {
                guestSelectAllButtoninitiallySelected = false;

                guestTopics.setSelected(false);
                guestNameOfResourcePerson.setSelected(false);
                guestDesignationOfResourcePerson.setSelected(false);
                guestResourcePersonOrganization.setSelected(false);
                guestResourcePersonMobileNo.setSelected(false);
                guestResourcePersonEmailId.setSelected(false);
                guestTargetAudience.setSelected(false);
                guestNumberOfParticipants.setSelected(false);
                guestRemuneration.setSelected(false);
                guestDateOfConduction.setSelected(false);

                guestTopics.setDisable(false);
                guestNameOfResourcePerson.setDisable(false);
                guestDesignationOfResourcePerson.setDisable(false);
                guestResourcePersonOrganization.setDisable(false);
                guestResourcePersonMobileNo.setDisable(false);
                guestResourcePersonEmailId.setDisable(false);
                guestTargetAudience.setDisable(false);
                guestNumberOfParticipants.setDisable(false);
                guestRemuneration.setDisable(false);
                guestDateOfConduction.setDisable(false);
                guestAcademicYear.setSelected(false);
                guestAcademicYear.setDisable(false);
            }
        }
    }
    public void getPersonalFields() {
        personallist.clear();
        if (personalSelectAll.isSelected()) {
            personallist.add("DOB");
            personallist.add("MotherName");
            personallist.add("Category");
            personallist.add("Religion");
            personallist.add("Caste");
            personallist.add("PhoneNo");
            personallist.add("Email");
            personallist.add("Address");
        } else {
            if (personalDob.isSelected())
                personallist.add("DOB");
            if (personalMomName.isSelected())
                personallist.add("MotherName");
            if (personalCategory.isSelected())
                personallist.add("Category");
            if (personalReligion.isSelected())
                personallist.add("Religion");
            if (personalCaste.isSelected())
                personallist.add("Caste");
            if (personalMobNo.isSelected())
                personallist.add("PhoneNo");
            if (personalEmail.isSelected())
                personallist.add("Email");
            if (personalAddress.isSelected())
                personallist.add("Address");
        }
    }
    public void getSPPUFields() {
        sppulist.clear();
        steslist.clear();
        if(sppuSelectAll.isSelected()) {
            sppulist.add("sppu.ApprovalCategory");
            sppulist.add("sppu.DateOfApproval");
            sppulist.add("sppu.ReferenceNo");
            sppulist.add("sppu.CurrentDesignation");

            steslist.add("stes.EmployeeNo");
            steslist.add("stes.TypesOfAppointment");
            steslist.add("stes.DateAppointmentCurrentDesignation");
            steslist.add("stes.AppointmentOrderReferenceNumber");
            steslist.add("stes.SPPUCurrentApprovalCategory");
            steslist.add("stes.PreviousAppointment");
            steslist.add("stes.SPPUDateOfApprovalOfCurrentDesignation");
            steslist.add("stes.SPPUApprovalReferenceNumber");
            steslist.add("stes.STESJoiningDate");
        } else {
            if(sppuApprovalCategory.isSelected())
                sppulist.add("sppu.ApprovalCategory");
            if(sppuDateOfApproval.isSelected())
                sppulist.add("sppu.DateOfApproval");
            if(sppuReferenceNo.isSelected())
                sppulist.add("sppu.ReferenceNo");
            if(sppuCurrentDesignation.isSelected())
                sppulist.add("sppu.CurrentDesignation");


            if(SPPUEmployeeNo.isSelected())
                steslist.add("stes.EmployeeNo");
            if(SPPUTypesOfAppointment.isSelected())
                steslist.add("stes.TypesOfAppointment");
            if(SPPUDateAppointmentCurrentDesignation.isSelected())
                steslist.add("stes.DateAppointmentCurrentDesignation");
            if(SPPUAppointmentOrderReferenceNumber.isSelected())
                steslist.add("stes.AppointmentOrderReferenceNumber");
            if(SPPUCurrentApprovalCategory.isSelected())
                steslist.add("stes.SPPUCurrentApprovalCategory");
            if(SPPUPreviousAppointment.isSelected())
                steslist.add("stes.PreviousAppointment");
            if(SPPUDateOfApprovalOfCurrentDesignation.isSelected())
                steslist.add("stes.SPPUDateOfApprovalOfCurrentDesignation");
            if(SPPUApprovalReferenceNumber.isSelected())
                steslist.add("stes.SPPUApprovalReferenceNumber");
            if(STESJoiningDate.isSelected())
                steslist.add("stes.STESJoiningDate");
        }
    }
    public void getEducationalFields() {
        educationallist.clear();
        educationalPHDlist.clear();
        if(eudcationSelectAll.isSelected()) {
            educationallist.add("educationalugpg.GraduationType");
            educationallist.add("educationalugpg.Degree");
            educationallist.add("educationalugpg.Specialization");
            educationallist.add("educationalugpg.UniversityName");
            educationallist.add("educationalugpg.Class");
            educationallist.add("educationalugpg.Percentage");
            educationallist.add("educationalugpg.MonthYear");
            educationallist.add("educationalugpg.CompletionStatus");

            //PHD list
            educationalPHDlist.add("educationalphd.Domain");
            educationalPHDlist.add("educationalphd.Specialization");
            educationalPHDlist.add("educationalphd.UniversityName");
            educationalPHDlist.add("educationalphd.Status");
            educationalPHDlist.add("educationalphd.YearAdmission");
            educationalPHDlist.add("educationalphd.YearCompletion");
        }
        else {
            if(eduMonthYear.isSelected())
                educationallist.add("educationalugpg.MonthYear");
            if(eduPercentage.isSelected())
                educationallist.add("educationalugpg.Percentage");
            if(eduSpecialization.isSelected())
                educationallist.add("educationalugpg.Specialization");
            if(eduUniversityName.isSelected())
                educationallist.add("educationalugpg.UniversityName");
            if(eduClass.isSelected())
                educationallist.add("educationalugpg.Class");
            if(edudegreePG.isSelected())
                educationallist.add("educationalugpg.GraduationType");
            if(edudegreeBoth.isSelected())
                educationallist.add("educationalugpg.GraduationType");
            if(eduCompletionStatus.isSelected())
                educationallist.add("educationalugpg.CompletionStatus");
            if(eduDegree.isSelected())
                educationallist.add("educationalugpg.Degree");
            if(edudegreeUG.isSelected())
                educationallist.add("educationalugpg.GraduationType");

            //PHD LIST
            if(eduPhdDomain.isSelected())
                educationalPHDlist.add("educationalphd.Domain");
            if(eduPhdSpecialization.isSelected())
                educationalPHDlist.add("educationalphd.Specialization");
            if(eduPhdUniversityName.isSelected())
                educationalPHDlist.add("educationalphd.UniversityName");
            if(eduPhdStatus.isSelected())
                educationalPHDlist.add("educationalphd.Status");
            if(eduPhdYearCompletion.isSelected())
                educationalPHDlist.add("educationalphd.YearCompletion");
        }
    }
    public void getWorkingFields(){
        workinglist.clear();
        if(workSelectAll.isSelected()){
            workinglist.add("experiencebeforejoiningstes.Field");
            workinglist.add("experiencebeforejoiningstes.NameOfInstitute");
            workinglist.add("experiencebeforejoiningstes.Position");
            workinglist.add("experiencebeforejoiningstes.DateOfJoining");
            workinglist.add("experiencebeforejoiningstes.DateOfLeaving");
            workinglist.add("TIMESTAMPDIFF(YEAR,DateOfJoining,DateOfLeaving) As "+"Experience"+"");
        }
        else{
            if(workFieldTeaching.isSelected() || workFieldIndustrial.isSelected() || workFieldBoth.isSelected())
                workinglist.add("experiencebeforejoiningstes.Field");
            if(workNameOfInstitute.isSelected())
                workinglist.add("experiencebeforejoiningstes.NameOfInstitute");
            if(workPosition.isSelected())
                workinglist.add("experiencebeforejoiningstes.Position");
            if(workDateOfJoining.isSelected())
                workinglist.add("experiencebeforejoiningstes.DateOfJoining");
            if(workDateOfLeaving.isSelected())
                workinglist.add("experiencebeforejoiningstes.DateOfLeaving");
            if(workExperience.isSelected())
                workinglist.add("TIMESTAMPDIFF(YEAR,DateOfJoining,DateOfLeaving) As "+"Experience(In Years)"+"");
        }
    }
    public void getAttendedFields() {
        attendedlist.clear();
        if (attendedSelectAll.isSelected()) {
            attendedlist.add("attended.TypeOfProgramme");
            attendedlist.add("attended.TitleOfProgramme");
            attendedlist.add("attended.LevelOfProgramme");
            attendedlist.add("attended.Duration");
            attendedlist.add("attended.Venue");
            attendedlist.add("attended.NameOfFundingAgency");
            attendedlist.add("attended.FundsReceived");
            attendedlist.add("attended.NameofApprovingAgency");
            attendedlist.add("attended.AcademicYear");
        } else {
            if (attendedTypeOfProgramme.isSelected())
                attendedlist.add("attended.TypeOfProgramme");
            if (attendedTitleOfProgramme.isSelected())
                attendedlist.add("attended.TitleOfProgramme");
            if (attendedLevelOfProgramme.isSelected())
                attendedlist.add("attended.LevelOfProgramme");
            if (attendedDuration.isSelected())
                attendedlist.add("attended.Duration");
            if (attendedVenue.isSelected())
                attendedlist.add("attended.Venue");
            if (attendedNameOfFundingAgency.isSelected())
                attendedlist.add("attended.NameOfFundingAgency");
            if (attendedFundsReceived.isSelected())
                attendedlist.add("attended.FundsReceived");
            if (attendedNameOfApprovingAgency.isSelected())
                attendedlist.add("attended.NameofApprovingAgency");
            if(attendedAcademicYear.isSelected())
                attendedlist.add("attended.AcademicYear");
        }
    }
    public void getOrganizedFields() {
        organizedlist.clear();
        if (organizedSelectAll.isSelected()) {
            organizedlist.add("organized.TypeOfProgramme");
            organizedlist.add("organized.TitleOfProgramme");
            organizedlist.add("organized.LevelOfProgramme");
            organizedlist.add("organized.Duration");
            organizedlist.add("organized.NoOfParticipants");
            organizedlist.add("organized.TargetAudience");
            organizedlist.add("organized.NameOfResourcePerson");
            organizedlist.add("organized.ContactNumber");
            organizedlist.add("organized.EmailId");
            organizedlist.add("organized.AcademicYear");

        } else {
            if (organizedTypeOfProgramme.isSelected())
                organizedlist.add("organized.TypeOfProgramme");
            if (organizedTitleOfProgramme.isSelected())
                organizedlist.add("organized.TitleOfProgramme");
            if (organizedLevelOfProgramme.isSelected())
                organizedlist.add("organized.LevelOfProgramme");
            if (organizedDuration.isSelected())
                organizedlist.add("organized.Duration");
            if (organizedNoOfParticipants.isSelected())
                organizedlist.add("organized.NoOfParticipants");
            if (organizedTargetAudience.isSelected())
                organizedlist.add("organized.TargetAudience");
            if (organizedNameOfResourcePerson.isSelected())
                organizedlist.add("organized.NameOfResourcePerson");
            if (organizedContactNumber.isSelected())
                organizedlist.add("organized.ContactNumber");
            if (organizedEmailId.isSelected())
                organizedlist.add("organized.EmailId");
            if(organizedAcademicYear.isSelected())
                organizedlist.add("organized.AcademicYear");
        }
    }
    public void getInteractionFields() {
        interactionlist.clear();
        if (interactionSelectAll.isSelected()) {
            interactionlist.add("interaction.RoleofFaculty");
            interactionlist.add("interaction.Particulars");
            interactionlist.add("interaction.TargetAudiance");
            interactionlist.add("interaction.NoofParticipants");
            interactionlist.add("interaction.ProgramDate");
            interactionlist.add("interaction.Venue");
            interactionlist.add("interaction.NoofDays");
            interactionlist.add("interaction.AcademicYear");
        } else {

            if (interactionRoleOfFaculty.isSelected())
                interactionlist.add("interaction.RoleofFaculty");
            if (interactionParticulars.isSelected())
                interactionlist.add("interaction.Particulars");
            if (interactionTargetAudiance.isSelected())
                interactionlist.add("interaction.TargetAudiance");
            if (interactionNoofParticipants.isSelected())
                interactionlist.add("interaction.NoofParticipants");
            if (interactionProgramDate.isSelected())
                interactionlist.add("interaction.ProgramDate");
            if (interactionVenue.isSelected())
                interactionlist.add("interaction.Venue");
            if (interactionNoOfDays.isSelected())
                interactionlist.add("interaction.NoofDays");
            if(interactionAcademicYear.isSelected())
                interactionlist.add("interaction.AcademicYear");
        }
    }
    public void getPublicationFields() {
        publicationlist.clear();
        if (publicationSelectAll.isSelected()) {

            publicationlist.add("publication.AuthorsName");
            publicationlist.add("publication.CorrespondanceAuthor");
            publicationlist.add("publication.PublicationType");
            publicationlist.add("publication.TitleOfPaper");
            publicationlist.add("publication.NameOfJournalConference");
            publicationlist.add("publication.NameOfPublisherOrganizer");
            publicationlist.add("publication.Link");
            publicationlist.add("publication.Venue");
            publicationlist.add("publication.ISSNISBNDOI");
            publicationlist.add("publication.VolumeAndIssueNumber");
            publicationlist.add("publication.MonthAndYearOfJournalPublication");
            publicationlist.add("TIMESTAMPDIFF(DAY,FromDateOfConferencePublication,ToDateOfConferencePublication) As "+"Duration"+"");
            publicationlist.add("publication.ImpactFactor");
            publicationlist.add("publication.CitiationCount");
            publicationlist.add("publication.ScopusIndex");
            publicationlist.add("publication.SNIP");
            publicationlist.add("publication.SJRRank");
            publicationlist.add("publication.IndexedBy");
            publicationlist.add("publication.FundsRecieved");
            publicationlist.add("publication.NameOfFundingAgency");
            publicationlist.add("publication.AcademicYear");

        } else {

            if (publicationAuthorsName.isSelected())
                publicationlist.add("publication.AuthorsName");
            if ( publicationCorrespondanceAuthor.isSelected())
                publicationlist.add("publication.CorrespondanceAuthor");
            if ( publicationType.isSelected())
                publicationlist.add("publication.PublicationType");
            if ( publicationTitleOfPaper.isSelected())
                publicationlist.add("publication.TitleOfPaper");
            if ( publicationNameOfJournalConference.isSelected())
                publicationlist.add("publication.NameOfJournalConference");
            if ( publicationNameOfPublisherOrganizer.isSelected())
                publicationlist.add("publication.NameOfPublisherOrganizer");
            if ( publicationLink.isSelected())
                publicationlist.add("publication.Link");
            if ( publicationVenue.isSelected())
                publicationlist.add("publication.Venue");
            if ( publicationISSNISBNDOI.isSelected())
                publicationlist.add("publication.ISSNISBNDOI");
            if ( publicationVolumeAndIssueNumber.isSelected())
                publicationlist.add("publication.VolumeAndIssueNumber");
            if ( publicationMonthAndYearOfJournalPublication.isSelected())
                publicationlist.add("publication.MonthAndYearOfJournalPublication");
            if ( publicationDuration.isSelected())
                publicationlist.add("TIMESTAMPDIFF(DAY,FromDateOfConferencePublication,ToDateOfConferencePublication)  As "+"Duration"+"");
            if ( publicationImpactFactor.isSelected())
                publicationlist.add("publication.ImpactFactor");
            if ( publicationCitiationCount.isSelected())
                publicationlist.add("publication.CitiationCount");
            if ( publicationScopusIndex.isSelected())
                publicationlist.add("publication.ScopusIndex");
            if ( publicationSNIP.isSelected())
                publicationlist.add("publication.SNIP");
            if ( publicationSJRRank.isSelected())
                publicationlist.add("publication.SJRRank");
            if ( publicationIndexedBy.isSelected())
                publicationlist.add("publication.IndexedBy");
            if ( publicationFundsRecieved.isSelected())
                publicationlist.add("publication.FundsRecieved");
            if ( publicationNameOfFundingAgency.isSelected())
                publicationlist.add("publication.NameOfFundingAgency");
            if(publicationAcademicYear.isSelected())
                publicationlist.add("publication.AcademicYear");
        }
    }
    public void getFundedFields() {
        fundedlist.clear();
        if (fundedSelectAll.isSelected()) {

            fundedlist.add("fundedresearchproduct.Role");
            fundedlist.add("fundedresearchproduct.InCollaborationWith");
            fundedlist.add("fundedresearchproduct.TitleofResearchProduct");
            fundedlist.add("fundedresearchproduct.SponsoringAgency");
            fundedlist.add("fundedresearchproduct.Status");
            fundedlist.add("fundedresearchproduct.SanctionedAmount");
            fundedlist.add("fundedresearchproduct.ReceivedAmount");
            fundedlist.add("fundedresearchproduct.UtilizedAmount");
            fundedlist.add("fundedresearchproduct.Outcome");
            fundedlist.add("fundedresearchproduct.FromDate");
            fundedlist.add("fundedresearchproduct.AcedemicYear");
        } else {

            if (fundedRole.isSelected())
                fundedlist.add("fundedresearchproduct.Role");
            if (fundedInCollaborationWith.isSelected())
                fundedlist.add("fundedresearchproduct.InCollaborationWith");
            if (fundedTitleofResearchProduct.isSelected())
                fundedlist.add("fundedresearchproduct.TitleofResearchProduct");
            if (fundedSponsoringAgency.isSelected())
                fundedlist.add("fundedresearchproduct.SponsoringAgency");
            if (fundedStatus.isSelected())
                fundedlist.add("fundedresearchproduct.Status");
            if (fundedSanctionedAmount.isSelected())
                fundedlist.add("fundedresearchproduct.SanctionedAmount");
            if (fundedReceivedAmount.isSelected())
                fundedlist.add("fundedresearchproduct.ReceivedAmount");
            if (fundedUtilizedAmount.isSelected())
                fundedlist.add("fundedresearchproduct.UtilizedAmount");
            if (fundedOutcome.isSelected())
                fundedlist.add("fundedresearchproduct.Outcome");
            if (fundedFromDate.isSelected())
                fundedlist.add("fundedresearchproduct.FromDate");
            if(fundedAcademicYear.isSelected())
                fundedlist.add("fundedresearchproduct.AcedemicYear");
        }
    }
    public void getGuestFields() {
        guestlist.clear();
        if (guestSelectAll.isSelected()) {

            guestlist.add("guestlecture.Topics");
            guestlist.add("guestlecture.NameOfResourcePerson");
            guestlist.add("guestlecture.DesignationOfResourcePerson");
            guestlist.add("guestlecture.ResourcePersonOrganization");
            guestlist.add("guestlecture.ResourcePersonMobileNo");
            guestlist.add("guestlecture.ResourcePersonEmailId");
            guestlist.add("guestlecture.TargetAudience");
            guestlist.add("guestlecture.NumberOfParticipants");
            guestlist.add("guestlecture.Remuneration");
            guestlist.add("guestlecture.DateOfConduction");
            guestlist.add("guestlecture.AcademicYear");
        } else {

            if (guestTopics.isSelected())
                guestlist.add("guestlecture.Topics");
            if (guestNameOfResourcePerson.isSelected())
                guestlist.add("guestlecture.NameOfResourcePerson");
            if (guestDesignationOfResourcePerson.isSelected())
                guestlist.add("guestlecture.DesignationOfResourcePerson");
            if (guestResourcePersonOrganization.isSelected())
                guestlist.add("guestlecture.ResourcePersonOrganization");
            if (guestResourcePersonMobileNo.isSelected())
                guestlist.add("guestlecture.ResourcePersonMobileNo");
            if (guestResourcePersonEmailId.isSelected())
                guestlist.add("guestlecture.ResourcePersonEmailId");
            if (guestTargetAudience.isSelected())
                guestlist.add("guestlecture.TargetAudience");
            if (guestNumberOfParticipants.isSelected())
                guestlist.add("guestlecture.NumberOfParticipants");
            if (guestRemuneration.isSelected())
                guestlist.add("guestlecture.Remuneration");
            if (guestDateOfConduction.isSelected())
                guestlist.add("guestlecture.DateOfConduction");
            if(guestAcademicYear.isSelected())
                guestlist.add("guestlecture.AcademicYear");
        }
    }
    //After selection of fields
    @FXML
    private void submitReport(ActionEvent event) {
        academicYearF = null;
        academicYearT = null;
       if(academicYearFrom.getValue()!=null){
           academicYearF = academicYearFrom.getValue();
           academicYearT = academicYearTo.getValue();
       }
       if(academicYearFrom.getValue()=="None" && academicYearTo.getValue()=="None") {
            academicYearF = null;
            academicYearT = null;
        }
       facultylist.clear();
        if(FacultyName.isSelected())
            facultylist.add("facultymain.FacultyName");
        if(UserName.isSelected())
            facultylist.add("facultymain.UserName");
        if(DepartmentName.isSelected())
            facultylist.add("facultymain.DepartmentName");
        if(DepartmentID.isSelected())
            facultylist.add("facultymain.DepartmentID");
        if(Status.isSelected())
            facultylist.add("facultymain.Status");
        if(!facultylist.isEmpty()) {
            getPersonalFields();
            getEducationalFields();
            getSPPUFields();
            getWorkingFields();
            getAttendedFields();
            getOrganizedFields();
            getInteractionFields();
            getPublicationFields();
            getFundedFields();
            getGuestFields();
            Stage primaryStage = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("FXML/ReportTable.fxml"));
                primaryStage.setTitle("Faculty IMS");
                primaryStage.setScene(new Scene(root, 1100, 700));
                primaryStage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            JOptionPane.showMessageDialog(null, "Please Select At List One Field Name/Department");
        }
    }
    //////////////////////////////////////////////////Working Mutiple CheckBox Selection////////////////////////////////
    @FXML
    private void workingHboxSelectionteaching(MouseEvent event) {
        workField = "TeachingExperience";
        workFieldBoth.setSelected(false);
        workFieldIndustrial.setSelected(false);
    }
    @FXML
    private void workingHboxSelectionIndustrial(MouseEvent event) {
        workField = "IndustrialExperience";
        workFieldBoth.setSelected(false);
        workFieldTeaching.setSelected(false);
    }
    @FXML
    private void workingHboxSelectionBoth(MouseEvent event) {
        workField = "Both";
        workFieldTeaching.setSelected(false);
        workFieldIndustrial.setSelected(false);
    }
    /////////////////////////////////////////////////SPPU MULTIPLE CHECKBOX///////////////////////////////////////////
    @FXML
    private void SPPUHboxSelectionPG(MouseEvent event) {
        sppuDomain = "PG";
        sppuDomainPHD.setSelected(false);
        sppuDomainBoth.setSelected(false);
    }
    @FXML
    private void SPPUHboxSelectionPHD(MouseEvent event) {
        sppuDomain = "PHD";
        sppuDomainPG.setSelected(false);
        sppuDomainBoth.setSelected(false);
    }
    @FXML
    private void SPPUHboxSelectionBoth(MouseEvent event) {
        sppuDomain = "BOTH";
        sppuDomainPHD.setSelected(false);
        sppuDomainPG.setSelected(false);
    }
    /////////////////////////////////////////////////EDUCATIONAL MULTIPLE CHECKBOX/////////////////////////////////////
    @FXML
    private void eduHboxSelectionPG(MouseEvent event) {
        eduDomain = "PG";
        edudegreeUG.setSelected(false);
        edudegreeBoth.setSelected(false);
    }
    @FXML
    private void eduHboxSelectionUG(MouseEvent event) {
        eduDomain = "UG";
        edudegreePG.setSelected(false);
        edudegreeBoth.setSelected(false);
    }
    @FXML
    private void eduHboxSelectionBoth(MouseEvent event) {
        eduDomain = "BOTH";
        edudegreeUG.setSelected(false);
        edudegreePG.setSelected(false);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Table ContextMenu
    @FXML
    private void contextMenu() throws IOException {
        ContextMenu cm = new ContextMenu();
        MenuItem active = new MenuItem("Active");
        cm.getItems().add(active);
        MenuItem inactive = new MenuItem("Inactive");
        cm.getItems().add(inactive);
        commonTable.setRowFactory(tv -> {
            TableRow<Column> row = new TableRow<>();
            final String[] columnField = new String[4];
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    if (event.getButton() == MouseButton.SECONDARY) {
                        cm.show(commonTable, event.getScreenX(), event.getScreenY());
                    }
                    active.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            columnField[0] = row.getItem().getFirstColumn();
                            columnField[1] = row.getItem().getSecondColumn();
                            columnField[2] = row.getItem().getThirdColumn();
                            columnField[3] = "Active";
                            updateStatus(columnField);
                            try {
                                initializeTable();
                            } catch (SQLException | IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    inactive.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            columnField[0] = row.getItem().getFirstColumn();
                            columnField[1] = row.getItem().getSecondColumn();
                            columnField[2] = row.getItem().getThirdColumn();
                            columnField[3] = "Inactive";
                            updateStatus(columnField);
                            try {
                                initializeTable();
                            } catch (SQLException | IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
            return row;
        });
    }
    //Update Status for Table
    private void updateStatus(String[] columnField) {
        String updateStatus = "update facultyMain set status = '" + columnField[3] + "' where UserName = '" + columnField[2] + "'";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = jdbcClass.connection.prepareStatement(updateStatus);
            jdbcClass.fireQuery(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //common table initialization
    protected void initializeTable() throws SQLException, IOException {
        commonTable.setItems(getDataFromDatabase());
        commonTable.setRowFactory( tv -> {
            TableRow<Column> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Column rowData = row.getItem();
                    thirdColumn = rowData.getThirdColumn();
                    selectedItem = thirdColumn;
                    try {
                        openFxml();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
    }
    //get data for table
    private ObservableList<Column> getDataFromDatabase() throws SQLException {
        ObservableList<Column> columns = FXCollections.observableArrayList();
        ArrayList registrationNo = new ArrayList();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String dataQuery = "select f.FacultyName,f.DepartmentName,f.UserName,f.Status,sppu.CurrentDesignation from sppu INNER JOIN facultymain f on sppu.RegistrationNo = f.RegistrationNo where DepartmentName = '"+Login.Department+"'";
        if(Login.adminFlag==1)
            dataQuery = "select f.FacultyName,f.DepartmentName,f.UserName,f.Status,sppu.CurrentDesignation from sppu INNER JOIN facultymain f on sppu.RegistrationNo = f.RegistrationNo";
        preparedStatement = jdbcClass.connection.prepareStatement(dataQuery);
        resultSet = jdbcClass.fireQuery(preparedStatement);
        while (resultSet.next()) {
            columns.add(new Column(resultSet.getString("FacultyName"), resultSet.getString("DepartmentName"), resultSet.getString("UserName"),resultSet.getString("CurrentDesignation"), resultSet.getString("Status")));
        }
        return columns;
    }
    ////////////////////////////////////////////////////////SEARCH/////////////////////////////////////////////////////
    protected void searchBox() {
            searchList.setVisible(false);
            try {
                entries = setDataFromDatabaseForSearch();
                searchList.setItems(entries);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            searchList.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent click) {
                    if (click.getClickCount() == 2) {
                        //Use ListView's getSelected Item
                        selectedItem = searchList.getSelectionModel().getSelectedItem();
                        String[] target = selectedItem.split("                    ", 0);
                        selectedItem = target[1];
                        try {
                            openFxml();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }//clicked on search item or table row
    protected void openFxml () throws SQLException {
            String getRegistrationNo = "select RegistrationNo from facultyMain where UserName = '" + selectedItem + "'";
            PreparedStatement preparedStatement = jdbcClass.connection.prepareStatement(getRegistrationNo);
            ResultSet resultSet = jdbcClass.fireQuery(preparedStatement);
            while (resultSet.next()) {
                RegistrationNo = resultSet.getInt("RegistrationNo");
            }
            Stage primaryStage = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("FXML/AdminRetrive.fxml"));
                primaryStage.setTitle("Faculty IMS");
                primaryStage.setScene(new Scene(root, 1280, 700));
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //Search Data
        private ObservableList<String> setDataFromDatabaseForSearch () throws SQLException {
            PreparedStatement preparedStatement;
            String query = "select FacultyName,UserName from facultymain where DepartmentName = '" + Login.Department + "'";
            if(Login.adminFlag==1)
                query = "select FacultyName,UserName from facultymain";
            ResultSet resultSet;
            preparedStatement = jdbcClass.connection.prepareStatement(query);
            resultSet = jdbcClass.fireQuery(preparedStatement);
            while (resultSet.next()) {
                entries.add(resultSet.getString("facultyName") + "                    " + resultSet.getString("UserName"));
            }
            return entries;
        }
        public void search (KeyEvent event){
            searchList.setVisible(true);
            searchBox.textProperty().addListener(
                    new ChangeListener() {
                        public void changed(ObservableValue observable,
                                            Object oldVal, Object newVal) {
                            handleSearchByKey((String) oldVal, (String) newVal);
                        }
                    });
        }
        public void handleSearchByKey (String oldVal, String newVal){
            // If the number of characters in the text box is less than last time
            // it must be because the user pressed delete
            if (oldVal != null && (newVal.length() < oldVal.length())) {
                // Restore the lists original set of entries
                // and start from the beginning
                searchList.setItems(entries);
            }

            // Break out all of the parts of the search text
            // by splitting on white space
            String[] parts = newVal.toUpperCase().split(" ");

            // Filter out the entries that don't contain the entered text
            ObservableList<String> subentries = FXCollections.observableArrayList();
            for (Object entry : searchList.getItems()) {
                boolean match = true;
                String entryText = (String) entry;
                for (String part : parts) {
                    // The entry needs to contain all portions of the
                    // search string *but* in any order
                    if (!entryText.toUpperCase().contains(part)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    subentries.add(entryText);
                }
            }
            searchList.setItems(subentries);
        }
        @FXML
        public void listDisable (MouseEvent event){
            searchList.setVisible(false);
        }
        @FXML
        protected void paneChange (ActionEvent event){
            Button button = (Button) event.getSource();
            if (adminDashboardButton.equals(button)) {
                dashboardPane.toFront();
                professorPane.toFront();
            } else if (adminReportButton.equals(button)) {
                reportPane.toFront();
            } else if (adminLogOutButton.equals(button)) {
                Stage primaryStage = new Stage();
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("FXML/Login.fxml"));
                    primaryStage.setTitle("Faculty IMS");
                    primaryStage.setScene(new Scene(root, 640, 640));
                    primaryStage.show();
                    ((Node) event.getSource()).getScene().getWindow().hide();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if(adminSummaryButton.equals(button)){
                summaryPane.toFront();
            }
            dashboardVbox.toFront();
        }
        private void drawerClose () {
            if (personalDrawer.isOpened()) {
                personalDrawer.close();
                personalDrawer.setVisible(false);
                selectFormVbox.getChildren().remove(1);
            } else if (educationalDrawer.isOpened()) {
                educationalDrawer.close();
                educationalDrawer.setVisible(false);
                selectFormVbox.getChildren().remove(2);
            } else if (workingDrawer.isOpened()) {
                workingDrawer.close();
                workingDrawer.setVisible(false);
                selectFormVbox.getChildren().remove(3);
            } else if (sppuDrawer.isOpened()) {
                sppuDrawer.close();
                sppuDrawer.setVisible(false);
                selectFormVbox.getChildren().remove(4);
            } else if (publicationalDrawer.isOpened()) {
                publicationalDrawer.close();
                publicationalDrawer.setVisible(false);
                selectFormVbox.getChildren().remove(5);
            }else if (attendedDrawer.isOpened()) {
                attendedDrawer.close();
                attendedDrawer.setVisible(false);
                selectFormVbox.getChildren().remove(6);
            } else if (organizedDrawer.isOpened()) {
                organizedDrawer.close();
                organizedDrawer.setVisible(false);
                selectFormVbox.getChildren().remove(7);
            }  else if (interactionDrawer.isOpened()) {
                interactionDrawer.close();
                interactionDrawer.setVisible(false);
                selectFormVbox.getChildren().remove(8);
            } else if (fundedDrawer.isOpened()) {
                fundedDrawer.close();
                fundedDrawer.setVisible(false);
                selectFormVbox.getChildren().remove(9);
            } else if (guestDrawer.isOpened()) {
                guestDrawer.close();
                guestDrawer.setVisible(false);
                selectFormVbox.getChildren().remove(10);
            }
        }
        @FXML
        public void hamburgerChange (MouseEvent event){
            JFXHamburger jfxHamburger = (JFXHamburger) event.getSource();
            if (personalHamburger.equals(jfxHamburger) && event.getClickCount() == 1) {
                if (personalDrawer.isOpened()) {
                    personalDrawer.close();
                    personalDrawer.setVisible(false);
                    selectFormVbox.getChildren().remove(1);
                } else {
                    drawerClose();
                    personalDrawer.open();
                    personalDrawer.toFront();
                    personalDrawer.setVisible(true);
                    selectFormVbox.getChildren().add(1, personalDrawer);
                }
            } else if (educationalHamburger.equals(jfxHamburger) && event.getClickCount() == 1) {
                if (educationalDrawer.isOpened()) {
                    educationalDrawer.close();
                    educationalDrawer.setVisible(false);
                    selectFormVbox.getChildren().remove(2);
                } else {
                    drawerClose();
                    educationalDrawer.open();
                    educationalDrawer.toFront();
                    educationalDrawer.setVisible(true);
                    selectFormVbox.getChildren().add(2, educationalDrawer);
                }
            } else if (workingHamburger.equals(jfxHamburger) && event.getClickCount() == 1) {
                if (workingDrawer.isOpened()) {
                    workingDrawer.close();
                    workingDrawer.setVisible(false);
                    selectFormVbox.getChildren().remove(3);
                } else {
                    drawerClose();
                    workingDrawer.open();
                    workingDrawer.toFront();
                    workingDrawer.setVisible(true);
                    selectFormVbox.getChildren().add(3, workingDrawer);
                }
            } else if (sppuHamburger.equals(jfxHamburger) && event.getClickCount() == 1) {
                if (sppuDrawer.isOpened()) {
                    sppuDrawer.close();
                    sppuDrawer.setVisible(false);
                    selectFormVbox.getChildren().remove(4);
                } else {
                    drawerClose();
                    sppuDrawer.open();
                    sppuDrawer.toFront();
                    sppuDrawer.setVisible(true);
                    selectFormVbox.getChildren().add(4, sppuDrawer);
                }
            } else if (publicationalHamburger.equals(jfxHamburger) && event.getClickCount() == 1) {
                if (publicationalDrawer.isOpened()) {
                    publicationalDrawer.close();
                    publicationalDrawer.setVisible(false);
                    selectFormVbox.getChildren().remove(5);
                } else {
                    drawerClose();
                    publicationalDrawer.open();
                    publicationalDrawer.toFront();
                    publicationalDrawer.setVisible(true);
                    selectFormVbox.getChildren().add(5, publicationalDrawer);
                }
            } else if (attendedHamburger.equals(jfxHamburger) && event.getClickCount() == 1) {
                if (attendedDrawer.isOpened()) {
                    attendedDrawer.close();
                    attendedDrawer.setVisible(false);
                    selectFormVbox.getChildren().remove(6);
                } else {
                    drawerClose();
                    attendedDrawer.open();
                    attendedDrawer.toFront();
                    attendedDrawer.setVisible(true);
                    selectFormVbox.getChildren().add(6, attendedDrawer);
                }
            } else if (organizedHamburger.equals(jfxHamburger) && event.getClickCount() == 1) {
                if (organizedDrawer.isOpened()) {
                    organizedDrawer.close();
                    organizedDrawer.setVisible(false);
                    selectFormVbox.getChildren().remove(7);
                } else {
                    drawerClose();
                    organizedDrawer.open();
                    organizedDrawer.toFront();
                    organizedDrawer.setVisible(true);
                    selectFormVbox.getChildren().add(7, organizedDrawer);
                }
            } else if (interactionHamburger.equals(jfxHamburger) && event.getClickCount() == 1) {
                if (interactionDrawer.isOpened()) {
                    interactionDrawer.close();
                    interactionDrawer.setVisible(false);
                    selectFormVbox.getChildren().remove(8);
                } else {
                    drawerClose();
                    interactionDrawer.open();
                    interactionDrawer.toFront();
                    interactionDrawer.setVisible(true);
                    selectFormVbox.getChildren().add(8, interactionDrawer);
                }
            } else if (fundedHamburger.equals(jfxHamburger) && event.getClickCount() == 1) {
                if (fundedDrawer.isOpened()) {
                    fundedDrawer.close();
                    fundedDrawer.setVisible(false);
                    selectFormVbox.getChildren().remove(9);
                } else {
                    drawerClose();
                    fundedDrawer.open();
                    fundedDrawer.toFront();
                    fundedDrawer.setVisible(true);
                    selectFormVbox.getChildren().add(9, fundedDrawer);
                }
            } else if (guestHamburger.equals(jfxHamburger) && event.getClickCount() == 1) {
                if (guestDrawer.isOpened()) {
                    guestDrawer.close();
                    guestDrawer.setVisible(false);
                    selectFormVbox.getChildren().remove(10);
                } else {
                    drawerClose();
                    guestDrawer.open();
                    guestDrawer.toFront();
                    guestDrawer.setVisible(true);
                    selectFormVbox.getChildren().add(10, guestDrawer);
                }
            }
        }
        @FXML
     protected void selectForm(MouseEvent event){
        if(publicationCheck.isSelected()){
            attendedCheck.setSelected(false);
            organizedCheck.setSelected(false);
            fundedCheck.setSelected(false);
            interactionCheck.setSelected(false);
            guestCheck.setSelected(false);
            summaryFlag = 1;
        }else if(attendedCheck.isSelected()){
            publicationCheck.setSelected(false);
            organizedCheck.setSelected(false);
            fundedCheck.setSelected(false);
            interactionCheck.setSelected(false);
            guestCheck.setSelected(false);
            summaryFlag = 2;
        }else if(organizedCheck.isSelected()){
            publicationCheck.setSelected(false);
            attendedCheck.setSelected(false);
            fundedCheck.setSelected(false);
            interactionCheck.setSelected(false);
            guestCheck.setSelected(false);
            summaryFlag = 3;
        } else if(fundedCheck.isSelected()){
            publicationCheck.setSelected(false);
            organizedCheck.setSelected(false);
            attendedCheck.setSelected(false);
            interactionCheck.setSelected(false);
            guestCheck.setSelected(false);
            summaryFlag = 4;
        } else if(interactionCheck.isSelected()){
            publicationCheck.setSelected(false);
            organizedCheck.setSelected(false);
            fundedCheck.setSelected(false);
            attendedCheck.setSelected(false);
            guestCheck.setSelected(false);
            summaryFlag = 5;
        }else if(guestCheck.isSelected()){
            publicationCheck.setSelected(false);
            organizedCheck.setSelected(false);
            fundedCheck.setSelected(false);
            attendedCheck.setSelected(false);
            interactionCheck.setSelected(false);
            summaryFlag = 6;
        }
     }
     @FXML
    void summarySubmit(ActionEvent event){
         academicYearTSummary = null;
         academicYearFSummary = null;
         if(academicYearFromSummary.getValue()!=null) {
             academicYearFSummary = academicYearFromSummary.getValue();
             academicYearTSummary = academicYearToSummary.getValue();
         }
         if(academicYearFromSummary.getValue()=="None" && academicYearToSummary.getValue()=="None"){
             academicYearTSummary = null;
             academicYearFSummary = null;
         }
         if(academicYearFSummary!=null && academicYearTSummary!=null) {
             Stage primaryStage = new Stage();
             try {
                 Parent root = FXMLLoader.load(getClass().getResource("FXML/ReportTable.fxml"));
                 primaryStage.setTitle("Faculty IMS");
                 primaryStage.setScene(new Scene(root, 1100, 700));
                 primaryStage.show();
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
         else
             JOptionPane.showMessageDialog(null, "Please Select Academic Year");
     }
}
