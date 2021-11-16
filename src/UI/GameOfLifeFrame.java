package UI;

import Factory.Factory;

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

<<<<<<< HEAD
        f.setSize(1100,700);//400 width and 500 height
        Board panel = new Board();

        panel.setLayout(new BorderLayout());
        //f.add()
        f.add(panel);
        f.setLocationRelativeTo(null);
        panel.Panel();

        f.setVisible(true);//making the frame visible
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

=======
        f.setLayout(new BorderLayout());

        f.setSize(1100,700);
        Board board = new Board();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        JPanel gridPanel = new JPanel();
        //gridPanel.setLayout(new GridLayout(Factory.gridRows,Factory.gridCols));


        panel1.setBackground(Color.GRAY);
        panel2.setBackground(Color.GRAY);
        panel3.setBackground(Color.GRAY);
        panel4.setBackground(Color.GRAY);
        panel5.setBackground(Color.GREEN);


        panel1.setPreferredSize(new Dimension(5, 5));
        panel2.setPreferredSize(new Dimension(5, 5));
        panel3.setPreferredSize(new Dimension(5, 5));
        panel4.setPreferredSize(new Dimension(5, 5));
        //panel.setPreferredSize(new Dimension(200, 200));



        f.add(panel1, BorderLayout.NORTH);
        f.add(panel2, BorderLayout.WEST);
        f.add(panel3, BorderLayout.EAST);
        f.add(panel4, BorderLayout.SOUTH);
        //f.add(panel5, BorderLayout.CENTER);

        f.add(board);
        board.drawBoard();

        f.setVisible(true);//making the frame visible
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

>>>>>>> a8a25d9a90d70e7725c7cb69aa1ca816095a5c6a
    }



    public static void main(String[] args)
    {
        GameOfLifeFrame f = new GameOfLifeFrame();
        f.Frame();

    }
}
