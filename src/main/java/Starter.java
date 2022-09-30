import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Starter {

    public static void main(String[] args) throws IOException {
//        new MainFrame();
        File file = new File("src/main/resources/camera_and_corners.csv");
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter(",");

        //comment here
        scanner.nextLine();

        //input
        double t = Double.parseDouble(scanner.next());

        int cameraTimeStamp = Integer.parseInt(scanner.next());

        int[] cameraObjectsDx = new int[15];
        for (int i = 0; i < cameraObjectsDx.length; i++){
            cameraObjectsDx[i] = Integer.parseInt(scanner.next());
        }

        int[] cameraObjectsDy = new int[15];
        for (int i = 0; i < cameraObjectsDy.length; i++){
            cameraObjectsDy[i] = Integer.parseInt(scanner.next());
        }

        int[] cameraObjectTypes = new int[15];
        for (int i = 0; i < cameraObjectTypes.length; i++) {
            cameraObjectTypes[i] = Integer.parseInt(scanner.next());
        }

        int[] cameraObjectsVx = new int[15];
        for (int i = 0; i < cameraObjectsVx.length; i++){
            cameraObjectsVx[i] = Integer.parseInt(scanner.next());
        }

        int[] cameraObjectsVy = new int[15];
        for(int i = 0; i < cameraObjectsVy.length; i++){
            cameraObjectsVy[i] = Integer.parseInt(scanner.next());
        }

        int[] cornerTimestamps = new int[4];
        for (int i = 0; i < cornerTimestamps.length; i++){
            cornerTimestamps[i] = Integer.parseInt(scanner.next());
        }

        //2D array to store corner sensor ax
        int[][] cornerAx = new int[10][4];
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 4; j++){
                cornerAx[i][j] = Integer.parseInt(scanner.next());
            }
        }

        //2D array to store corner sensor ay
        int[][] cornerAy = new int[10][4];
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 4; j++){
                cornerAy[i][j] = Integer.parseInt(scanner.next());
            }
        }

        //2D array to store corner sensor dx
        int[][] cornerDx = new int[10][4];
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 4; j++){
                cornerDx[i][j] = Integer.parseInt(scanner.next());
            }
        }



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

        //corner Ay
        System.out.println("corner Dx where line is object and column is sensorID");
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 4; j++){
                System.out.print(cornerDx[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();





//        String[] lines = scanner.nextLine().split(",");
//        for (String line : lines){
//
//            System.out.println(line);
//        }



    }

}
