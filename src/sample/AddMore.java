package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static sample.Main.jdbcClass;

public class AddMore extends RegistrationForms implements Initializable {

    @FXML
    private Pane workPane;

    public void teachSave(ActionEvent event) throws SQLException, ParseException {
        PreparedStatement workExperienceTeaching = null;
        ArrayList<String> teachingData = getTeachingData();

        System.out.println(Login.REGISTRATION_NO);
        //  System.out.println(teachingData);
        String workStringExperienceTeaching = "insert into experiencebeforejoiningstes values(?,?,?,?,?,?)";
        System.out.println(teachingData);
        workExperienceTeaching = jdbcClass.connection.prepareStatement(workStringExperienceTeaching);
        workExperienceTeaching.setInt(1, Login.REGISTRATION_NO);
        for (int i = 2; i <= 6; i++) {
            workExperienceTeaching.setString(i, teachingData.get(i - 2));
        }
        jdbcClass.fireQuery(workExperienceTeaching);
    }
    protected ArrayList<String> getIndustrialData(){
        ArrayList<String> industrialData = new ArrayList<String>();
        industrialData.add("IndustrialExperience");
        industrialData.add(eduWorkIndustrialName.getText());
        industrialData.add(eduWorkIndustrialPosition.getText());
        industrialData.add(eduWorkIndustrialDateOfJoining.getValue().toString());
        industrialData.add(eduWorkIndustrialDateOfLeaving.getValue().toString());
        return industrialData;
    }
    public void industrySave(ActionEvent event){
        PreparedStatement workExperienceIndustrial = null;
        ArrayList<String> industrialData = getIndustrialData();
        String workStringExperienceIndustrial = "insert into experiencebeforejoiningstes values(?,?,?,?,?,?)";
        try {
            workExperienceIndustrial = jdbcClass.connection.prepareStatement(workStringExperienceIndustrial);
            workExperienceIndustrial.setInt(1, Login.REGISTRATION_NO);
            for(int i= 2;i <=6;i++){
                workExperienceIndustrial.setString(i,industrialData.get(i-2));
            }
            jdbcClass.fireQuery(workExperienceIndustrial);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        jdbcClass.getConnection();
        initializeComboBox();
        if(RetrivalForms.addMoreFlag == 8){
            workPane.toFront();
        }
        if(RetrivalForms.addMoreFlag == 10){
            publicationPane.toFront();
            publicationPane.setPrefHeight(1500);
        }
        if(RetrivalForms.addMoreFlag == 11){
            attendedPane.toFront();
        }
        if(RetrivalForms.addMoreFlag == 12){
            organizedPane.toFront();
        }
        if(RetrivalForms.addMoreFlag == 13){
            interactionPane.toFront();
        }
        if(RetrivalForms.addMoreFlag == 14){
            fundedPane.toFront();
        }
        if(RetrivalForms.addMoreFlag == 15){
            guestPane.toFront();
        }
    }
    public void initializeComboBox(){
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

        interactionRole.setItems(interactionRoleList);

        fundedOtherSponsoringAgency.setDisable(true);
        fundedOtherSponsoringAgency.setOpacity(0);
        fundedRole.setItems(fundedRoleList);
        fundedSponsoringAgency.setItems(fundedSponsoringList);
        fundedStatus.setItems(fundedStatusList);

        attendedTypeOfProgramme.setItems(attendedtypeofprogramme);
        attendedLevelOfProgramme.setItems(attendedlevelofprogramme);

        organizedTypeOfProgrammeOther.setOpacity(0);
        organizedTypeOfProgrammeOther.setDisable(true);
        organizedTypeOfProgramme.setItems(organizedtypeOfProgramme);
        organizedLevelOfProgramme.setItems(organizedlevelOfProgramme);
        organizedSponsoringAuthority.setItems(organizedsponsoringAuthority);
        organizedApprovingAuthority.setItems(organizedapprovingAuthority);

        publicationPublicationTypeOther.setOpacity(0);
        publicationPublicationTypeOther.setDisable(true);
        publicationIndexedByOther.setOpacity(0);
        publicationIndexedByOther.setDisable(true);
        publicationPublicationType.setItems(publicationTypeList);
        publicationAcademicYear.setItems(academicYearComboBox);
    }
}
