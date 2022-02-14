import javax.swing.*;
import java.awt.*;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Random;

public class Food extends JComponent implements Runnable {
    ArrayList <Point> foods = new ArrayList();
    Random gen = new Random();
    Snake snake;
    final int NUMOFFOOD = 1;

    public Food(Snake snake) {
        this.snake = snake;
        for (int i = 0 ; i < NUMOFFOOD ; i++) {
            foods.add(this.newFood());
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D draw = (Graphics2D)g;

        draw.setColor(new Color(255, 0, 0));
        for (int i = 0 ; i < foods.size() ; i++) {
            draw.fillRect(this.convert(foods.get(i).x, "x"), this.convert(foods.get(i).y, "y"), Game.xScale, Game.yScale);
        }
    }

    @Override
    public void run() {
        while (Game.running) {
            for (int i = 0 ; i < foods.size() ; i++) {
                System.out.println("a");
                if (foods.get(i).equals(snake.points.get(0))) {
                    System.out.println("eaten");
                    snake.length++;
                    foods.remove(i);
                    foods.add(newFood());
                }
            }
        }
    }

    private int convert (int i, String x) {return Game.convert(i, x);}

    private Point newFood() {
        Point point = new Point(gen.nextInt(Game.GAMEX), gen.nextInt(Game.GAMEY));
        return point;
    }

    public void nextFrame() {repaint();}
}