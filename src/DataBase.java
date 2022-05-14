import javax.swing.*;
import java.sql.*;

public class DataBase {

    Connection c;

    DataBase() {
        c = connectDataBase();
    }

    public static Connection connectDataBase() {
        String connectionUrl =
                "jdbc:sqlserver://DESKTOP-4OT5UGF\\mohamed.database.windows.net:1433;"
                        + "database=Bugsdetection;"
                        + "user=mohamed;"
                        + "password=Mohamed123456;"
                        + "encrypt=false;"
                        + "trustServerCertificate=false;"
                        + "loginTimeout=30;";
        Connection connection;
        try {
            System.out.println("connected");
            connection = DriverManager.getConnection(connectionUrl);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static DataBase database = new DataBase();

    public void accessDataBase(String query) {
        Statement st = null;
        try {
            st = c.createStatement();
            st.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ResultSet retriveDataBase(String query) {
        Statement st = null;
        try {
            st = c.createStatement();
            return st.executeQuery(query);
        }   catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public void updateDataBase(String query) {
        Statement st = null;
        try {
            st = c.createStatement();
            st.executeUpdate(query);
            JOptionPane.showMessageDialog(null, " record has been update successfully ", "success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, "error", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }


    public int insertDataBase(String query) {
        Statement st = null;
        try {
            st = c.createStatement();
            st.executeUpdate(query);
            return 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }

    public ResultSet getUsersWithSpecificType(String tableName, String userType) {

        Statement st = null;
        try {
            st = c.createStatement();
            ResultSet r = st.executeQuery("select * from " + tableName + " where code='" + userType + "' ");
            return r;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

}
