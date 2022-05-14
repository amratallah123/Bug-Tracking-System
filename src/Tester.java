import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Tester extends user  {


    public Tester() { }

    public static void  detectBug(Bug b) {
        String query= "insert into " + "bug" + "(bugName,bugType,bugPriority,bugLevel,projectName,bugDate,bugStatus,screenShot,tester) values ('" + b.getBugName() + "','" + b.getBugType() + "','" + b.getPriority() + "','" + b.getBugLevel() + "','" + b.getProjectName() + "','" + b.getBugDate() + "','" + b.getBugStatus() + "','" + b.getScreenshot() + "','"+b.getTester()+"') ";
        int check = DataBase.database.insertDataBase(query);
        if (check == 1) {
            Tester.updateTesterRate();
            JOptionPane.showMessageDialog(null, "bug inserted successfully", null, JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(null, "bug couldn't be inserted make sure that the bug name is not duplicated!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

     public static void  updateTesterRate() {
      String query = "select rate from users where email='" + user.curentuser + "'";
      try {
          ResultSet tester = DataBase.database.retriveDataBase(query);
          tester.next();
          int rate = Integer.parseInt(tester.getString("rate"));
          DataBase.database.updateDataBase("update users set rate='" + (rate + 1) + "' where email='" + user.curentuser + "'");
      } catch (
              SQLException throwables) {
          throwables.printStackTrace();
      }
  }

    public static void  attachScreenshotTOBug(JFileChooser fileChooser,JTextField attachScreenshotText,Bug b) {
        int response = fileChooser.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            File sourceFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
            File destinationFolder = new File(System.getProperty("user.dir") + "\\Screenshots\\" + sourceFile.getName());
            attachScreenshotText.setText(sourceFile.toString());
            try {
                copyScreenshotTOFile(sourceFile, destinationFolder);
                b.setScreenshot(sourceFile.getName());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private static void copyScreenshotTOFile(File source, File dest) throws IOException {
        FileChannel sourceChannel = null;
        FileChannel destChannel = null;
        try {
            sourceChannel = new FileInputStream(source).getChannel();
            destChannel = new FileOutputStream(dest).getChannel();
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        }finally{
            sourceChannel.close();
            destChannel.close();
        }
    }

    public static void  AssignBugToDeveloper(JComboBox bugs,JComboBox developerEmails) {
        DataBase.database.accessDataBase("update bug set developer='" + developerEmails.getSelectedItem() + "' where bugName='" + bugs.getSelectedItem() + "'");
        Notification.sendMail(user.curentuser, (String) bugs.getSelectedItem());
        bugs.removeItemAt(bugs.getSelectedIndex());
        JOptionPane.showMessageDialog(null, "bug assinged successfully", null, JOptionPane.INFORMATION_MESSAGE);
        if (bugs.getSelectedItem().equals(null))
            JOptionPane.showMessageDialog(null, "there are no bugs to assign", null, JOptionPane.INFORMATION_MESSAGE);

    }

    public static void FindBugsWithoutAssining(JComboBox developerEmails, JComboBox bugs){
        ResultSet rs,rs2 ;
        rs= DataBase.database.getUsersWithSpecificType("users","developer");
        rs2= DataBase.database.retriveDataBase("select * from bug where developer IS NULL  and tester='"+user.curentuser+"'");
        developerEmails.removeAllItems();
        bugs.removeAllItems();
        while(true){
            try {
                if (!rs.next()) break;
                developerEmails.addItem(rs.getString("email"));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        while(true){
            try {
                if(!rs2.next())break;
                bugs.addItem(rs2.getString("bugName"));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
