import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainPanel extends JPanel implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

    }



    static final int SCREEN_WIDTH = 500;
    static final int SCREEN_HEIGHT = 500;
    static final int STEP = 15;
    static final int AVAILABLE_STEPS = SCREEN_HEIGHT-100/STEP;
    final int[] y = new int[AVAILABLE_STEPS];

    int y_position = SCREEN_HEIGHT-100;
    {
        for (int i = 0; i < y.length; i++) {
            y_position -= STEP;
            y[i] = y_position;
        }
    }

    boolean running = false;
    int i = 0;

    MainPanel(){
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        draw(graphics);
    }

    public void draw(Graphics graphics){


        // debug
        for (int value : y){
            System.out.println(value);
        }

        for (int i = 0; i < AVAILABLE_STEPS; i++) {

        }
        graphics.setColor(Color.BLUE);
        graphics.fillRect(225, y[i], 50, 100);

        graphics.setColor(Color.GREEN);
        graphics.fillRect(195, y[i]-30,30, 30);
        graphics.fillRect(195, y[i]+100,30, 30);
        graphics.fillRect(275, y[i]-30,30, 30);
        graphics.fillRect(275, y[i]+100,30, 30);

        //origin
        graphics.setColor(Color.RED);
        graphics.fillRect(250, y[i]+30, 1, 20);
        graphics.setColor(Color.YELLOW);
        graphics.fillRect(230,y[i]+50,20, 1);

        graphics.setColor(Color.RED);
        graphics.fillRect(200, 100, 10, 10);
    }

    public void moveUp(){
        i++;
        repaint();
    }

    public void moveDown(){
        i--;
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_UP){
                System.out.println("Button Pressed");
                moveUp();
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN){
                moveDown();
            }
        }
    }

}
