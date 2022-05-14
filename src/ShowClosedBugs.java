
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShowClosedBugs extends BugSystemGUI {
    private JTable table;
    private JPanel Panel;
    private JScrollPane scrollPane;
    private JButton backButton;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private DefaultTableModel tableModel = new DefaultTableModel();
    private Manager manager;

    ShowClosedBugs(String windowTitle, int width, int height, int defaultCloseOperation) {
        super(windowTitle, width, height, defaultCloseOperation);
        /** styles */
        ContentPanel.add(Panel);
        StyleComponents(Panel);
        StyleComponents(topPanel);
        StyleComponents(bottomPanel);
        /***************************/

        showClosedBugs();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManagerPage("Buggy (Project Manager view)", 500, 500, EXIT_ON_CLOSE);
                dispose();
            }
        });
    }


    public void CreateTableOfClosedBugs() {
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

        public void showClosedBugs(){
            CreateTableOfClosedBugs();
            manager= new Manager();
        ArrayList<Bug> bugs = manager.getclosedBugs();
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
        if (bugs.size() == 0){ // checking if there is no closed bugs
            JOptionPane.showMessageDialog(null, "There is no closed bugs.");
        }
    }
}
