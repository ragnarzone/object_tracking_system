import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {

    double t;
    int cameraTimeStamp;
    int[] cameraObjectsDx = new int[15];
    int[] cameraObjectsDy = new int[15];
    int[] cameraObjectTypes = new int[15];
    int[] cameraObjectsVx = new int[15];
    int[] cameraObjectsVy = new int[15];
    int[] cornerTimestamps = new int[4];
    //2D array to store corner sensor ax
    int[][] cornerAx = new int[10][4];
    //2D array to store corner sensor ay
    int[][] cornerAy = new int[10][4];
    //2D array to store corner sensor dx
    int[][] cornerDx = new int[10][4];
    //2D array to store corner sensor dy
    int[][] cornerDy = new int[10][4];
    //2D array to store corner sensor dz
    int[][] cornerDz = new int[10][4];
    //2D array to store corner sensor prob1Obstacle
    int[][] cornerProb1Obstacle = new int[10][4];
    //2D array to store corner sensor vx
    int[][] cornerVx = new int[10][4];
    //2D array to store corner sensor vy
    int[][] cornerVy = new int[10][4];

    double cameraPositionX;
    double cameraPositionY;
    double cameraPositionZ;

    public void startReading() throws Exception {
        File file = new File("src/main/resources/camera_and_corners.csv");
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter("[,\\n]");

        scanner.nextLine();

        while (scanner.hasNext()){
            //input
            t = Double.parseDouble(scanner.next());

            // Cameras
            cameraTimeStamp = Integer.parseInt(scanner.next());

            for (int i = 0; i < cameraObjectsDx.length; i++){
                cameraObjectsDx[i] = Integer.parseInt(scanner.next())/128;
            }

            for (int i = 0; i < cameraObjectsDy.length; i++){
                cameraObjectsDy[i] = Integer.parseInt(scanner.next())/128;
            }

            for (int i = 0; i < cameraObjectTypes.length; i++) {
                cameraObjectTypes[i] = Integer.parseInt(scanner.next());
            }

            for (int i = 0; i < cameraObjectsVx.length; i++){
                cameraObjectsVx[i] = Integer.parseInt(scanner.next());
            }

            for(int i = 0; i < cameraObjectsVy.length; i++){
                cameraObjectsVy[i] = Integer.parseInt(scanner.next());
            }

            // Corners
            for (int i = 0; i < cornerTimestamps.length; i++){
                cornerTimestamps[i] = Integer.parseInt(scanner.next());
            }

            //2D array to store corner sensor ax
            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 4; j++){
                    cornerAx[i][j] = Integer.parseInt(scanner.next());
                }
            }

            //2D array to store corner sensor ay
            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 4; j++){
                    cornerAy[i][j] = Integer.parseInt(scanner.next());
                }
            }

            //2D array to store corner sensor dx
            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 4; j++){
                    cornerDx[i][j] = Integer.parseInt(scanner.next())/128;
                }
            }

            //2D array to store corner sensor dy
            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 4; j++){
                    cornerDy[i][j] = Integer.parseInt(scanner.next())/128;
                }
            }

            //2D array to store corner sensor dz
            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 4; j++){
                    cornerDz[i][j] = Integer.parseInt(scanner.next())/128;
                }
            }

            //2D array to store corner sensor prob1Obstacle
            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 4; j++){
                    cornerProb1Obstacle[i][j] = Integer.parseInt(scanner.next());
                }
            }

            //2D array to store corner sensor vx
            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 4; j++){
                    cornerVx[i][j] = Integer.parseInt(scanner.next());
                }
            }

            //2D array to store corner sensor vy
            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 4; j++){
                    cornerVy[i][j] = Integer.parseInt(scanner.next());
                }
            }

            cameraPositionX = Double.parseDouble(scanner.next());
            cameraPositionY = Double.parseDouble(scanner.next());
            cameraPositionZ = Double.parseDouble(scanner.next());




            //output
            //time
            System.out.println("time");
            System.out.println(t);
            System.out.println();

            //camera timeStamp
            System.out.println("camera time stamp");
            System.out.println(cameraTimeStamp);
            System.out.println();

            //camera objects dx
            System.out.println("camera objects dx");
            for(Integer value : cameraObjectsDx){
                System.out.println(value);
            }
            System.out.println();

            //camera objects dy
            System.out.println("camera objects dy");
            for(Integer value : cameraObjectsDy){
                System.out.println(value);
            }
            System.out.println();

            //camera obj types
            System.out.println("camera objects types");
            for(Integer value : cameraObjectTypes){
                System.out.println(value);
            }
            System.out.println();

            //camera objects Vx
            System.out.println("camera objects vx");
            for(Integer value: cameraObjectsVx){
                System.out.println(value);
            }
            System.out.println();

            // camera objects Vy
            System.out.println("camera objects vy");
            for(Integer value : cameraObjectsVy){
                System.out.println(value);
            }
            System.out.println();

            //corner timestamps
            System.out.println("corner time stamps");
            for(Integer value : cornerTimestamps){
                System.out.println(value);
            }
            System.out.println();

            //corner Ax
            System.out.println("corner Ax where line is object and column is sensorID");
            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 4; j++){
                    System.out.print(cornerAx[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();

            //corner Ay
            System.out.println("corner Ay where line is object and column is sensorID");
            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 4; j++){
                    System.out.print(cornerAy[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();

            //corner Dx
            System.out.println("corner Dx where line is object and column is sensorID");
            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 4; j++){
                    System.out.print(cornerDx[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();

            //corner Dy
            System.out.println("corner Dy where line is object and column is sensorID");
            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 4; j++){
                    System.out.print(cornerDy[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();

            //corner Dz
            System.out.println("corner Dz where line is object and column is sensorID");
            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 4; j++){
                    System.out.print(cornerDz[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();

            //corner prob1Obstacle
            System.out.println("corner prob1Obstacle where line is object and column is sensorID");
            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 4; j++){
                    System.out.print(cornerProb1Obstacle[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();

            //corner Vx
            System.out.println("corner Vx where line is object and column is sensorID");
            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 4; j++){
                    System.out.print(cornerVx[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();

            //corner Vy
            System.out.println("corner Vy where line is object and column is sensorID");
            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 4; j++){
                    System.out.print(cornerVy[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();

            //camera position

            System.out.println("Camera position X: " + cameraPositionX);
            System.out.println("Camera position Y: " + cameraPositionY);
            System.out.println("Camera position Z: " + cameraPositionZ);

            Thread.sleep(MainPanel.DELAY);
        }
    }

}
