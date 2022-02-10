import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Snake extends JComponent implements Runnable {
    ArrayList <Point> points;
    KeyListener listener = new KeyListener(this);
    Point nextPoint;
    int vx = -1;
    int vy = 0;
    int speed = 750;
    int i = Game.GAMEX / 2;
    int j = Game.GAMEY / 2;

    public Snake(JFrame jFrame) {
        jFrame.addKeyListener(listener);

        points = new ArrayList<Point>(Arrays.asList(
                new Point(i, j),
                new Point(i + 1, j),
                new Point(i + 2, j),
                new Point(i + 3, j)
        ));

        nextPoint = new Point(points.get(0).x, points.get(0).y - 1);
    }

    @Override
    public void paintComponents(Graphics g) {
        Graphics2D draw = (Graphics2D)g;

        for (int i = 0 ; i < points.size() ; i++) {
            draw.setColor(Color.red);
            if (i != 0)
                draw.setColor(Color.blue);
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
            points.set(0, nextPoint);


        }
    }

    public int convert (int i, String x) {return Game.convert(i, x);}

    private void shift() {
        for (int i = points.size() - 1 ; i >= 0 ; i--) {
            try {
                points.set(i + 1, points.get(i));
                System.out.println("shifted");
            } catch (IndexOutOfBoundsException e) {
                points.add(points.get(i));
                System.out.println("added");
            }
        }
    }

    public void nextFrame() {repaint();}
}