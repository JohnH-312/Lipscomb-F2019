import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
//import java.awt.image.BufferedImage;
//import javax.imageio.ImageIO;
import javax.swing.*;

import java.io.*;

public class House extends JPanel {
    public static void main(String[] args){
        JFrame window = new JFrame("My House");
        House content = new House();
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
        Rectangle2D.Double house = new Rectangle2D.Double(200, 350, 400, 450);
        Rectangle2D.Double grass = new Rectangle2D.Double(0, 600, 800, 200);
        Rectangle2D.Double sky = new Rectangle2D.Double(0, 0, 800, 600);
        Polygon roof = new Polygon(
                    new int[]{200, 600, 400},
                    new int[]{350, 350, 175},
                    3);
        Rectangle2D.Double window1 = house;
        Rectangle2D.Double window2 = house;
        g2.setColor(new Color(51, 204, 204));
        g2.draw(sky);
        g2.fill(sky);
        g2.setColor(Color.GREEN);
        g2.draw(grass);
        g2.fill(grass);
        g2.setColor(new Color(255, 191, 128));
        g2.draw(house);
        g2.fill(house);
        g2.setColor(new Color(153, 51, 0));
        g2.draw(roof);
        g2.fill(roof);
        g2.translate(200, 350);
        g2.scale(.25, .25);
        g2.setColor(Color.BLUE);
        
        g2.draw(window1);
        g2.fill(window1);
        g2.translate(800, 0);
        g2.draw(window2);

        g2.fill(window2);
        g2.dispose();

        g2 = (Graphics2D)g.create();
        Rectangle2D.Double door = new Rectangle2D.Double(325, 650, 75, 200);
        g2.setColor(new Color(128, 0, 0));
        g2.draw(door);
        g2.fill(door);

        Ellipse2D.Double sun = new Ellipse2D.Double(650, -150, 300, 300);
        g2.setColor(Color.yellow);
        g2.draw(sun);
        g2.fill(sun);
        g2.translate(800, 0);
        g2.rotate(Math.toRadians(90));
        g2.fill(new Rectangle2D.Double(200, -10, 60, 10));
        for (int i = 0; i < 9; i++) {
            g2.rotate(Math.toRadians(10));
            Rectangle2D.Double ray = new Rectangle2D.Double(200, -10, 60, 10);
            g2.draw(ray);
            g2.fill(ray);
        }
        
        g2.dispose();

        g2 = (Graphics2D)g.create();
        Rectangle2D.Double treeTrunk = new Rectangle2D.Double(85, 500, 30, 200);
        g2.setColor(new Color(153, 51, 0));
        g2.draw(treeTrunk);
        g2.fill(treeTrunk);

        Polygon branches = new Polygon(
                    new int[]{35, 165, 100},
                    new int[]{520, 520, 300},
                    3);
        g2.setColor(new Color(0, 102, 0));
        g2.translate(0, 50);
        g2.draw(branches);
        g2.fill(branches);





        //g2.rotate(Math.toRadians(45), 200, 350); //Rotates around given point
        //g2.scale();
    }
}


