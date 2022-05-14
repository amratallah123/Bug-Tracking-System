import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class updateUsers extends BugSystemGUI {
    private JFormattedTextField usernamefield;
    private JTextField emailfeild;
    private JButton saveChangeButton;
    private JButton deleteButton;
    private JTextField passwordfield;
    private JPanel panel;
    private JTextField usertypefield;
    private JButton backButton;
    static String getemail;
    private Admin admin;

    public updateUsers(String windowTitle, int width, int height, int defaultCloseOperation, String emailvalue) throws SQLException {
        /** styles */
        super(windowTitle, width, height, defaultCloseOperation);
        panel.setPreferredSize(new Dimension(480,480));
        ContentPanel.add(panel);
        StyleComponents(panel);
        /** styles */

        GetUserData(emailvalue);

        saveChangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                admin=new Admin(usernamefield.getText(),getemail,passwordfield.getText(),usertypefield.getText());
                admin.updateUsers(admin);
                dispose();
                viewallusers defpage = null;
                try {
                    defpage = new viewallusers( "update page",550,580, JFrame.EXIT_ON_CLOSE);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                 } });


        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                admin=new Admin();
                admin.setEmail(getemail);
                admin.DeleteUser(admin);
                dispose();
                viewallusers defpage = null;
                try {
                    defpage = new viewallusers( "update page",550,580, JFrame.EXIT_ON_CLOSE);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            } });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewallusers  defpage = null;
                try {
                    defpage = new viewallusers( "update page",550,580, JFrame.EXIT_ON_CLOSE);
                    dispose();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }});

    }

    public void GetUserData(String emailvalue) throws SQLException {
        getemail=emailvalue;
        emailfeild.setText(emailvalue);
        ResultSet r = Admin.retriveSpecificUser(getemail);
        while (r.next()) {
            usernamefield.setText(r.getString("username"));
            passwordfield.setText(r.getString("userpassword"));
            usertypefield.setText(r.getString("code"));
        }
    }

    }
