import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {
    Snake snake;

    public KeyListener(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyChar() == 'w') {
            snake.vx = 0;
            snake.vy = -1;
        }

        if (e.getKeyChar() == 'a') {
            snake.vx = -1;
            snake.vy = 0;
        }

        if (e.getKeyChar() == 's') {
            snake.vx = 0;
            snake.vy = 1;
        }

        if (e.getKeyChar() == 'd') {
            snake.vx = 1;
            snake.vy = 0;
        }

        //System.out.println(snake.vx + " " + snake.vy);
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
