import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;

public class Ovals extends JPanel {
    public static void main(String[] args){
        JFrame window = new JFrame("Oval Design");
        Ovals content = new Ovals();
        window.setContentPane(content);
        window.pack();
        window.setResizable(false);
        window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        window.setVisible(true);


    }
}