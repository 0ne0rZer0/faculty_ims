package sample;

import org.apache.poi.ss.util.DateFormatConverter;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Validation {
    public String name;
    static int dialogFlag = 0;
    static boolean validateDigits(String input,String message) {
            String regex = "[0-9]+";
            String data = input;
            if(dialogFlag==0) {
                if (!data.matches(regex)) {
                    JOptionPane.showMessageDialog(null, message + " Should have Integer Value ");
                    dialogFlag = 1;
                    return false;
                }
            }
            return true;
    }
    static boolean validateDigitsLength(String input,String message,int length) {
        if(dialogFlag==0) {
            if (input.length() != length) {
                JOptionPane.showMessageDialog(null, message + " Number Should Be " + length + " Digits");
                dialogFlag = 1;
                return false;
            }
        }
        return true;
    }
    static int dateDiff(String Datefrom,String Dateto, String message) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        java.util.Date from = simpleDateFormat.parse(Datefrom);
        java.util.Date to = simpleDateFormat.parse(Dateto);
        if(dialogFlag==0) {
            System.out.println(Datefrom);
            System.out.println(Dateto);
            if (from.compareTo(to) > 0 || from.compareTo(to)==0) {
                JOptionPane.showMessageDialog(null, "Date Is Not Valid For " + message);
                dialogFlag = 1;
                return 0;
            }
        }
        return 1;
    }
    static boolean isEmpty(String input,String message){
        if(dialogFlag ==0) {
            if(input==null)
                input = "";
            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Enter " + message);
                dialogFlag = 1;
                return true;
            }
        }
        return false;
    }
    boolean validateSinhgadEmail(String input) {
            String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*.scoe@sinhgad.edu";
            String data = input;
            if(dialogFlag==0) {
                if (!data.matches(regex)) {
                    dialogFlag = 1;
                    return false;
                }
            }
            return true;
    }
    static boolean validatePersonalEmail(String input) {
            String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            String data = input;
            if(dialogFlag==0) {
                if (!data.matches(regex)) {
                    dialogFlag = 1;
                    JOptionPane.showMessageDialog(null, "Please Enter Valid Email");
                    return false;
                }
            }
            return false;
    }
    static boolean validateAlphabets(String input,String message) {
            String regex = "^[A-Za-z]+$";
            String data = input;
            if(dialogFlag==0) {
                if (!data.matches(regex)) {
                    dialogFlag = 1;
                    JOptionPane.showMessageDialog(null, "Please Enter Valid " + message);
                    return false;
                }
            }
            return true;
     }
}
