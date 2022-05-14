import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DefineBugForm extends JFrame{

    private JPanel panel;
    private JButton defineBugButton;
    private JComboBox priority;
    private JComboBox buglevel;
    private JFormattedTextField bugname;
    private JFormattedTextField bugtype;
    private JFormattedTextField projectname;
    private JRadioButton bugStatus;
    private JTextField attachScreenshotText;
    private JButton attachScreenshotButton;
    private Tester tester=new Tester();
    private JFileChooser fileChooser = new JFileChooser();
    private Bug b=new Bug();

    DefineBugForm() {

        /** styles */
        panel.setPreferredSize(new Dimension(400, 400));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg","jpeg", "png", "gif", "bmp");
        fileChooser.setFileFilter(filter);
        fileChooser. setAcceptAllFileFilterUsed(false);
        /** ********* */

        defineBugButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefineBug();
            }
        });

        attachScreenshotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tester.attachScreenshotTOBug( fileChooser, attachScreenshotText, b);
            }
        });
    }

    public JPanel GetRoot() { return panel; }

    private  void DefineBug() {
        b.setBugName(bugname.getText());
        b.setBugType(bugtype.getText());
        b.setBugStatus("opened");
        b.setProjectName(projectname.getText());
        b.setPriority(Integer.parseInt(priority.getSelectedItem().toString()));
        b.setBugLevel(Integer.parseInt(buglevel.getSelectedItem().toString()));
        b.setTester(user.curentuser);
        if (!bugname.getText().equals("")&&!projectname.getText().equals("")&&!bugtype.getText().equals("")) {
            tester.detectBug(b);
        }
        else {
            JOptionPane.showMessageDialog(null,"make sure you didn't forgot anything empty!","Erorr",JOptionPane.ERROR_MESSAGE);
        }
    }

    }
