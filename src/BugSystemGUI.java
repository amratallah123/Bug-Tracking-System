import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BugSystemGUI extends JFrame implements ActionListener, MouseListener {

    public JPanel TitlebarPanel = new GraphicalPanel(this);
    public JPanel ContentPanel = new JPanel();
    private JButton closeWindow;
    private JButton minimizeWindow;
/*
    private Color dark = new Color(0x2C2F33);
    private Color light = new Color(0x3b3f44);
    private Color veryDark = new Color(0x23272A);*/

    private Color dark = new Color(0x3C0102);
    private Color light = new Color(0x2B2B2B);
    private Color veryDark = new Color(0x0E0B0E);


    BugSystemGUI(String windowTitle, int width, int height, int defaultCloseOperation){

        ImageIcon icon = new ImageIcon("icon.png");
        setSize(width, height);
        setDefaultCloseOperation(defaultCloseOperation);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setLayout(new BorderLayout());
        setIconImage(icon.getImage());

        JPanel titlePanel = new JPanel();
        JPanel windowControlsPanel = new JPanel();

        JLabel title = new JLabel(windowTitle);
        title.setIcon(icon);
        title.setForeground(Color.WHITE);

        titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        titlePanel.add(title);

        closeWindow = new JButton("X");
        closeWindow.setFocusable(false);
        closeWindow.setBackground(null);
        closeWindow.setForeground(Color.WHITE);
        closeWindow.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        closeWindow.addActionListener(this);
        closeWindow.addMouseListener(this);

        minimizeWindow = new JButton("_");
        minimizeWindow.setFocusable(false);
        minimizeWindow.setBackground(null);
        minimizeWindow.setForeground(Color.WHITE);
        minimizeWindow.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        minimizeWindow.addActionListener(this);
        minimizeWindow.addMouseListener(this);

        windowControlsPanel.add(minimizeWindow);
        windowControlsPanel.add(closeWindow);

        TitlebarPanel.setBackground(veryDark);
        titlePanel.setBackground(TitlebarPanel.getBackground());
        windowControlsPanel.setBackground(TitlebarPanel.getBackground());
        ContentPanel.setBackground(dark);


        TitlebarPanel.setLayout(new BorderLayout());
        TitlebarPanel.add(titlePanel, BorderLayout.WEST);
        TitlebarPanel.add(windowControlsPanel, BorderLayout.EAST);


        add(TitlebarPanel, BorderLayout.NORTH);
        add(ContentPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == closeWindow){
            dispose();
        }
        else if(e.getSource() == minimizeWindow){
            this.setState(Frame.ICONIFIED);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == closeWindow){
            closeWindow.setBackground(Color.RED);
        }
        else if (e.getSource() == minimizeWindow){
            ((JButton) e.getSource()).setBackground(dark);
        }
        else if (e.getSource() instanceof JButton){
            ((JButton) e.getSource()).setBackground(veryDark);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == closeWindow || e.getSource() == minimizeWindow){
            ((JButton) e.getSource()).setBackground(null);
        }
        else if (e.getSource() instanceof JButton){
            ((JButton) e.getSource()).setBackground(light);
        }
    }

    public void StyleComponents(JPanel parent){
        for (Component comp : parent.getComponents()) {
            parent.setBackground(dark);
            if (comp instanceof  JPanel){
                comp.setBackground(dark);
            }
            else if (comp instanceof JButton) {
                ((JButton)comp).setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                //((JButton)comp).setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
                ((JButton)comp).setFocusable(false);
                ((JButton)comp).setBackground(light);
                ((JButton)comp).setForeground(Color.WHITE);
                ((JButton)comp).addActionListener(this);
                ((JButton)comp).addMouseListener(this);
            }
            else if (comp instanceof JLabel){
                comp.setForeground(Color.WHITE);
            }
            else if (comp instanceof JRadioButton){
                comp.setBackground(null);
                comp.setForeground(Color.WHITE);
            }
            else if (comp instanceof JTextField){
                comp.setBackground(light);
                comp.setForeground(Color.WHITE);
                ((JTextField) comp).setCaretColor(Color.WHITE);
            }
            else if (comp instanceof JComboBox){
                comp.setBackground(light);
                ((JComboBox) comp).setForeground(Color.WHITE);
            }
            else if (comp instanceof JScrollPane){
                comp.getParent().setBackground(dark);
            }
            setVisible(true);
        }
    }
}
