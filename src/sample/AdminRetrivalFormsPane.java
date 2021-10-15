package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminRetrivalFormsPane implements Initializable {

    //Publication fields declared here
    @FXML
    private Pane publicationRetrivalPane;
    @FXML
    private Label publicationAuthorName;
    @FXML
    private Label publicationCorrespondenceAuthor;
    @FXML
    private Label publicationPublicationType;
    @FXML
    private Label publicationTitleOfPaper;
    @FXML
    private Label publicationNameOfJournal;
    @FXML
    private Label publicationNameOfPublisher;
    @FXML
    private Label publicationLink;
    @FXML
    private Label publicationMonthAndYear;
    @FXML
    private Label publicationAcademicYear;
    @FXML
    private Label publicationPageNo;
    @FXML
    private Label publicationISSN;
    @FXML
    private Label publicationVolume;
    @FXML
    private Label publicationDateFrom;
    @FXML
    private Label publicationDateTo;
    @FXML
    private Label publicationNameOfFundingAgency;
    @FXML
    private Label publicationFundsReceived;
    @FXML
    private Label publicationVenue;
    @FXML
    private Label publicationImpactFactor;
    @FXML
    private Label publicationCitationCount;
    @FXML
    private Label publicationSNIP;
    @FXML
    private Label publicationSJRRank;
    @FXML
    private Label publicationIndexedBy;
    @FXML
    private Label publicationIndexedByOther;
    @FXML
    private Label publicationScopusIndex;
    //Attended fields declare here
    @FXML
    private Pane attendedRetrivalPane;
    @FXML
    private Label attendedTitleOfProgramme;
    @FXML
    private Label attendedTypeOfProgramme;
    @FXML
    private Label attendedLevelOfProgramme;
    @FXML
    private Label attendedNameOfApprovingAgency;
    @FXML
    private Label attendedOrganizerName;
    @FXML
    private Label attendedVenue;
    @FXML
    private Label attendedDateFrom;
    @FXML
    private Label attendedDateTo;
    @FXML
    private Label attendedDuration;
    @FXML
    private Label attendedNameOfFundinfAgency;
    @FXML
    private Label attendedFundsRecieved;
    @FXML
    private Label attendedAcademicYear;

    //Interaction fields declare here
    @FXML
    private Pane interactionRetrivalPane;
    @FXML
    private Label interactionRole;
    @FXML
    private Label interactionTitle;
    @FXML
    private Label interactionTargetAudience;
    @FXML
    private Label interactionNumberOfParticipants;
    @FXML
    private Label interactionVenue;
    @FXML
    private Label interactionProgrammeDate;
    @FXML
    private Label interactionNumberOfDays;

    //Guest fields declare here
    @FXML
    private Pane guestRetrivalPane;
    @FXML
    private Label guestNameOfResourcePerson;
    @FXML
    private Label guestResourcePersonMobileNumber;
    @FXML
    private Label guestResourcePersonEmailId;
    @FXML
    private Label guestResourcePersonOrganization;
    @FXML
    private Label guestDesignationOfResourcePerson;
    @FXML
    private Label guestTopic;
    @FXML
    private Label guestNumberOfParticipants;
    @FXML
    private Label guestRemuneration;
    @FXML
    private Label guestDateOfConduction;
    @FXML
    private Label guestAcademicYear;
    @FXML
    private Label guestTargetAudience;

    //Funded fields declare here
    @FXML
    private Pane fundedRetrivalPane;
    @FXML
    private Label fundedRole;
    @FXML
    private Label fundedNameOfTheCompany;
    @FXML
    private Label fundedTitleOfResearchProject;
    @FXML
    private Label fundedSponsoringAgency;
    @FXML
    private Label fundedSponsoringAgencyOther;
    @FXML
    private Label fundedSanctionedAmount;
    @FXML
    private Label fundedReceivedAmount;
    @FXML
    private Label fundedUtiilizedAmount;
    @FXML
    private Label fundedOutcome;
    @FXML
    private Label fundedStatus;
    @FXML
    private Label fundedDateFrom;
    @FXML
    private Label fundedDateTo;
    @FXML
    private Label fundedAcademicYear;

    //Organized fields declare here
    @FXML
    private Pane organizedRetrivalPane;
    @FXML
    private Label organizedTitleOfProgramme;
    @FXML
    private Label organizedTypeOfProgramme;
    @FXML
    private Label organizedLevelOfProgramme;
    @FXML
    private Label organizedApprovingAuthority;
    @FXML
    private Label organizedSponsoringAuthority;
    @FXML
    private Label organizedFundsReceived;
    @FXML
    private Label organizedFundsUtilized;
    @FXML
    private Label organizedDuration;
    @FXML
    private Label organizedDateFrom;
    @FXML
    private Label organizedDateTo;
    @FXML
    private Label organizedNumberOfParticipants;
    @FXML
    private Label organizedAcademicYear;
    @FXML
    private Label organizedTargetAudience;
    @FXML
    private Label organizedNameOfResourcePerson;
    @FXML
    private Label organizedContactDetails;
    @FXML
    private Label organizedEmailId;

    static String firstColumn = null;
    static int flag = 0;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        switch (flag){
            case 5:
                getPublicationData(firstColumn);
                publicationRetrivalPane.toFront();
                break;
            case 6:
                getAttendedData(firstColumn);
                attendedRetrivalPane.toFront();
                break;
            case 7:
                getOrganizedData(firstColumn);
                organizedRetrivalPane.toFront();
                break;
            case 8:
                getFundedData(firstColumn);
                fundedRetrivalPane.toFront();
                break;
            case 9:
                getInteractionData(firstColumn);
                interactionRetrivalPane.toFront();
                break;
            case 10:
                getGuestData(firstColumn);
                guestRetrivalPane.toFront();
                break;
        }
    }
    public void generateData(int flag, String firstColumn) {
        this.firstColumn = firstColumn;
        this.flag = flag;
        showPane();
        System.out.println("generateData");
    }
    //Organized Data Retrival
    public void getOrganizedData(String firstColumn) {
        PreparedStatement organizedQuery;
        PreparedStatement authorityQuery;
        String organizedDataString = "select * from organized where RegistrationNo = '"+AdminMain.RegistrationNo+"' and TitleOfProgramme='" + firstColumn + "'";
        String authorityDataString = "select * from authority where RegistrationNo = '"+AdminMain.RegistrationNo+"' and TitleOfProgramme='" + firstColumn + "'";
        Main.jdbcClass.getConnection();
        ResultSet organizedResultSet;
        ResultSet authorityResultSet;
        try {
            organizedQuery = Main.jdbcClass.connection.prepareStatement(organizedDataString);
            organizedResultSet = Main.jdbcClass.fireQuery(organizedQuery);
            authorityQuery = Main.jdbcClass.connection.prepareStatement(authorityDataString);
            authorityResultSet = Main.jdbcClass.fireQuery(authorityQuery);
            while(organizedResultSet.next()) {

                organizedTitleOfProgramme.setText(organizedResultSet.getString("TitleOfProgramme"));
                organizedTypeOfProgramme.setText(organizedResultSet.getString("TypeOfProgramme"));
                //organizedTypeOfProgrammeOther.setText(resultSet.getString(""));
                organizedLevelOfProgramme.setText(organizedResultSet.getString("LevelOfProgramme"));
                organizedDuration.setText(organizedResultSet.getString("Duration"));
                organizedDateFrom.setText(organizedResultSet.getString("DateFrom"));
                organizedDateTo.setText(organizedResultSet.getString("DateTo"));
                organizedNumberOfParticipants.setText(organizedResultSet.getString("NoOfParticipants"));
                organizedAcademicYear.setText(organizedResultSet.getString("AcademicYear"));
                organizedTargetAudience.setText(organizedResultSet.getString("TargetAudience"));
                organizedNameOfResourcePerson.setText(organizedResultSet.getString("NameOfResourcePerson"));
                organizedContactDetails.setText(organizedResultSet.getString("ContactNumber"));
                organizedEmailId.setText(organizedResultSet.getString("EmailId"));
            }
            while (authorityResultSet.next()) {
                organizedApprovingAuthority.setText(authorityResultSet.getString("ApprovingAuthority"));
                organizedSponsoringAuthority.setText(authorityResultSet.getString("SponsoringAuthority"));
                organizedFundsReceived.setText(authorityResultSet.getString("FundsReceived"));
                organizedFundsUtilized.setText(authorityResultSet.getString("FundsUtilized"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Funded Data Retrival
    public void getFundedData(String firstColumn) {
        PreparedStatement fundedQuery;
        String fundedDataString = "select * from fundedresearchproduct where RegistrationNo = '"+AdminMain.RegistrationNo+"' and TitleofResearchProduct ='"+firstColumn+"'";
        Main.jdbcClass.getConnection();
        ResultSet resultSet;

        try {
            fundedQuery = Main.jdbcClass.connection.prepareStatement(fundedDataString);
            resultSet = Main.jdbcClass.fireQuery(fundedQuery);

            while (resultSet.next()) {

                fundedRole.setText(resultSet.getString("Role"));
                fundedNameOfTheCompany.setText(resultSet.getString("InCollaborationWith"));
                fundedTitleOfResearchProject.setText(resultSet.getString("TitleofResearchProduct"));
                fundedSponsoringAgency.setText(resultSet.getString("SponsoringAgency"));
            //    fundedSponsoringAgencyOther.setText(resultSet.getString(""));
                fundedSanctionedAmount.setText(resultSet.getString("SanctionedAmount"));
                fundedReceivedAmount.setText(resultSet.getString("ReceivedAmount"));
                fundedUtiilizedAmount.setText(resultSet.getString("UtilizedAmount"));
                fundedOutcome.setText(resultSet.getString("Outcome"));
                fundedStatus.setText(resultSet.getString("Status"));
                fundedDateFrom.setText(resultSet.getString("FromDate"));
                fundedDateTo.setText(resultSet.getString("ToDate"));
                fundedAcademicYear.setText(resultSet.getString("AcademicYear"));

            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Guest Data Retrival
    public void getGuestData(String firstColumn) {
        PreparedStatement guestQuery;
        String guestDataString = "select * from guestlecture where RegistrationNo = '"+AdminMain.RegistrationNo+"' and Topics='" + firstColumn + "'";
        Main.jdbcClass.getConnection();
        ResultSet resultSet;

        try {
            guestQuery = Main.jdbcClass.connection.prepareStatement(guestDataString);
            resultSet = Main.jdbcClass.fireQuery(guestQuery);

            while(resultSet.next()) {

                guestNameOfResourcePerson.setText(resultSet.getString("NameOfResourcePerson"));
                guestResourcePersonMobileNumber.setText(resultSet.getString("ResourcePersonMobileNo"));
                guestResourcePersonEmailId.setText(resultSet.getString("ResourcePersonEmailId"));
                guestResourcePersonOrganization.setText(resultSet.getString("ResourcePersonOrganization"));
                guestDesignationOfResourcePerson.setText(resultSet.getString("DesignationOfResourcePerson"));
                guestTopic.setText(resultSet.getString("Topics"));
                guestNumberOfParticipants.setText(resultSet.getString("NumberOfParticipants"));
                guestRemuneration.setText(resultSet.getString("Remuneration"));
                guestDateOfConduction.setText(resultSet.getString("DateOfConduction"));
                guestAcademicYear.setText(resultSet.getString("AcademicYear"));
                guestTargetAudience.setText(resultSet.getString("TargetAudience"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Interaction Data Retrival
    public void getInteractionData(String firstColumn) {

        PreparedStatement interactionQuery;
        String interactionDataString = "select * from interaction where RegistrationNo = '"+AdminMain.RegistrationNo+"' and Particulars ='"+firstColumn+"' and ProgramDate = '"+RetrivalForms.thirdColumn+"' and AcademicYear='"+RetrivalForms.fourthColumn+"'";
        Main.jdbcClass.getConnection();
        ResultSet resultSet;
        try {
            interactionQuery = Main.jdbcClass.connection.prepareStatement(interactionDataString);
            resultSet = Main.jdbcClass.fireQuery(interactionQuery);
            while (resultSet.next()) {

                interactionRole.setText(resultSet.getString("RoleOfFaculty"));
                interactionTitle.setText(resultSet.getString("Particulars"));
                interactionTargetAudience.setText(resultSet.getString("TargetAudiance"));
                interactionNumberOfParticipants.setText(resultSet.getString("NoofParticipants"));
                interactionVenue.setText(resultSet.getString("Venue"));
                interactionProgrammeDate.setText(resultSet.getString("ProgramDate"));
                interactionNumberOfDays.setText(resultSet.getString("NoofDays"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //Attended Data Retrival
    public void getAttendedData(String firstColumn) {

        PreparedStatement attendedQuery;
        String attendedDataString = "select * from attended where RegistrationNo = '"+AdminMain.RegistrationNo+"' and TitleOfProgramme='" + firstColumn + "'";
        Main.jdbcClass.getConnection();
        ResultSet resultSet;
        try {
             attendedQuery = Main.jdbcClass.connection.prepareStatement(attendedDataString);
             resultSet = Main.jdbcClass.fireQuery(attendedQuery);
             while (resultSet.next()) {

                 attendedTypeOfProgramme.setText(resultSet.getString("TypeOfProgramme"));
                 attendedTitleOfProgramme.setText(resultSet.getString("TitleOfProgramme"));
                 attendedLevelOfProgramme.setText(resultSet.getString("LevelOfProgramme"));
                 attendedNameOfApprovingAgency.setText(resultSet.getString("NameofApprovingAgency"));
                 attendedOrganizerName.setText(resultSet.getString("OrganizerName"));
                 attendedVenue.setText(resultSet.getString("Venue"));
                 attendedDateFrom.setText(resultSet.getString("DateFrom"));
                 attendedDateTo.setText(resultSet.getString("DateTo"));
                 attendedDuration.setText(resultSet.getString("Duration"));
                 attendedNameOfFundinfAgency.setText(resultSet.getString("NameOfFundingAgency"));
                 attendedFundsRecieved.setText(resultSet.getString("FundsReceived"));
                 attendedAcademicYear.setText(resultSet.getString("AcademicYear"));
             }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    //Pubication Data Retrival
    public void getPublicationData(String firstColumn) {
        PreparedStatement publicationQuery;
        String publicationDataString = "select * from publication where RegistrationNo = '"+AdminMain.RegistrationNo+"' and TitleOfPaper='"+firstColumn+"'";;
        Main.jdbcClass.getConnection();
        ResultSet resultSet;
        try {
            publicationQuery = Main.jdbcClass.connection.prepareStatement(publicationDataString);
            resultSet = Main.jdbcClass.fireQuery(publicationQuery);
            while(resultSet.next()) {

                publicationAuthorName.setText(resultSet.getString("AuthorsName"));
                publicationCorrespondenceAuthor.setText(resultSet.getString("CorrespondanceAuthor"));
                publicationPublicationType.setText(resultSet.getString("PublicationType"));
                publicationTitleOfPaper.setText(resultSet.getString("TitleOfPaper"));
                publicationNameOfJournal.setText(resultSet.getString("NameOfJournalConference"));
                publicationNameOfPublisher.setText(resultSet.getString("NameOfPublisherOrganizer"));
                publicationLink.setText(resultSet.getString("Link"));
                publicationMonthAndYear.setText(resultSet.getString("MonthAndYearOfJournalPublication"));
                publicationAcademicYear.setText(resultSet.getString("AcademicYear"));
                publicationPageNo.setText(resultSet.getString("PageNo"));
                publicationISSN.setText(resultSet.getString("ISSNISBNDOI"));
                publicationVolume.setText(resultSet.getString("VolumeAndIssueNumber"));
                publicationDateFrom.setText(resultSet.getString("FromDateofConferencePublication"));
                publicationDateTo.setText(resultSet.getString("ToDateofConferencePublication"));
                publicationNameOfFundingAgency.setText(resultSet.getString("NameOfFundingAgency"));
                publicationFundsReceived.setText(resultSet.getString("FundsRecieved"));
                publicationVenue.setText(resultSet.getString("Venue"));
                publicationImpactFactor.setText(resultSet.getString("ImpactFactor"));
                publicationCitationCount.setText(resultSet.getString("CitiationCount"));
                publicationSNIP.setText(resultSet.getString("SNIP"));
                publicationSJRRank.setText(resultSet.getString("SJRRank"));
                publicationIndexedBy.setText(resultSet.getString("IndexedBy"));
               // publicationIndexedByOther.setText(resultSet.getString(""));
                publicationScopusIndex.setText(resultSet.getString("ScopusIndex"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private void showPane() {
        System.out.println("show pane");
        Stage primaryStage = new Stage();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("FXML/FacultyRetrivePanes.fxml"));
            primaryStage.setTitle("Faculty IMS");
            //if(flag == 8)
                primaryStage.setScene(new Scene(root, 1000, 600));
            //else
               // primaryStage.setScene(new Scene(root, 1000, 700));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
