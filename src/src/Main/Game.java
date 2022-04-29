package Main;

import javax.swing.*;
import java.awt.*;

public class Game extends JComponent {
    //final static int SCREENWIDTH = 1920;
    //final static int SCREENHEIGHT = 1080;
    final static int X_SCALE = 1;
    final static int Y_SCALE = 100;
    final static int GAMEX = 1920 / X_SCALE;
    final static int GAMEY = 1080 / Y_SCALE;

    static boolean running = true;

    Snake snake;
    Food food;

    Thread TSnake, TFood;

    public Game(JFrame jFrame) {
        this.setPreferredSize(new Dimension(GAMEX * X_SCALE, GAMEY * Y_SCALE));
        this.setDoubleBuffered(true);

        //assign object
        snake = new Snake(jFrame);
        food = new Food(snake);

        //assign thread
        TSnake = new Thread(snake);
        TFood = new Thread(food);

        //start thread
        TSnake.start();
        TFood.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D draw = (Graphics2D)g;
        if (running) {
            //generates grid
            for (int i = 0; i < GAMEX; i++) {
                for (int j = 0; j < GAMEY; j++) {
                    if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) {
                        draw.setColor(new Color(162, 209, 73));
                    } else {
                        draw.setColor(new Color(170, 215, 81));
                    }

                    draw.fillRect(Game.convert(i, "x"), Game.convert(j, "y"), X_SCALE, Y_SCALE);
                }
            }

            food.paintComponent(g);
            snake.paintComponents(g);
        }
    }

    //converts cords to pixel pos
    public static int convert(int x, String y) {
        if (y.equals("x")) {
            return (x * X_SCALE);
        }

        return (x * Y_SCALE);
    }

    public void nextFrame() {repaint();}

    public void stopThread() {
        TSnake.stop();
        TFood.stop();
    }
}
