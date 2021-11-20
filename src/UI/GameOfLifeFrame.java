package UI;

import Factory.Factory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GameOfLifeFrame extends JFrame implements KeyListener, ActionListener
{
    private final JFrame f = new JFrame("Game Of Life");
    private final Board board;
    private JButton startBtn = new JButton("Start");
    private JButton nextBtn = new JButton("Next");
    private JButton resetBtn = new JButton("Reset");

    public GameOfLifeFrame()
    {
        f.setLayout(null);

        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setUndecorated(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        board = new Board(screenSize.width, (int) (screenSize.height-(screenSize.getHeight()*18.75/100)));
        startBtn = new JButton("Start");
        nextBtn = new JButton("Next");
        resetBtn = new JButton("Reset");

        f.addKeyListener(this);

        f.add(board);
        f.setVisible(true);//making the frame visible
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int menu1Y = (screenSize.height - (int) (screenSize.getHeight() * 18.75/100) + (int) (screenSize.getHeight() * 18.75/100)/3 - (int) (screenSize.getHeight() * 18.75/100)*26/100);
        int menu1Height = (int) (screenSize.getHeight() * 18.75/100)*26/100;

        int startBtnWidth = (int) (screenSize.width * 20 /100);
        int starBtnX = (int) (screenSize.width/2) - startBtnWidth/2;

        int nextBtnWidth = (int) (screenSize.width *12/ 100);
        int nextBtnX = (int) (starBtnX + startBtnWidth + (screenSize.getWidth()*3/100));

        int resetBtnWidth = (int) (screenSize.width *12/ 100);
        int resetBtnX = (int) (nextBtnX + nextBtnWidth + (screenSize.getWidth()*3/100) - 10);

        //adding Buttons to Frame
        startBtn.setBounds(starBtnX, menu1Y , startBtnWidth, menu1Height);
        nextBtn.setBounds(nextBtnX, menu1Y+2, nextBtnWidth, menu1Height-2);
        resetBtn.setBounds(resetBtnX, menu1Y+2, resetBtnWidth, menu1Height-2);

        startBtn.addActionListener(this);
        nextBtn.addActionListener(this);
        resetBtn.addActionListener(this);

        startBtn.setLayout(null);
        startBtn.setFocusable(false);
        nextBtn.setLayout(null);
        nextBtn.setFocusable(false);
        resetBtn.setLayout(null);
        resetBtn.setFocusable(false);

        startBtn.setBackground(Color.darkGray);
        startBtn.setForeground(Color.white);
        nextBtn.setBackground(Color.darkGray);
        nextBtn.setForeground(Color.white);
        resetBtn.setBackground(Color.darkGray);
        resetBtn.setForeground(Color.white);

        f.add(startBtn);
        f.add(nextBtn);
        f.add(resetBtn);

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
                if(board.startY < Factory.gridRows - board.yPanel/Factory.currentZoom)
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

                if(board.startX < Factory.gridCols -board.xPanel/Factory.currentZoom)
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
        if(e.getSource() == startBtn)
        {
            System.out.print("Button Click");
        }
    }

    public void updateFrameDimension()
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        board.setDimensions(screenSize.width, (int) (screenSize.height-(screenSize.getHeight()*18.75/100)));
    }
}
