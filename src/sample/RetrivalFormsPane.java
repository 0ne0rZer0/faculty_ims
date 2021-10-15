package sample;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Year;
import java.util.*;

import static sample.Main.jdbcClass;

public class RetrivalFormsPane extends RegistrationForms implements Initializable {
    @FXML
    private JFXTextField attendedDuration;
    @FXML
    public Pane organizedRetrivalPane;
    @FXML
    public Pane fundedRetrivalPane;
    @FXML
    public Pane attendedRetrivalPane;
    @FXML
    public Pane guestRetrivalPane;
    @FXML
    public Pane interactionRetrivalPane;
    @FXML
    public Pane workingPane;
    @FXML
    public Pane publicationRetrivalPane;
    @FXML
    private JFXTextField organizedDuration;
    @FXML
    private DatePicker eduWorkDateOfJoining;
    @FXML
    private DatePicker eduWorkDateOfLeaving;
    @FXML
    private JFXTextField eduWorkPosition;
    @FXML
    private JFXTextField eduWorkName;
    @FXML
    private Label publicationPublicationTypeOtherLabel;
    @FXML
    private Label organizedTypeOfProgrammeOtherLabel;
    @FXML
    private Label fundedOtherSponsoringAgencyLabel;
    @FXML
    private AnchorPane guestRetrivalAnchorPane;
    @FXML
    private AnchorPane attendedRetrivalAnchorPane;
    @FXML
    private AnchorPane organizedRetrivalAnchorPane;
    @FXML
    private AnchorPane workingRetrivalAnchorPane;
    @FXML
    private AnchorPane fundedRetrivalAnchorPane;
    @FXML
    private AnchorPane interactionRetrivalAnchorPane;
    @FXML
    private AnchorPane publicationRetrivalAnchorPane;

    private static String firstColumn;
    private static int flag = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Edit Pane
        guestRetrivalAnchorPane.setDisable(true);
        attendedRetrivalAnchorPane.setDisable(true);
        organizedRetrivalAnchorPane.setDisable(true);
        fundedRetrivalAnchorPane.setDisable(true);
        interactionRetrivalAnchorPane.setDisable(true);
        workingRetrivalAnchorPane.setDisable(true);
        publicationRetrivalAnchorPane.setDisable(true);

        publicationRetrivalPane.setPrefHeight(1100);
        organizedRetrivalPane.setPrefHeight(1100);
        guestRetrivalPane.setPrefHeight(1100);
        List<String> academicYearList = new ArrayList<>();
        for (int i = Year.now().getValue(); i >= 1995; i--) {
            academicYearList.add(i + "-" + (i + 1));
        }
        switch (this.flag) {
            case 8:
                getWorkExperienceData(firstColumn);
                workingPane.toFront();
                break;
            case 10:
                getPublicationData(firstColumn);
                publicationRetrivalPane.toFront();
                publicationRetrivalPane.setPrefHeight(2100);
                break;
            case 11:
                System.out.println("Switch Case Initialize with flag " + flag);
                getAttendedData(firstColumn);
                System.out.println("Pane with flag  " + flag);
                attendedRetrivalPane.toFront();
                break;
            case 12:
                getOrganizedData(firstColumn);
                organizedRetrivalPane.toFront();
                organizedRetrivalPane.setPrefHeight(1200);
                break;
            case 13:
                getInteractionData(firstColumn);
                interactionRetrivalPane.toFront();
                break;
            case 14:
                getFundedData(firstColumn);
                fundedRetrivalPane.toFront();
                break;
            case 15:
                getGuestData(firstColumn);
                guestRetrivalPane.toFront();
                guestRetrivalPane.setPrefHeight(1200);
                break;
        }
        academicYearComboBox.setAll(academicYearList);
        attendedAcademicYear.setItems(academicYearComboBox);
        organizedAcademicYear.setItems(academicYearComboBox);
        //ATTENDED COMBO BOX
        attendedTypeOfProgramme.setItems(attendedtypeofprogramme);
        attendedLevelOfProgramme.setItems(attendedlevelofprogramme);

        //ORGANIZED COMBO BOX
        organizedTypeOfProgramme.setItems(organizedtypeOfProgramme);
        organizedLevelOfProgramme.setItems(organizedlevelOfProgramme);
        organizedTypeOfProgramme.setItems(organizedtypeOfProgramme);
        organizedLevelOfProgramme.setItems(organizedlevelOfProgramme);
        organizedSponsoringAuthority.setItems(organizedsponsoringAuthority);
        organizedApprovingAuthority.setItems(organizedapprovingAuthority);

        //Guest
        guestAcademicYear.setItems(academicYearComboBox);

        //Publication
        publicationIndexedByOther.setOpacity(0);
        publicationIndexedByOther.setDisable(true);
        publicationAcademicYear.setItems(academicYearComboBox);
        publicationPublicationType.setItems(publicationTypeList);

        //Funded
        //fundedOtherSponsoringAgency.setDisable(true);
        //fundedOtherSponsoringAgency.setOpacity(0);
        fundedRole.setItems(fundedRoleList);
        fundedSponsoringAgency.setItems(fundedSponsoringList);
        fundedStatus.setItems(fundedStatusList);
        fundedAcademicYear.setItems(academicYearComboBox);
    }

    /*This Method is called in RetriveController it will Choose appropriate Form when we clicked on Table Entry
    * then Flag will be set and pane will be generated for that form*/
    public void generateData(int flag, String firstColumn) {
        this.firstColumn = firstColumn;
        this.flag = flag;
        System.out.println("generateData");
        showPane();
    }

    //Attended get Data
    private void getAttendedData(String firstColumn) {
        System.out.println("get Attended Data");
        PreparedStatement attended = null;
        ResultSet resultSet;
        String attendedQuery = "select * from attended where RegistrationNo ='"+Login.REGISTRATION_NO+"' and TitleOfProgramme='" + firstColumn + "'";
        try {
            attended = jdbcClass.connection.prepareStatement(attendedQuery);
            resultSet = jdbcClass.fireQuery(attended);
            while (resultSet.next()) {
                attendedTypeOfProgramme.getSelectionModel().select(resultSet.getString("TypeOfProgramme"));
                attendedTitleOfProgramme.setText(resultSet.getString("TitleOfProgramme"));
                attendedOrganizerName.setText(resultSet.getString("OrganizerName"));
                attendedLevelOfProgramme.getSelectionModel().select(resultSet.getString("LevelOfProgramme"));
                attendedNameOfApprovingAgency.setText(resultSet.getString("NameOfApprovingAgency"));
                attendedVenue.setText(resultSet.getString("Venue"));
                attendedDateFrom.setValue(LocalDate.parse(resultSet.getString("DateFrom")));
                attendedDateTo.setValue(LocalDate.parse(resultSet.getString("DateTo")));
                attendedDuration.setText(resultSet.getString("Duration"));
                attendedNameOfFundingAgency.setText(resultSet.getString("NameOfFundingAgency"));
                attendedFundsReceived.setText(resultSet.getString("FundsReceived"));
                attendedAcademicYear.getSelectionModel().select(resultSet.getString("AcademicYear"));
            }
        } catch (SQLException e) {

        }
    }

    //Organized get Data
    private void getOrganizedData(String firstColumn) {
        System.out.println("get Organized Data");
        PreparedStatement organized = null;
        PreparedStatement authority = null;
        ResultSet authorityResultSet;
        ResultSet resultSet;
        String organizedQuery = "select * from organized where RegistrationNo ='"+Login.REGISTRATION_NO+"' and TitleOfProgramme='" + firstColumn + "'";
        String authorityQuery = "select * from authority where RegistrationNo ='"+Login.REGISTRATION_NO+"' and TitleOfProgramme='" + firstColumn + "'";

        try {
            organized = jdbcClass.connection.prepareStatement(organizedQuery);
            resultSet = jdbcClass.fireQuery(organized);
            authority = jdbcClass.connection.prepareStatement(authorityQuery);
            authorityResultSet = jdbcClass.fireQuery(authority);
            while (resultSet.next()) {
                //organized data
                organizedTitleOfProgramme.setText(resultSet.getString("TitleOfProgramme"));
                String type = resultSet.getString("TypeOfProgramme") ;
                if(type!= "STTP" || type!="FDP" || type!= "Seminar" || type!= "Workshop" || type!= "Industrial Training Programme" || type!= "Conference")  {
                    organizedTypeOfProgrammeOther.setDisable(false);
                    organizedTypeOfProgrammeOther.setOpacity(1);
                    organizedTypeOfProgrammeOtherLabel.setOpacity(1);
                    organizedTypeOfProgrammeOtherLabel.setDisable(false);
                    organizedTypeOfProgrammeOther.setText(type);
                    organizedTypeOfProgramme.getSelectionModel().select("Other");
                }
                else {
                    organizedTypeOfProgrammeOtherLabel.setOpacity(0);
                    organizedTypeOfProgrammeOtherLabel.setDisable(true);
                    organizedTypeOfProgrammeOther.setDisable(true);
                    organizedTypeOfProgrammeOther.setOpacity(0);
                    organizedTypeOfProgramme.getSelectionModel().select(type);
                }
                organizedLevelOfProgramme.getSelectionModel().select(resultSet.getString("LevelOfProgramme"));
                organizedDateFrom.setValue(LocalDate.parse(resultSet.getString("DateFrom")));
                organizedDateTo.setValue(LocalDate.parse(resultSet.getString("DateTo")));
                organizedDuration.setText(resultSet.getString("Duration"));
                organizedNumberOfParticipants.setText(resultSet.getString("NoOfParticipants"));

                String targetResult = resultSet.getString("TargetAudience");
                String[] target = targetResult.split("," ,0);

                for(int i = 0 ; i<target.length ; i++) {
                    if(target[i].equals("UG Students")){
                        System.out.println("UG");
                        organizedHBOXUG.setSelected(true);
                    }
                    if(target[i].equals("PG Students")){
                        System.out.println("PG");
                        organizedHBOXPG.setSelected(true);
                    }
                    if(target[i].equals("Doctoral(PhD)Students")) {
                        System.out.println("PhD");
                        organizedHBOXPhD.setSelected(true);
                    }
                    if(target[i].equals("Faculties")){
                        System.out.println("Faculty");
                        organizedHBOXFaculties.setSelected(true);
                    }
                    if(target[i].equals("Industry Personnel")){
                        System.out.println("Industry");
                        organizedHBOXIndustryPersonnel.setSelected(true);
                    }
                    if(target[i].equals("Other")){
                        System.out.println("Other");
                        organizedHBOXOther.setSelected(true);
                    }
                }
                organizedAcademicYear.getSelectionModel().select(resultSet.getString("AcademicYear"));
                organizedNameOfResourcePerson.setText(resultSet.getString("NameOfResourcePerson"));
                organizedMobileNo.setText(resultSet.getString("ContactNumber"));
                organizedEmailId.setText(resultSet.getString("EmailId"));
            }
            while(authorityResultSet.next()) {
                //authority Data
                organizedApprovingAuthority.getSelectionModel().select(authorityResultSet.getString("ApprovingAuthority"));
                organizedSponsoringAuthority.getSelectionModel().select(authorityResultSet.getString("SponsoringAuthority"));
                organizedFundsReceived.setText(authorityResultSet.getString("FundsReceived"));
                organizedFundsUtilized.setText(authorityResultSet.getString("FundsUtilized"));
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    protected void enabledOther(ActionEvent event){
        if(organizedTypeOfProgramme.getValue() == "Other"){
            organizedTypeOfProgrammeOtherLabel.setOpacity(1);
            organizedTypeOfProgrammeOtherLabel.setDisable(false);
            organizedTypeOfProgrammeOther.setOpacity(1);
            organizedTypeOfProgrammeOther.setDisable(false);
            organizedFlag = 1;
        }
        else{
            //organizedFlag = 0;
            organizedTypeOfProgrammeOtherLabel.setOpacity(0);
            organizedTypeOfProgrammeOtherLabel.setDisable(true);
            organizedTypeOfProgrammeOther.setDisable(true);
            organizedTypeOfProgrammeOther.setOpacity(0);
            organizedTypeOther = organizedTypeOfProgramme.getValue();
        }
    }

    //Guest get Data
    private void getGuestData(String firstColumn) {

        PreparedStatement guest = null;
        ResultSet resultSet;
        String guestQuery = "select * from guestlecture where RegistrationNo ='"+Login.REGISTRATION_NO+"' and Topics='" + firstColumn + "'";

        try {
            guest = jdbcClass.connection.prepareStatement(guestQuery);
            resultSet = jdbcClass.fireQuery(guest);

            while (resultSet.next()) {
                guestTopic.setText(resultSet.getString("Topics"));
                guestNameOfResourcePerson.setText(resultSet.getString(("NameOfResourcePerson")));
                guestDesignationOfResourcePerson.setText(resultSet.getString("DesignationOfResourcePerson"));
                guestResourcePersonOrganization.setText(resultSet.getString("ResourcePersonOrganization"));
                guestResourcePersonMobileNo.setText(resultSet.getString("ResourcePersonMobileNo"));
                guestResourcePersonEmailId.setText(resultSet.getString("ResourcePersonEmailId"));
                String targetResult = resultSet.getString("TargetAudience");
                String[] target = targetResult.split("," ,0);

                for(int i = 0 ;i <target.length;i++) {
                    if(target[i].equals("FE Students")) {
                        guestVBOXFE.setSelected(true);
                    }
                    if(target[i].equals("SE Students")) {
                        guestVBOXSE.setSelected(true);
                    }
                    if(target[i].equals("TE Students")) {
                        guestVBOXTE.setSelected(true);
                    }
                    if(target[i].equals("BE Students")) {
                        guestVBOXBE.setSelected(true);
                    }
                    if(target[i].equals("ME-I Students")) {
                        guestVBOXMEI.setSelected(true);
                    }
                    if(target[i].equals("ME-II Students")) {
                        guestVBOXMEII.setSelected(true);
                    }
                    if(target[i].equals("Doctoral(PhD) Students")) {
                        guestVBOXPhD.setSelected(true);
                    }
                    if(target[i].equals("Faculty")) {
                        guestVBOXFaculty.setSelected(true);
                    }
                    if(target[i].equals("MBA-I")) {
                        guestVBOXMBAI.setSelected(true);
                    }
                    if(target[i].equals("MBA-II")) {
                        guestVBOXMBAII.setSelected(true);
                    }
                }
                guestNumberOfParticipant.setText(resultSet.getString("NumberOfParticipants"));
                guestRemuneration.setText(resultSet.getString("Remuneration"));
                guestDateOfConduction.setValue(LocalDate.parse(resultSet.getString("DateOfConduction")));
                guestAcademicYear.getSelectionModel().select(resultSet.getString("AcademicYear"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //Interaction get Data
    private void getInteractionData(String firstColumn) {
        PreparedStatement interaction;
        ResultSet interactionResultSet;
        String interactionQuery = "select * from interaction where RegistrationNo ='"+Login.REGISTRATION_NO+"' and Particulars ='"+firstColumn+"' and ProgramDate = '"+RetrivalForms.thirdColumn+"' and AcademicYear='"+RetrivalForms.fourthColumn+"'";
        try {
            interaction = jdbcClass.connection.prepareStatement(interactionQuery);
            personalCategory.setItems(personalCategoryList);
            interactionResultSet = jdbcClass.fireQuery(interaction);
            while(interactionResultSet.next()) {
                interactionRole.getSelectionModel().select(interactionResultSet.getString("RoleofFaculty"));
                interactionTitle.setText(interactionResultSet.getString(3));
                interactionVenue.setText(interactionResultSet.getString(4));
                interactionProgrammeDate.setValue(LocalDate.parse(interactionResultSet.getString(5)));
                //System.out.println(interactionResultSet.getString(5));
                String[] selectedAll = interactionResultSet.getString(6).split(" ",0);
                for(String a : selectedAll) {
                    System.out.println(a);
                    switch (a) {
                        case "Student":
                            interactionTargetStudent.setSelected(true);
                            break;
                        case "Research":
                        case "Scholar":
                            interactionTargetResearch.setSelected(true);
                            break;
                        case "Faculty":
                            interactionTargetFaculty.setSelected(true);
                            break;
                        case "Industry":
                        case "Person":
                            interactionTargetIndustry.setSelected(true);
                            break;
                        case "Non":
                        case "Teaching":
                        case "Staff":
                            interactionTargetNonTeaching.setSelected(true);
                            break;
                    }
                }
                interactionNoOfParticipants.setText(interactionResultSet.getString(7));
                interactionNoOfDays.setText(interactionResultSet.getString(8));
                interactionAcademicYear.getSelectionModel().select(interactionResultSet.getString(9));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Funded Get Data
    private void getFundedData(String firstColumn){
        PreparedStatement funded = null;
        ResultSet fundedresultSet;
        String fundedQuery = "select * from fundedresearchproduct where RegistrationNo ='"+Login.REGISTRATION_NO+"' and TitleofResearchProduct ='"+firstColumn+"'";
        try {
            funded = Main.jdbcClass.connection.prepareStatement(fundedQuery);
            fundedresultSet = Main.jdbcClass.fireQuery(funded);
            while(fundedresultSet.next()) {
                fundedRole.getSelectionModel().select(fundedresultSet.getString("Role"));
                fundedNameOfCompany.setText(fundedresultSet.getString("InCollaborationWith"));
                fundedTitleOfResearch.setText(fundedresultSet.getString("TitleofResearchProduct"));
                String agency = fundedresultSet.getString("SponsoringAgency");
                if(agency.equals("BCUD") || agency.equals("AICTC") || agency.equals("ISTE") || agency.equals("MODROBS") || agency.equals("TEQIP")){
                    fundedOtherSponsoringAgencyLabel.setOpacity(0);
                    fundedOtherSponsoringAgencyLabel.setDisable(true);
                    fundedOtherSponsoringAgency.setOpacity(0);
                    fundedOtherSponsoringAgency.setDisable(true);
                    fundedSponsoringAgency.getSelectionModel().select(agency);
                }
                else{
                    fundedOtherSponsoringAgencyLabel.setOpacity(1);
                    fundedOtherSponsoringAgencyLabel.setDisable(false);
                    fundedOtherSponsoringAgency.setOpacity(1);
                    fundedOtherSponsoringAgency.setDisable(false);
                    fundedOtherSponsoringAgency.setText(agency);
                    fundedSponsoringAgency.getSelectionModel().select("OTHER");
                }
                fundedSanctiondeAmount.setText(fundedresultSet.getString("SanctionedAmount"));
                fundedReceivedAmount.setText(fundedresultSet.getString("ReceivedAmount"));
                fundedUtilizedAmount.setText(fundedresultSet.getString("UtilizedAmount"));
                fundedFromDate.setValue(LocalDate.parse(fundedresultSet.getString("FromDate")));
                fundedToDate.setValue(LocalDate.parse(fundedresultSet.getString("ToDate")));
                fundedStatus.getSelectionModel().select(fundedresultSet.getString("Status"));
                fundedAcademicYear.getSelectionModel().select(fundedresultSet.getString("AcademicYear"));

                String OutCome = fundedresultSet.getString("Outcome");
                String[] out = OutCome.split(",",0);
                for(int i = 0;i <out.length;i++) {
                    if (out[i].equals("Research Paper")) {
                        fundedHBOXResearchPaper.setSelected(true);
                    }else if (out[i].equals("Patents")) {
                        fundedHBOXPatents.setSelected(true);
                    } else if (out[i].equals("Books")) {
                        fundedHBOXBooks.setSelected(true);
                    }else if (out[i].equals("Other-for-MBA")) {
                        fundedHBOXOther.setSelected(true);
                    }
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    protected void enableSponsoring(javafx.event.ActionEvent event){
        if(fundedSponsoringAgency.getValue()=="OTHER") {
            fundedFlag = 1;
            fundedOtherSponsoringAgencyLabel.setOpacity(1);
            fundedOtherSponsoringAgencyLabel.setDisable(false);
            fundedOtherSponsoringAgency.setOpacity(1);
            fundedOtherSponsoringAgency.setDisable(false);
        }
        else{
            fundedFlag = 0;
            fundedOtherSponsoringAgencyLabel.setOpacity(0);
            fundedOtherSponsoringAgencyLabel.setDisable(true);
            fundedOtherSponsoringAgency.setOpacity(0);
            fundedOtherSponsoringAgency.setDisable(true);
            sponsoringAgency = fundedSponsoringAgency.getValue();
        }
    }

    //Work Get Data
    private void getWorkExperienceData(String firstColumn){
        System.out.println("get Working Data");
        PreparedStatement work = null;
        ResultSet resultSet;
        String workQuery = "select * from experiencebeforejoiningstes where RegistrationNo ='"+Login.REGISTRATION_NO+"'and Field='" + firstColumn + "' and DateOfJoining='"+RetrivalForms.thirdColumn+"'";
        try{
            work = jdbcClass.connection.prepareStatement(workQuery);
            resultSet = jdbcClass.fireQuery(work);
            while (resultSet.next()){
                eduWorkDateOfJoining.setValue(LocalDate.parse(resultSet.getString("DateOfJoining")));
                eduWorkDateOfLeaving.setValue(LocalDate.parse(resultSet.getString("DateOfLeaving")));
                eduWorkPosition.setText(resultSet.getString("Position"));
                eduWorkName.setText(resultSet.getString("NameOfInstitute"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //Publication Get Data
    private void getPublicationData(String firstColumn){
        PreparedStatement publication = null;
        ResultSet resultSet;
        String publicationQuery = "select * from publication where RegistrationNo ='"+Login.REGISTRATION_NO+"'and TitleOfPaper='"+firstColumn+"'"; //TitleOfPaper or any
        try {
            publication = Main.jdbcClass.connection.prepareStatement(publicationQuery);
            resultSet = Main.jdbcClass.fireQuery(publication);
            while(resultSet.next()) {
                publicationAuthorsName.setText(resultSet.getString("AuthorsName"));
                publicationCorrespondenceAuthors.setText(resultSet.getString("CorrespondanceAuthor"));
                String type = resultSet.getString("PublicationType");
                if(type!="National Conference" || type!="International Conference" || type!="National Journal" || type!="International Journal" || type!="White Paper" || type!="Book(s)" || type!="Monographs") {
                    publicationPublicationTypeOther.setOpacity(1);
                    publicationPublicationTypeOther.setDisable(false);
                    publicationPublicationTypeOtherLabel.setOpacity(1);
                    publicationPublicationTypeOtherLabel.setDisable(false);
                    publicationPublicationType.getSelectionModel().select("Other");
                    publicationPublicationTypeOther.setText(type);
                }else{
                    publicationPublicationTypeOther.setOpacity(0);
                    publicationPublicationTypeOther.setDisable(true);
                    publicationPublicationTypeOtherLabel.setOpacity(0);
                    publicationPublicationTypeOtherLabel.setDisable(true);
                    publicationPublicationType.getSelectionModel().select(type);
                }
                publicationTitleOfPaper.setText(resultSet.getString("TitleOfPaper"));
                publicationNameOfJournal.setText(resultSet.getString("NameOfJournalConference"));
                publicationNameOfPublisherOrganizer.setText(resultSet.getString("NameOfPublisherOrganizer"));
                publicationLink.setText(resultSet.getString("Link"));
                publicationMonthYear.setValue(LocalDate.parse(resultSet.getString("MonthAndYearOfJournalPublication")));
                publicationPageNoFrom.setText(resultSet.getString("PageNo"));
                publicationISSN.setText(resultSet.getString("ISSNISBNDOI"));
                publicationVolumeIssueNumber.setText(resultSet.getString("VolumeAndIssueNumber"));
                publicationAddress.setText(resultSet.getString("Venue"));
                publicationConferenceDateFrom.setValue(LocalDate.parse(resultSet.getString("FromDateofConferencePublication")));
                publicationConferenceDateTo.setValue(LocalDate.parse(resultSet.getString("ToDateofConferencePublication")));
                publicationNameOfFundingAgency.setText(resultSet.getString("NameOfFundingAgency"));
                publicationFundsReceived.setText(resultSet.getString("FundsRecieved"));
                publicationImpactFactor.setText(resultSet.getString("ImpactFactor"));
                publicationCitationCount.setText(resultSet.getString("CitiationCount"));
                publicationScopusIndex.setText(resultSet.getString("ScopusIndex"));
                publicationSNIP.setText(resultSet.getString("SNIP"));
                publicationSJRRank.setText(resultSet.getString("SJRRank"));
                publicationAcademicYear.getSelectionModel().select(resultSet.getString("AcademicYear"));
            }
        }catch(SQLException e){

        }
    }
    protected void enabledTypeOther(ActionEvent event){
        if(publicationPublicationType.getValue() == "Other") {
            publicationPublicationTypeOtherLabel.setOpacity(1);
            publicationPublicationTypeOtherLabel.setDisable(false);
            publicationPublicationTypeOther.setOpacity(1);
            publicationPublicationTypeOther.setDisable(false);
        }
        else{
            publicationPublicationTypeOtherLabel.setOpacity(0);
            publicationPublicationTypeOtherLabel.setDisable(true);
            publicationPublicationTypeOther.setDisable(true);
            publicationPublicationTypeOther.setOpacity(0);
            publicationTypeOther = publicationPublicationType.getValue();
        }
    }

    ////////////////////////////////////////////////////////UPDATE BUTTONS/////////////////////////////////////////////
    @FXML
    private void workingExperienceHandle(ActionEvent event) throws SQLException{
        PreparedStatement workExperience = null;
        ArrayList<String> data = new ArrayList<>();
        data.add(this.firstColumn);
        data.add(eduWorkName.getText());
        data.add(eduWorkPosition.getText());
        data.add(eduWorkDateOfJoining.getValue().toString());
        data.add(eduWorkDateOfLeaving.getValue().toString());

        String workStringExperienceTeaching = "update experiencebeforejoiningstes set Field = ?, NameOfInstitute = ?, Position = ?, DateOfJoining = ?, DateOfLeaving = ? where RegistrationNo = ? and Field = ? ";
        workExperience = jdbcClass.connection.prepareStatement(workStringExperienceTeaching);
        workExperience.setInt(6,Login.REGISTRATION_NO);
        workExperience.setString(7,this.firstColumn);
        for(int i= 1;i <=5;i++){
            workExperience.setString(i,data.get(i-1));
        }
        jdbcClass.fireQuery(workExperience);
        JOptionPane.showMessageDialog(null, "Data is Updated");
        ((Node)event.getSource()).getScene().getWindow().hide();

    }
    @Override
    protected void fundedSaveButtonHandle(javafx.event.ActionEvent event) throws ParseException {      //Make this method public in main code
        enableSponsoring(event);
        PreparedStatement funded = null;
        ArrayList<String> fundedData = getFundedData();
        try {
            String fundedQuery = "update fundedresearchproduct set Role = ?,InCollaborationWith = ?, TitleofResearchProduct = ?, SponsoringAgency = ?, Status = ?, SanctionedAmount = ?, ReceivedAmount = ?, UtilizedAmount = ?, Outcome = ?, FromDate = ?, ToDate = ?, AcademicYear = ? where RegistrationNo = ? and TitleofResearchProduct =?";
            funded = jdbcClass.connection.prepareStatement(fundedQuery);
            funded.setInt(13, Login.REGISTRATION_NO);
            funded.setString(14, this.firstColumn);
            for (int i = 1; i < 13; i++) {
                if (i != 7 || i != 8 || i != 9)
                    funded.setString(i, fundedData.get(i - 1));
                else
                    funded.setInt(i, Integer.parseInt(fundedData.get(i - 1)));
            }
            System.out.println(funded);
            jdbcClass.fireQuery(funded);
            JOptionPane.showMessageDialog(null, "Data is Updated");
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Database error in Funded Form");
            e.printStackTrace();
        }
    }
    //Attended Update
    @Override
    protected void attendedSaveButtonHandle(ActionEvent event) throws ParseException {
        PreparedStatement attendedQuery = null;
        ArrayList<String> attendedData = getAttendedData();
        String attendedStringQuery = "update attended set TypeOfProgramme = ?, TitleOfProgramme = ?, OrganizerName = ?, LevelOfProgramme = ?,NameofApprovingAgency = ?,Venue= ?,DateFrom = ?,DateTo = ?,Duration = ?,NameOfFundingAgency = ?,FundsReceived = ?,AcademicYear = ? where RegistrationNo = ? and TitleOfProgramme = ?";
        System.out.println(attendedData);
        try {
            attendedQuery = jdbcClass.connection.prepareStatement(attendedStringQuery);
            attendedQuery.setInt(13, Login.REGISTRATION_NO);
            attendedQuery.setString(14 ,this.firstColumn);
            for (int i = 1; i <= 12; ++i) {
                if (i != 9 || i != 11) {
                    attendedQuery.setString(i, attendedData.get(i - 1));
                } else {
                    attendedQuery.setInt(i, Integer.parseInt(attendedData.get(i - 1)));
                }
            }
            jdbcClass.fireQuery(attendedQuery);
            JOptionPane.showMessageDialog(null, "Data is Updated");
            ((Node)event.getSource()).getScene().getWindow().hide();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    //Organized Update
    protected  void organizedSaveButtonHandle(ActionEvent event) throws ParseException {
        enabledOther(event);
        PreparedStatement organizedQuery ;
        PreparedStatement authorityQuery ;
        ArrayList<String> organizedData = getOrganizedData();
        ArrayList<String> authorityData = getAuthorityData();
        String organizedStringQuery = "update organized set TitleOfProgramme = ?, TypeOfProgramme = ?,LevelOfProgramme = ?,DateFrom = ?,DateTo = ?,Duration = ?,NoOfParticipants = ?,TargetAudience = ?,AcademicYear = ?,NameOfResourcePerson= ?,ContactNumber = ?,EmailId = ? where RegistrationNo = ? and TitleOfProgramme = ?" ;
        String authorityStringQuery = "update authority set TitleOfProgramme = ?,ApprovingAuthority =?,SponsoringAuthority = ?,FundsReceived = ?,FundsUtilized =? where RegistrationNo = ? and TitleOfProgramme = ?";
        System.out.println(organizedData);
        System.out.println(authorityData);
        try {
            organizedQuery = jdbcClass.connection.prepareStatement(organizedStringQuery);
            organizedQuery.setInt(13, Login.REGISTRATION_NO);
            organizedQuery.setString(14,this.firstColumn);
            for (int i = 1; i < 13; ++i) {
                if (i != 6 || i != 7 || i != 11) {
                    organizedQuery.setString(i, organizedData.get(i - 1));
                } else {
                    organizedQuery.setInt(i, Integer.parseInt(organizedData.get(i - 1)));
                }
            }
            authorityQuery = jdbcClass.connection.prepareStatement(authorityStringQuery);
            authorityQuery.setInt(6, Login.REGISTRATION_NO);
            authorityQuery.setString(7,this.firstColumn);
            for (int i = 1; i < 6; ++i) {
                if (i != 4 || i != 5) {
                    authorityQuery.setString(i, authorityData.get(i - 1));
                } else {
                    authorityQuery.setInt(i, Integer.parseInt(organizedData.get(i - 1)));
                }
            }
            jdbcClass.fireQuery(organizedQuery);
            jdbcClass.fireQuery(authorityQuery);
            JOptionPane.showMessageDialog(null, "Data is Updated");
            ((Node)event.getSource()).getScene().getWindow().hide();
            } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Publication Update Button
    @Override
    protected void publicationSaveButtonHandle(ActionEvent event) throws ParseException {
        enabledTypeOther(event);
        enabledIndexedOther(event);
        PreparedStatement publicationQuery;
        ArrayList<String> publicationData = getPublicationData();
        String publicationStringQuery = "update publication set AuthorsName = ?,CorrespondanceAuthor = ?,PublicationType = ?,TitleOfPaper = ?,NameOfJournalConference = ?,Link = ?,NameOfPublisherOrganizer = ?,Venue = ?,ISSNISBNDOI=?,VolumeAndIssueNumber = ?,PageNo = ?," +
                "MonthAndYearOfJournalPublication = ?,FromDateofConferencePublication = ?,ToDateofConferencePublication = ?,ImpactFactor = ?,CitiationCount = ?,ScopusIndex = ?,SNIP = ?,SJRRank = ?,IndexedBy = ?,NameOfFundingAgency = ?,FundsRecieved=? where RegistrationNo = ? and TitleOfPaper = ?";
        try {
            publicationQuery =  jdbcClass.connection.prepareStatement(publicationStringQuery);
            publicationQuery.setInt(23,Login.REGISTRATION_NO);
            publicationQuery.setString(24,this.firstColumn);
            for(int i = 1; i < 23 ; ++i ) {
                if(i==22)
                    publicationQuery.setLong(i,Long.parseLong(publicationData.get(i-1)));
                else if(i == 15 || i==18 || i==19)
                    publicationQuery.setFloat(i,Float.parseFloat(publicationData.get(i-1)));
                else
                    publicationQuery.setString(i,publicationData.get(i-1));
            }
            System.out.println(publicationQuery.toString());
            jdbcClass.fireQuery(publicationQuery);
            JOptionPane.showMessageDialog(null,"Data is Updated");
            ((Node)event.getSource()).getScene().getWindow().hide();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Guest Update Button
    @Override
    protected void guestSaveButtonHandle(ActionEvent event) {
        PreparedStatement guestQuery;
        ArrayList<String> guestData = getGuestData();
        String guestStringQuery = "update guestlecture set Topics = ? ,NameOfResourcePerson = ?, DesignationOfResourcePerson = ? ,ResourcePersonOrganization = ?,ResourcePersonMobileNo = ?," +
                " ResourcePersonEmailId = ?, TargetAudience = ?, NumberOfParticipants = ? , Remuneration = ?, DateOfConduction = ?, AcademicYear = ?  where RegistrationNo = ? and Topics = ?";
        try {
            guestQuery = jdbcClass.connection.prepareStatement(guestStringQuery);
            guestQuery.setInt(12, Login.REGISTRATION_NO);
            guestQuery.setString(13,this.firstColumn);
            for(int i=1 ;i<12 ;++i) {
                if(i==5 || i==9){
                    guestQuery.setLong(i,Long.parseLong(guestData.get(i - 1)));
                }
                else if(i==8){
                    guestQuery.setInt(i,Integer.parseInt(guestData.get(i - 1)));
                }
                else {
                    guestQuery.setString(i, guestData.get(i - 1));
                }
            }
            System.out.println(guestQuery.toString());
            jdbcClass.fireQuery(guestQuery);
            JOptionPane.showMessageDialog(null,"Data is Updated");
            ((Node)event.getSource()).getScene().getWindow().hide();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    /////////////////////////////////////////////////////Edit Button///////////////////////////////////////////
    private int guestEditFlag = 1;
    private int fundedEditFlag = 1;
    private int interactionEditFlag = 1;
    private int publicationEditFlag = 1;
    private int workingEditFlag = 1;
    private int attendedEditFlag = 1;
    private int organizedEditFlag = 1;
    @FXML
    private void guestEdit(ActionEvent event){
        if(guestEditFlag == 1) {
            guestRetrivalAnchorPane.setDisable(false);
            guestEditFlag = 0;
        }
        else{
            guestRetrivalAnchorPane.setDisable(true);
            guestEditFlag = 1;
        }
    }
    @FXML
    private void attendedEdit(ActionEvent event){
        if(attendedEditFlag == 1) {
           attendedRetrivalAnchorPane.setDisable(false);
           attendedEditFlag = 0;
        }
        else{
            attendedRetrivalAnchorPane.setDisable(true);
            attendedEditFlag = 1;
        }
    }
    @FXML
    private void organizedEdit(ActionEvent event){
        if(organizedEditFlag == 1) {
            organizedRetrivalAnchorPane.setDisable(false);
            organizedEditFlag = 0;
        }
        else{
            organizedRetrivalAnchorPane.setDisable(true);
            organizedEditFlag = 1;
        }
    }
    @FXML
    private void publicationEdit(ActionEvent event){
        if(publicationEditFlag == 1) {
           publicationRetrivalAnchorPane.setDisable(false);
           publicationEditFlag = 0;
        }
        else{
            publicationRetrivalAnchorPane.setDisable(true);
            publicationEditFlag = 1;
        }
    }
    @FXML
    private void workingEdit(ActionEvent event){
        if(workingEditFlag == 1) {
          workingRetrivalAnchorPane.setDisable(false);
          workingEditFlag = 0;
        }
        else{
            workingRetrivalAnchorPane.setDisable(true);
            workingEditFlag = 1;
        }
    }
    @FXML
    private void interactionEdit(ActionEvent event){
        if(interactionEditFlag == 1) {
          interactionRetrivalAnchorPane.setDisable(false);
          interactionEditFlag = 0;
        }
        else{
            interactionRetrivalAnchorPane.setDisable(true);
            interactionEditFlag = 1;
        }
    }
    @FXML
    private void fundedEdit(ActionEvent event){
        if(fundedEditFlag == 1) {
         fundedRetrivalAnchorPane.setDisable(false);
         fundedEditFlag = 0;
        }
        else{
            fundedRetrivalAnchorPane.setDisable(true);
            fundedEditFlag = 1;
        }
    }

    //It will Generate New FXML file
    private void showPane() {
        System.out.println("show pane");
        Stage primaryStage = new Stage();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("FXML/RetirvalFormsPane.fxml"));
            primaryStage.setTitle("Faculty IMS");
            if(flag == 8)
                primaryStage.setScene(new Scene(root, 1000, 600));
            else
                primaryStage.setScene(new Scene(root, 1000, 700));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
