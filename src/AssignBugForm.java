import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AssignBugForm extends JFrame {
    private JPanel root;
    private JButton assignBugButton;
    private JComboBox bugs;
    private JComboBox developerEmails;
    private ResultSet rs,rs2 ;
    private Tester tester;

    public AssignBugForm() {
        /** styles */
        root.setVisible(false);
        add(root);
        root.setPreferredSize(new Dimension(400,400));
        /************* */

        tester=new Tester();
        tester.FindBugsWithoutAssining(developerEmails,bugs);

        assignBugButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                tester.AssignBugToDeveloper( bugs, developerEmails);
            }
        });
    }




    public JPanel GetRoot() { return root; }

    public  void updateDevloperList(){
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