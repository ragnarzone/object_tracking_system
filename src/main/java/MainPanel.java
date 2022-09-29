import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainPanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int STEP = 5;

    int x = 0;
    int y = 0;

    int real_position_x = x + 5;
    int real_position_y = x + 5;

    int estimated_position_x = 0;
    int estimated_position_y = 0;
    int last_sensed_obstacle_x = 0;
    int last_sensed_obstacle_y = 0;
    int steps_of_vehicle_y_direction = 0;
    boolean in_blind_zone = false;

    MainPanel(){
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        draw(graphics);
    }

    public void draw(Graphics graphics){

        //upper sensor
//        graphics.setColor(Color.BLUE);
//        graphics.fillRect(225, 100, 50, 50);

        //blind zones
        graphics.setColor(Color.GRAY);
        graphics.fillRect(175, 100, 100, 200);
        graphics.fillRect(325, 100, 100, 200);

        //bottom sensor
//        graphics.setColor(Color.BLUE);
//        graphics.fillRect(225, 250, 50, 50);

        //car body
        graphics.setColor(Color.CYAN);
        graphics.fillRect(275, 150, 50, 100);

        graphics.setColor(Color.BLUE);
        //top-right sensor
        graphics.drawPolygon(new int[] {325, 325, 375}, new int[] {150, 100, 150}, 3);
        //top-left sensor
        graphics.drawPolygon(new int[] {275, 275, 225}, new int[] {150, 100, 150}, 3);
        //bottom-left sensor
        graphics.drawPolygon(new int[] {275, 275, 225}, new int[] {250, 300, 250}, 3);
        //bottom-right sensor
        graphics.drawPolygon(new int[] {325, 325, 375}, new int[] {250, 300, 250}, 3);


        //obstacle
        graphics.setColor(Color.RED);
        graphics.fillRect(x, y, 10, 10);

        if(real_position_x >= 225 && real_position_x <= 275
                && real_position_y >= 100 && real_position_y <=150){
            last_sensed_obstacle_x = real_position_x;
            last_sensed_obstacle_y = real_position_y;
        }

        if(real_position_x >= 225 && real_position_x <= 275
                && real_position_y >= 250 && real_position_y <= 300){
            last_sensed_obstacle_x = real_position_x;
            last_sensed_obstacle_y = real_position_y;
        }

        if (last_sensed_obstacle_x >= 225 && last_sensed_obstacle_x <= 275
                && real_position_y > 150 && real_position_y < 250){
            in_blind_zone = true;
            estimated_position_x = real_position_x;
            estimated_position_y = real_position_y;
        } else {
            in_blind_zone = false;
        }

        // text output
        graphics.setColor(Color.GREEN);
        graphics.drawString("Real position of object: " + real_position_x + " " + real_position_y, 300, 10);
        graphics.setColor(Color.RED);
        graphics.drawString("Last sensed position: " + last_sensed_obstacle_x + " " + last_sensed_obstacle_y,300,25);

        if(in_blind_zone){
            graphics.setColor(Color.ORANGE);
            graphics.drawString("In blind zone", 330, 60);
        }
        graphics.setColor(Color.RED);
        graphics.drawString("Estimated position of object: " + estimated_position_x + " " + estimated_position_y, 270, 40);


    }

    public void countBlindSteps(){
        if(in_blind_zone){
            steps_of_vehicle_y_direction++;
        }
    }

    public void moveUp(){
        real_position_y -= STEP;
        y -= STEP;
        repaint();
    }

    public void moveDown(){
        real_position_y += STEP;
        y += STEP;
        repaint();
    }

    public void moveLeft(){
        real_position_x -= STEP;
        x -= STEP;
        repaint();
    }

    public void moveRight(){
        real_position_x += STEP;
        x += STEP;
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_UP){
                moveUp();
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN){
                moveDown();
                countBlindSteps();
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT){
                moveLeft();
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT){
                moveRight();
            }
        }
    }

}
