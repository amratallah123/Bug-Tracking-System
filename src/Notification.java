import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Notification {

    public Notification() { }

    private static int checkNotification(String tableName, String coulmnName, String name, String code) {
        Statement st = null;
        int x=0;
        ResultSet r;
        try {
            st=DataBase.database.c.createStatement();
            r = st.executeQuery("select count(*) from " + tableName + " where " + coulmnName + "='"+ name +"' and notif="+code);
            while (r.next()){
                x = r.getInt(1);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return x;
    }

    public static void checkInbox(JButton btn, String userName, String code) {
        int x = checkNotification("bug",check(user.currentUserType) , userName, code);
        if (x > 0) {
            btn.setIcon(new ImageIcon("src/icon1.png"));
        } else {
            btn.setIcon(new ImageIcon("src/icon2.png"));
        }
    }

    private static String check(String code){
        if(code.equals("developer")){
            return "developer";
        }
        else{
            return "tester";
        }
    }

    private static String whoSender(){ return (user.currentUserType.equals("developer"))?"tester":"developer"; }

    public static void viewMails(DefaultTableModel tableModel) {
        String msg;
        String sender;
        tableModel.addColumn("sender");
        tableModel.addColumn("mail");
        tableModel.addColumn("bug name");
        tableModel.addColumn("receiver");
        if (user.currentUserType.equals("developer")) {
            msg = "assigned new bug ";
        } else {
            msg = "completed the bug";
        }
        try {
            ResultSet r = DataBase.database.retriveDataBase("select * from bug where " + check(user.currentUserType) + " ='" + user.curentuser + "' and notif ='" + user.currentUserType + "'");
            while (r.next()) {
                tableModel.addRow(new Object[]{r.getString(check(whoSender())),
                        msg,r.getString("bugName"),
                        user.curentuser
                });
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void sendMail( String username, String bName) {
        DataBase.database.updateDataBase("update bug set notif =" + whoSender() + " where " +check(user.currentUserType)+ "='" + username + "' and bugName ='" + bName + "'");
    }

    public static void deleteNotifications(DefaultTableModel tableModel){
        for(int i=0;i<tableModel.getRowCount();i++){
            DataBase.database.updateDataBase("update bug set notif =null where  bugName ='" + tableModel.getValueAt(i,2) + "'");
        }
        tableModel.setRowCount(0);
    }
}

