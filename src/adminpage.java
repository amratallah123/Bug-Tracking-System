import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class adminpage extends BugSystemGUI {

    private JButton viewallbugButton;
    private JButton insertButton;
    private JButton updateButton;
    private JPanel admin;
    private JButton backButton;

    public adminpage (String windowTitle, int width, int height, int defaultCloseOperation) {
        super(windowTitle, width, height, defaultCloseOperation);

        /** styles */
        admin.setPreferredSize(new Dimension(380,460));
        ContentPanel.add(admin);
        StyleComponents(admin);
        /**************/

        viewallbugButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewallbugs defpage= null;
                try {
                   defpage = new viewallbugs( "view all bug",1000,500, JFrame.EXIT_ON_CLOSE);
                } catch (SQLException throwables) {
                   throwables.printStackTrace();
               }
            }
        });

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertUserForm defPage=new insertUserForm( "insert page",500,500, JFrame.EXIT_ON_CLOSE);
                /** styles */
                defPage.setSize(500,500);
                dispose();
                /****************/
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewallusers defpage= null;
                try {
                    defpage = new viewallusers( "update page",550,580, JFrame.EXIT_ON_CLOSE);
                    dispose();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              LoginVerivication defpage=new LoginVerivication( " Bug Tracking System ",400,260, JFrame.EXIT_ON_CLOSE);
              dispose();
            }
        });
    }

}
