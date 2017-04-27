/**
 * Created by denys on 24.04.17.
 */
public class Circle {
    private Point O;
    private int R;

    public Circle(Point point1, Point point2) {
        this.O = new Point(Math.round(point1.getX()-point2.getX())/2, Math.round(point1.getY()-point2.getY())/2);
        this.R = (int) Math.round(Math.sqrt(Math.pow(point1.getX()-point2.getX(), 2)+Math.pow(point1.getY()-point2.getY(), 2)));
    }


    public Point getO() {
        return O;
    }

    public void setO(Point o) {
        O = o;
    }

    public double getR() {
        return R;
    }

    public void setR(int r) {
        R = r;
    }
}
