package Main;

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
    final int speed = 100;
    int length;
    int nvx, nvy, vx, vy;
    boolean wrapAround, invincible;


    public Snake(JFrame jFrame) {
        jFrame.addKeyListener(listener);

        points = new ArrayList<Point>(Arrays.asList(
                new Point(HEADSTARTINGPOSX + 1, HEADSTARTINGPOSY),
                new Point(HEADSTARTINGPOSX + 2, HEADSTARTINGPOSY),
                new Point(HEADSTARTINGPOSX + 3, HEADSTARTINGPOSY),
                new Point(HEADSTARTINGPOSX + 4, HEADSTARTINGPOSY)
        ));

        length = points.size() - 1;
        //length = 225;

        nextPoint = new Point(points.get(0).x, points.get(0).y);

        wrapAround = true;
        invincible = false;

        vx = -1;
        nvx = -1;
        vy = 0;
        nvy = 0;
    }

    @Override
    public void paintComponents(Graphics g) {
        Graphics2D draw = (Graphics2D)g;

        for (int i = 0 ; i < points.size() ; i++) {
            draw.setColor(new Color(0, 0, 255));
            if (i != 0)
                draw.setColor(new Color(100, 100, 255));
            draw.fillRect(convert(points.get(i).x, "x"), convert(points.get(i).y, "y"), Game.X_SCALE, Game.Y_SCALE);
        }
    }

    @Override
    public void run() {
        while (Game.running) {
            try {
                Thread.sleep(speed - length / 2);
            } catch (Exception e) {}

            shift();

            //set nextPoint
            vx = nvx;
            vy = nvy;

            if (wrapAround) {
                nextPoint.x = (nextPoint.x + vx) % Game.GAMEX;
                nextPoint.y = (nextPoint.y + vy) % Game.GAMEY;

                if (nextPoint.x < 0)
                    nextPoint.x = Game.GAMEX + nextPoint.x;
                if (nextPoint.y < 0)
                    nextPoint.y = Game.GAMEY + nextPoint.y;
            }
            else {
                nextPoint.x += vx;
                nextPoint.y += vy;
            }

            //length regulation
            if (points.size() < length) {
                points.add(points.get(points.size() - 1));
            }

            if (!invincible) {
                //crash detection
                for (int i = 1 ; i < points.size() - 1 ; i++) {
                    if (points.get(i).equals(points.get(0))) {
                        Game.running = false;
                        System.out.println(points.get(i));
                    }
                }
            }

            if ((points.get(0).x < 0 || points.get(0).x > Game.GAMEX || points.get(0).y < 0 || points.get(0).y > Game.GAMEY) && !wrapAround)
                Game.running = false;
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