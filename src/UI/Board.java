package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Scanner;

import Factory.Factory;
import com.company.Grid;

public class Board extends JPanel implements ActionListener, MouseListener, MouseMotionListener
{
    GameOfLifeControls controls = Factory.controler;

    static final int cols = Factory.gridCols;
    static final int rows = Factory.gridRows;
    static final int originX = 0;
    static final int originY = 0;
    static final int size = Factory.defaultZoom;


    int xPanel = 1280, yPanel = 650;
    public int startX = 64-((xPanel/size)/2), startY = 32-((yPanel/size)/2);

    boolean[][] life = new boolean[65][128];
    boolean start = true;
    int check=0;
    boolean clicked = false;


    public Board()
    {
        setSize(xPanel, yPanel);
        setLayout(null);
        setBackground(Color.black);
        addMouseListener(this);
        addMouseListener(this);
        controls.setBoard(this);
        for(int i=0;i<yPanel/size;i++)
        {
            for(int j=0;j<xPanel/size;j++)
            {
                life[i][j]=false;
            }
        }
        for(int i=16;i<48;i++)
        {
            for(int j=32;j<96;j++)
            {
                life[i][j]=true;
            }
        }

        life[0][0]=true;
        life[64][0]=true;
        life[0][127]=true;
        life[64][127]=true;
        //new Timer(200, this).start();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        grid(g);
        drawGraphics(g);
    }


    private void grid(Graphics g)
    {
        g.setColor(Color.darkGray);

        for(int i=0;i<xPanel/size;i++)
        {
            for (int j=0;j<yPanel/size;j++)
            {
                g.drawRect((i * size) + originX, (j*size)+originY, size, size);
            }
        }

    }


    private void drawGraphics(Graphics g)
    {
        g.setColor(Color.red);

        for(int x=0;x<yPanel/size;x++)
        {
            for(int y=0;y<xPanel/size;y++)
            {
                try
                {
                    if(life[(x+startY)][(y+startX)])
                        g.fillRect((1+y*size), (1+x*size), size-2, size-2);
                }
                catch (ArrayIndexOutOfBoundsException e)
                {
                    // do nothing
                }

            }
        }

    }

    public void updateBoard(Grid g)
    {
        for(int i=0;i<yPanel/10;i++)
        {
            for(int j=0;j<xPanel/10;j++)
            {
                life[i][j] = g.grid[i][j].isAlive();
            }
        }
        repaint();
    }


    public void actionPerformed(ActionEvent e)
    {
        repaint();
    }


    public void mouseDragged(MouseEvent e)
    {

    }
    public void mouseMoved(MouseEvent e)
    {

    }
    synchronized public void mouseClicked(MouseEvent e)
    {
        clicked = false;
    }
    synchronized public void mousePressed(MouseEvent e)
    {
        int x = (e.getX()/size)+startX;
        int y = (e.getY()/size)+startY;

        controls.setCell(y, x, true);

    }
    public void mouseReleased(MouseEvent e)
    {

    }
    public void mouseEntered(MouseEvent e)
    {

    }
    public void mouseExited(MouseEvent e)
    {

    }


}
