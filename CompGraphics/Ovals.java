import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
//import java.awt.image.BufferedImage;
//import javax.imageio.ImageIO;
import javax.swing.*;

import java.io.*;

public class Ovals extends JPanel {
    public static void main(String[] args){
        JFrame window = new JFrame("Oval Design");
        Ovals content = new Ovals();
        window.setContentPane(content);
        window.pack();
        window.setSize(800, 800);
        window.setResizable(true);
        window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        window.setVisible(true);
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2;
        g2 = (Graphics2D)g.create();

        g2.draw(new Ellipse2D.Double(400, 400, 200, 200));
    }
}