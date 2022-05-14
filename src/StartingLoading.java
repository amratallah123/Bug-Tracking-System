import javax.swing.*;

public class StartingLoading extends BugSystemGUI {
    private JPanel panel1;
    private JLabel ic;
    private JProgressBar progressBar1;
    private JFrame k;
    private long startTime;

    public StartingLoading(){
        super("",500,600,JFrame.EXIT_ON_CLOSE);
        ContentPanel.add(panel1);
        StyleComponents(panel1);
        startTime=(System.currentTimeMillis()/1000);
        progressBar1.setMaximum(5);
        time();
    }

    public void time(){
        long endTime=startTime;
        while(endTime-startTime<=3){
            endTime=(System.currentTimeMillis()/1000);
            progressBar1.setValue((int)(System.currentTimeMillis()/1000-startTime));
        }
        dispose();
    }
}
