import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class insertUserForm extends BugSystemGUI {

    private JButton insertusers;
    private JComboBox usertype;
    private JFormattedTextField emailfield;
    private JPanel Panel;
    private JPasswordField passwordField1;
    private JTextField usernamefield;
    private JButton backButton;
    private Admin admin;

    public insertUserForm(String windowTitle, int width, int height, int defaultCloseOperation) {
        /** styles */
        super(windowTitle, width, height, defaultCloseOperation);
        Panel.setPreferredSize(new Dimension(480,480));
        ContentPanel.add(Panel);
        StyleComponents(Panel);
        /************ */

        insertusers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 admin = new Admin(usernamefield.getText(), emailfield.getText(), passwordField1.getText(), getUserType());
                if (!emailfield.getText().trim().isEmpty() && !usernamefield.getText().trim().isEmpty() && !passwordField1.getText().trim().isEmpty()) {
                    admin.insertUsers(admin);
                } else {
                    JOptionPane.showMessageDialog(null, "please enter all required information", "Error", JOptionPane.ERROR_MESSAGE);
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

    public String getUserType(){
        int index = Integer.parseInt(String.valueOf(usertype.getSelectedIndex()));
        String TypeOfUser = null;
        if (index == 0) {
            TypeOfUser = "tester";
        } else if (index == 1) {
            TypeOfUser = "developer";
        } else if (index == 2) {
            TypeOfUser = "manager";
        } else if (index == 3) {
            TypeOfUser = "admin";
        }
        return TypeOfUser;
    }

}
