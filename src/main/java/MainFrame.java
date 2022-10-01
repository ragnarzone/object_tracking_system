import javax.swing.*;

public class MainFrame extends JFrame {


    MainFrame() throws Exception {
        this.add(new MainPanel());
        this.setTitle("Object Tracking");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

}
