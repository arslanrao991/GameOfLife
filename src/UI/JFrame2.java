package UI;

import Factory.Constants;
import com.company.GameOfLife;
import com.company.Grid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class JFrame2 extends JFrame implements KeyListener, ActionListener
{
    private final JFrame f = new JFrame("View State Frame");
    private final Board2 board;
    JButton exitBtn;


    double bottomControlsPanelRatio = 15.75;

    public JFrame2(Grid g)
    {
        f.setBackground(Color.blue);
        f.setLayout(null);

        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setUndecorated(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        board = new Board2(screenSize.width, (int) (screenSize.height-(screenSize.getHeight()*bottomControlsPanelRatio/100)), g);

        exitBtn = new JButton("Exit");


        f.addKeyListener(this);

        f.add(board);
        f.setVisible(true);//making the frame visible
        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int menu1Y = (screenSize.height - (int) (screenSize.getHeight() * bottomControlsPanelRatio/100) + (int) (screenSize.getHeight() * bottomControlsPanelRatio/100)/3 - (int) (screenSize.getHeight() * bottomControlsPanelRatio/100)*26/100);
        int menu1Height = (int) (screenSize.getHeight() * bottomControlsPanelRatio/100)*26/100;
        int menu1Spacing = (int) (screenSize.getWidth()*3/100);

        int menu2Y = menu1Y + menu1Height*2;
        int menu2Spacing = (int) (screenSize.width * 1.56 /100);;

        int exitBtnWidth = (int) (screenSize.width * 20 /100);
        int exitBtnX = (int) (screenSize.width/2) - exitBtnWidth/2;


        //adding Buttons to Frame
        exitBtn.setBounds(exitBtnX, menu1Y , exitBtnWidth, menu1Height);

        exitBtn.setBackground(Color.darkGray);
        exitBtn.setForeground(Color.white);

        exitBtn.addActionListener(this);

        exitBtn.setLayout(null);
        exitBtn.setFocusable(false);
        f.add(exitBtn);


    }


    @Override
    public void keyTyped(KeyEvent e)
    {
        //do nothing
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP)
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
        else if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            try
            {
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                if(board.startY < Constants.gridRows - board.yPanel/Board.size)
                    board.startY += 1;
                board.repaint();
            }
            catch (ArrayIndexOutOfBoundsException error)
            {
                //do nothing
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            try
            {
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

                if(board.startX < Constants.gridCols -board.xPanel/Board.size)
                    board.startX += 1;
                board.repaint();
            }
            catch (ArrayIndexOutOfBoundsException error)
            {
                //do nothing
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT)
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
    public void keyReleased(KeyEvent e)
    {
        //do nothing
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == exitBtn)
        {
            f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
        }

    }

}
