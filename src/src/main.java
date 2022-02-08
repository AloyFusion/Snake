import javax.swing.*;
import java.awt.*;

public class main {
    public static  void main(String[] args) {
        JFrame window = new JFrame();
        Game Game = new Game();

        final int FPS = 60;
        int currentFrame = 0;

        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setUndecorated(true);
        window.setResizable(false);
        window.setTitle("Snake");

        window.add(Game);

        window.pack();

        window.setVisible(true);

        int i = 1;
        while (i == 1) {
            currentFrame++;
            Game.nextFrame();
            try {
                Thread.sleep(1000/FPS);
            } catch (Exception e) {}
        }

        Game.stopThread();
    }
}
