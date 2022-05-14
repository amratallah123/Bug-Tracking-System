import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TesterWindowGUI extends BugSystemGUI {
    private JButton defineBug = new JButton("Define a Bug");
    private JButton assignBug = new JButton("Assign a Bug");
    private JButton monitorBugs = new JButton("Monitor all Bugs");
    private JButton logout = new JButton("Logout");
    private JButton notification=new JButton();

    TesterWindowGUI(String windowTitle, int width, int height, int defaultCloseOperation) {
        super(windowTitle, width, height, defaultCloseOperation);

        AssignBugForm assignBugMenu = new AssignBugForm();
        DefineBugForm defineBugMenu = new DefineBugForm();
        BugTracking monitorBugsMenu = new BugTracking();
        ContentPanel.add(defineBug);
        ContentPanel.add(assignBug);
        ContentPanel.add(monitorBugs);
        ContentPanel.add(logout);
        ContentPanel.add(notification);
        ContentPanel.add(defineBugMenu.GetRoot());
        ContentPanel.add(assignBugMenu.GetRoot());
        ContentPanel.add(monitorBugsMenu.GetRoot());
        StyleComponents(ContentPanel);
        StyleComponents(defineBugMenu.GetRoot());
        StyleComponents(assignBugMenu.GetRoot());
        StyleComponents(monitorBugsMenu.GetRoot());

        Notification.checkInbox(notification,user.curentuser,user.currentUserType);
        logout.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginVerivication loginPage = new LoginVerivication("Bug Tracking System ", 400, 260, JFrame.EXIT_ON_CLOSE);
            }
        });
        notification.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mail not=new Mail(user.curentuser,user.currentUserType);
                Notification.checkInbox(notification,user.curentuser,user.currentUserType);
            }
        });

        defineBug.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                defineBugMenu.GetRoot().setVisible(true);
                assignBugMenu.GetRoot().setVisible(false);
                monitorBugsMenu.GetRoot().setVisible(false);
                setSize(new Dimension(width, height));
            }
        });

        assignBug.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                defineBugMenu.GetRoot().setVisible(false);
                assignBugMenu.GetRoot().setVisible(true);
                monitorBugsMenu.GetRoot().setVisible(false);
                setSize(new Dimension(width, height));
                assignBugMenu.updateDevloperList();
            }
        });

        monitorBugs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                monitorBugsMenu.UpdateTableOfBugs();
                defineBugMenu.GetRoot().setVisible(false);
                assignBugMenu.GetRoot().setVisible(false);
                monitorBugsMenu.GetRoot().setVisible(true);
                setSize(new Dimension(900, height));
            }
        });

    }
}
