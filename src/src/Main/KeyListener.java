package Main;

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

        if (e.getKeyCode() == KeyEvent.VK_UP && snake.vy != 1) {
            snake.nvx = 0;
            snake.nvy = -1;
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT && snake.vx != 1) {
            snake.nvx = -1;
            snake.nvy = 0;
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN && snake.vy != -1) {
            snake.nvx = 0;
            snake.nvy = 1;
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT && snake.vx != -1) {
            snake.nvx = 1;
            snake.nvy = 0;
        }

        //System.out.println(snake.vx + " " + snake.vy);
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
