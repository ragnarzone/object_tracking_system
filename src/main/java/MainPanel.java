import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainPanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 1600;
    static final int SCREEN_HEIGHT = 800;
    static final int STEP = 10;
    static final int ORIGIN_Y = 800;
    static final int ORIGIN_X = 400;
    static final int DELAY = 10;
    static final int ALARM_AREA = 10;

    boolean flag = false;

    int count = 0;

    double[] absDistCamera = new double[15];
    double[][] absDistCorner = new double[10][4];
    boolean[][] savedObject = new boolean[10][4];

    String typeOfObject;

    Timer timer;
    Reader reader;
    Thread t1;

    MainPanel() throws Exception {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        startThisNow();
    }

    public void startThisNow() throws Exception {
        timer = new Timer(DELAY, this);
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

        //text output
        graphics.setColor(Color.CYAN);
        graphics.drawString("Reading from dataset and redrawing rate: " + DELAY + " ms",1200,20);
        graphics.drawString("FoVs of sensors are arbitrary",1200,40);

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
            absDistCamera[i] = Math.sqrt((double) reader.cameraObjectsDx[i]*reader.cameraObjectsDx[i]
                    +(double)reader.cameraObjectsDy[i]*reader.cameraObjectsDy[i]);
            if(reader.cameraObjectTypes[i] == 1){
                typeOfObject = "Truck";
            } else if(reader.cameraObjectTypes[i] == 2){
                typeOfObject = "Car";
            } else if(reader.cameraObjectTypes[i] == 3){
                typeOfObject = "Motorbike";
            } else if(reader.cameraObjectTypes[i] == 4){
                typeOfObject = "Bicycle";
            } else if(reader.cameraObjectTypes[i] == 5){
                typeOfObject = "Pedestrian";
            } else if(reader.cameraObjectTypes[i] == 6){
                typeOfObject = "Car or Truck";
            } else {
                typeOfObject = "Not detected";
            }

                graphics.setColor(Color.MAGENTA);
                graphics.fillRect(ORIGIN_Y - reader.cameraObjectsDy[i]*STEP - 6, ORIGIN_X - reader.cameraObjectsDx[i]*STEP - 6 - 30, 12, 12);
                graphics.setColor(Color.GREEN);
                graphics.setFont(new Font("Helvetica Neue", Font.BOLD, 12));
                graphics.drawString(typeOfObject,ORIGIN_Y - reader.cameraObjectsDy[i]*STEP-4,ORIGIN_X - reader.cameraObjectsDx[i]*STEP-25);
                graphics.drawString(String.format("Camera abs distance: %.2f",absDistCamera[i]),10,20 + i*10);

        }
        //corner
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                graphics.setColor(Color.YELLOW);
                graphics.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
                graphics.drawString("Count of alert distance crossings: " + count, 1200,220);
                if(count%2 == 1){
                    graphics.drawString("Something crossed in alarm area!", 1200,200);
                } else {
                    graphics.drawString("Something crossed out alarm area!", 1200,200);
                }
                if(flag){
                    graphics.setColor(Color.YELLOW);
                    graphics.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
                    graphics.drawString("Blind zone!",1200,200);
                }
                absDistCorner[i][j] = Math.sqrt(reader.cornerDx[i][j]*reader.cornerDx[i][j]
                        +reader.cornerDy[i][j]*reader.cornerDy[i][j]);
                graphics.setColor(Color.RED);
                graphics.setFont(new Font("Helvetica Neue", Font.BOLD, 12));
                graphics.drawString(String.format("Corner abs distance: %.2f",absDistCorner[i][j]),200,20+i*60+j*10);

                //within 10 meter and sensed?
                if(absDistCorner[i][j] > 1 && absDistCorner[i][j] <= 10){
                    if(savedObject[i][j] == false && reader.cornerProb1Obstacle[i][j] > 100){
                        savedObject[i][j] = true;
                        count++;
                    }

                }
                if(absDistCorner[i][j] < 1 || absDistCorner[i][j] > 10){
                    savedObject[i][j] = false;
                }
                graphics.drawString(String.valueOf(savedObject[i][j]), 400, 20+i*60+j*10);

                if(reader.cornerDx[i][j] != 0 && reader.cornerDy[i][j] != 0){
                    graphics.setColor(Color.GREEN);
                    graphics.fillRect(ORIGIN_Y - reader.cornerDy[i][j]*STEP - 4,ORIGIN_X - reader.cornerDx[i][j]*STEP,8,8);
                    graphics.setColor(Color.RED);
                    graphics.drawString("object " + i, ORIGIN_Y - reader.cornerDy[i][j]*STEP - 4 - 4, ORIGIN_X - reader.cornerDx[i][j]*STEP-10);
                    graphics.drawString("sensor " + j, ORIGIN_Y - reader.cornerDy[i][j]*STEP - 4 - 4, ORIGIN_X - reader.cornerDx[i][j]*STEP);
                    if(absDistCorner[i][j] < ALARM_AREA){
                        graphics.setFont(new Font("Helvetica Neue", Font.BOLD, 40));
                        graphics.drawString("Sensed within distance: " + ALARM_AREA + "m, don't turn the vehicle!",600,700);
                    }
                }
            }
        }
    }
}
