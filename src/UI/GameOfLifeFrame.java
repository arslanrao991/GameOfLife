package UI;

import Factory.Factory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;


public class GameOfLifeFrame extends JFrame implements KeyListener
{
    private JFrame f = new JFrame("Game Of Life");
    private Board board = new Board();
    public GameOfLifeFrame()
    {
        Graphics g = null;
        int spacing = 10;

        f.setLayout(new BorderLayout());

        //f.setSize(1280,650);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setUndecorated(true);


        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        f.addKeyListener(this);

        f.add(board);

        f.setVisible(true);//making the frame visible
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public void borderLayout()
    {

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == e.VK_ESCAPE)
        {
            f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
        }
        else if (e.getKeyCode() == e.VK_UP)
        {
            try
            {
                if(board.startY!=0)
                    board.startY -=1;
                board.repaint();
            }
            catch (ArrayIndexOutOfBoundsException error)
            {
                //do nothing
            }

        }
        else if (e.getKeyCode() == e.VK_DOWN)
        {
            try
            {
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                if(board.startY < Factory.gridRows - board.yPanel/Factory.defaultZoom)
                    board.startY += 1;
                board.repaint();
            }
            catch (ArrayIndexOutOfBoundsException error)
            {
                //do nothing
            }
        }
        else if (e.getKeyCode() == e.VK_RIGHT)
        {
            try
            {
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

                if(board.startX < Factory.gridCols -board.xPanel/Factory.defaultZoom)
                    board.startX += 1;
                board.repaint();
            }
            catch (ArrayIndexOutOfBoundsException error)
            {
                //do nothing
            }
        }
        else if (e.getKeyCode() == e.VK_LEFT)
        {
            try
            {
                if(board.startX!=0)
                    board.startX -= 1;
                board.repaint();
            }
            catch (ArrayIndexOutOfBoundsException error)
            {
                //do nothing
            }
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


}
