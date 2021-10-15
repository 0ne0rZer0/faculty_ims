package sample;
import javax.swing.*;
import java.sql.*;


public class JDBCClass {
    String className, URL, user, password;
    Connection connection;

    /* The constructor will take DriveClass name, SQL URL, Username and password as @param (string)*/
    public JDBCClass(String className, String URL, String user, String password) {
        this.className = className;
        this.URL = URL;
        this.user = user;
        this.password = password;
        this.connection = null;
        this.className = className;
    }
    /*
        To start SQL Connection use this method for connecting
        The class has connection variable which will be initialized using this method
    */
    public Connection getConnection() {
        // Loading the driver class
        try {
            Class.forName(className);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Could not load Class, Check Constructor parameters, Terminating");
        }
        // Get the Connection
        try {
            connection = DriverManager.getConnection(URL, user, password);
            return connection;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error getting connection Contact Admin");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"General Exception other than SQL inside getConnection");
        }
        return null;
    }
    /*
    This method will fire the query using Statement.execute()
    This method is used for both "Select" and "insert" statements.
    It will return a returnSet if you fire a Select statement, otherwise will return NULL
    and print the count of things updated or inserted.
    @param Query (Type:String)
    */
    public ResultSet fireQuery(PreparedStatement query) {
        try {
            boolean status = query.execute();
            if(status) {
                // For Select statements
                return query.getResultSet();
            } else {
                // For Insert update statements
                int updateCount = query.getUpdateCount();
                System.out.println("Update count is :" + updateCount);
                return null;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error getting connection because : " + ex.getMessage());
        }
        return null;
    }
}