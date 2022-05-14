import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class viewallbugs extends BugSystemGUI {
    private JTable showtable;
    private JPanel panel;
    private DefaultTableModel tableModel=new DefaultTableModel();

    public viewallbugs(String windowTitle, int width, int height, int defaultCloseOperation) throws SQLException {
        /** styles */
        super(windowTitle, width, height, defaultCloseOperation);
        panel.setPreferredSize(new Dimension(1000,480));
        ContentPanel.add(panel);
        StyleComponents(panel);
        /** ******* */

        ShowAllBug() ;
    }

    public void CreateTableOfBugs(){
        showtable.setModel(tableModel);
        tableModel.addColumn("bugName");
        tableModel.addColumn("bugType");
        tableModel.addColumn("bugStatues");
        tableModel.addColumn("bugPriority");
        tableModel.addColumn("bugLevel");
        tableModel.addColumn("projectName");
        tableModel.addColumn("bugDate");
        tableModel.addColumn("screenShot");
        tableModel.addColumn("developer");
        tableModel.addColumn("tester");
    }

    public void ShowAllBug() throws SQLException {
        CreateTableOfBugs();
        ResultSet r = DataBase.database.retriveDataBase("select * from " + "bug" + "");
        while(r.next()) {
            tableModel.addRow(new Object[]{r.getString("bugName"),
                    r.getString("bugType"),
                    r.getString("bugStatus"),
                    r.getString("bugPriority"),
                    r.getString("bugLevel"),
                    r.getString("projectName"),
                    r.getString("bugDate"),
                    r.getString("screenShot"),
                    r.getString("developer"),
                    r.getString("tester")});
        }
    }

}







