package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Factory.Constants;
import com.BL.GameOfLife;
import com.BL.Grid;

//main board of game of life
public class Board extends JPanel implements ActionListener, MouseListener, MouseMotionListener
{
    GameOfLifeControls controls;

    protected static final int cols = Constants.gridCols;
    protected static final int rows = Constants.gridRows;
    protected static final int originX = 0;
    protected static final int originY = 0;
    protected static int size;
    protected static int delay;
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
        startX = (cols)/2-((xPanel/size)/2);
        startY = (rows)/2-((yPanel/size)/2);

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
        g.setColor(Color.PINK);

        for(int x=0;x<yPanel/size;x++)
        {
            for(int y=0;y<xPanel/size;y++)
            {
                try
                {
                    try
                    {
                        if (life[(x + startY)][(y + startX)])
                            g.fillRect((1 + y * size), (1 + x * size), size - 2, size - 2);
                    }
                    catch (ArrayIndexOutOfBoundsException e)
                    {
                        // do nothing
                    }
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
        for(int i = 0; i< rows; i++)
        {
            for(int j = 0; j< cols; j++)
            {
                life[i][j] = g.getCellStatus(i, j);
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
            /*size = controls.getCurrentZoom();
            startX = (xPanel / Constants.maxZoomOut) / 2 - ((xPanel / changedZoom) / 2);
            startY = (yPanel / Constants.maxZoomOut) / 2 - ((yPanel / changedZoom) / 2);*/

            startX = startX + ((xPanel/size) - (xPanel/changedZoom))/2;
            startY = startY + ((yPanel/size) - (yPanel/changedZoom))/2;
        }
        else if (size > changedZoom)
        {

            /*startX = (xPanel / Constants.maxZoomOut) / 2 - ((xPanel / changedZoom) / 2);
            startY = (yPanel / Constants.maxZoomOut) / 2 - ((yPanel / changedZoom) / 2);*/

            int xOriginCalculation = ((startX - ((xPanel/changedZoom)/2 - (xPanel/size)/2))+(xPanel/changedZoom));
            int yOriginCalculation = ((startY - ((yPanel/changedZoom)/2 - (yPanel/size)/2))+(yPanel/changedZoom));
            if(xOriginCalculation < cols && startX !=0)
                startX = startX - ((xPanel/changedZoom)/2 - (xPanel/size)/2);
            else if (startX != 0)
                startX = startX - ((xPanel/changedZoom) - (xPanel/size));

            if (yOriginCalculation < rows && startY!=0)
                startY = startY - ((yPanel/changedZoom)/2 - (yPanel/size)/2);
            else if (startY != 0 )
                startY = startY - ((yPanel/changedZoom) - (yPanel/size));

            xOriginCalculation = startX+(xPanel/changedZoom);
            yOriginCalculation = startY+(yPanel/changedZoom);
            if(startX<0)
                startX=0;
            if(startY<0)
                startY=0;
            if(xOriginCalculation > cols)
            {
                startX = cols-xOriginCalculation;
            }
            if(yOriginCalculation > rows)
            {
                startY = rows-yOriginCalculation;
            }
        }
        size = changedZoom;
    }

    public void actionPerformed(ActionEvent e)
    {
        repaint();
    }


    public void mouseDragged(MouseEvent e)
    {
        try
        {
            int x = (e.getX() / size) + startX;
            int y = (e.getY() / size) + startY;
            if (!controls.getCell(y, x) && clicked) {
                controls.setCell(y, x, true);
                //mouseClicked(e);
            } else if (controls.getCell(y, x) && !clicked)
                controls.setCell(y, x, false);

            repaint();
            controls.gameControls.setGeneration();
            updateBoard(controls.gameControls.getGrid());
        }
        catch (ArrayIndexOutOfBoundsException e3)
        {
            //do nothing
        }
    }
    public void mouseMoved(MouseEvent e)
    {
        //do nothing
    }
    synchronized public void mouseClicked(MouseEvent e)
    {
        //do nothing
    }
    synchronized public void mousePressed(MouseEvent e)
    {
        try
        {
            int x = (e.getX() / size) + startX;
            int y = (e.getY() / size) + startY;
            clicked = true;

            if (!controls.getCell(y, x) && clicked) {
                controls.setCell(y, x, true);
            } else if (controls.getCell(y, x))
                controls.setCell(y, x, false);

            controls.gameControls.setGeneration();
            updateBoard(controls.gameControls.getGrid());
        }
        catch (ArrayIndexOutOfBoundsException e2)
        {
            //do nothing
        }

    }
    public void mouseReleased(MouseEvent e)
    {
        clicked = false;
    }
    public void mouseEntered(MouseEvent e)
    {
        //do nothing
    }
    public void mouseExited(MouseEvent e)
    {
        //do nothing
    }

}
