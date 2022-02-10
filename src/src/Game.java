import javax.swing.*;
import java.awt.*;

public class Game extends JComponent implements Runnable {
    //final static int SCREENWIDTH = 1920;
    //final static int SCREENHEIGHT = 1080;
    final static int GAMEX = 15;
    final static int GAMEY = 15;
    final static int xScale = 50;
    final static int yScale = 50;

    Snake snake;
    Food food;

    Thread TSnake, TFood;

    public Game(JFrame jFrame) {
        this.setPreferredSize(new Dimension(GAMEX * xScale, GAMEY * yScale));
        this.setDoubleBuffered(true);

        //assign object
        snake = new Snake(jFrame);
        food = new Food();

        //assign thread
        TSnake = new Thread(snake);
        TFood = new Thread(food);

        //start thread
        TSnake.start();
        TFood.start();
    }

    @Override
    public void run() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D draw = (Graphics2D)g;

        //generates grid
        for (int i = 0 ; i < GAMEX ; i++) {
            for (int j = 0 ; j < GAMEY ; j++) {
                if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) {
                    draw.setColor(new Color(162, 209, 73));
                }

                else {
                    draw.setColor(new Color(170, 215, 81));
                }

                draw.fillRect(Game.convert(i, "x"), Game.convert(j, "y"), xScale, yScale);
            }
        }

        food.paintComponent(g);
        snake.paintComponents(g);
    }

    //converts cords to pixel pos
    public static int convert(int x, String y) {
        if (y.equals("x")) {
            return (x * xScale);
        }

        return (x * yScale);
    }

    public void nextFrame() {repaint();}

    public void stopThread() {
        TSnake.stop();
        TFood.stop();
    }
}
