package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;


public class GameOfLifeFrame extends JFrame
{
    JFrame j = new JFrame("Game Of Life");
    public void Frame()
    {
        JFrame f = new JFrame("Game of Life");//creating instance of JFrame

        Graphics g = null;
        int spacing = 10;

        f.setSize(1100,700);//400 width and 500 height
        Board panel = new Board();

        panel.setLayout(new BorderLayout());
        //f.add()
        f.add(panel);
        f.setLocationRelativeTo(null);
        panel.Panel();

        f.setVisible(true);//making the frame visible
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }



    public static void main(String[] args)
    {
        GameOfLifeFrame f = new GameOfLifeFrame();
        f.Frame();

    }
}
