package sample;

import com.jfoenix.controls.*;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static sample.Main.jdbcClass;

public class RegistrationForms extends Object implements Initializable {
    @FXML
    public Tooltip toolFirstName;
    @FXML
    public JFXTextField personalFirstName;
    @FXML
    public JFXTextField personalMiddleName;
    @FXML
    public JFXTextField personalLastName;
    @FXML
    public JFXTextField personalMothersName;
    @FXML
    public JFXTextField personalReligion;
    @FXML
    public JFXTextField personalCaste;
    @FXML
    public DatePicker personalDateOfBirth;
    @FXML
    public JFXComboBox<String> personalCategory;
    @FXML
    public JFXRadioButton Male;
    @FXML
    public JFXRadioButton Female;
    @FXML
    public JFXRadioButton Other;
    @FXML
    public JFXTextField personalMobileNumber;
    @FXML
    public JFXTextField personalPersonalEmail;
    @FXML
    public JFXTextArea personalAddress;
    @FXML
    public JFXTextField personalAadharNumber;
    @FXML
    public JFXTextField personalPANNumber;
    @FXML
    public JFXTextField personalBankAccountNumber;
    @FXML
    public JFXTextField personalIFSCCode;
    @FXML
    public JFXTextField personalMICRCode;
    @FXML
    public JFXTextField personalPFNumber;
    @FXML
    public JFXTextField personalPassportNumber;
    @FXML
    public Button personalCallButton;
    @FXML
    public Button educationalCallButton;
    @FXML
    public Button sppuCallButton;
    @FXML
    public Button publicationCallButton;
    @FXML
    public Button attendedCallButton;
    @FXML
    public Button organizedCallButton;
    @FXML
    public Button outsideWorldCallButton;
    @FXML
    public Button fundedCallButton;
    @FXML
    public Button guestLectureCallButton;
    @FXML
    public Button aboutCallButton;
    @FXML
    public Button logoutCallButton;
    @FXML
    public VBox vBox;
    @FXML
    public Pane personalPane;
    ///////////////////////////////// Interaction IDs
    @FXML
    public Pane interactionPane;
    @FXML
    public JFXTextField interactionTitle;
    @FXML
    public JFXCheckBox interactionTargetStudent;
    @FXML
    public JFXCheckBox interactionTargetResearch;
    @FXML
    public JFXCheckBox interactionTargetFaculty;
    @FXML
    public JFXCheckBox interactionTargetIndustry;
    @FXML
    public JFXCheckBox interactionTargetNonTeaching;
    @FXML
    public JFXTextField interactionNoOfParticipants;
    @FXML
    public JFXTextArea interactionVenue;
    @FXML
    public DatePicker interactionProgrammeDate;
    @FXML
    public JFXTextField interactionNoOfDays;
    @FXML
    public JFXComboBox<String> interactionAcademicYear;
    @FXML
    public JFXComboBox<String> interactionRole;

    ////////////////////////////////////Educational ID
    @FXML
    public Pane educationalPane;
    @FXML
    public JFXComboBox<String> eduUGDegree;
    @FXML
    public JFXComboBox<String> eduPGDegree;
    @FXML
    public JFXComboBox<String> eduPGSpecialization;
    @FXML
    public JFXComboBox<String> eduUGSpecialization;
    @FXML
    public JFXComboBox<String> eduUGClass;
    @FXML
    public JFXComboBox<String> eduPGClass;
    @FXML
    public JFXTextField eduUGUniversity;
    @FXML
    public JFXTextField eduPGUniversity;
    @FXML
    public JFXTextField eduUGPercentage;
    @FXML
    public JFXTextField eduPGPercentage;
    @FXML
    public DatePicker eduUGYearPassing;
    @FXML
    public DatePicker eduPGYearPassing;
    @FXML
    public JFXComboBox<String> eduPGCompletionStatus;
    @FXML
    public JFXToggleButton eduEnabledPHD;
    @FXML
    public JFXTextField eduPHDSpecialization;
    @FXML
    public JFXTextField eduPHDDomain;
    @FXML
    public JFXTextField eduPHDUniversity;
    @FXML
    public DatePicker eduPHDYearAdmission;
    @FXML
    public DatePicker eduPHDYearCompletion;
    @FXML
    public JFXComboBox<String> eduPHDCompletionStatus;
    @FXML
    public JFXTextField eduWorkTeachingPosition;
    @FXML
    public JFXTextField eduWorkTeachingInstitution;
    @FXML
    public DatePicker eduWorkTeachingDateOfJoining;
    @FXML
    public DatePicker eduWorkTeachingDateOfLeaving;
    @FXML
    public JFXButton eduWorkTeachingAddMore;
    @FXML
    public JFXTextField eduWorkIndustrialPosition;
    @FXML
    public JFXTextField eduWorkIndustrialName;
    @FXML
    public JFXButton eduWorkIndustrialAddMore;
    @FXML
    public   DatePicker eduWorkIndustrialDateOfJoining;
    @FXML
    public   DatePicker eduWorkIndustrialDateOfLeaving;
    @FXML
    public Pane eduPHDPane;
    @FXML
    public   DatePicker eduSCOEJoiningDate;
    ////////////////////////////////////////Funded ID
    @FXML
    public Pane fundedPane;
    @FXML
    public JFXComboBox<String> fundedRole;
    @FXML
    public JFXTextField fundedNameOfCompany;
    @FXML
    public JFXTextField fundedTitleOfResearch;
    @FXML
    public JFXComboBox<String> fundedSponsoringAgency;
    @FXML
    public JFXTextField fundedSanctiondeAmount;
    @FXML
    public JFXTextField fundedReceivedAmount;
    @FXML
    public JFXTextField fundedUtilizedAmount;
    @FXML
    public JFXComboBox<String> fundedStatus;
    @FXML
    public DatePicker fundedFromDate;
    @FXML
    public DatePicker fundedToDate;
    @FXML
    public JFXComboBox<String> fundedAcademicYear;
    @FXML
    public JFXCheckBox fundedHBOXResearchPaper;
    @FXML
    public JFXCheckBox fundedHBOXPatents;
    @FXML
    public JFXCheckBox fundedHBOXBooks;
    @FXML
    public JFXCheckBox fundedHBOXOther;
    @FXML
    public JFXTextField fundedOtherSponsoringAgency;

    /////////////////////////////////////////Attended ID
    @FXML
    public Pane attendedPane;
    @FXML
    public JFXComboBox<String> attendedTypeOfProgramme;
    @FXML
    public JFXTextField attendedTitleOfProgramme;
    @FXML
    public JFXTextField attendedOrganizerName;
    @FXML
    public JFXComboBox<String> attendedLevelOfProgramme;
    @FXML
    public JFXTextField attendedNameOfApprovingAgency;
    @FXML
    public JFXTextArea attendedVenue;
    @FXML
    public DatePicker attendedDateFrom;
    @FXML
    public DatePicker attendedDateTo;
    @FXML
    public JFXTextField attendedNameOfFundingAgency;
    @FXML
    public JFXTextField attendedFundsReceived;
    @FXML
    public JFXComboBox<String> attendedAcademicYear;
    ////////////////////////////////////////////////Guest Lecture ID
    @FXML
    public Pane guestPane;
    @FXML
    public JFXTextField guestDesignationOfResourcePerson;
    @FXML
    public JFXTextField guestNameOfResourcePerson;
    @FXML
    public JFXTextField guestResourcePersonOrganization;
    @FXML
    public JFXTextField guestResourcePersonMobileNo;
    @FXML
    public JFXTextField guestResourcePersonEmailId;
    @FXML
    public JFXTextField guestTopic;
    @FXML
    public JFXTextField guestNumberOfParticipant;
    @FXML
    public JFXTextField guestRemuneration;
    @FXML
    public DatePicker guestDateOfConduction;
    @FXML
    public JFXComboBox guestAcademicYear;
    @FXML
    public VBox guestVBOXTargetAudience;
    @FXML
    public JFXCheckBox guestVBOXFE;
    @FXML
    public JFXCheckBox guestVBOXSE;
    @FXML
    public JFXCheckBox guestVBOXTE;
    @FXML
    public JFXCheckBox guestVBOXBE;
    @FXML
    public JFXCheckBox guestVBOXMEI;
    @FXML
    public JFXCheckBox guestVBOXMEII;
    @FXML
    public JFXCheckBox guestVBOXPhD;
    @FXML
    public JFXCheckBox guestVBOXFaculty;
    @FXML
    public JFXCheckBox guestVBOXMBAI;
    @FXML
    public JFXCheckBox guestVBOXMBAII;
    @FXML
    public JFXButton guestSaveButton;
    //////////////////////////////////////////////
    @FXML
    public Pane organizedPane;
    @FXML
    public JFXTextField organizedTitleOfProgramme;
    @FXML
    public JFXComboBox<String> organizedTypeOfProgramme;
    @FXML
    public JFXComboBox<String> organizedLevelOfProgramme;
    @FXML
    public JFXComboBox<String> organizedApprovingAuthority;
    @FXML
    public JFXComboBox<String> organizedSponsoringAuthority;
    @FXML
    public JFXTextField organizedFundsReceived;
    @FXML
    public JFXTextField organizedFundsUtilized;
    @FXML
    public DatePicker organizedDateFrom;
    @FXML
    public DatePicker organizedDateTo;
    @FXML
    public JFXTextField organizedNumberOfParticipants;
    @FXML
    public JFXComboBox<String> organizedAcademicYear;
    @FXML
    public JFXTextField organizedNameOfResourcePerson;
    @FXML
    public JFXTextField organizedEmailId;
    @FXML
    public JFXTextField organizedMobileNo;
    @FXML
    public JFXButton organizedSaveButton;
    @FXML
    public JFXCheckBox organizedHBOXUG;
    @FXML
    public JFXCheckBox organizedHBOXPG;
    @FXML
    public JFXCheckBox organizedHBOXPhD;
    @FXML
    public JFXCheckBox organizedHBOXFaculties;
    @FXML
    public JFXCheckBox organizedHBOXOther;
    @FXML
    public JFXCheckBox organizedHBOXIndustryPersonnel;
    @FXML
    public JFXTextField organizedTypeOfProgrammeOther;
    @FXML
    public JFXButton organizedAddMore;
    ///////////////////////////////////////////////////////////
    @FXML
    public Pane SPPUPane;
    @FXML
    public JFXTextField sppuEmployeeNumber;
    @FXML
    public JFXComboBox sppuTypeOfAppointment;
    @FXML
    public DatePicker stesDateOfAppointmentOfCurrentDesignation;
    @FXML
    public JFXComboBox sppuCurrentDesignation;
    @FXML
    public JFXTextField sppuReferenceNumber;
    @FXML
    public JFXTextField sppuPhdGuideApprovalReferenceNumber;
    @FXML
    public JFXTextField sppuStesAppointmentOrderReferenceNumber;
    @FXML
    public JFXTextField sppuNameOfAssociatedResearchCentre;
    @FXML
    public JFXButton sppuSaveAndContinue;
    @FXML
    public DatePicker sppuPgTeacherApprovalDate;
    @FXML
    public DatePicker sppuDateOfApprovalOfCurrentDesignation;
    @FXML
    public DatePicker sppuPhdGuideRecognitionApprovalDate;
    @FXML
    public JFXComboBox<String> sppuDepartmentName;
    @FXML
    public JFXComboBox<String> sppuStesCurrentApprovalCatagory;
    @FXML
    public JFXComboBox<String> sppuFacultyName;
    @FXML
    public ToggleGroup Approval;
    @FXML
    public RadioButton sppuHBOXAssistnatProfessor;
    @FXML
    public RadioButton sppuHBOXNo;
    @FXML
    public RadioButton sppuHBOXAssociateProfessor;
    @FXML
    public JFXTextField sppuApprovalReferenceNumber;


    /////////////////////////////////////////////////////////////
    @FXML
    public Pane publicationPane;
    @FXML
    public JFXTextField publicationAuthorsName;
    @FXML
    public JFXTextField publicationCorrespondenceAuthors;
    @FXML
    public JFXComboBox<String> publicationPublicationType;
    @FXML
    public JFXTextField publicationPublicationTypeOther;
    @FXML
    public JFXTextField publicationTitleOfPaper;
    @FXML
    public JFXTextField publicationNameOfJournal;
    @FXML
    public JFXTextField publicationNameOfPublisherOrganizer;
    @FXML
    public JFXTextField publicationLink;
    @FXML
    public DatePicker publicationMonthYear;
    @FXML
    public JFXTextField publicationPageNoFrom;
    @FXML
    public JFXTextField publicationISSN;
    @FXML
    public JFXTextField publicationVolumeIssueNumber;
    @FXML
    public JFXTextArea publicationAddress;
    @FXML
    public DatePicker publicationConferenceDateFrom;
    @FXML
    public DatePicker publicationConferenceDateTo;
    @FXML
    public JFXTextField publicationNameOfFundingAgency;
    @FXML
    public JFXTextField publicationFundsReceived;
    @FXML
    public JFXTextField publicationImpactFactor;
    @FXML
    public JFXTextField publicationCitationCount;
    @FXML
    public JFXTextField publicationScopusIndex;
    @FXML
    public JFXTextField publicationSNIP;
    @FXML
    public JFXTextField publicationSJRRank;
    @FXML
    public JFXTextField publicationIndexedByOther;
    @FXML
    public JFXComboBox<String> publicationAcademicYear;
    @FXML
    public HBox publicationHBOXIndexedBy;
    @FXML
    public JFXCheckBox publicationHBOXScopus;
    @FXML
    public JFXCheckBox publicationHBOXWebofScience;
    @FXML
    public JFXCheckBox publicationHBOXGoogleScholar;
    @FXML
    public JFXCheckBox publicationHBOXOther;
    @FXML
    public JFXCheckBox publicationHBOXNotIndexed;
    /*FXML ID's End here */

    //OBSERVABLE LISTS
    public ObservableList<String> personalCategoryList = FXCollections.observableArrayList("Open","OBC","SC","ST","DT(A)","NT(2)","NT(B)","NT(C)","NT(D)","SBC");

    public ObservableList<String>  academicYearComboBox = FXCollections.observableArrayList();
    public ObservableList<String>  interactionRoleList = FXCollections.observableArrayList("Speaker(Guest Lecture)","Reviewer","Session Chair - Conference","Resource Person - FDP","Resource Person - STTP","Resource Person - Conference","Resource Person - Seminar","Resource Person - Workshop","Single Point of Contact(SPoC)","Judge","Other");

    public ObservableList<String> UG_degreelist = FXCollections.observableArrayList("B.E","B.Tech","B.Sc","B.Com","B.A","B.B.A");
    public ObservableList<String> specializationlist = FXCollections.observableArrayList("Mechanical" ,"Civil ","Electronics", "Computer","Information Technology","Electrical" );
    public ObservableList<String> classlist = FXCollections.observableArrayList("First Class With Distinction","First Class","Higher Second Class","Second Class","Pass Class");
    public ObservableList<String> PG_degreelist = FXCollections.observableArrayList("M.E","M.Tech","M.S","M.Sc","M.B.A","M.Phil","M.Com","M.A");
    public ObservableList<String> completionStatuslist = FXCollections.observableArrayList("Completed","Pursing");

    public ObservableList<String> fundedRoleList = FXCollections.observableArrayList("Principal Investigator","Co-Investigator","Co-ordinator");
    public ObservableList<String> fundedSponsoringList = FXCollections.observableArrayList("BCUD","AICTC","ISTE","MODROBS","TEQIP","OTHER");
    public ObservableList<String> fundedStatusList =  FXCollections.observableArrayList("Completed","Pursing");

    public ObservableList<String> attendedtypeofprogramme = FXCollections.observableArrayList("STTP","FDP","Seminar","Workshop","Industrial Training Programme","Conference","Other");
    public ObservableList<String> attendedlevelofprogramme = FXCollections.observableArrayList("State","National","International");

    public ObservableList<String> organizedtypeOfProgramme = FXCollections.observableArrayList("STTP", "FDP", "Seminar", "Workshop", "Industrial Training Programme", "Conference", "Other");
    public ObservableList<String> organizedlevelOfProgramme = FXCollections.observableArrayList("State", "National", "International");
    public ObservableList<String> organizedapprovingAuthority = FXCollections.observableArrayList("BCUD", "AICTE", "MODROBS", "TEQIP", "ISTE", "Other");
    public ObservableList<String> organizedsponsoringAuthority = FXCollections.observableArrayList("BCUD", "AICTE", "MODROBS", "TEQIP", "ISTE", "Self Sponsor", "SCOE", "Other");

    public ObservableList<String> currentDesignationList = FXCollections.observableArrayList("Professor","Associate Professor","Assistant Professor");
    public ObservableList<String> typeOfAppointmentList = FXCollections.observableArrayList("Permanent","Academic Year","Ad Hoc");
    public ObservableList<String> stesCurrentApprovalCatagoryList = FXCollections.observableArrayList("Open","OBC","SC","ST","DT(A)","NT(2)","NT(B)","NT(C)","NT(D)","SBC");

    public ObservableList<String> publicationTypeList = FXCollections.observableArrayList("National Conference","International Conference","National Journal","International Journal","White Paper","Book(s)","Monographs","Other");
    public ObservableList<String> publicationIndexedByList = FXCollections.observableArrayList("Scopus","Web-od_Science","Google-Scholar","Other","Not-Indexed");
    protected String organizedTypeOther = "None";
    public static String SCOEJoiningDate = "";
    protected String sponsoringAgency = "";
    public String publicationTypeOther = "None";
    public String IndexedBy = "";
    protected String nameOfAssociatedResearchCentre = null;
    protected static int fundedFlag = 0;
    protected static int organizedFlag = 0;
    protected static int publicationTypeFlag = 0;
    private static int publicationIndexedFlag = 0;
    private static int fundedAddFlag = 0;
    private static int interactionAddFlag = 0;
    protected static int organizedAddFlag = 0;
    private static int attendedAddFlag = 0;
    private static int publicationAddFlag = 0;
    private String PHDDateCompletion = null;
    public static int PHDAddFlag = 1;
    public static boolean personalAccessFlag = false;
    public static boolean educationalAccessFlag = false;
    public static boolean sppuAccessFlag = false;

    ArrayList<String> PHDData = new ArrayList<>();
    ArrayList<String> teachingData = new ArrayList<>();
    ArrayList<String> industrialData = new ArrayList<>();

    static PreparedStatement personalQuery;
    static PreparedStatement bankQuery;
    static PreparedStatement educationalUG = null;
    static PreparedStatement educationalPHD = null;
    static PreparedStatement educationalPG = null;
    static PreparedStatement workExperienceTeaching = null;
    static PreparedStatement workExperienceIndustrial = null;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        jdbcClass.getConnection();
        try {
            initializeComboBox();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        educationalPane.setPrefHeight(1100);
        SPPUPane.setPrefHeight(1100);
    }
    public void initializeComboBox() throws SQLException {
        List<String> academicYearList = new ArrayList<>();
        for(int i = Year.now().getValue(); i >= 1995 ; i--) {
            academicYearList.add(i+"-"+(i+1));
        }
        academicYearComboBox.setAll(academicYearList);
        interactionAcademicYear.setItems(academicYearComboBox);
        fundedAcademicYear.setItems(academicYearComboBox);
        attendedAcademicYear.setItems(academicYearComboBox);
        guestAcademicYear.setItems(academicYearComboBox);
        organizedAcademicYear.setItems(academicYearComboBox);
        publicationAcademicYear.setItems(academicYearComboBox);

        personalCategory.setItems(personalCategoryList);
        interactionRole.setItems(interactionRoleList);

        fundedOtherSponsoringAgency.setDisable(true);
        fundedOtherSponsoringAgency.setOpacity(0);
        fundedRole.setItems(fundedRoleList);
        fundedSponsoringAgency.setItems(fundedSponsoringList);
        fundedStatus.setItems(fundedStatusList);

        eduPHDPane.setDisable(true);
        eduPHDYearCompletion.setOpacity(0);
        eduPHDYearCompletion.setDisable(true);
        eduUGDegree.setItems(UG_degreelist);
        eduPGDegree.setItems(PG_degreelist);
        eduPGClass.setItems(classlist);
        eduUGClass.setItems(classlist);
        eduPGCompletionStatus.setItems(completionStatuslist);
        eduPHDCompletionStatus.setItems(completionStatuslist);
        eduUGSpecialization.setItems(specializationlist);
        eduPGSpecialization.setItems(specializationlist);

        attendedTypeOfProgramme.setItems(attendedtypeofprogramme);
        attendedLevelOfProgramme.setItems(attendedlevelofprogramme);

        organizedTypeOfProgrammeOther.setOpacity(0);
        organizedTypeOfProgrammeOther.setDisable(true);
        organizedTypeOfProgramme.setItems(organizedtypeOfProgramme);
        organizedLevelOfProgramme.setItems(organizedlevelOfProgramme);
        organizedSponsoringAuthority.setItems(organizedsponsoringAuthority);
        organizedApprovingAuthority.setItems(organizedapprovingAuthority);

        sppuTypeOfAppointment.setItems(typeOfAppointmentList);
        sppuStesCurrentApprovalCatagory.setItems(stesCurrentApprovalCatagoryList);
        sppuCurrentDesignation.setItems(currentDesignationList);

        publicationPublicationTypeOther.setOpacity(0);
        publicationPublicationTypeOther.setDisable(true);
        publicationIndexedByOther.setOpacity(0);
        publicationIndexedByOther.setDisable(true);
        publicationPublicationType.setItems(publicationTypeList);
        publicationAcademicYear.setItems(academicYearComboBox);

        String personalNameQuery = "select FacultyName from facultymain where RegistrationNo = '"+Login.REGISTRATION_NO+"'";
        PreparedStatement preparedStatement = jdbcClass.connection.prepareStatement(personalNameQuery);
        ResultSet resultSet = jdbcClass.fireQuery(preparedStatement);
        String name = null;
        String firstName,lastName,middleName;
        while (resultSet.next()){
           name = resultSet.getString("facultyName");
        }
        String[] target = name.split(" ",0);
        firstName = target[0];
        middleName = target[1];
        lastName = target[2];
        personalFirstName.setText(firstName);
        personalMiddleName.setText(middleName);
        personalLastName.setText(lastName);
    }
    /////////////////////////////////////////////////ALL SAVE BUTTONS///////////////////////////////////////////////////
    @FXML
    protected void personalSaveButtonHandle(ActionEvent event) {
        ArrayList<String> personalData = getPersonalData();
        ArrayList<String> bankData = getBankData();
        String personalStringQuery = "insert into personal values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String bankStringQuery = "insert into bankdetails values(?,?,?,?,?,?,?,?)";
        System.out.println(bankData);
            if(personalData!=null) {
                try {
                    personalQuery = jdbcClass.connection.prepareStatement(personalStringQuery);
                    personalQuery.setInt(1, Login.REGISTRATION_NO);
                    for (int i = 2; i < 14; ++i) {
                        if (i != 11)
                            personalQuery.setString(i, personalData.get(i - 2));
                        else
                            personalQuery.setLong(i, Long.parseLong(personalData.get(i - 2)));
                    }
                    System.out.println(personalQuery.toString());
                    bankQuery = jdbcClass.connection.prepareStatement(bankStringQuery);
                    bankQuery.setInt(1, Login.REGISTRATION_NO);
                    for (int i = 2; i < 9; ++i) {
                        if (i == 2 || i == 6) {
                            bankQuery.setLong(i, Long.parseLong(bankData.get(i - 2)));
                        } else {
                            bankQuery.setString(i, bankData.get(i - 2));
                        }
                    }
                    System.out.println(bankQuery.toString());
                    if(Validation.dialogFlag==0) {
                        educationalPane.toFront();
                        educationalPane.setPrefHeight(2200);
                        vBox.setPrefHeight(2200);
                        vBox.toFront();
                        personalAccessFlag = true;
                    }
                }
                catch (NumberFormatException e) {

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null,"Database error in Personal Form");
                    e.printStackTrace();
                }
            }
    }
    @FXML
    protected void educationalSaveButton(ActionEvent event) throws ParseException {
        ArrayList <String> UGData = getUGEducationalData();
        ArrayList <String> PGData = getPGEducationalData();
        PHDData = getPHDEducationalData();
        teachingData = getTeachingData();
        industrialData = getIndustrialData();
        SCOEJoiningDate = eduSCOEJoiningDate.getValue()==null ? "":eduSCOEJoiningDate.getValue().toString();
        Validation.isEmpty(SCOEJoiningDate,"SCOE Joining Date");
        System.out.println(UGData);
        System.out.println(PGData);
        System.out.println(PHDData);
        System.out.println(teachingData);
        System.out.println(industrialData);
        try {
            String educationalStringUGQuery = "insert into educationalugpg values(?,?,?,?,?,?,?,?,?)";
            String educationalStringPGQuery = "insert into educationalugpg values(?,?,?,?,?,?,?,?,?)";
            String educationalStringPHDQuery = "insert into educationalphd values(?,?,?,?,?,?,?,?)";
            String workStringExperienceTeaching = "insert into experiencebeforejoiningstes values(?,?,?,?,?,?)";
            String workStringExperienceIndustrial = "insert into experiencebeforejoiningstes values(?,?,?,?,?,?)";

            educationalUG = jdbcClass.connection.prepareStatement(educationalStringUGQuery);
            educationalPG = jdbcClass.connection.prepareStatement(educationalStringPGQuery);
            educationalPHD = jdbcClass.connection.prepareStatement(educationalStringPHDQuery);
            workExperienceTeaching = jdbcClass.connection.prepareStatement(workStringExperienceTeaching);
            workExperienceIndustrial = jdbcClass.connection.prepareStatement(workStringExperienceIndustrial);

            educationalUG.setInt(1, Login.REGISTRATION_NO);
            educationalPG.setInt(1, Login.REGISTRATION_NO);
            educationalPHD.setInt(1, Login.REGISTRATION_NO);
            workExperienceIndustrial.setInt(1, Login.REGISTRATION_NO);
            workExperienceTeaching.setInt(1, Login.REGISTRATION_NO);

            for (int i = 2; i <= 9; i++) {
                if (i != 6)
                    educationalUG.setString(i, UGData.get(i - 2));
                else
                    educationalUG.setFloat(i, Float.parseFloat(UGData.get(i - 2)));
            }
            for (int i = 2; i <= 9; i++) {
                if (i != 6)
                    educationalPG.setString(i, PGData.get(i - 2));
                else
                    educationalPG.setFloat(i, Float.parseFloat(PGData.get(i - 2)));
            }
            if (eduEnabledPHD.isSelected() && !PHDData.isEmpty()) {
                for (int i = 2; i <= 8; i++) {
                    educationalPHD.setString(i, PHDData.get(i - 2));
                }
            }
            if(!teachingData.isEmpty()) {
                for (int i = 2; i <= 6; i++) {
                    workExperienceTeaching.setString(i, teachingData.get(i - 2));
                }
            }
            if(!industrialData.isEmpty()) {
                for (int i = 2; i <= 6; i++) {
                    workExperienceIndustrial.setString(i, industrialData.get(i - 2));
                }
            }
            if(Validation.dialogFlag==0) {
                SPPUPane.toFront();
                educationalPane.setPrefHeight(900);
                SPPUPane.setPrefHeight(1100);
                vBox.setPrefHeight(1100);
                vBox.toFront();
                educationalAccessFlag = true;
            }
        }
        catch(NumberFormatException e) {

        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null,"Database error in Educational Form");
        }
    }
    @FXML
    protected void sppuSaveButton(ActionEvent event){
        Validation.dialogFlag = 0;
        PreparedStatement sppuPGQuery;
        PreparedStatement sppuPhdQuery;
        PreparedStatement stesQuery;
        PreparedStatement updatephdQuery = null;
        nameOfAssociatedResearchCentre = sppuNameOfAssociatedResearchCentre.getText();
        String sppuPGStringQuery = "insert into sppu values (?,?,?,?,?)";
        String sppuPhdStringQuery = "insert into sppu values (?,?,?,?,?)";
        String stesStringQuery = "insert into stes values (?,?,?,?,?,?,?,?,?,?)";
        String updateNameResearchCentre = "update educationalphd set NameResearch ='" + nameOfAssociatedResearchCentre + "' where RegistrationNo='"+Login.REGISTRATION_NO+"';";

        String employeeNo = sppuEmployeeNumber.getText();
        String referenceNo = sppuReferenceNumber.getText().isEmpty() ? "None":sppuReferenceNumber.getText();
        String currentDesignation = sppuCurrentDesignation.getSelectionModel().isEmpty() ? "" : sppuCurrentDesignation.getValue().toString();
        String sppuPgApprovalDate = sppuPgTeacherApprovalDate.getValue()==null ? "0000-00-00": sppuPgTeacherApprovalDate.getValue().toString();
        String sppuPhdGuideRecognitionApprovalDateVar = sppuPhdGuideRecognitionApprovalDate.getValue()==null ? "0000-00-00":sppuPhdGuideRecognitionApprovalDate.getValue().toString();
        String phdGuideApprovalReferenceNo = sppuPhdGuideApprovalReferenceNumber.getText().isEmpty() ? "None":sppuPhdGuideApprovalReferenceNumber.getText();
        String typeOfAppointment = sppuTypeOfAppointment.getSelectionModel().isEmpty() ? "" :sppuTypeOfAppointment.getValue().toString();

        String sppuDateOfApprovalOfCurrentDesignationVar = sppuDateOfApprovalOfCurrentDesignation.getValue() == null ? "0000-00-00":sppuDateOfApprovalOfCurrentDesignation.getValue().toString();
        String stesAppointmentOrderReferenceNo = sppuStesAppointmentOrderReferenceNumber.getText();
        String stesDateOfAppointmentOfCurrentDesignationVar = stesDateOfAppointmentOfCurrentDesignation.getValue()== null ? "": stesDateOfAppointmentOfCurrentDesignation.getValue().toString();
        String stesCurrentApprovalCatagory = sppuStesCurrentApprovalCatagory.getSelectionModel().isEmpty() ? "None":sppuStesCurrentApprovalCatagory.getValue().toString();
        String sppuApprovalReferenceNumberVar = sppuApprovalReferenceNumber.getText().isEmpty() ? "None":sppuApprovalReferenceNumber.getText();

        Validation.isEmpty(employeeNo,"Employee Number");
        Validation.isEmpty(currentDesignation,"Current Designation");
        Validation.isEmpty(typeOfAppointment,"Type Of Appointment");
        Validation.isEmpty(stesDateOfAppointmentOfCurrentDesignationVar,"STES Date Of Appointment Of Current Designation");
        Validation.isEmpty(stesAppointmentOrderReferenceNo,"STES Appointment Order Reference Number");
        if (Validation.dialogFlag == 0) {
                try {
                    sppuPGQuery = jdbcClass.connection.prepareStatement(sppuPGStringQuery);
                    sppuPhdQuery = jdbcClass.connection.prepareStatement(sppuPhdStringQuery);
                    updatephdQuery = jdbcClass.connection.prepareStatement(updateNameResearchCentre);
                    stesQuery = jdbcClass.connection.prepareStatement(stesStringQuery);
                    sppuPGQuery.setInt(1, Login.REGISTRATION_NO);
                    sppuPGQuery.setString(2, "PG");
                    sppuPGQuery.setDate(3, Date.valueOf(sppuPgApprovalDate));
                    sppuPGQuery.setString(4, referenceNo);
                    sppuPGQuery.setString(5, currentDesignation);

                    sppuPhdQuery.setInt(1, Login.REGISTRATION_NO);
                    sppuPhdQuery.setString(2, "PHD");
                    sppuPhdQuery.setDate(3, Date.valueOf(sppuPhdGuideRecognitionApprovalDateVar));
                    sppuPhdQuery.setString(4, phdGuideApprovalReferenceNo);
                    sppuPhdQuery.setString(5, currentDesignation);

                    stesQuery.setInt(1, Login.REGISTRATION_NO);
                    stesQuery.setString(2, employeeNo);
                    stesQuery.setString(3, typeOfAppointment);
                    stesQuery.setDate(4, Date.valueOf(stesDateOfAppointmentOfCurrentDesignationVar));
                    stesQuery.setString(5, stesAppointmentOrderReferenceNo);
                    stesQuery.setString(6, stesCurrentApprovalCatagory);
                    stesQuery.setString(7, radioButtonHandler());
                    stesQuery.setDate(8, Date.valueOf(sppuDateOfApprovalOfCurrentDesignationVar));
                    stesQuery.setString(9, sppuApprovalReferenceNumberVar);
                    stesQuery.setDate(10, Date.valueOf(SCOEJoiningDate));
                    System.out.println(stesQuery.toString());
                    System.out.println(updatephdQuery.toString());
                    sppuAccessFlag = true;
                    if (personalAccessFlag && educationalAccessFlag && sppuAccessFlag && Validation.dialogFlag == 0) {
                        String accessFlag = "update facultyMain set AccessFlag = 0 where RegistrationNo = '" + Login.REGISTRATION_NO + "' ";
                        PreparedStatement preparedStatement = jdbcClass.connection.prepareStatement(accessFlag);
                        //SPPU
                        jdbcClass.fireQuery(sppuPGQuery);
                        jdbcClass.fireQuery(sppuPhdQuery);
                        jdbcClass.fireQuery(stesQuery);
                        jdbcClass.fireQuery(updatephdQuery);

                        //Educational
                        jdbcClass.fireQuery(educationalUG);
                        jdbcClass.fireQuery(educationalPG);
                        if (!PHDData.isEmpty())
                            jdbcClass.fireQuery(educationalPHD);
                        if (!teachingData.isEmpty())
                            jdbcClass.fireQuery(workExperienceTeaching);
                        if (!industrialData.isEmpty())
                            jdbcClass.fireQuery(workExperienceIndustrial);

                        //Personal
                        jdbcClass.fireQuery(personalQuery);
                        jdbcClass.fireQuery(bankQuery);
                        jdbcClass.fireQuery(preparedStatement);
                        publicationPane.toFront();
                        vBox.toFront();
                    }
                } catch (NumberFormatException e) {

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Database error in SPPU Form");
                }
            }
    }
    @FXML
    protected void publicationSaveButtonHandle(ActionEvent event) throws ParseException {
        PreparedStatement publicationQuery = null;
        ArrayList<String> publicationData = getPublicationData();
        String publicationStringQuery = "insert into publication values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            publicationQuery =  jdbcClass.connection.prepareStatement(publicationStringQuery);
            publicationQuery.setInt(1,Login.REGISTRATION_NO);
            for(int i = 2; i < 25 ; ++i ) {
                if(i==23)
                    publicationQuery.setInt(i,Integer.parseInt(publicationData.get(i-2)));
                else if(i == 16 || i==19 || i==20)
                    publicationQuery.setFloat(i,Float.parseFloat(publicationData.get(i-2)));
                else
                    publicationQuery.setString(i,publicationData.get(i-2));
            }
            System.out.println(publicationQuery.toString());
            if(Validation.dialogFlag==0)
                jdbcClass.fireQuery(publicationQuery);
            if(publicationAddFlag!=1 && Validation.dialogFlag==0){
                attendedPane.toFront();
                publicationPane.setPrefHeight(900);
                attendedPane.setPrefHeight(1100);
                vBox.setPrefHeight(1100);
                vBox.toFront();
            }
            else
                publicationAddFlag = 0;
        }  catch(NumberFormatException e) {

        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Database error in Publication Form");
        }
    }
    @FXML
    protected void attendedSaveButtonHandle(ActionEvent event) throws SQLException, ParseException {
        PreparedStatement attendedQuery = null;
        ArrayList<String> attendedData = getAttendedData();
        String attendedStringQuery = "insert into attended values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        System.out.println(attendedData);
        try {
            attendedQuery = jdbcClass.connection.prepareStatement(attendedStringQuery);
            attendedQuery.setInt(1,Login.REGISTRATION_NO);
            for (int i = 2; i <14; ++i) {

                if (i != 10 || i != 12) {
                    attendedQuery.setString(i, attendedData.get(i - 2));
                }
                else {
                    attendedQuery.setInt(i, Integer.parseInt(attendedData.get(i - 2)));
                }
            }
            if(Validation.dialogFlag==0)
                jdbcClass.fireQuery(attendedQuery);
            if(attendedAddFlag != 1 && Validation.dialogFlag==0){
                organizedPane.toFront();
                vBox.toFront();
            }
            else
                attendedAddFlag = 0;
        } catch(NumberFormatException e) {

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Database error in Attended Form");
        }
    }
    @FXML
    protected void organizedSaveButtonHandle(ActionEvent event) throws ParseException {
        PreparedStatement organizedQuery;
        PreparedStatement authorityQuery;
        ArrayList<String> organizedData = getOrganizedData();
        ArrayList<String> authorityData = getAuthorityData();
        String organizedStringQuery = "insert into organized values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String authorityStringQuery = "insert into authority values(?,?,?,?,?,?)";
        System.out.println(organizedData);
        System.out.println(authorityData);
        try {
            organizedQuery = jdbcClass.connection.prepareStatement(organizedStringQuery);
            organizedQuery.setInt(1, Login.REGISTRATION_NO);
            for (int i = 2; i < 14; ++i) {

                if (i != 5 || i != 8 || i != 12) {
                    organizedQuery.setString(i, organizedData.get(i - 2));
                } else {
                    organizedQuery.setInt(i, Integer.parseInt(organizedData.get(i - 2)));
                }
            }
            authorityQuery = jdbcClass.connection.prepareStatement(authorityStringQuery);
            authorityQuery.setInt(1, Login.REGISTRATION_NO);
            for (int i = 2; i < 7; ++i) {
                if (i != 5 || i != 6) {
                    authorityQuery.setString(i, authorityData.get(i - 2));
                } else {
                    authorityQuery.setInt(i, Integer.parseInt(organizedData.get(i - 2)));
                }
            }

            if(Validation.dialogFlag==0) {
                jdbcClass.fireQuery(organizedQuery);
                jdbcClass.fireQuery(authorityQuery);
            }
            if(organizedAddFlag != 1 && Validation.dialogFlag==0) {
                interactionPane.toFront();
                vBox.toFront();
            }
            else
                organizedAddFlag = 0;
        }  catch(NumberFormatException e) {

        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Database error in Organized Form");
        }
    }
    @FXML
    protected void interactionSaveButtonHandle(ActionEvent event) {
        ArrayList<String> interactionData = getInteractionData();
        PreparedStatement interactionQuery = null;
        String interactionQueryString = "insert into interaction values(?,?,?,?,?,?,?,?,?)";
        System.out.println(interactionData);
        try{
            interactionQuery = jdbcClass.connection.prepareStatement(interactionQueryString);
            interactionQuery.setInt(1,Login.REGISTRATION_NO);
            for(int i = 2; i < 10 ; i++) {
                if(i == 7 || i == 9) {
                    interactionQuery.setInt(i,Integer.parseInt(interactionData.get(i-2)));
                } else {
                    interactionQuery.setString(i,interactionData.get(i-2));
                }
            }
            if(Validation.dialogFlag==0)
                jdbcClass.fireQuery(interactionQuery);
            if(interactionAddFlag != 1 && Validation.dialogFlag==0) {
                fundedPane.toFront();
                vBox.toFront();
            }
            else
                interactionAddFlag = 0;
        }  catch(NumberFormatException e) {

        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Database error in Interaction Form");
        }
    }
    @FXML
    protected void fundedSaveButtonHandle(javafx.event.ActionEvent event) throws ParseException {
        PreparedStatement funded = null;
        ArrayList<String> fundedData = getFundedData();
        try {
            String fundedQuery = "insert into fundedresearchproduct values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            funded = jdbcClass.connection.prepareStatement(fundedQuery);
            funded.setInt(1, Login.REGISTRATION_NO);
            for (int i = 2; i <= 13; i++) {
                if (i != 9 || i != 7 || i != 8)
                    funded.setString(i, fundedData.get(i - 2));
                else
                    funded.setInt(i, Integer.parseInt(fundedData.get(i - 2)));
            }
            System.out.println(funded);
            if(Validation.dialogFlag==0)
                jdbcClass.fireQuery(funded);
            if (fundedAddFlag != 1 && Validation.dialogFlag==0)
                guestPane.toFront();
            else
                fundedAddFlag = 0;
        } catch(NumberFormatException e) {

        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null,"Database error in Funded Form");
        }
    }
    @FXML
    protected void guestSaveButtonHandle(ActionEvent event) {
        PreparedStatement guestQuery;
        ArrayList<String> guestData = getGuestData();
        String guestStringQuery = "insert into guestlecture values(?,?,?,?,?,?,?,?,?,?,?,?)";
        System.out.println(guestStringQuery);
        try {
            guestQuery = jdbcClass.connection.prepareStatement(guestStringQuery);
            guestQuery.setInt(1, Login.REGISTRATION_NO);
            for (int i = 2; i < 13; ++i) {
                if (i != 6 || i != 9 || i != 10) {
                    guestQuery.setString(i, guestData.get(i - 2));
                } else {
                    guestQuery.setInt(i, Integer.parseInt(guestData.get(i - 2)));
                }
            }
            if (Validation.dialogFlag == 0) {
                jdbcClass.fireQuery(guestQuery);
            }
        } catch(NumberFormatException e) {

        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Database error in Guest Form");
        }
    }
    ////////////////////////////////////////////////PERSONAL FORM /////////////////////////////////////////////////////
    protected ArrayList<String> getPersonalData() {
        Validation.dialogFlag = 0;
        ArrayList<String> personalData = new ArrayList<>();
        String firstName = personalFirstName.getText();
        String middleName = personalMiddleName.getText();
        String lastName = personalLastName.getText();
        String mothersName = personalMothersName.getText();
        String religion = personalReligion.getText();
        String category = personalCategory.getSelectionModel().isEmpty() ? "" : personalCategory.getValue();
        String caste = personalCaste.getText();
        String mobileNumber = personalMobileNumber.getText();
        String email = personalPersonalEmail.getText();
        String address = personalAddress.getText();
        String gender = radioButtonHandle();
        String dateOfBirth = personalDateOfBirth.getValue()==null ? "":personalDateOfBirth.getValue().toString();

        Validation.isEmpty(firstName,"First Name");
        Validation.isEmpty(middleName,"Middle Name");
        Validation.isEmpty(lastName,"Last Name");
        Validation.isEmpty(mothersName,"Mothers Name");
        Validation.isEmpty(religion,"Religion ");
        Validation.isEmpty(caste,"Caste ");
        Validation.isEmpty(dateOfBirth,"Date Of Birth");
        Validation.isEmpty(category,"Category ");
        Validation.isEmpty(gender,"Gender");
        Validation.isEmpty(mobileNumber,"Mobile Number");
        Validation.isEmpty(email,"Email");
        Validation.isEmpty(address,"Address");

        Validation.validateAlphabets(firstName,"First Name");
        Validation.validateAlphabets(middleName,"Middle Name");
        Validation.validateAlphabets(lastName,"Last Name");
        Validation.validateAlphabets(mothersName,"Mothers Name");
        Validation.validateAlphabets(religion,"Religion ");
        Validation.validateAlphabets(caste,"Caste ");
        Validation.validateDigits(mobileNumber,"Mobile Number");
        Validation.validateDigitsLength(mobileNumber,"Mobile Number",10);
        Validation.validatePersonalEmail(email);

        personalData.add(firstName);
        personalData.add(middleName);
        personalData.add(lastName);
        personalData.add(gender);
        personalData.add(dateOfBirth);
        personalData.add(mothersName);
        personalData.add(category);
        personalData.add(religion);
        personalData.add(caste);
        personalData.add(mobileNumber);
        personalData.add(email);
        personalData.add(address);
        return personalData;
    }
    protected ArrayList<String> getBankData() {
        ArrayList<String> bankData = new ArrayList<>();
        String aadharNumber = personalAadharNumber.getText();
        String PANNumber = personalPANNumber.getText();
        String bankAccountNumber = personalBankAccountNumber.getText();
        String IFSCCode = personalIFSCCode.getText();
        String PFNumber = personalPFNumber.getText();
        String MICRCode = personalMICRCode.getText();
        String passportNumber = personalPassportNumber.getText();
        if(MICRCode.isEmpty()) MICRCode = "0";
        else Validation.validateDigits(MICRCode,"MICR Code");
        if(PFNumber.isEmpty()) PFNumber = "0";
        if(passportNumber.isEmpty()) passportNumber = "0";

        Validation.isEmpty(aadharNumber,"Aadhar Number ");
        Validation.isEmpty(PANNumber,"PAN Number");
        Validation.isEmpty(bankAccountNumber,"Bank Account No:");
        Validation.isEmpty(IFSCCode,"IFSC Code");
        Validation.validateDigits(aadharNumber,"Aadhar Number");
        Validation.validateDigits(bankAccountNumber,"Bank Account No");
        Validation.validateDigitsLength(aadharNumber,"Aadhar Number",12);
        bankData.add(aadharNumber);
        bankData.add(PANNumber);
        bankData.add(bankAccountNumber);
        bankData.add(IFSCCode);
        bankData.add(MICRCode);
        bankData.add(PFNumber);
        bankData.add(passportNumber);
        return bankData;
    }
    protected String radioButtonHandle() {
        if(Male.isSelected()) {
            return "Male";
        } else if(Female.isSelected()) {
            return "Female";
        } else if(Other.isSelected()) {
            return "Other";
        }
        return "";
    }
    ////////////////////////////////////////////////INTERACTION FORM//////////////////////////////////////////////////
    protected ArrayList<String> getInteractionData() {
        Validation.dialogFlag = 0;
        ArrayList<String> interactionData = new ArrayList<>();
        String Roleoffaculty = interactionRole.getSelectionModel().isEmpty() ? "" :interactionRole.getValue() ;
        String Particulars = interactionTitle.getText();
        String Venue = interactionVenue.getText();
        String ProgramDate = interactionProgrammeDate.getValue()==null ? "":interactionProgrammeDate.getValue().toString();
        String TargetAudience = "";
        String NoofParticipants = interactionNoOfParticipants.getText();
        String NoofDays = interactionNoOfDays.getText();
        String AcademicYear = interactionAcademicYear.getSelectionModel().isEmpty() ? "" :interactionAcademicYear.getValue() ;
        if(interactionTargetStudent.isSelected()) {
            TargetAudience += "Student ";
        }
        if(interactionTargetResearch.isSelected()) {
            TargetAudience += "Research Scholar ";
        }
        if (interactionTargetFaculty.isSelected()) {
            TargetAudience += "Faculty ";
        }
        if (interactionTargetIndustry.isSelected()) {
            TargetAudience += "Industry Person ";
        }
        if (interactionTargetNonTeaching.isSelected()) {
            TargetAudience += "Non Teaching Staff ";
        }

        Validation.isEmpty(Roleoffaculty,"Role of faculty");
        Validation.isEmpty(Particulars,"Title of ( Lecture / Conference / STTP / FDP ) / Company name for SPoC");
        Validation.isEmpty(TargetAudience,"TargetAudience");
        Validation.isEmpty(NoofParticipants,"No of Participants");
        Validation.isEmpty(Venue,"Venue");
        Validation.isEmpty(ProgramDate,"Program Date");
        Validation.isEmpty(NoofDays,"No of Days");
        Validation.isEmpty(AcademicYear,"Academic Year");
        Validation.validateDigits(NoofParticipants,"No of Participants");
        Validation.validateDigits(NoofDays,"No of Days");

        interactionData.add(Roleoffaculty);
        interactionData.add(Particulars);
        interactionData.add(Venue);
        interactionData.add(ProgramDate);
        interactionData.add(TargetAudience);
        interactionData.add(NoofParticipants);
        interactionData.add(AcademicYear);
        interactionData.add(NoofDays);
        return interactionData;
    }
    //////////////////////////////////////////////////EDUCATIONAL FORM//////////////////////////////////////////////////
    protected ArrayList<String> getUGEducationalData(){
        Validation.dialogFlag = 0;
        ArrayList<String> UGEducationalData = new ArrayList<String>();
        String UGDegree = eduUGDegree.getValue().isEmpty() ? "" : eduUGDegree.getValue();
        String UGSpecialization = eduUGSpecialization.getSelectionModel().isEmpty() ? "" : eduUGSpecialization.getValue();
        String UGClass = eduUGClass.getSelectionModel().isEmpty() ? "" : eduUGClass.getValue();
        String UGUniversity = eduUGUniversity.getText();
        String UGPercentage = eduUGPercentage.getText();
        String UGDatePassing = eduUGYearPassing.getValue()==null ? "":eduUGYearPassing.getValue().toString();
        Validation.isEmpty(UGDegree,"UG Degree");
        Validation.isEmpty(UGSpecialization,"UG Specialization");
        Validation.isEmpty(UGClass,"UG Class");
        Validation.isEmpty(UGUniversity,"UG University");
        Validation.isEmpty(UGPercentage,"UG Percentage");
        Validation.isEmpty(UGDatePassing,"UG Date Of Passing");
        Validation.validateDigits(UGPercentage,"UG Percentage");
        UGEducationalData.add("UG");
        UGEducationalData.add(UGDegree);
        UGEducationalData.add(UGSpecialization);
        UGEducationalData.add(UGUniversity);
        UGEducationalData.add(UGPercentage);
        UGEducationalData.add(UGClass);
        UGEducationalData.add(UGDatePassing);
        UGEducationalData.add("Completed");
        return UGEducationalData;
    }
    protected ArrayList<String> getPGEducationalData(){
        ArrayList<String> PGEducationalData = new ArrayList<String>();
        String PGDegree = eduPGDegree.getSelectionModel().isEmpty() ? "" : eduPGDegree.getValue();
        String PGSpecialization = eduPGSpecialization.getSelectionModel().isEmpty() ? "" : eduUGSpecialization.getValue();
        String PGClass = eduPGClass.getSelectionModel().isEmpty() ? "" : eduUGClass.getValue();
        String PGUniversity = eduPGUniversity.getText();
        String PGPercentage = eduPGPercentage.getText();
        String PGDatePassing = eduPGYearPassing.getValue()==null ? "":eduPGYearPassing.getValue().toString();
        String PGCompletionStatus = eduPGCompletionStatus.getSelectionModel().isEmpty() ? "" : eduPGCompletionStatus.getValue();
        //VALIDATE EMPTY OR NOT
        Validation.isEmpty(PGDegree,"PG Degree");
        Validation.isEmpty(PGSpecialization,"PG Specialization");
        Validation.isEmpty(PGClass,"PG Class");
        Validation.isEmpty(PGUniversity,"PG University");
        Validation.isEmpty(PGPercentage,"PG Percentage");
        Validation.isEmpty(PGDatePassing,"PG Date Of Passing");
        Validation.isEmpty(PGCompletionStatus,"PG Completion Status");
        //VALIDATE NUMBER
        Validation.validateDigits(PGPercentage,"PG Percentage");
        PGEducationalData.add("PG");
        PGEducationalData.add(PGDegree);
        PGEducationalData.add(PGSpecialization);
        PGEducationalData.add(PGUniversity);
        PGEducationalData.add(PGPercentage);
        PGEducationalData.add(PGClass);
        PGEducationalData.add(PGDatePassing);
        PGEducationalData.add(PGCompletionStatus);
        return PGEducationalData;
    }
    protected ArrayList<String> getPHDEducationalData(){
        ArrayList<String> PHDEducationalData = new ArrayList<String>();
        String PHDSpecialization = null;
        String PHDDomain = null;
        String PHDUniversity = null;
        String PHDDateAdmission = null;
        String PHDCompletionStatus = null;
        if(PHDAddFlag==0) {
            PHDDomain = eduPHDDomain.getText();
            PHDSpecialization = eduPHDSpecialization.getText();
            PHDUniversity = eduPHDUniversity.getText();
            PHDDateAdmission = eduPHDYearAdmission.getValue()==null ? "":eduPHDYearAdmission.getValue().toString();
            PHDCompletionStatus = eduPHDCompletionStatus.getSelectionModel().isEmpty() ? "" : eduPHDCompletionStatus.getValue();
            Validation.isEmpty(PHDDomain,"PHD Domain");
            Validation.isEmpty(PHDSpecialization,"PHD Specialization");
            Validation.isEmpty(PHDUniversity,"PHD University");
            Validation.isEmpty(PHDDateAdmission,"PHD Date Of Admission");
            Validation.isEmpty(PHDCompletionStatus,"PHD Completion Status");
            PHDEducationalData.add(PHDDomain);
            PHDEducationalData.add(PHDSpecialization);
            PHDEducationalData.add(PHDUniversity);
            PHDEducationalData.add(PHDCompletionStatus);
            PHDEducationalData.add(PHDDateAdmission);
            PHDEducationalData.add(PHDDateCompletion);
            PHDEducationalData.add("None");
            return PHDEducationalData;
        }
        return PHDEducationalData;
    }
    protected ArrayList<String> getTeachingData() throws ParseException {
        ArrayList<String> teachingData = new ArrayList<String>();
        String date1 = eduWorkTeachingDateOfJoining.getValue()==null ? "":eduWorkTeachingDateOfJoining.getValue().toString();
        String date2 = eduWorkTeachingDateOfLeaving.getValue()==null ? "":eduWorkTeachingDateOfLeaving.getValue().toString();
        if(!date1.isEmpty()) {
            Validation.isEmpty(date1, "Date Of Joining");
            Validation.isEmpty(date2, "Date Of Leaving");
            Validation.isEmpty(eduWorkTeachingPosition.getText(), "Position");
            Validation.isEmpty(eduWorkTeachingInstitution.getText(), "Institute");
            if(!date1.isEmpty() && !date2.isEmpty()) Validation.dateDiff(date1,date2,"Teaching");
            teachingData.add("TeachingExperience");
            teachingData.add(eduWorkTeachingInstitution.getText());
            teachingData.add(eduWorkTeachingPosition.getText());
            teachingData.add(eduWorkTeachingDateOfJoining.getValue().toString());
            teachingData.add(eduWorkTeachingDateOfLeaving.getValue().toString());
            return teachingData;
        }
        return teachingData;
    }
    protected ArrayList<String> getIndustrialData() throws ParseException {
        ArrayList<String> industrialData = new ArrayList<String>();
        String date1 = eduWorkIndustrialDateOfJoining.getValue()==null ? "":eduWorkIndustrialDateOfJoining.getValue().toString();
        String date2 = eduWorkIndustrialDateOfLeaving.getValue()==null ? "":eduWorkIndustrialDateOfLeaving.getValue().toString();
        if(!date1.isEmpty()) {
            Validation.isEmpty(date1, "Date Of Joining");
            Validation.isEmpty(date2, "Date Of Leaving");
            Validation.isEmpty(eduWorkIndustrialPosition.getText(), "Position");
            Validation.isEmpty(eduWorkIndustrialName.getText(), "Industry");
            //VALIDATION OF DATE
            if(!date1.isEmpty() && !date2.isEmpty()) Validation.dateDiff(date1,date2,"Industrial");
            industrialData.add("IndustrialExperience");
            industrialData.add(eduWorkIndustrialName.getText());
            industrialData.add(eduWorkIndustrialPosition.getText());
            industrialData.add(eduWorkIndustrialDateOfJoining.getValue().toString());
            industrialData.add(eduWorkIndustrialDateOfLeaving.getValue().toString());

            return industrialData;
        }
        return industrialData;
    }
    @FXML
    protected void enablePHD(javafx.event.ActionEvent event){
        if(eduEnabledPHD.isSelected()){
            eduPHDPane.setDisable(false);
            PHDAddFlag = 0;
        }
        else{
            eduPHDPane.setDisable(true);
            PHDAddFlag = 1;
        }
    }
    @FXML
    protected void enablePHDDateCompletion(javafx.event.ActionEvent event){
        PHDDateCompletion = null;
        if(eduPHDCompletionStatus.getValue()=="Completed"){
            eduPHDYearCompletion.setOpacity(1);
            eduPHDYearCompletion.setDisable(false);
            PHDDateCompletion = eduPHDYearCompletion.getValue()==null ? "":eduPHDYearCompletion.getValue().toString();
            Validation.isEmpty(PHDDateCompletion,"PHD Date Completion");
        }
        else{
            eduPHDYearCompletion.setOpacity(0);
            eduPHDYearCompletion.setDisable(true);
            PHDDateCompletion = "0000-00-00";
        }
    }
    @FXML
    protected void workTeachingAddMore(javafx.event.ActionEvent event) throws SQLException, ParseException {
        Validation.dialogFlag = 0;
        teachingData.clear();
        teachingData = getTeachingData();
        PreparedStatement workExperienceTeaching = null;
        String workStringExperienceTeaching = "insert into experiencebeforejoiningstes values(?,?,?,?,?,?)";
        workExperienceTeaching = jdbcClass.connection.prepareStatement(workStringExperienceTeaching);
        workExperienceTeaching.setInt(1,Login.REGISTRATION_NO);
        if(!teachingData.isEmpty()) {
            for (int i = 2; i <= 6; i++) {
                workExperienceTeaching.setString(i, teachingData.get(i - 2));
            }
        }
        if(Validation.dialogFlag==0 && !teachingData.isEmpty()) {
            jdbcClass.fireQuery(workExperienceTeaching);
            eduWorkTeachingDateOfJoining.setValue(null);
            eduWorkTeachingDateOfLeaving.setValue(null);
            eduWorkTeachingInstitution.setText(null);
            eduWorkTeachingPosition.setText(null);
        }
    }
    @FXML
    protected void workIndustrialAddMore(javafx.event.ActionEvent event) throws SQLException, ParseException {
        Validation.dialogFlag = 0;
        industrialData.clear();
        industrialData = getIndustrialData();
        PreparedStatement workExperienceIndustrial = null;
        String workStringExperienceIndustrial = "insert into experiencebeforejoiningstes values(?,?,?,?,?,?)";
        workExperienceIndustrial = jdbcClass.connection.prepareStatement(workStringExperienceIndustrial);
        workExperienceIndustrial.setInt(1,Login.REGISTRATION_NO);
        if(!industrialData.isEmpty()) {
            for (int i = 2; i <= 6; i++) {
                workExperienceIndustrial.setString(i, industrialData.get(i - 2));
            }
        }
        if(Validation.dialogFlag==0 && !industrialData.isEmpty()){
            jdbcClass.fireQuery(workExperienceIndustrial);
            eduWorkIndustrialDateOfJoining.setValue(null);
            eduWorkIndustrialDateOfLeaving.setValue(null);
            eduWorkIndustrialName.setText(null);
            eduWorkIndustrialPosition.setText(null);
        }
    }
    //////////////////////////////////////////////////FUNDED FORM//////////////////////////////////////////////////////
    @FXML
    protected void enableSponsoring(javafx.event.ActionEvent event){
        if(fundedSponsoringAgency.getValue()=="OTHER") {
            fundedFlag = 1;
            fundedOtherSponsoringAgency.setOpacity(1);
            fundedOtherSponsoringAgency.setDisable(false);
        }
        else{
            fundedFlag = 0;
            fundedOtherSponsoringAgency.setOpacity(0);
            fundedOtherSponsoringAgency.setDisable(true);
            sponsoringAgency = fundedSponsoringAgency.getSelectionModel().isEmpty() ? "" : fundedSponsoringAgency.getValue();
        }
    }
    protected ArrayList<String> getFundedData() throws ParseException {
        Validation.dialogFlag = 0;
        ArrayList<String> fundedData = new ArrayList<String>();
        String role = fundedRole.getSelectionModel().isEmpty() ? "" : fundedRole.getValue();
        String nameCompany = fundedNameOfCompany.getText()==null ? "" : fundedNameOfCompany.getText() ;
        String titleResearch = fundedTitleOfResearch.getText()==null ? "" : fundedTitleOfResearch.getText();
        String sanctionedAmount = fundedSanctiondeAmount.getText()==null ? "" : fundedSanctiondeAmount.getText();
        String receivedAmount = fundedReceivedAmount.getText()==null ? "" : fundedReceivedAmount.getText();
        String utilizedAmount = fundedUtilizedAmount.getText()==null ? "" :  fundedUtilizedAmount.getText();
        String status = fundedStatus.getSelectionModel().isEmpty() ? "" : fundedStatus.getValue();
        String fromDate = fundedFromDate.getValue()==null ? "":fundedFromDate.getValue().toString();
        String toDate =   fundedToDate.getValue()==null ? "":fundedToDate.getValue().toString();
        String fundAcademicYear = fundedAcademicYear.getSelectionModel().isEmpty() ? "" : fundedAcademicYear.getValue();
        if(fundedFlag==1)
            sponsoringAgency = fundedOtherSponsoringAgency.getText();
        String outCome ="";
        if(fundedHBOXResearchPaper.isSelected())
            outCome+="Research Paper,";
        if(fundedHBOXPatents.isSelected())
            outCome+="Patents,";
        if(fundedHBOXBooks.isSelected())
            outCome+="Books,";
        if(fundedHBOXOther.isSelected()){
            outCome+="Other-for-MBA,";
        }
        if(receivedAmount.isEmpty()) receivedAmount = "0";
        if(utilizedAmount.isEmpty()) utilizedAmount = "0";
        Validation.isEmpty(role,"Role");
        Validation.isEmpty(nameCompany,"Company Name");
        Validation.isEmpty(titleResearch,"Research Title");
        Validation.isEmpty(sponsoringAgency,"Sponsoring Agency");
        Validation.isEmpty(sanctionedAmount,"Sanctioned Amount ");
        Validation.isEmpty(outCome,"Outcome");
        Validation.isEmpty(status,"Status");
        Validation.isEmpty(fromDate,"Date From");
        Validation.isEmpty(toDate,"Date To");
        Validation.isEmpty(fundAcademicYear,"Academic Year");
        Validation.validateDigits(sanctionedAmount,"Sanctioned Amount");
        Validation.validateDigits(receivedAmount,"Received Amount");
        Validation.validateDigits(utilizedAmount,"Utilized Amount");
        if(!fromDate.isEmpty() && !toDate.isEmpty())
            Validation.dateDiff(fromDate,toDate,"Funded");
        fundedData.add(role);
        fundedData.add(nameCompany);
        fundedData.add(titleResearch);
        fundedData.add(sponsoringAgency);
        fundedData.add(status);
        fundedData.add(sanctionedAmount);
        fundedData.add(receivedAmount);
        fundedData.add(utilizedAmount);
        fundedData.add(outCome);
        fundedData.add(fromDate);
        fundedData.add(toDate);
        fundedData.add(fundAcademicYear);
        return fundedData;
    }
    //////////////////////////////////////////////////ATTENDED FORM///////////////////////////////////////////////////
    protected ArrayList<String> getAttendedData() throws ParseException {
        Validation.dialogFlag = 0;
        ArrayList<String> attendedData = new ArrayList<String>();
        String typeOfProgramme =  attendedTypeOfProgramme.getSelectionModel().isEmpty() ? "" : attendedTypeOfProgramme.getValue();
        String titleOfProgramme = attendedTitleOfProgramme.getText()==null ? "" : attendedTitleOfProgramme.getText() ;
        String levelOfProgramme = attendedLevelOfProgramme.getSelectionModel().isEmpty() ? "" : attendedLevelOfProgramme.getValue();
        String organizerName = attendedOrganizerName.getText()==null ? "" : attendedOrganizerName.getText() ;
        String nameOfApprovingAgency = attendedNameOfApprovingAgency.getText()==null ? "" : attendedNameOfApprovingAgency.getText() ;
        String venue = attendedVenue.getText();
        String dateFrom = attendedDateFrom.getValue()==null ? "":attendedDateFrom.getValue().toString();
        String dateTo = attendedDateTo.getValue()==null ? "":attendedDateTo.getValue().toString();
        String nameOfFundingAgency = attendedNameOfFundingAgency.getText()==null ? "" : attendedNameOfFundingAgency.getText() ;
        String fundsReceived = attendedFundsReceived.getText();
        String academicYear = attendedAcademicYear.getSelectionModel().isEmpty() ? "" : attendedAcademicYear.getValue();
        String duration = null;
        if(nameOfApprovingAgency.isEmpty()) nameOfApprovingAgency = "None";
        if(nameOfFundingAgency.isEmpty()) nameOfFundingAgency = "None";
        Validation.isEmpty(typeOfProgramme,"Type Of Programme");
        Validation.isEmpty(levelOfProgramme,"Level Of Programme");
        Validation.isEmpty(titleOfProgramme,"Title Of Programme");
        Validation.isEmpty(organizerName,"Organizer Name");
        Validation.isEmpty(venue,"Venue");
        try {
            if(!Validation.isEmpty(dateFrom,"Date From") && !  Validation.isEmpty(dateTo,"Date To"))
                duration = findDuration(dateTo,dateFrom);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Validation.isEmpty(academicYear,"Academic Year");
        if(!fundsReceived.isEmpty()) Validation.validateDigits(fundsReceived,"Funds Received");
        else fundsReceived = "0";

        Validation.dateDiff(dateFrom,dateTo,"Attended");
        attendedData.add(typeOfProgramme);
        attendedData.add(titleOfProgramme);
        attendedData.add(organizerName);
        attendedData.add(levelOfProgramme);
        attendedData.add(nameOfApprovingAgency);
        attendedData.add(venue);
        attendedData.add(dateFrom);
        attendedData.add(dateTo);
        attendedData.add(duration);
        attendedData.add(nameOfFundingAgency);
        attendedData.add(fundsReceived);
        attendedData.add(academicYear);
        return attendedData;
    }
    protected String findDuration(String dateTo, String dateFrom) throws SQLException{
        String query = "select datediff('"+dateTo+"','"+dateFrom+"')days";
        String diff = "";
        int diffInt = 0;
        PreparedStatement preparedStatement = jdbcClass.connection.prepareStatement(query);
        ResultSet resultSet = jdbcClass.fireQuery(preparedStatement);
        while (resultSet.next()) {
                diff = resultSet.getObject("days").toString();
        }
        System.out.println(diff);
        if(Integer.parseInt(diff)<0) {
            diffInt = Integer.parseInt(diff) * (-1);
            diff="";
            diff+=diffInt;
        }
        System.out.println(diff);
        return diff;
    }
    //////////////////////////////////////////////////GUEST FORM///////////////////////////////////////////////////////
    protected ArrayList<String> getGuestData() {
        Validation.dialogFlag = 0;
        ArrayList<String> guestData = new ArrayList<String>();
        String designationOfResourcePerson = guestDesignationOfResourcePerson.getText();
        String nameOfResourcePerson = guestNameOfResourcePerson.getText();
        String resourcePersonOrganization = guestResourcePersonOrganization.getText();
        String resourcePersonMobileNo = guestResourcePersonMobileNo.getText();
        String resourcePersonEmailId = guestResourcePersonEmailId.getText();
        String topic = guestTopic.getText();
        String numberOfParticipant = guestNumberOfParticipant.getText();
        String remuneration = guestRemuneration.getText();
        String dateOfConduction = guestDateOfConduction.getValue()==null ? "":guestDateOfConduction.getValue().toString();
        String academicYear = guestAcademicYear.getSelectionModel().isEmpty() ? "" : guestAcademicYear.getValue().toString();
        String TargetAudience = "";
        //delim by commas
        if(guestVBOXFE.isSelected()) {
            TargetAudience += "FE Students,";
        }
        if(guestVBOXSE.isSelected()) {
            TargetAudience += "SE Students,";
        }
        if(guestVBOXTE.isSelected()) {
            TargetAudience += "TE Students,";
        }
        if(guestVBOXBE.isSelected()) {
            TargetAudience += "BE Students,";
        }
        if(guestVBOXMEI.isSelected()) {
            TargetAudience += "ME-I Students,";
        }
        if(guestVBOXMEII.isSelected()) {
            TargetAudience += "ME-II Students,";
        }
        if(guestVBOXPhD.isSelected()) {
            TargetAudience += "Doctoral(PhD) Students,";
        }
        if(guestVBOXFaculty.isSelected()){
            TargetAudience += "Faculty,";
        }
        if(guestVBOXMBAI.isSelected()) {
            TargetAudience += "MBA-I Students,";
        }
        if(guestVBOXMBAII.isSelected()) {
            TargetAudience += "MBA-II Students,";
        }
        Validation.isEmpty(nameOfResourcePerson,"Name Of Resource Person");
        Validation.isEmpty(designationOfResourcePerson,"Designation Of Resource Person");
        Validation.isEmpty(resourcePersonOrganization,"Resource Person Organization");
        Validation.isEmpty(resourcePersonMobileNo,"Resource Person MobileNo");
        Validation.isEmpty(resourcePersonEmailId,"Resource Person EmailId");
        Validation.isEmpty(topic,"Topic");
        Validation.isEmpty(numberOfParticipant,"Number Of Participant");
        Validation.isEmpty(remuneration,"Remuneration");
        Validation.isEmpty(dateOfConduction,"Date Of Conduction");
        Validation.isEmpty(academicYear,"AcademicYear");
        Validation.isEmpty(TargetAudience,"Target Audience");
        Validation.validateDigits(resourcePersonMobileNo,"Mobile Number");
        Validation.validateDigitsLength(resourcePersonMobileNo,"Mobile Number",10);
        Validation.validatePersonalEmail(resourcePersonEmailId);
        Validation.validateDigits(numberOfParticipant,"Number Of Participant");
        Validation.validateDigits(remuneration,"Remuneration");
        guestData.add(topic);
        guestData.add(nameOfResourcePerson);
        guestData.add(designationOfResourcePerson);
        guestData.add(resourcePersonOrganization);
        guestData.add(resourcePersonMobileNo);
        guestData.add(resourcePersonEmailId);
        guestData.add(TargetAudience);
        guestData.add(numberOfParticipant);
        guestData.add(remuneration);
        guestData.add(dateOfConduction.toString());
        guestData.add(academicYear);
        return guestData;
    }
    //////////////////////////////////////////////////ORGANIZED FORM//////////////////////////////////////////////////
    protected ArrayList<String> getOrganizedData() throws ParseException {
        Validation.dialogFlag = 0;
        ArrayList<String> organizedData = new ArrayList<String>();
        String titleOfProgramme = organizedTitleOfProgramme.getText();
        String levelOfProgramme = organizedLevelOfProgramme.getSelectionModel().isEmpty() ? "" : organizedLevelOfProgramme.getValue();
        String dateFrom = organizedDateFrom.getValue()==null ? "":organizedDateFrom.getValue().toString();
        String dateTo = organizedDateTo.getValue()==null ? "":organizedDateTo.getValue().toString();
        String numberOfParticipants = organizedNumberOfParticipants.getText();
        String academicYear = organizedAcademicYear.getSelectionModel().isEmpty() ? "" : organizedAcademicYear.getValue();
        String nameOfResourcePerson = organizedNameOfResourcePerson.getText();
        String mobileNo = organizedMobileNo.getText()==null ? "" : organizedMobileNo.getText() ;
        String emailId = organizedEmailId.getText()==null ? "" : organizedEmailId.getText() ;
        if(organizedFlag == 1)
            organizedTypeOther = organizedTypeOfProgrammeOther.getText();
        String TargetAudience = "";
        //delim by commas
        String duration = null;
        try {
            if(!dateFrom.isEmpty() && !dateTo.isEmpty())
                duration = findDuration(dateTo,dateFrom);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (organizedHBOXUG.isSelected()) {
            TargetAudience += "UG Students,";
        }
        if (organizedHBOXPG.isSelected()) {
            TargetAudience += "PG Students,";
        }
        if (organizedHBOXPhD.isSelected()) {
            TargetAudience += "Doctoral(PhD)Students,";
        }
        if (organizedHBOXFaculties.isSelected()) {
            TargetAudience += "Faculties,";
        }
        if (organizedHBOXIndustryPersonnel.isSelected()) {
            TargetAudience += "Industry Personnel,";
        }
        if (organizedHBOXOther.isSelected()) {
            TargetAudience += "Other,";
        }
        if(levelOfProgramme.isEmpty()) levelOfProgramme = "None";
        if(nameOfResourcePerson.isEmpty()) nameOfResourcePerson = "None";

        Validation.isEmpty(titleOfProgramme,"Title Of Programme");
        Validation.isEmpty(organizedTypeOfProgramme.getSelectionModel().isEmpty() ? "" : organizedTypeOfProgramme.getValue(),"Type Of Programme");
        Validation.isEmpty(dateFrom,"Date From");
        Validation.isEmpty(dateTo,"Date To");
        Validation.isEmpty(TargetAudience,"Target Audience");
        Validation.isEmpty(numberOfParticipants,"Number Of Participants");
        Validation.isEmpty(academicYear,"Academic Year");
        if(!mobileNo.isEmpty()){
            Validation.validateDigits(mobileNo,"MobileNo");
            Validation.validateDigitsLength(mobileNo,"MobileNo",10);
        }
        else mobileNo = "0";
        if(!emailId.isEmpty()) Validation.validatePersonalEmail(emailId);
        else emailId = "None";
        Validation.validateDigits(duration,"Duration");
        Validation.validateDigits(numberOfParticipants,"Number Of Participants");
        if(!dateFrom.isEmpty() && !dateTo.isEmpty())
            Validation.dateDiff(dateFrom,dateTo,"Organized");
        organizedData.add(titleOfProgramme);
        organizedData.add(organizedTypeOther);
        organizedData.add(levelOfProgramme);
        organizedData.add(dateFrom);
        organizedData.add(dateTo);
        organizedData.add(duration);
        organizedData.add(numberOfParticipants);
        organizedData.add(TargetAudience);
        organizedData.add(academicYear);
        organizedData.add(nameOfResourcePerson);
        organizedData.add(mobileNo);
        organizedData.add(emailId);
        return (organizedData);
    }
    @FXML
    protected void enabledOther(ActionEvent event){
        if(organizedTypeOfProgramme.getValue() == "Other"){
            organizedTypeOfProgrammeOther.setOpacity(1);
            organizedTypeOfProgrammeOther.setDisable(false);
            organizedFlag = 1;
        }
        else{
            organizedFlag = 0;
            organizedTypeOfProgrammeOther.setDisable(true);
            organizedTypeOfProgrammeOther.setOpacity(0);
            organizedTypeOther = organizedTypeOfProgramme.getSelectionModel().isEmpty() ? "" : organizedTypeOfProgramme.getValue();
           // Validation.isEmpty(organizedTypeOther,"Type Of Programme Other");
        }
    }
    protected ArrayList<String> getAuthorityData() {
        ArrayList<String> authorityData = new ArrayList<String>();
        String titleOfProgramme = organizedTitleOfProgramme.getText();
        String fundsReceived = organizedFundsReceived.getText()==null ? "" : organizedFundsReceived.getText() ;
        String fundsUtilized = organizedFundsUtilized.getText()==null ? "" : organizedFundsUtilized.getText() ;
        String approvingAuthority = organizedApprovingAuthority.getSelectionModel().isEmpty() ? "" : organizedApprovingAuthority.getValue();
        String sponsoringAuthority = organizedSponsoringAuthority.getSelectionModel().isEmpty() ? "" : organizedSponsoringAuthority.getValue();

        Validation.isEmpty(titleOfProgramme,"Title Of Programme");
        if(approvingAuthority.isEmpty()) approvingAuthority = "None";
        if(sponsoringAuthority.isEmpty()) sponsoringAuthority = "None";
        if(!fundsReceived.isEmpty()) Validation.validateDigits(fundsReceived,"Funds Received");
        else fundsReceived = "0";
        if(!fundsUtilized.isEmpty()) Validation.validateDigits(fundsUtilized,"Funds Received");
        else fundsUtilized = "0";
        authorityData.add(titleOfProgramme);
        authorityData.add(approvingAuthority);
        authorityData.add(sponsoringAuthority);
        authorityData.add(fundsReceived);
        authorityData.add(fundsUtilized);

        return authorityData;
    }
    ///////////////////////////////////////////////////SPPU FORM///////////////////////////////////////////////////////
    protected String radioButtonHandler() {
        if(sppuHBOXAssistnatProfessor.isSelected()){
            return "Assistant Professor";
        }
        else if(sppuHBOXAssociateProfessor.isSelected()){
            return "Associate Professor";
        }
        else if(sppuHBOXNo.isSelected()){
            return "No";
        }
        return "NULL";
    }
    ////////////////////////////////////////////////////PUBLICATION FORM///////////////////////////////////////////////
    protected ArrayList<String> getPublicationData() throws ParseException {
        Validation.dialogFlag = 0;
        ArrayList<String> publicationData=new ArrayList<String>();
        String  authorsName=publicationAuthorsName.getText();
        String  correspondenceAuthors=publicationCorrespondenceAuthors.getText()==null ? "" : publicationCorrespondenceAuthors.getText() ;
        String  titleOfPaper=publicationTitleOfPaper.getText();
        String  nameOfJournal=publicationNameOfJournal.getText();
        String  nameOfPublisherOrganizer=publicationNameOfPublisherOrganizer.getText();
        String  link=publicationLink.getText()==null ? "" : publicationLink.getText() ;
        String  monthYear=publicationMonthYear.getValue()==null ? "":publicationMonthYear.getValue().toString();
        String  pageNoFrom=publicationPageNoFrom.getText()==null ? "" : publicationPageNoFrom.getText() ;
        String  ISSN=publicationISSN.getText();
        String  volumeIssueNumber=publicationVolumeIssueNumber.getText()==null ? "" : publicationVolumeIssueNumber.getText() ;
        String  address=publicationAddress.getText();
        String  conferenceDateFrom=publicationConferenceDateFrom.getValue()==null ? "":publicationConferenceDateFrom.getValue().toString();
        String  conferenceDateTo=publicationConferenceDateTo.getValue()==null ? "":publicationConferenceDateTo.getValue().toString();
        String  nameOfFundingAgency=publicationNameOfFundingAgency.getText()==null ? "" : publicationNameOfFundingAgency.getText() ;
        String  fundsReceived=publicationFundsReceived.getText();
        String  impactFactor=publicationImpactFactor.getText()==null ? "" : publicationImpactFactor.getText() ;
        String  citationCount=publicationCitationCount.getText()==null ? "" :publicationCitationCount.getText() ;
        String  scopusIndex=publicationScopusIndex.getText()==null ? "" : publicationScopusIndex.getText() ;
        String  SNIP=publicationSNIP.getText()==null ? "" : publicationSNIP.getText() ;
        String  SJRRank=publicationSJRRank.getText()==null ? "" : publicationSJRRank.getText() ;
        String academicYear = publicationAcademicYear.getSelectionModel().isEmpty() ? "" : publicationAcademicYear.getValue();

        if(publicationHBOXScopus.isSelected()) {
            IndexedBy += "Scopus,";

        }
        if(publicationHBOXWebofScience.isSelected()) {
            IndexedBy += "Web-of-Science,";
        }
        if(publicationHBOXGoogleScholar.isSelected()) {
            IndexedBy += "Google-Scholar,";
        }
        if(publicationHBOXNotIndexed.isSelected()) {
            IndexedBy += "Not-Indexed,";
        }
        if(publicationTypeFlag == 1)
            publicationTypeOther = publicationPublicationTypeOther.getText();
        if(publicationIndexedFlag == 1)
            IndexedBy += publicationIndexedByOther.getText();

        if(correspondenceAuthors.isEmpty()) correspondenceAuthors = "None";
        if(link.isEmpty()) link = "None";
        if(volumeIssueNumber.isEmpty()) volumeIssueNumber = "None";
        if(pageNoFrom.isEmpty()) pageNoFrom = "None";
        if(monthYear.isEmpty()) monthYear = "0000-00-00";
        if(conferenceDateFrom.isEmpty()) conferenceDateFrom = "0000-00-00";
        if(conferenceDateTo.isEmpty()) conferenceDateTo = "0000-00-00";
        else impactFactor = "0";
        if(citationCount.isEmpty()) citationCount = "None";
        if(scopusIndex.isEmpty()) scopusIndex = "None";
        if(nameOfFundingAgency.isEmpty()) nameOfFundingAgency = "None";

        Validation.isEmpty(authorsName,"Authors Name");
        Validation.isEmpty(publicationTypeOther,"Publication Type");
        Validation.isEmpty(titleOfPaper,"Title Of Paper");
        Validation.isEmpty(nameOfJournal,"Name Of Journal");
        Validation.isEmpty(nameOfPublisherOrganizer,"Name Of Publisher/Organizer");
        Validation.isEmpty(academicYear,"Academic Year");
        Validation.isEmpty(ISSN,"ISSN");
        Validation.isEmpty(address,"Address");
        Validation.isEmpty(IndexedBy,"Indexed By");
        if(!impactFactor.isEmpty()) Validation.validateDigits(impactFactor,"Impact Factor");
        if(!SNIP.isEmpty()) Validation.validateDigits(SNIP,"SNIP");
        else SNIP = "0";
        if(!SJRRank.isEmpty()) Validation.validateDigits(SJRRank,"SJR Rank");
        else SJRRank = "0";
        if(!fundsReceived.isEmpty()) Validation.validateDigits(fundsReceived,"SJR Rank");
        else fundsReceived = "0";
        if(!conferenceDateFrom.equals("0000-00-00") && !conferenceDateTo.equals("0000-00-00"))
            Validation.dateDiff(conferenceDateFrom,conferenceDateTo,"Publication");
        publicationData.add(authorsName);
        publicationData.add(correspondenceAuthors);
        publicationData.add(publicationTypeOther);
        publicationData.add(titleOfPaper);
        publicationData.add(nameOfJournal);
        publicationData.add(link);
        publicationData.add(nameOfPublisherOrganizer);
        publicationData.add(address);
        publicationData.add(ISSN);
        publicationData.add(volumeIssueNumber);
        publicationData.add(pageNoFrom);
        publicationData.add(monthYear);
        publicationData.add(conferenceDateFrom);
        publicationData.add(conferenceDateTo);
        publicationData.add(impactFactor);
        publicationData.add(citationCount);
        publicationData.add(scopusIndex);
        publicationData.add(SNIP);
        publicationData.add(SJRRank);
        publicationData.add(IndexedBy);
        publicationData.add(nameOfFundingAgency);
        publicationData.add(fundsReceived);
        publicationData.add(academicYear);
        return publicationData;
    }
    @FXML
    protected void enabledTypeOther(ActionEvent event){
        if(publicationPublicationType.getValue() == "Other") {
            publicationPublicationTypeOther.setOpacity(1);
            publicationPublicationTypeOther.setDisable(false);
            publicationTypeFlag = 1;
        }
        else{
            publicationPublicationTypeOther.setDisable(true);
            publicationPublicationTypeOther.setOpacity(0);
            publicationTypeOther = publicationPublicationType.getSelectionModel().isEmpty() ? "" : publicationPublicationType.getValue();
            publicationTypeFlag = 0;
        }
    }
    @FXML
    protected void enabledIndexedOther(ActionEvent event) {
        if(publicationHBOXOther.isSelected()) {
            publicationIndexedByOther.setOpacity(1);
            publicationIndexedByOther.setDisable(false);
            //publicationIndexedOther = publicationIndexedByOther.getText();
            publicationIndexedFlag = 1;
        } else{
            publicationIndexedByOther.setDisable(true);
            publicationIndexedByOther.setOpacity(0);
            //publicationIndexedOther=" ";
            publicationIndexedFlag = 0;
        }
    }

    ///////////////////////////////////////////////////////ADD MORE BUTTONS////////////////////////////////////////////
    @FXML
    protected void publicationAddMore(ActionEvent event) throws ParseException {
        publicationAddFlag = 1;
        publicationSaveButtonHandle(event);
        if(Validation.dialogFlag==0) {
            publicationAuthorsName.setText(null);
            publicationCorrespondenceAuthors.setText(null);
            publicationPublicationType.setValue(null);
            publicationPublicationTypeOther.setText(null);
            publicationTitleOfPaper.setText(null);
            publicationNameOfJournal.setText(null);
            publicationNameOfPublisherOrganizer.setText(null);
            publicationLink.setText(null);
            publicationMonthYear.setValue(null);
            publicationPageNoFrom.setText(null);
            publicationISSN.setText(null);
            publicationVolumeIssueNumber.setText(null);
            publicationAddress.setText(null);
            publicationConferenceDateFrom.setValue(null);
            publicationConferenceDateTo.setValue(null);
            publicationNameOfFundingAgency.setText(null);
            publicationFundsReceived.setText(null);
            publicationImpactFactor.setText(null);
            publicationCitationCount.setText(null);
            publicationScopusIndex.setText(null);
            publicationSJRRank.setText(null);
            publicationSNIP.setText(null);
            publicationIndexedByOther.setText(null);
            publicationAcademicYear.setValue(null);
            if (publicationHBOXScopus.isSelected()) {
                publicationHBOXScopus.setSelected(false);
            }
            if (publicationHBOXWebofScience.isSelected()) {
                publicationHBOXWebofScience.setSelected(false);
            }
            if (publicationHBOXGoogleScholar.isSelected()) {
                publicationHBOXGoogleScholar.setSelected(false);
            }
            if (publicationHBOXOther.isSelected()) {
                publicationHBOXOther.setSelected(false);
            }
            if (publicationHBOXNotIndexed.isSelected()) {
                publicationHBOXNotIndexed.setSelected(false);
            }
        }
    }
    @FXML
    protected void attendedAddMore(ActionEvent event) throws SQLException,ParseException{
        attendedAddFlag = 1;
        attendedSaveButtonHandle(event);
        if(Validation.dialogFlag==0) {
            attendedTypeOfProgramme.setValue(null);
            attendedTitleOfProgramme.setText(null);
            attendedOrganizerName.setText(null);
            attendedLevelOfProgramme.setValue(null);
            attendedNameOfApprovingAgency.setText(null);
            attendedVenue.setText(null);
            attendedDateFrom.setValue(null);
            attendedDateTo.setValue(null);
            attendedNameOfFundingAgency.setText(null);
            attendedFundsReceived.setText(null);
            attendedAcademicYear.setValue(null);
        }
    }
    @FXML
    protected void organizedAddMore(ActionEvent event) throws ParseException {
        organizedAddFlag = 1;
        organizedSaveButtonHandle(event);
        if(Validation.dialogFlag==0) {
            organizedTitleOfProgramme.setText(null);
            organizedTypeOfProgramme.setValue(null);
            organizedTypeOfProgrammeOther.setText(null);
            organizedLevelOfProgramme.setValue(null);
            organizedApprovingAuthority.setValue(null);
            organizedSponsoringAuthority.setValue(null);
            organizedFundsReceived.setText(null);
            organizedFundsUtilized.setText(null);
            organizedDateFrom.setValue(null);
            organizedDateTo.setValue(null);
            if (organizedHBOXUG.isSelected()) {
                organizedHBOXUG.setSelected(false);
            }
            if (organizedHBOXPG.isSelected()) {
                organizedHBOXPG.setSelected(false);
            }
            if (organizedHBOXPhD.isSelected()) {
                organizedHBOXPhD.setSelected(false);
            }
            if (organizedHBOXFaculties.isSelected()) {
                organizedHBOXFaculties.setSelected(false);
            }
            if (organizedHBOXIndustryPersonnel.isSelected()) {
                organizedHBOXIndustryPersonnel.setSelected(false);
            }
            if (organizedHBOXOther.isSelected()) {
                organizedHBOXOther.setSelected(false);
            }
            organizedNumberOfParticipants.setText(null);
            organizedAcademicYear.setValue(null);
        }
    }
    @FXML
    protected void interactionAddMore(ActionEvent event){
        interactionAddFlag = 1;
        interactionSaveButtonHandle(event);
        if(Validation.dialogFlag==0) {
            interactionRole.setValue(null);
            interactionTitle.setText(null);
            interactionNoOfParticipants.setText(null);
            interactionNoOfDays.setText(null);
            interactionVenue.setText(null);
            interactionProgrammeDate.setValue(null);
            interactionAcademicYear.setValue(null);
            if (interactionTargetStudent.isSelected()) {
                interactionTargetStudent.setSelected(false);
            }
            if (interactionTargetResearch.isSelected()) {
                interactionTargetResearch.setSelected(false);
            }
            if (interactionTargetFaculty.isSelected()) {
                interactionTargetFaculty.setSelected(false);
            }
            if (interactionTargetIndustry.isSelected()) {
                interactionTargetIndustry.setSelected(false);
            }
            if (interactionTargetNonTeaching.isSelected()) {
                interactionTargetNonTeaching.setSelected(false);
            }
        }
    }
    @FXML
    protected void fundedAddMore(ActionEvent event) throws ParseException{
        fundedAddFlag = 1;
        fundedSaveButtonHandle(event);
        if(Validation.dialogFlag==0) {
            fundedRole.setValue(null);
            fundedNameOfCompany.setText(null);
            fundedTitleOfResearch.setText(null);
            fundedSponsoringAgency.setValue(null);
            fundedOtherSponsoringAgency.setDisable(true);
            fundedOtherSponsoringAgency.setText(null);
            fundedSanctiondeAmount.setText(null);
            fundedReceivedAmount.setText(null);
            fundedUtilizedAmount.setText(null);
            fundedStatus.setValue(null);
            fundedFromDate.setValue(null);
            fundedToDate.setValue(null);
            if (fundedHBOXResearchPaper.isSelected())
                fundedHBOXResearchPaper.setSelected(false);
            if (fundedHBOXPatents.isSelected())
                fundedHBOXPatents.setSelected(false);
            if (fundedHBOXBooks.isSelected())
                fundedHBOXBooks.setSelected(false);
            if (fundedHBOXOther.isSelected()) {
                fundedHBOXOther.setSelected(false);
            }
            fundedAcademicYear.setValue(null);
        }
    }
    @FXML
    protected void guestAddMore(ActionEvent event){
        guestSaveButtonHandle(event);
        if(Validation.dialogFlag==0) {
            guestDesignationOfResourcePerson.setText(null);
            guestNameOfResourcePerson.setText(null);
            guestAcademicYear.setValue(null);
            guestResourcePersonOrganization.setText(null);
            guestResourcePersonMobileNo.setText(null);
            guestResourcePersonEmailId.setText(null);
            guestTopic.setText(null);
            guestNumberOfParticipant.setText(null);
            guestRemuneration.setText(null);
            guestDateOfConduction.setValue(null);
            guestAcademicYear.setValue(null);
            if (guestVBOXFE.isSelected()) {
                guestVBOXFE.setSelected(false);
            }
            if (guestVBOXSE.isSelected()) {
                guestVBOXSE.setSelected(false);
            }
            if (guestVBOXTE.isSelected()) {
                guestVBOXTE.setSelected(false);
            }
            if (guestVBOXBE.isSelected()) {
                guestVBOXBE.setSelected(false);
            }
            if (guestVBOXMEI.isSelected()) {
                guestVBOXMEI.setSelected(false);
            }
            if (guestVBOXMEII.isSelected()) {
                guestVBOXMEII.setSelected(false);
            }
            if (guestVBOXPhD.isSelected()) {
                guestVBOXPhD.setSelected(false);
            }
            if (guestVBOXFaculty.isSelected()) {
                guestVBOXFaculty.setSelected(false);
            }
            if (guestVBOXMBAI.isSelected()) {
                guestVBOXMBAI.setSelected(false);
            }
            if (guestVBOXMBAII.isSelected()) {
                guestVBOXMBAII.setSelected(false);
            }
        }
    }

    //PANE CHANGE
    @FXML
    public void paneChange(ActionEvent event) {
        educationalPane.setPrefHeight(1100);
        publicationPane.setPrefHeight(1100);
        vBox.setPrefHeight(1100);
        Button button = (Button) event.getSource();
        if(personalCallButton.equals(button)) {
            personalPane.toFront();
        } else if(educationalCallButton.equals(button)) {
            educationalPane.toFront();
            educationalPane.setPrefHeight(2200);
            vBox.setPrefHeight(2200);
            System.out.println(" Educational ");
        } else if(sppuCallButton.equals(button)) {
            SPPUPane.toFront();
            System.out.println(" Sppu ");
        } else if(publicationCallButton.equals(button)) {
            publicationPane.toFront();
            publicationPane.setPrefHeight(1600);
            vBox.setPrefHeight(1600);
            System.out.println(" Publication ");
        } else if(attendedCallButton.equals(button)) {
            attendedPane.toFront();
            System.out.println(" Attended ");
        } else if(organizedCallButton.equals(button)) {
            organizedPane.toFront();
            System.out.println(" Organized ");
        } else if(outsideWorldCallButton.equals(button)) {
            interactionPane.toFront();
        } else if(fundedCallButton.equals(button)) {
            fundedPane.toFront();
            System.out.println(" Funded ");
        } else if(guestLectureCallButton.equals(button)) {
            guestPane.toFront();
            System.out.println(" Guest ");
        } else if (logoutCallButton.equals(button)) {
            System.out.println(" Log OUT ");
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
        vBox.toFront();
    }
}
