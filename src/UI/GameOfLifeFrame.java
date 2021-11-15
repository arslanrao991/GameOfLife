package UI;

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

        f.setSize(1100,700);//400 width and 500 height
        Board panel = new Board();
        //f.add()
        f.add(panel);
        panel.Panel();
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
