import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginVerivication extends BugSystemGUI {
    private JPanel page;
    private JFormattedTextField emailfield;
    private JPasswordField passwordfield;
    private JButton loginbutton;
    private JButton exitButton;
    private Admin admin;
    public LoginVerivication(String windowTitle, int width, int height, int defaultCloseOperation) {
        /** styles */
        super(windowTitle, width, height, defaultCloseOperation);
        page.setPreferredSize(new Dimension(330,240));
         ContentPanel.add(page);
         StyleComponents(page);
        /************ */

         admin= new Admin();

        loginbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user u=new user();
                u.setEmail(emailfield.getText());
                u.setPassword(passwordfield.getText());
                user.setCurentuser(emailfield.getText());
                String code=admin.LoginVerivication(u);
                user.setCurentCode(code);
                admin.identifiyUserType(code);
                dispose();
            }

        });

                 exitButton.addActionListener(new ActionListener() {
                 @Override
                  public void actionPerformed(ActionEvent e) {
                          System.exit(0);
                 }
                 });
    }
}


