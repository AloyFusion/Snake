import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Snake extends JComponent implements Runnable {
    ArrayList <Point> points;
    KeyListener listener = new KeyListener(this);
    Point nextPoint;
    final int HEADSTARTINGPOSX = Game.GAMEX / 2;
    final int HEADSTARTINGPOSY = Game.GAMEY / 2;
    final int speed = 1500;
    int length;
    int vx = -1;
    int vy = 0;

    public Snake(JFrame jFrame) {
        jFrame.addKeyListener(listener);

        points = new ArrayList<Point>(Arrays.asList(
                new Point(HEADSTARTINGPOSX, HEADSTARTINGPOSY),
                new Point(HEADSTARTINGPOSX + 1, HEADSTARTINGPOSY),
                new Point(HEADSTARTINGPOSX + 2, HEADSTARTINGPOSY),
                new Point(HEADSTARTINGPOSX + 3, HEADSTARTINGPOSY)
        ));

        length = points.size() - 1;

        nextPoint = new Point(points.get(0).x, points.get(0).y);
    }

    @Override
    public void paintComponents(Graphics g) {
        Graphics2D draw = (Graphics2D)g;

        for (int i = 0 ; i < points.size() ; i++) {
            draw.setColor(Color.red);
            //if (i != 0)
                //draw.setColor(Color.blue);
            draw.fillRect(convert(points.get(i).x, "x"), convert(points.get(i).y, "y"), Game.xScale, Game.yScale);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(speed);
            } catch (Exception e) {}


            shift();
            nextPoint.x += vx;
            nextPoint.y += vy;


        }
    }

    public int convert (int i, String x) {return Game.convert(i, x);}

    private void shift() {
        System.out.println(points);
        for (int i = points.size() - 1 ; i >= 0 ; i--) {
            try {
                points.set(i, points.get(i - 1));
            } catch (IndexOutOfBoundsException e) {
                points.set(i, nextPoint);
                System.out.println("exception");
            }
        }

        if (length < points.size() - 1) {
            //points.remove(points.size() - 1);
            Game.convert()

        }
        else if (length > points.size() - 1) {
            //points.add(new Point(-1, -1));
        }
    }

    public void nextFrame() {repaint();}
}