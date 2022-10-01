import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



public class MainPanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 1600;
    static final int SCREEN_HEIGHT = 800;
    static final int STEP = 10;
    static final int ORIGIN_Y = 800;
    static final int ORIGIN_X = 400;


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

    Timer timer;

    Reader reader;

    Thread t1;

    MainPanel() throws Exception {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startThisNow();
    }

    public void startThisNow() throws Exception {
        timer = new Timer(3, this);
        reader = new Reader();
        t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    reader.startReading();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t1.start();
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        try {
            draw(graphics);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics graphics) throws Exception {

        //car body
        graphics.setColor(Color.CYAN);
        graphics.fillRect(790, 370, 20, 40);

        //corners
        graphics.setColor(Color.BLUE);
        //sensor 0
        graphics.drawPolygon(new int[] {790, 810, 600}, new int[] {370, 200, 410}, 3);

        //sensor 1
        graphics.drawPolygon(new int[] {810, 790, 1000}, new int[] {370, 200, 410}, 3);

        //sensor 2
        graphics.drawPolygon(new int[] {790, 810, 600}, new int[] {410, 580, 390}, 3);

        //sensor 3
        graphics.drawPolygon(new int[] {810, 790, 1000}, new int[] {410, 580, 390}, 3);


        graphics.setColor(Color.CYAN);
        graphics.drawString("sensor 0", 720, 320);
        graphics.drawString("sensor 1", 830, 320);
        graphics.drawString("sensor 2", 720, 470);
        graphics.drawString("sensor 3", 830, 470);

        //camera zone
        graphics.setColor(Color.YELLOW);
        graphics.drawPolygon(new int[] {800, 550, 1050}, new int[] {390, 200, 200}, 3);
        graphics.drawString("Camera", 780, 280);


        //camera
        for (int i = 0; i < 15; i++) {
            if(reader.cameraObjectTypes[i] != 0){
                graphics.setColor(Color.MAGENTA);
                graphics.fillRect(ORIGIN_Y - reader.cameraObjectsDy[i]*STEP - 6, ORIGIN_X - reader.cameraObjectsDx[i]*STEP - 6 - 30, 12, 12);
                graphics.setColor(Color.GREEN);
                graphics.drawString(String.valueOf(i),ORIGIN_Y - reader.cameraObjectsDy[i]*STEP-4,ORIGIN_X - reader.cameraObjectsDx[i]*STEP-25);
            }
        }
        //corner
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                if(reader.cornerDx[i][j] != 0 && reader.cornerDy[i][j] != 0){
                    graphics.setColor(Color.GREEN);
                    graphics.fillRect(ORIGIN_Y - reader.cornerDy[i][j]*STEP - 4,ORIGIN_X - reader.cornerDx[i][j]*STEP,8,8);
                    graphics.setColor(Color.RED);
                    graphics.drawString("object " + i, ORIGIN_Y - reader.cornerDy[i][j]*STEP - 4 - 4, ORIGIN_X - reader.cornerDx[i][j]*STEP-10);
                    graphics.drawString("sensor " + j, ORIGIN_Y - reader.cornerDy[i][j]*STEP - 4 - 4, ORIGIN_X - reader.cornerDx[i][j]*STEP);
                }

            }
        }

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
