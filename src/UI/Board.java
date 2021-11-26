package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Constants.Constants;
import com.company.GameOfLife;
import com.company.Grid;

public class Board extends JPanel implements ActionListener, MouseListener, MouseMotionListener
{
    GameOfLifeControls controls;

    static final int cols = Constants.gridCols;
    static final int rows = Constants.gridRows;
    static final int originX = 0;
    static final int originY = 0;
    public static int size;
    public static int delay;


    int xPanel, yPanel;
    public int startX, startY;

    boolean[][] life;
    boolean clicked = false;


    public Board(int xPanel, int yPanel, GameOfLife g)
    {
        this.controls = new GameOfLifeControls(g);
        this.xPanel = xPanel;
        this.yPanel = yPanel;
        size = controls.getCurrentZoom();
        delay = controls.getCurrentSpeed();
        startX = (xPanel/ Constants.maxZoomOut)/2-((xPanel/size)/2);
        startY = (yPanel/ Constants.maxZoomOut)/2-((yPanel/size)/2);

        life = new boolean[Constants.gridRows][Constants.gridCols];

        setSize(xPanel, yPanel);
        setLayout(null);
        setBackground(Color.black);
        addMouseListener(this);
        addMouseMotionListener(this);

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
                g.drawRect((i * size) + originX, (j*size) + originY, size, size);
            }
        }

    }


    private void drawGraphics(Graphics g)
    {
        g.setColor(Color.yellow);

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
        setDimensions(xPanel, yPanel);
        for(int i = 0; i< Constants.gridRows; i++)
        {
            for(int j = 0; j< Constants.gridCols; j++)
            {
                life[i][j] = g.grid[i][j].isAlive();
            }
        }
        repaint();
    }

    public void setDimensions(int xPanel, int yPanel)
    {
        this.xPanel = xPanel;
        this.yPanel = yPanel;
        int changedZoom = controls.getCurrentZoom();
        if(size < changedZoom)
        {
            //size = controls.getCurrentZoom();
            /*startX = (xPanel / Constants.maxZoomOut) / 2 - ((xPanel / changedZoom) / 2);
            startY = (yPanel / Constants.maxZoomOut) / 2 - ((yPanel / changedZoom) / 2);*/
            startX = startX + ((xPanel/size) - (xPanel/changedZoom))/2;
            startY = startY + ((yPanel/size) - (yPanel/changedZoom))/2;
        }
        else if (size > changedZoom)
        {

            /*startX = (xPanel / Constants.maxZoomOut) / 2 - ((xPanel / changedZoom) / 2);
            startY = (yPanel / Constants.maxZoomOut) / 2 - ((yPanel / changedZoom) / 2);*/
            if(((startX - ((xPanel/changedZoom) - (xPanel/size)))+(xPanel/changedZoom)) < Constants.gridCols && startX !=0)
                startX = startX - ((xPanel/changedZoom)/2 - (xPanel/size)/2);
            else if (startX != 0)
                startX = startX - ((xPanel/changedZoom) - (xPanel/size));

            if (((startY - ((yPanel/changedZoom) - (yPanel/size)))+(yPanel/changedZoom)) < Constants.gridRows && startY!=0)
                startY = startY - ((yPanel/changedZoom)/2 - (yPanel/size)/2);
            else if (startY != 0 )
                startY = startY - ((yPanel/changedZoom) - (yPanel/size));

            if(startX<0)
                startX=0;
            if(startY<0)
                startY=0;
        }
        size = changedZoom;
    }

    public void actionPerformed(ActionEvent e)
    {
        repaint();
    }


    public void mouseDragged(MouseEvent e)
    {
        int x = (e.getX()/size)+startX;
        int y = (e.getY()/size)+startY;
        if(!controls.getCell(y, x) && clicked)
        {
            controls.setCell(y, x, true);
            //mouseClicked(e);
        }
        else if(controls.getCell(y, x) && !clicked)
            controls.setCell(y, x, false);

        repaint();
        controls.gameControls.setGeneration();
        updateBoard(controls.gameControls.getGrid());
    }
    public void mouseMoved(MouseEvent e)
    {

    }
    synchronized public void mouseClicked(MouseEvent e)
    {

    }
    synchronized public void mousePressed(MouseEvent e)
    {
        int x = (e.getX()/size)+startX;
        int y = (e.getY()/size)+startY;
        clicked = true;

        if(!controls.getCell(y, x) && clicked)
        {
            controls.setCell(y, x, true);
        }
        else if(controls.getCell(y, x))
            controls.setCell(y, x, false);

        controls.gameControls.setGeneration();
        updateBoard(controls.gameControls.getGrid());

    }
    public void mouseReleased(MouseEvent e)
    {
        clicked = false;

    }
    public void mouseEntered(MouseEvent e)
    {

    }
    public void mouseExited(MouseEvent e)
    {

    }

}
