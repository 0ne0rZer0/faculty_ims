package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static sample.Main.jdbcClass;

public class ReportTable extends AdminMain implements Initializable {
    @FXML
    public TableView<ObservableList> reportTable;
    static ObservableList<String> row =  FXCollections.observableArrayList();
    ObservableList<ObservableList> result = FXCollections.observableArrayList();
    static TableColumn col;
    static ArrayList<String> combinedList = new ArrayList<>();
    //Report Table Generation
    public void tableGenerate() throws SQLException, IOException {
        combinedList.clear();
        reportTable.getItems().clear();
        result.clear();
        row.clear();
        col = null;

        String combinedInnerQuery = "";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String combinedQuery ="";
        //if academic year selected
        if(!facultylist.isEmpty() && academicYearF!=null && academicYearT!=null) {
            int flagAdd = 0;
            combinedList.addAll(facultylist);
            if(!personallist.isEmpty()) {
                combinedList.addAll(personallist);
                combinedInnerQuery = combinedInnerQuery.concat(" INNER JOIN personal on facultyMain.RegistrationNo=personal.RegistrationNo");
            }
            if(!educationallist.isEmpty() || !educationalPHDlist.isEmpty()) {
                String phdQuery = " ";
                if(!educationallist.isEmpty())
                    combinedList.addAll(educationallist);
                if(!educationalPHDlist.isEmpty()) {
                    combinedList.addAll(educationalPHDlist);
                    phdQuery = " INNER JOIN educationalphd on facultyMain.RegistrationNo=educationalphd.RegistrationNo";
                }
                combinedInnerQuery = combinedInnerQuery.concat(" INNER JOIN educationalugpg on facultyMain.RegistrationNo=educationalugpg.RegistrationNo "+phdQuery+"");
            }
            if(!sppulist.isEmpty()){
                combinedList.addAll(sppulist);
                combinedInnerQuery = combinedInnerQuery.concat(" INNER JOIN sppu on facultyMain.RegistrationNo=sppu.RegistrationNo");
            }
            if(!workinglist.isEmpty()) {
                combinedList.addAll(workinglist);
                combinedInnerQuery = combinedInnerQuery.concat(" INNER JOIN experiencebeforejoiningstes on facultyMain.RegistrationNo=experiencebeforejoiningstes.RegistrationNo");
            }
            if(!steslist.isEmpty()){
                combinedList.addAll(steslist);
                combinedInnerQuery = combinedInnerQuery.concat(" INNER JOIN stes on facultyMain.RegistrationNo=stes.RegistrationNo");
            }
            if(!publicationlist.isEmpty()) {
                flagAdd = 1;
                combinedList.addAll(publicationlist);
                combinedInnerQuery = combinedInnerQuery.concat(" INNER JOIN publication on facultyMain.RegistrationNo=publication.RegistrationNo and "+getAcedemicYearQuery("publication",academicYearF,academicYearT)+"");
            }
            if(!attendedlist.isEmpty()) {
                flagAdd = 1;
                combinedList.addAll(attendedlist);
                combinedInnerQuery = combinedInnerQuery.concat(" INNER JOIN attended on facultyMain.RegistrationNo=attended.RegistrationNo and "+getAcedemicYearQuery("attended",academicYearF,academicYearT)+"");
            }
            if(!organizedlist.isEmpty()) {
                flagAdd = 1;
                combinedList.addAll(organizedlist);
                combinedInnerQuery = combinedInnerQuery.concat(" INNER JOIN organized on facultyMain.RegistrationNo=organized.RegistrationNo and "+getAcedemicYearQuery("organized",academicYearF,academicYearT)+"");
            }
            if(!interactionlist.isEmpty()) {
                flagAdd = 1;
                combinedList.addAll(interactionlist);
                combinedInnerQuery = combinedInnerQuery.concat(" INNER JOIN interaction on facultyMain.RegistrationNo=interaction.RegistrationNo and  "+getAcedemicYearQuery("interaction",academicYearF,academicYearT)+"");
            }
            if(!fundedlist.isEmpty()) {
                flagAdd = 1;
                combinedList.addAll(fundedlist);
                combinedInnerQuery = combinedInnerQuery.concat(" INNER JOIN fundedresearchproduct on facultyMain.RegistrationNo=fundedresearchproduct.RegistrationNo and "+getAcedemicYearQuery("fundedresearchproduct",academicYearF,academicYearT)+"");
            }
            if(!guestlist.isEmpty()) {
                flagAdd = 1;
                combinedList.addAll(guestlist);
                combinedInnerQuery = combinedInnerQuery.concat(" INNER JOIN guestlecture on facultyMain.RegistrationNo=guestlecture.RegistrationNo and "+getAcedemicYearQuery("guestlecture",academicYearF,academicYearT)+"");
            }
            combinedQuery = "select " + makeQuery(combinedList) +" from facultymain " +combinedInnerQuery+"";

            if(eduDomain!="BOTH" && eduDomain!=null){
                combinedQuery = combinedQuery.concat(" and educationalugpg.GraduationType = '" + eduDomain + "'");
                flagAdd = 1;
            }
            if(sppuDomain!="BOTH" && sppuDomain!=null) {
                combinedQuery = combinedQuery.concat(" and sppu.ApprovalCategory = '" + sppuDomain + "'");
                flagAdd = 1;
            }
            if(workField!="BOTH" && workField!=null) {
                combinedQuery = combinedQuery.concat(" and experiencebeforejoiningstes.Field = '" + workField + "'");
                flagAdd = 1;
            }
            if(Login.adminFlag==0) {
                if(Login.Department.equals("First Year") && flagAdd==1)
                    combinedQuery = combinedQuery.concat(" where facultymain.departmentName = 'First Year - Mathematics' or facultymain.departmentName = 'First Year - Physics' or facultymain.departmentName = 'First Year - Chemistry' or facultymain.departmentName = 'First Year - Electrical'");
                else if(Login.Department.equals("First Year") && flagAdd==0)
                    combinedQuery = combinedQuery.concat(" where facultymain.departmentName = 'First Year - Mathematics' or facultymain.departmentName = 'First Year - Physics' or facultymain.departmentName = 'First Year - Chemistry' or facultymain.departmentName = 'First Year - Electrical'");
                else if (Login.Department!="First Year" && flagAdd == 1)
                    combinedQuery = combinedQuery.concat(" and facultymain.departmentName = '" + Login.Department + "'");
                else if(Login.Department!="First Year" && flagAdd == 0)
                    combinedQuery = combinedQuery.concat(" where facultymain.departmentName = '" + Login.Department + "'");
            }
            System.out.println(combinedQuery);
            preparedStatement = jdbcClass.connection.prepareStatement(combinedQuery);
            resultSet = jdbcClass.fireQuery(preparedStatement);
            intializeColumsTable(resultSet);
            reportTable.setItems(result);
        }
        //if academic year is not selected
        else if (!facultylist.isEmpty()){
            int flagAdd = 0;
            combinedList.addAll(facultylist);
            if(!personallist.isEmpty()) {
                combinedList.addAll(personallist);
                combinedInnerQuery = combinedInnerQuery.concat(" INNER JOIN personal on facultyMain.RegistrationNo=personal.RegistrationNo");
            }
            if(!steslist.isEmpty()){
                combinedList.addAll(steslist);
                combinedInnerQuery = combinedInnerQuery.concat(" INNER JOIN stes on facultyMain.RegistrationNo=stes.RegistrationNo");
            }
            if(!publicationlist.isEmpty()) {
                combinedList.addAll(publicationlist);
                combinedInnerQuery = combinedInnerQuery.concat(" INNER JOIN publication on facultyMain.RegistrationNo=publication.RegistrationNo");
            }
            if(!attendedlist.isEmpty()) {
                combinedList.addAll(attendedlist);
                combinedInnerQuery = combinedInnerQuery.concat(" INNER JOIN attended on facultyMain.RegistrationNo=attended.RegistrationNo");
            }
            if(!organizedlist.isEmpty()) {
                combinedList.addAll(organizedlist);
                combinedInnerQuery = combinedInnerQuery.concat(" INNER JOIN organized on facultyMain.RegistrationNo=organized.RegistrationNo");
            }
            if(!interactionlist.isEmpty()) {
                combinedList.addAll(interactionlist);
                combinedInnerQuery = combinedInnerQuery.concat(" INNER JOIN interaction on facultyMain.RegistrationNo=interaction.RegistrationNo");
            }
            if(!fundedlist.isEmpty()) {
                combinedList.addAll(fundedlist);
                combinedInnerQuery = combinedInnerQuery.concat(" INNER JOIN fundedresearchproduct on facultyMain.RegistrationNo=fundedresearchproduct.RegistrationNo");
            }
            if(!guestlist.isEmpty()) {
                combinedList.addAll(guestlist);
                combinedInnerQuery = combinedInnerQuery.concat(" INNER JOIN guestlecture on facultyMain.RegistrationNo=guestlecture.RegistrationNo");
            }
            if(!educationallist.isEmpty() || !educationalPHDlist.isEmpty()) {
                String phdQuery = " ";
                if(!educationallist.isEmpty())
                    combinedList.addAll(educationallist);
                if(!educationalPHDlist.isEmpty()) {
                    combinedList.addAll(educationalPHDlist);
                    phdQuery = " INNER JOIN educationalphd on facultyMain.RegistrationNo=educationalphd.RegistrationNo";
                }
                    combinedInnerQuery = combinedInnerQuery.concat(" INNER JOIN educationalugpg on facultyMain.RegistrationNo=educationalugpg.RegistrationNo "+phdQuery+"");
            }
            if(!sppulist.isEmpty()){
                combinedList.addAll(sppulist);
                    combinedInnerQuery = combinedInnerQuery.concat(" INNER JOIN sppu on facultyMain.RegistrationNo=sppu.RegistrationNo");
            }
            if(!workinglist.isEmpty()) {
                combinedList.addAll(workinglist);
                combinedInnerQuery = combinedInnerQuery.concat(" INNER JOIN experiencebeforejoiningstes on facultyMain.RegistrationNo=experiencebeforejoiningstes.RegistrationNo");
            }
            combinedQuery = "select " + makeQuery(combinedList) +" from facultymain " +combinedInnerQuery+"";
                if(eduDomain!="BOTH" && eduDomain!=null){
                    combinedQuery = combinedQuery.concat(" and educationalugpg.GraduationType = '" + eduDomain + "'");
                    flagAdd = 1;
                }
                if(sppuDomain!="BOTH" && sppuDomain!=null) {
                    combinedQuery = combinedQuery.concat(" and sppu.ApprovalCategory = '" + sppuDomain + "'");
                    flagAdd = 1;
                }
                if(workField!="BOTH" &&workField!=null) {
                        combinedQuery = combinedQuery.concat(" and experiencebeforejoiningstes.Field = '" + workField + "'");
                    flagAdd = 1;
                }
            if(Login.adminFlag==0) {
                if(Login.Department.equals("First Year") && flagAdd==1)
                    combinedQuery = combinedQuery.concat(" where facultymain.departmentName = 'First Year - Mathematics' or facultymain.departmentName = 'First Year - Physics' or facultymain.departmentName = 'First Year - Chemistry' or facultymain.departmentName = 'First Year - Electrical'");
                else if(Login.Department.equals("First Year") && flagAdd==0)
                    combinedQuery = combinedQuery.concat(" where facultymain.departmentName = 'First Year - Mathematics' or facultymain.departmentName = 'First Year - Physics' or facultymain.departmentName = 'First Year - Chemistry' or facultymain.departmentName = 'First Year - Electrical'");
                else if (Login.Department!="First Year" && flagAdd == 1)
                    combinedQuery = combinedQuery.concat(" and facultymain.departmentName = '" + Login.Department + "'");
                else if(Login.Department!="First Year" && flagAdd == 0)
                    combinedQuery = combinedQuery.concat(" where facultymain.departmentName = '" + Login.Department + "'");
            }
            System.out.println(combinedQuery);
            preparedStatement = jdbcClass.connection.prepareStatement(combinedQuery);
            resultSet = jdbcClass.fireQuery(preparedStatement);
            intializeColumsTable(resultSet);
            reportTable.setItems(result);
        }
        else{
            System.out.println("EXIT");
        }
    }
    //ACADEMIC YEAR QUERY
    private String getAcedemicYearQuery(String colName, String from, String to){
        String query = "";
        Integer fromDate = Integer.parseInt(from);
        Integer toDate = Integer.parseInt(to);
        int diff = toDate - fromDate;
        for(int i = 0; i < diff; i++) {
            if(i==0)
                 query = query + colName + ".AcademicYear = '"+ fromDate.toString()+ "-" + (++fromDate).toString() + "'";
            query = query + " or " + colName + ".AcademicYear = '"+ fromDate.toString()+ "-" + (++fromDate).toString() + "'";
        }
        return query;
    }
    private void intializeColumsTable(ResultSet resultSet) throws SQLException {
        for (int i = 1; i <=  resultSet.getMetaData().getColumnCount(); i++) {
            //We are using non property style for making dynamic table
            final int j = i;
            col = new TableColumn(resultSet.getMetaData().getColumnName(i));
            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(param.getValue().get(j - 1).toString());
                }
            });
            reportTable.getColumns().addAll(col);
        }
        while (resultSet.next()) {
            ObservableList<String> row = FXCollections.observableArrayList();
            for (int i = 1; i <=  resultSet.getMetaData().getColumnCount(); i++) {
                //Iterate Column
                row.add(resultSet.getString(i));
            }
            result.add(row);
        }
    }
    //Make Query
    private String makeQuery(ArrayList<String> list) {
        String str = "";
        Iterator itr = list.iterator();
        while (itr.hasNext()) {
            str = str + (String) itr.next();
            if (itr.hasNext()) {
                str = str + ",";
            }
        }
        return str;
    }
    @FXML
    void generateReport(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        String path = null;
        primaryStage.setTitle("Save File");
        FileChooser fileChooser = new FileChooser();
        //Show save file dialog
        File file = fileChooser.showSaveDialog(primaryStage);
        try{
            path = new AdminRetrieve().tokenizePath(file.toString());
        }
        catch (NullPointerException e){
            System.out.println("Path is not selected");
        }
        Workbook workbook = new HSSFWorkbook();
        Sheet spreadsheet = workbook.createSheet("Report");
        Row row = spreadsheet.createRow(0);
        for (int j = 0; j < reportTable.getColumns().size(); j++) {
            row.createCell(j).setCellValue(reportTable.getColumns().get(j).getText());
        }
        for (int i = 0; i < reportTable.getItems().size(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < reportTable.getColumns().size(); j++) {
                if (reportTable.getColumns().get(j).getCellData(i) != null) {
                    row.createCell(j).setCellValue(reportTable.getColumns().get(j).getCellData(i).toString());
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        if(path!=null) {
            FileOutputStream fileOut = new FileOutputStream(path + ".xls");
            workbook.write(fileOut);
            fileOut.close();
        }
        ((Node)event.getSource()).getScene().getWindow().hide();
        for ( int i = 0; i<reportTable.getItems().size(); i++) {
            reportTable.getItems().clear();
        }
        //Platform.exit();
    }
    void summaryTableGenerate() throws SQLException {
        reportTable.getItems().clear();
        result.clear();
        row.clear();
        col = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String combinedQuery ="";
        if(academicYearFSummary!=null && academicYearTSummary!=null) {
            if (summaryFlag == 1) {
                combinedQuery = combinedQuery.concat("select facultymain.FacultyName "+summaryQuery("publication",academicYearFSummary,academicYearTSummary) +" from facultymain inner join publication on facultymain.RegistrationNo = publication.RegistrationNo where facultymain.departmentName = '"+ Login.Department +"' group by facultymain.FacultyName");
            } else if(summaryFlag==2){
                combinedQuery = combinedQuery.concat("select facultymain.FacultyName "+summaryQuery("attended",academicYearFSummary,academicYearTSummary) +"from facultymain inner join attended on facultymain.RegistrationNo = attended.RegistrationNo where facultymain.departmentName = '"+ Login.Department +"' group by facultymain.FacultyName");
            } else if(summaryFlag==3){
                combinedQuery = combinedQuery.concat("select facultymain.FacultyName "+summaryQuery("organized",academicYearFSummary,academicYearTSummary) +" from facultymain inner join organized on facultymain.RegistrationNo = organized.RegistrationNo where facultymain.departmentName = '"+ Login.Department +"' group by facultymain.FacultyName");
            } else if(summaryFlag==4){
                combinedQuery = combinedQuery.concat("select facultymain.FacultyName "+summaryQuery("interaction",academicYearFSummary,academicYearTSummary) +" from facultymain inner join interaction on facultymain.RegistrationNo = interaction.RegistrationNo where facultymain.departmentName = '"+ Login.Department +"' group by facultymain.FacultyName");
            } else if(summaryFlag==5){
                combinedQuery = combinedQuery.concat("select facultymain.FacultyName "+summaryQuery("funded",academicYearFSummary,academicYearTSummary) +" from facultymain inner join funded on facultymain.RegistrationNo = funded.RegistrationNo where facultymain.departmentName = '"+ Login.Department +"' group by facultymain.FacultyName");
            } else if(summaryFlag==6)
                combinedQuery = combinedQuery.concat("select facultymain.FacultyName "+summaryQuery("guestlecture",academicYearFSummary,academicYearTSummary) +" from facultymain inner join guestlecture on facultymain.RegistrationNo = guestlecture.RegistrationNo where facultymain.departmentName = '"+ Login.Department +"' group by facultymain.FacultyName");
        }
        System.out.println(combinedQuery);
        preparedStatement = jdbcClass.connection.prepareStatement(combinedQuery);
        resultSet = jdbcClass.fireQuery(preparedStatement);
        intializeColumsTable(resultSet);
        reportTable.setItems(result);
    }
    private String summaryQuery(String tableName,String from,String to){
        String query = "";
        Integer fromDate = Integer.parseInt(from);
        Integer toDate = Integer.parseInt(to);
        int diff = toDate - fromDate;
        //sum(case when attended.AcademicYear='2014-2015' then 1 else 0 end) as '2014-2015',
        for(int i = 0; i < diff; i++) {
            Integer tempFrom = fromDate;
                query = query + ",sum(case when " + tableName + ".AcademicYear = '" + fromDate.toString()+ "-" + (++fromDate).toString() + "' then 1 else 0 end ) as '"+ tempFrom.toString()+ "-" + (++tempFrom).toString() +"'";
        }
        return query;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            if(summaryFlag!=0) {
                summaryTableGenerate();
                summaryFlag = 0;
            }
            else
                tableGenerate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
