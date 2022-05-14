

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Manager extends user {
    public   ArrayList <Bug> openBugs = new ArrayList<Bug>();
    public   ArrayList <Bug> closedBugs = new ArrayList<Bug>() ;

    public Manager() { }

    public void ClassifyBugs(){
        try{
            String query = "select * from bug";
            ResultSet rs = DataBase.database.retriveDataBase(query);
            Bug bug;
            while(rs.next()){
                // sending the data from database row into the Bug class
                bug = new Bug(rs.getString("bugStatus"), rs.getString("bugName"),
                        rs.getString("bugType"), rs.getInt("bugPriority"),
                        rs.getInt("bugLevel"), rs.getString("projectName"),
                        rs.getString("bugDate"), rs.getString("developer"),
                        rs.getString("tester"));
                // discarding the open bugs
                if(bug.getBugStatus().equals("closed") )
                    closedBugs.add(bug);
                // discarding the closed bugs
                if(bug.getBugStatus().equals("opened"))
                    openBugs.add(bug);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  ArrayList<Bug>  getOpenBugs() {
        this.ClassifyBugs();
        return openBugs; }

    public  ArrayList<Bug>  getclosedBugs() {
        this.ClassifyBugs();
        return closedBugs; }

     public static ArrayList<user> CreateDeveloperPerformanceList(){
         String typeoOfuser="developer";
         return CreatePerformanceList( typeoOfuser);
     }

    public static ArrayList<user> CreateTesterPerformanceList(){
        String typeoOfuser="tester";
        return CreatePerformanceList( typeoOfuser);
    }

    public static ArrayList<user> CreatePerformanceList(String typeoOfuser){
        ArrayList<user> users = new ArrayList<>();
        try{
            String query = "select * from users";
            ResultSet rs = DataBase.database.retriveDataBase(query);
            user newUser;
            while(rs.next()){
                // sending the data from database row into the User class
                newUser = new user(rs.getString("username"), rs.getString("email"),
                                   rs.getString("userpassword"), rs.getString("code"), rs.getInt("rate"));
                if(newUser.getUserType().equals(typeoOfuser))
                    users.add(newUser);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public static void Checkperformance(String email, JTextField performance) {
        String query = "select rate from users where email='" + email + "'";
        try {
            ResultSet developer = DataBase.database.retriveDataBase(query);
            developer.next();
            int rate = Integer.parseInt(developer.getString("rate"));
            performance.setText(rate + "");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}


