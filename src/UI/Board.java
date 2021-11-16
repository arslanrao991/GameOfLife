package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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

    int xPanel = 1100, yPanel = 700;

    boolean[][] life = new boolean[xPanel/size][yPanel/size];
    boolean start = true;
    int check=0;
    boolean clicked = false;


    public void drawBoard()
    {
        setSize(1100, 700);
        setLayout(null);
        setBackground(Color.black);
        addMouseListener(this);
        addMouseListener(this);
        controls.setBoard(this);

        //new Timer(200, this).start();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        grid(g);
        //spawn(g);
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
        g.setColor(Color.blue);
        for(int x=0;x<xPanel/size;x++)
        {
            for(int y=0;y<(yPanel/size);y++)
            {
                if(life[x][y]==true)
                    g.fillRect(y*size, x*size, size, size);
            }
        }

    }

    public void updateBoard(Grid g)
    {
        for(int i=0;i<10;i++)
        {
            for(int j=0;j<10;j++)
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
        System.out.print("9");
    }
    public void mouseClicked(MouseEvent e)
    {
        clicked = false;
    }
    public void mousePressed(MouseEvent e)
    {
        int x = e.getX()/size;
        int y = e.getY()/size;

        controls.setCell(y, x, true);
        /*life[x][y] = true;
        repaint();*/

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
