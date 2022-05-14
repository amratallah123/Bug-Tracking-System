
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShowOpenBugs extends BugSystemGUI {
    private JTable table;
    private JScrollPane scrollPane;
    private JButton backButton;
    private JPanel bottomPanel;
    private JPanel root;
    private JPanel topPanel;
    private DefaultTableModel tableModel = new DefaultTableModel();
    private Manager manager;

    ShowOpenBugs(String windowTitle, int width, int height, int defaultCloseOperation) {
        super(windowTitle, width, height, defaultCloseOperation);

        /** styles */
        ContentPanel.add(root);
        StyleComponents(root);
        StyleComponents(topPanel);
        StyleComponents(bottomPanel);
        /***************************/

        showOpenBugs();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManagerPage("Buggy (Project Manager view)", 500, 500, EXIT_ON_CLOSE);
                dispose();
            }
        });
    }
    public void CreateTableOfOpenBugs() {
        tableModel.addColumn("Bug name");
        tableModel.addColumn("Bug type");
        tableModel.addColumn("Bug priority");
        tableModel.addColumn("Bug level");
        tableModel.addColumn("Project name");
        tableModel.addColumn("Bug date");
        tableModel.addColumn("Developer");
        tableModel.addColumn("Tester");
        table.setModel(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(800, 250));
    }

        public void showOpenBugs(){
            CreateTableOfOpenBugs();
            manager= new Manager();
        ArrayList<Bug> bugs = manager.getOpenBugs();
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        Object[] row = new Object[8];
        for (int i=0 ; i<bugs.size() ; i++){
            row[0] = bugs.get(i).getBugName();
            row[1] = bugs.get(i).getBugType();
            row[2] = bugs.get(i).getPriority();
            row[3] = bugs.get(i).getBugLevel();
            row[4] = bugs.get(i).getProjectName();
            row[5] = bugs.get(i).getDisplayableBugDate();
            row[6] = bugs.get(i).getDeveloper();
            row[7] = bugs.get(i).getTester();
            model.addRow(row);
        }
        if (bugs.size() == 0){ // checking if there is no open bugs
            JOptionPane.showMessageDialog(null, "The is no open bugs.");
        }
    }

}
