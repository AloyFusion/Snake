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
    final int speed = 500;
    int length;
    int vx = -1;
    int vy = 0;

    public Snake(JFrame jFrame) {
        jFrame.addKeyListener(listener);

        points = new ArrayList<Point>(Arrays.asList(
                new Point(HEADSTARTINGPOSX + 1, HEADSTARTINGPOSY),
                new Point(HEADSTARTINGPOSX + 2, HEADSTARTINGPOSY),
                new Point(HEADSTARTINGPOSX + 3, HEADSTARTINGPOSY),
                new Point(HEADSTARTINGPOSX + 4, HEADSTARTINGPOSY)
        ));

        length = points.size() - 1;

        nextPoint = new Point(points.get(0).x, points.get(0).y);
    }

    @Override
    public void paintComponents(Graphics g) {
        Graphics2D draw = (Graphics2D)g;

        for (int i = 0 ; i < points.size() ; i++) {
            draw.setColor(new Color(0, 0, 255));
            if (i != 0)
                draw.setColor(new Color(100, 100, 255));
            draw.fillRect(convert(points.get(i).x, "x"), convert(points.get(i).y, "y"), Game.xScale, Game.yScale);
        }
    }

    @Override
    public void run() {
        while (Game.running) {
            try {
                Thread.sleep(speed);
            } catch (Exception e) {}

            shift();

            //set nextPoint
            nextPoint.x = (nextPoint.x + vx) % Game.GAMEX;
            nextPoint.y = (nextPoint.y + vy) % Game.GAMEY;

            if (nextPoint.x < 0)
                nextPoint.x = Game.GAMEX + nextPoint.x;
            if (nextPoint.y < 0)
                nextPoint.y = Game.GAMEY + nextPoint.y;

            //length regulation
            if (points.size() < length) {
                points.add(points.get(points.size() - 1));
            }

            //crash detection
            for (int i = 1 ; i < points.size() - 1 ; i++) {
                if (points.get(i).equals(points.get(0)))
                    Game.running = false;
            }
        }
    }

    private int convert (int i, String x) {return Game.convert(i, x);}

    private void shift() {
        //System.out.println(points);
        for (int i = points.size() - 1 ; i >= 0 ; i--) {
            try {
                points.set(i, new Point(points.get(i - 1)));
                //System.out.println("set " + i + " to " + points.get(i - 1));
            } catch (IndexOutOfBoundsException e) {
                points.set(i, nextPoint);
                //System.out.println("set " + i + " to nextPoint: " + nextPoint);
            }
        }
    }

    public void nextFrame() {repaint();
    }
}