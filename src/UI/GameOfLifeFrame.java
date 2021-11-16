package UI;

import Factory.Factory;

import javax.swing.*;
import java.awt.*;


public class GameOfLifeFrame extends JFrame
{
    JFrame j = new JFrame("Game Of Life");
    public void Frame()
    {
        JFrame f = new JFrame("Game of Life");//creating instance of JFrame
        Graphics g = null;
        int spacing = 10;

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

    }
    public void borderLayout()
    {

    }

    public static void main(String[] args)
    {
        GameOfLifeFrame f = new GameOfLifeFrame();
        f.Frame();

    }
}
