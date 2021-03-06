package Main;

import javax.swing.*;

public class main {
    public static  void main(String[] args) {
        JFrame window = new JFrame();
        Game game = new Game(window);

        final int FPS = 75;
        int currentFrame = 0;

        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setUndecorated(true);
        window.add(game);
        window.pack();
        window.setTitle("Main.Snake");
        window.setVisible(true);

        while (true) {
            currentFrame++;
            game.nextFrame();
            try {
                Thread.sleep(1000 / FPS);
            } catch (Exception e) {}
        }

        //Main.Game.stopThread();
    }
}
