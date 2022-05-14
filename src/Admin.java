import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Admin extends user {

    Admin(){ };

    public Admin(String username, String email, String password, String typeOfUser) {
        setEmail(email);
        setPassword(password);
        setUsertype(typeOfUser);
        setUsername(username);
    }

    public static String LoginVerivication(user u) {
        String UserType="NULL";
        String query = "select code from " + "users" + " where email ='" + u.getEmail() + "' and userpassword='" + u.getPassword() + "'";
        ResultSet found = DataBase.database.retriveDataBase(query);
        while (true) {
            try {
                if (!found.next()) break;
            }   catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                UserType = found.getString("code");
            }   catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return UserType;
    }

    public static void identifiyUserType(String UserType) {

        if (UserType.equals("tester") ) {
            JOptionPane.showMessageDialog(null, "login successfully", "success", JOptionPane.INFORMATION_MESSAGE);
            /*tester page **/
            TesterWindowGUI testerModule = new TesterWindowGUI(user.curentuser, 500, 500, EXIT_ON_CLOSE);
        } else if (UserType.equals("developer")) {
            JOptionPane.showMessageDialog(null, "login successfully", "success", JOptionPane.INFORMATION_MESSAGE);
            /* developer page**/
            DeveloperPage developerPage = new DeveloperPage();
        } else if (UserType.equals("manager")) {
            JOptionPane.showMessageDialog(null, "login successfully", "success", JOptionPane.INFORMATION_MESSAGE);
            /* manager page**/
            new ManagerPage(user.curentuser, 500, 500, EXIT_ON_CLOSE);
        } else if (UserType.equals("admin")) {
            JOptionPane.showMessageDialog(null, "login successfully", "success", JOptionPane.INFORMATION_MESSAGE);
            /* admin page**/
            adminpage adminpage = new adminpage(user.curentuser, 450, 500, EXIT_ON_CLOSE);
        } else {
            JOptionPane.showMessageDialog(null, "invaild email or password", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void insertUsers(user u) {
        String query = null;
        if ((u.getUserType() == "tester" || u.getUserType() == "developer") && u.getEmail() != null) {
            query = "insert into " + "users" + "(username,email,userpassword ,code,rate) values ('" + u.getUsername() + "','" + u.getEmail() + "','" + u.getPassword() + "','" + u.getUserType() + "','" + u.getRate() + "') ";
        } else {
            query = "insert into " + "users" + "(username,email,userpassword ,code) values ('" + u.getUsername() + "','" + u.getEmail() + "','" + u.getPassword() + "','" + u.getUserType() + "') ";
        }
        int result = DataBase.database.insertDataBase(query);
        if (result == 1) {
            JOptionPane.showMessageDialog(null, " successfully process", "success", JOptionPane.INFORMATION_MESSAGE);
        } else if (result == 0) {
            JOptionPane.showMessageDialog(null, "please enter a valid email adress ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void updateUsers( user u) {
        String query = null;
        if (u.getUserType()=="tester"||u.getUserType()=="developer"){
            query = "update " + "users" +" set username='"+u.getUsername()+"', userpassword='"+u.getPassword()+"', code='"+u.getUserType()+"',rate='"+u.getRate()+"' where email='" + u.getEmail() + "'";}
        else{ query = "update " + "users" +" set username='"+u.getUsername()+"', userpassword='"+u.getPassword()+"', code='"+u.getUserType()+"'  where email='" + u.getEmail() + "'";}
                DataBase.database.updateDataBase(query);
    }

    public static void DeleteUser(user u) {
        String query = "delete from " + "users" + " where email='" + u.getEmail() + "'";
        DataBase.database.updateDataBase(query);
        JOptionPane.showMessageDialog(null, " record has been deleted successfully ", "success", JOptionPane.INFORMATION_MESSAGE);

    }

    public static ResultSet retriveSpecificUser( String email) {
        String query="select * from " + "users" + " where email='" + email + "'";
        return DataBase.database.retriveDataBase(query);

    }

    }








