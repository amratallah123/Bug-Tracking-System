
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CheckTesterPerformance extends BugSystemGUI {
    private JPanel panel;
    private JTextField performance;
    private JButton checkButton;
    private JButton backButton;
    private JPanel leftPanel;
    private JPanel bottomPanel;
    private JPanel rightPanel;
    private JComboBox testerEmail;
    private Manager manager;

    CheckTesterPerformance(String windowTitle, int width, int height, int defaultCloseOperation) {

        /** styles */
        super(windowTitle, width, height, defaultCloseOperation);
        ContentPanel.add(panel);
        StyleComponents(panel);
        StyleComponents(leftPanel);
        StyleComponents(bottomPanel);
        StyleComponents(rightPanel);
        /** ********** */

        ShowTesterList();

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = (String) testerEmail.getSelectedItem();
                manager.Checkperformance( email,performance);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManagerPage("Buggy (Project Manager view)", 500, 500, EXIT_ON_CLOSE);
                dispose();
            }
        });
    }

    private void ShowTesterList() {
        manager=new Manager();
        ArrayList<user> testers = manager.CreateTesterPerformanceList(); // method for returning an array of all testers
        // Boolean found = false; //for checking if this email exists
        for (int i=0 ; i<testers.size() ; i++){
            testerEmail.addItem(testers.get(i).getEmail()); // adding each email to the comboBox
        }
    }


}
