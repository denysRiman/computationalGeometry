/**
 * Created by denys on 24.04.17.
 */
public class Circle {
    private Point O;
    private int R;

    public Circle(Point point1, Point point2) {
        this.O = new Point(Math.round(point1.getX()-point2.getX())/2, Math.round(point1.getY()-point2.getY())/2);
        this.R = (int) Math.round(Math.sqrt(Math.pow(point1.getX()-point2.getX(), 2)+Math.pow(point1.getY()-point2.getY(), 2))/2);
    }

    public Circle(Point point1, Point point2, Point point3) {
        this.O = new Point(
                (int) Math.round(( point1.getX()*countLength(point2,point3)+point2.getX()*countLength(point3,point1)+point3.getX()*countLength(point1,point2))/
                        (countLength(point1,point2)+countLength(point2,point3)+countLength(point3,point1)))
                ,
                (int) Math.round(( point1.getY()*countLength(point2,point3)+point2.getY()*countLength(point3,point1)+point3.getY()*countLength(point1,point2))/
                        (countLength(point1,point2)+countLength(point2,point3)+countLength(point3,point1)))
        );
        this.R = (int) Math.round((countLength(point1,point2)*countLength(point2,point3)*countLength(point3,point1))/
                (4*Math.sqrt(countHalbPerimeter(point1,point2,point3)*(countHalbPerimeter(point1,point2,point3)-countLength(point1,point3)*
                        (countHalbPerimeter(point1,point2,point3)-countLength(point1,point2))*(countHalbPerimeter(point1,point2,point3)-countLength(point2,point3))))));
    }

    public double countLength(Point p1, Point p2){
        return Math.sqrt(Math.pow(p1.getX()-p2.getX(), 2)+Math.pow(p1.getY()-p2.getY(), 2));
    }

    public double countHalbPerimeter(Point p1, Point p2, Point p3){
        return (countLength(p1,p2)+countLength(p1,p3)+countLength(p2,p3))/2;
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
