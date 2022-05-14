import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;


public class updateBugsStatus extends BugSystemGUI {
    private JPanel panel;
    private JTable bugList;
    private JButton refreshButton;
    private JButton update;
    private JButton back;
    private String [] status;
    private Developer developer=new Developer();

    DefaultTableModel tableModel=new DefaultTableModel(){
        boolean[] canEdit = new boolean[]{
                false, false, false, false, false,false,true,false
        };
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit[columnIndex];
        }
    };

    public updateBugsStatus(String username){

        /** styles */
        super("developer",500,560,JFrame.DISPOSE_ON_CLOSE);
        ContentPanel.add(panel);
        StyleComponents(panel);
        /** ********** */

        ShowAssignedBugs(username);
        status=developer.GetlastStatus(tableModel);

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowAssignedBugs(username);
            }
        });

        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                developer.updateBugsStatus(status,username,tableModel);
                status=developer.GetlastStatus(tableModel);
            }
        });

        bugList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = bugList.getSelectedRow();
                int col = bugList.getSelectedColumn();
                if (col == 7){
                    try {
                        String imageName = tableModel.getValueAt(row, col).toString();
                        BufferedImage bufferedImage = null;
                        try {
                            bufferedImage = ImageIO.read(new File(System.getProperty("user.dir") + "\\Screenshots\\" + imageName));
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        int width = bufferedImage.getWidth();
                        int height = bufferedImage.getHeight();
                        ShowImage previewImage = new ShowImage(imageName, width, height + 30, JFrame.DO_NOTHING_ON_CLOSE, bufferedImage);
                    }
                    catch (NullPointerException ex) { }
                }
            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeveloperPage d=new DeveloperPage();
                dispose();
            }
        });
    }

    public void CreateTableOfBugs() {
        bugList.setModel(tableModel);
        tableModel.setColumnCount(0);
        tableModel.addColumn("bug name");
        tableModel.addColumn("bug type");
        tableModel.addColumn("level");
        tableModel.addColumn("priority");
        tableModel.addColumn("Project Name");
        tableModel.addColumn("date");
        tableModel.addColumn("status");
        tableModel.addColumn("screenShoot");
        tableModel.addColumn("tester");
        tableModel.setRowCount(0);
    }

        // SHOW ASSIGNED BUGS FOR SPECIFIC DEVELOPER
    private  void ShowAssignedBugs(String name){
        CreateTableOfBugs();
        try {
            ResultSet r= DataBase.database.retriveDataBase("select *from bug where developer ='"+name+"'");
            while (r.next()) {
                tableModel.addRow(new Object[]{r.getString("bugName"),
                        r.getString("bugType"),
                        r.getString("bugLevel"),
                        r.getString("bugPriority"),
                        r.getString("projectName"),
                        r.getString("bugDate"),
                        r.getString("bugStatus"),
                        r.getString("screenShot"),
                        r.getString("tester"),
                    });
                }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
