package UI;

import Factory.Constants;
import com.company.Grid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Board2 extends JPanel implements ActionListener
{
    GameOfLifeControls controls;

    protected static final int cols = Constants.gridCols;
    protected static final int rows = Constants.gridRows;
    protected static final int originX = 0;
    protected static final int originY = 0;
    protected static int size;
    protected int startX, startY;
    int xPanel, yPanel;
    Grid life;

    public Board2(int xPanel, int yPanel, Grid g)
    {

        this.xPanel = xPanel;
        this.yPanel = yPanel;
        size = Constants.maxZoomOut;
        startX = (cols)/2-((xPanel/size)/2);
        startY = (rows)/2-((yPanel/size)/2);

        life = g;

        setSize(xPanel, yPanel);
        setLayout(null);
        setBackground(Color.black);
        //controls.setBoard(this);

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
                    if(life.getCellStatus((x+startY), (y+startX)))
                        g.fillRect((1+y*size), (1+x*size), size-2, size-2);
                }
                catch (ArrayIndexOutOfBoundsException e)
                {
                    // do nothing
                }

            }
        }

    }


    public void setDimensions(int xPanel, int yPanel)
    {
        this.xPanel = xPanel;
        this.yPanel = yPanel;
        if(size != controls.getCurrentZoom())
        {
            size = controls.getCurrentZoom();
            startX = (xPanel / Constants.maxZoomOut) / 2 - ((xPanel / size) / 2);
            startY = (yPanel / Constants.maxZoomOut) / 2 - ((yPanel / size) / 2);
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        repaint();
    }

}
