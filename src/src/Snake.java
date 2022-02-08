import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Snake extends JComponent implements Runnable {
    ArrayList <Integer> x, y;

    public Snake() {
        x = ;
        y = ;
    }

    @Override
    public void paintComponents(Graphics g) {
        Graphics2D draw = (Graphics2D)g;
    }

    @Override
    public void run() {

    }

    public void nextFrame() {repaint();}
}