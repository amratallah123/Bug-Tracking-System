import javax.swing.*;
import java.awt.event.*;

public class DeveloperPage extends BugSystemGUI {
    private JPanel panel1;
    private JButton viewAllBugsButton;
    private JButton notification;
    private JButton logoutButton;
    private JLabel welcomeLabel;
    private Mail n;
    public DeveloperPage(){
        /** styles */
        super(user.curentuser,500,500,JFrame.EXIT_ON_CLOSE);
        ContentPanel.add(panel1);
        StyleComponents(panel1);
        /** ******* */

        String usermail=user.curentuser;
        welcomeLabel.setText("welcome back "+user.curentuser);
        Notification.checkInbox(notification,usermail,user.currentUserType);

        viewAllBugsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBugsStatus vb=new updateBugsStatus(usermail);
                dispose();
            }
        });

        notification.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 n=new Mail(usermail,"developer");
                Notification.checkInbox(notification,usermail,user.currentUserType);
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginVerivication c=new LoginVerivication("Bug Tracking System ", 400, 260, JFrame.EXIT_ON_CLOSE);
                dispose();
            }
        });
    }


    }

