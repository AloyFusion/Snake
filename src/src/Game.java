import javax.swing.*;
import java.awt.*;

public class Game extends JComponent implements Runnable {
    final static int SCREENWIDTH = 1920;
    final static int SCREENHEIGHT = 1080;
    final static int GAMEX = 10;
    final static int GAMEY = 10;
    int xSale = 100;
    int yScale = 100;

    Snake snake;
    Food food;

    Thread TSnake, TFood;

    public Game() {
        this.setPreferredSize(new Dimension(SCREENWIDTH, SCREENHEIGHT));
        this.setDoubleBuffered(true);

        //assign object
        snake = new Snake();
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

        for (int i = 0 ; i < GAMEX ; i++) {
            for (int j = 0 ; j < GAMEY ; i++) {

            }
        }
    }

    public void nextFrame() {repaint();}

    private int convert(int x, String y) {
        if (y.equals("x")) {
            return (x/xSale);
        }

        return (x/yScale);
    }

    public void stopThread() {
        TSnake.stop();
        TFood.stop();
    }
}
