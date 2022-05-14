import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Developer extends user {

    public Developer() { }

    public static void updateBugsStatus(String[] lastStatus, String username, DefaultTableModel dtm){
        for(int i=0;i<dtm.getRowCount();i++){
            if(!(dtm.getValueAt(i,6)).equals(lastStatus[i])){
                if(lastStatus[i].equals("closed")){
                    JOptionPane.showMessageDialog(null,dtm.getValueAt(i,0)+" is closed","error",JOptionPane.ERROR_MESSAGE);
                    continue;
                }
                else {
                    DataBase.database.updateDataBase("update bug set bugStatus ='" + dtm.getValueAt(i, 6) + "' where bugName ='" + dtm.getValueAt(i, 0) + "'");
                    JOptionPane.showMessageDialog(null,  dtm.getValueAt(i,0)+" changed successfully", "success",JOptionPane.INFORMATION_MESSAGE);
                }
                if(dtm.getValueAt(i,6).equals("closed")){
                    updateDeveloperRate();
                    Notification.sendMail(username, (String) dtm.getValueAt(i,0));
                }
            }
        }
    }

    public static void  updateDeveloperRate() {
        String query = "select rate from users where email='" + user.curentuser + "'";
        try {
            ResultSet developer = DataBase.database.retriveDataBase(query);
            developer.next();
            int rate = Integer.parseInt(developer.getString("rate"));
            DataBase.database.updateDataBase("update users set rate='"+(rate+1)+"' where email='"+user.curentuser+"'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

        public static String[] GetlastStatus(DefaultTableModel dtm){
        String[] status=new String[dtm.getRowCount()];
        for (int i =0;i<dtm.getRowCount();i++){
            status[i]=(String) dtm.getValueAt(i,6);
        }
        return status;
    }


}


