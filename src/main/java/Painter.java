import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Painter {

    private static final String FILENAME = "/home/denys/IdeaProjects/Denys/IdeaProjects/LR/files/dimensions.json";

    static ArrayList<Point> points = new ArrayList<Point>();

    public static void main(String[] args) {
        readJSON();
        painter();
    }
    public static void painter(){
        JFrame frame = new JFrame("Лабораторна: AO2. Нагорного Дениса");
        frame.setBounds(0, 0, 1600, 1000);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel contentPane = new JPanel() {
            Graphics2D g2;

            protected void paintComponent(Graphics g) {
                g.drawLine(800,1000,800,0);
                g.drawLine(0,500,1600,500);
                g.drawString("y", 810, 20);
                g.drawString("x", 1580, 520);
                g.setColor(new Color(34, 22, 255));
                for(Point point: points){
                    g.drawLine(point.getX()+800,500-point.getY(),point.getX()+801,500-point.getY());
                    g.drawLine(point.getX()+800,501-point.getY(),point.getX()+801,501-point.getY());
                }


//                int R = (int) resR;
//
//                g.setColor(new Color(255, 59, 43));
//                g.drawOval(800+resX-R, 500-resY-R, R*2-1, R*2-1);

            }
        };
        frame.setContentPane(contentPane);
    }

    public static void readJSON(){
        int iterator = 0;
        JSONParser parser = new JSONParser();
        try {
            JSONObject object = (JSONObject) parser.parse(new FileReader(FILENAME));
            JSONArray xs = (JSONArray) object.get("x");
            for (Object o: xs){
                points.add(new Point(Integer.parseInt(o.toString())));
            }
            JSONArray ys = (JSONArray) object.get("y");
            for (Object i: ys){
                points.get(iterator).setY(Integer.parseInt(i.toString()));
                System.out.println(ys.indexOf(i));
                iterator++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public static void minDisk (ArrayList<Point> points){
        Point point1 = points.get(0);
        Point point2 = points.get(1);
        Circle circle = new Circle(point1, point2 );
        for (int i = 2; i < points.size(); i++){
            if (belonging(points.get(i), circle)){

            }
        }

    }

    public static boolean belonging(Point point, Circle circle){

        return true;
    }
//    public static void minDiskWithPoint (){
//
//    }
//    public static void minDiskWith2Points (){
//
//    }

}


