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
        painter(minDisk(points));
    }
    public static void painter(final Circle circle){
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
                g.setColor(new Color(255, 59, 43));

                g.drawOval((int)(800+circle.getO().getX()-circle.getR()), (int) (500-circle.getO().getY()-circle.getR()), (int)(circle.getR()*2), (int)(circle.getR()*2-1));

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
                iterator++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public static Circle minDisk(ArrayList<Point> points){
        ArrayList<Circle> circles = new ArrayList<Circle>();
        Point point1 = points.get(0);
        Point point2 = points.get(1);
          circles.add(new Circle(new Point(0,0), new Point(0,0)));
          circles.add(new Circle(point1, point2));
        for (int i = 2; i < points.size(); i++){
//            Circle circle = new Circle(points.get(i-1), points.get(i));
            if (belonging(points.get(i), circles.get(i-1))){
                circles.add(circles.get(i-1));
            } else{
                ArrayList<Point> pointArrayList = new ArrayList<Point>();
                for (int k = 0; k<i-1; k++){
                   pointArrayList.add(points.get(k));
                }
                circles.add(minDiskWithPoint(pointArrayList, points.get(i)));
            }
        }
        return circles.get(circles.size()-1);
    }

    public static boolean belonging(Point point, Circle circle){
        if(Math.sqrt(Math.pow(circle.getO().getX()-point.getX(), 2)+Math.pow(circle.getO().getY()-point.getY(),2)) <= circle.getR()){
            return true;
        }
       return false;
    }

    public static Circle  minDiskWithPoint (ArrayList<Point> points, Point q){
        ArrayList<Circle> circles = new ArrayList<Circle>();
        circles.add(new Circle(points.get(0), q));
        for (int i = 1; i < points.size(); i++){
            if (belonging(points.get(i), circles.get(i-1))){
                circles.add(circles.get(i-1));
            } else {
                ArrayList<Point> pointArrayList = new ArrayList<Point>();
                for (int k = 0; k<i-1; k++){
                    pointArrayList.add(points.get(k));
                }
                circles.add(minDiskWith2Points(pointArrayList, points.get(i), q));
            }
        }
        return circles.get(circles.size()-1);
    }
    public static Circle  minDiskWith2Points(ArrayList<Point> points, Point q1, Point q2){
        ArrayList<Circle> circles = new ArrayList<Circle>();
        circles.add(new Circle(q1,q2));
        for (int i=1; i< points.size(); i++){
            if (belonging(points.get(i-1),circles.get(i-1))){
                circles.add(circles.get(i-1));
            } else {
                circles.add(circumCircle(points.get(i-1), q1, q2));
            }
        }
        return circles.get(circles.size()-1);
    }

    public static Circle circumCircle(Point point, Point q1, Point q2){
        return new Circle(point,q1,q2);
    }

}


