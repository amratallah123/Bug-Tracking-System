
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CheckDeveloperPerformance extends BugSystemGUI {
    private JTextField performance;
    private JButton checkButton;
    private JButton backButton;
    private JPanel root;
    private JPanel leftPanel;
    private JPanel bottomPanel;
    private JPanel rightPanel;
    private JComboBox devEmail;
    private Manager manager;

    CheckDeveloperPerformance(String windowTitle, int width, int height, int defaultCloseOperation) {

        /** styles */
        super(windowTitle, width, height, defaultCloseOperation);
        ContentPanel.add(root);
        StyleComponents(root);
        StyleComponents(leftPanel);
        StyleComponents(bottomPanel);
        StyleComponents(rightPanel);
        /** ********** */

        ShowDeveloperList();

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = (String)devEmail.getSelectedItem();
                manager.Checkperformance(email,performance);
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

    private void ShowDeveloperList() {
        manager=new Manager();
        ArrayList<user> developers = manager.CreateDeveloperPerformanceList(); // method for returning an array of all developers
        //  Boolean found = false; //for checking if this email exists
        for (int i=0 ; i<developers.size() ; i++){
            devEmail.addItem(developers.get(i).getEmail()); // adding each email to the comboBox
        }
    }


}
