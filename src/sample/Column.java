package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.DatePicker;

import java.util.Date;

public class Column {

    private String firstColumn;
    private String secondColumn;
    private String thirdColumn;
    private String fourthColumn;
    private String fifthColumn;
    private String sixthColumn;
    private String sevenColumn;

    public Column(){
        this.firstColumn = "";
        this.secondColumn = "";
        this.thirdColumn = "";
        this.fourthColumn = "";
        this.fifthColumn = "";
        this.sixthColumn = "";
        this.sevenColumn = "";
    }
    private final StringProperty column = new SimpleStringProperty();
    public Column(String firstColumn, String secondColumn, String thirdColumn,String fourthColumn){
        this.firstColumn= firstColumn;
        this.secondColumn = secondColumn;
        this.thirdColumn = thirdColumn;
        this.fourthColumn = fourthColumn;
        setColumn(firstColumn);
    }

    public Column(String firstColumn, String secondColumn, String thirdColumn,String fourthColumn,String fifthColumn){
        this.firstColumn= firstColumn;
        this.secondColumn = secondColumn;
        this.thirdColumn = thirdColumn;
        this.fourthColumn = fourthColumn;
        this.fifthColumn = fifthColumn;
        setColumn(firstColumn);
    }

    public Column(String firstColumn, String secondColumn, String thirdColumn,String fourthColumn,Long fifthColumn){
        this.firstColumn= firstColumn;
        this.secondColumn = secondColumn;
        this.thirdColumn = thirdColumn;
        this.fourthColumn = fourthColumn;
        this.fifthColumn = fifthColumn.toString();
        setColumn(firstColumn);
    }

    public Column(String firstColumn, String secondColumn, String thirdColumn,String fourthColumn,String fifthColumn,String sixthColumn){
        this.firstColumn= firstColumn;
        this.secondColumn = secondColumn;
        this.thirdColumn = thirdColumn;
        this.fourthColumn = fourthColumn;
        this.fifthColumn = fifthColumn;
        this.sixthColumn = sixthColumn;
        setColumn(firstColumn);
    }

    public Column(String firstColumn, String secondColumn, String thirdColumn,String fourthColumn,String fifthColumn,String sixthColumn,String sevenColumn){
        this.firstColumn= firstColumn;
        this.secondColumn = secondColumn;
        this.thirdColumn = thirdColumn;
        this.fourthColumn = fourthColumn;
        this.fifthColumn = fifthColumn;
        this.sixthColumn = sixthColumn;
        this.sevenColumn = sevenColumn;
        setColumn(firstColumn);
    }


    public StringProperty nameProperty() {
        return column;
    }

    public final void setColumn(String firstColumn) {
        nameProperty().set(firstColumn);
    }

    public String getFirstColumn() {
        return firstColumn;
    }
    public void setFirstColumn(String firstColumn) {
        this.firstColumn = firstColumn;
    }

    public String getSecondColumn() {
        return secondColumn;
    }
    public void setSecondColumn(String secondColumn) {
        this.secondColumn = secondColumn;
    }

    public String getThirdColumn() {
        return thirdColumn;
    }
    public void setThirdColumn(String thirdColumn) {
        this.thirdColumn = thirdColumn;
    }

    public String getFourthColumn(){
        return fourthColumn;
    }
    public void setFourthColumn(String fourthColumn){
        this.fourthColumn = fourthColumn;
    }

    public String getFifthColumn() {
        return fifthColumn;
    }
    public void setFifthColumn(String fifthColumn) {
        this.fifthColumn = fifthColumn;
    }

    public String getSixthColumn() { return sixthColumn; }
    public void setSixthColumn() {
        this.sixthColumn = sixthColumn;
    }

    public String getSevenColumn() { return sevenColumn; }
    public void setSevenColumn() {
        this.sevenColumn = sevenColumn;
    }
}
