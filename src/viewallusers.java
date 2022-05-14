import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class viewallusers extends BugSystemGUI {
    private JTable userstable;
    private JButton updateButton;
    private JPanel panel;
    private JButton backButton;
    private DefaultTableModel tableModel = new DefaultTableModel();

    public viewallusers(String windowTitle, int width, int height, int defaultCloseOperation) throws SQLException {
        /** styles */
        super(windowTitle, width, height, defaultCloseOperation);
        panel.setPreferredSize(new Dimension(540,540));
        setLocation(400,100);
        ContentPanel.add(panel);
        StyleComponents(panel);
        /** *********** */

        ShowAllUsers();

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row=-1;
                row= userstable.getSelectedRow();
                if(row==-1){
                    JOptionPane.showMessageDialog(null,"please select row" ,"WARNING",JOptionPane.INFORMATION_MESSAGE);
                }
                String email= (String) userstable.getValueAt(row,1);
                updateUsers defpage= null;
                try {
                    defpage = new updateUsers("update page",500,500, JFrame.EXIT_ON_CLOSE,email);
                    dispose();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminpage defpage=new adminpage( "admin page",450,500, JFrame.EXIT_ON_CLOSE);
                dispose();
            }
        });
    }

    public void CreateTableOfUsers(){
        userstable.setModel(tableModel);
        tableModel.addColumn("username");
        tableModel.addColumn("email");
        tableModel.addColumn("userpassword");
        tableModel.addColumn("code");
        tableModel.addColumn("rate");
        userstable.getTableHeader().setBackground(new Color(0x2C2F33));
        userstable.getTableHeader().setForeground(Color.WHITE);
    }

    public void ShowAllUsers() throws SQLException {
        CreateTableOfUsers();
        ResultSet r = DataBase.database.retriveDataBase("select * from " + "users" + "");
        while (r.next()) {
            tableModel.addRow(new Object[]{r.getString("username"),
                    r.getString("email"),
                    r.getString("userpassword"),
                    r.getString("code"),
                    r.getString("rate")});
        }
    }
}