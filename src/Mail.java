import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mail extends BugSystemGUI {
    private JPanel root;
    private JTable table1;
    private JButton deleteAllButton;
    DefaultTableModel tableModel;

    public Mail(String userName, String code) {

        super("Inbox",850,500,JFrame.DISPOSE_ON_CLOSE);
        ContentPanel.add(root);
        StyleComponents(root);
        tableModel=new DefaultTableModel();
        table1.setModel(tableModel);
        Notification.viewMails(tableModel);
        deleteAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Notification.deleteNotifications(tableModel);
            }
        });
    }

public JPanel getRoot(){
        return root;
}

}
