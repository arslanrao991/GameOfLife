package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import UI.GameOfLifeControls;
import com.company.CellGrid;
import com.company.Factory;

public class Board extends JPanel implements ActionListener, MouseListener, MouseMotionListener
{
    GameOfLifeControls controls = new GameOfLifeControls();


    static final int cols = Factory.gridCols;
    static final int rows = Factory.gridRows;
    static final int originX = 0;
    static final int originY = 0;
    static final int cellSide = 100;
    static final int size = Factory.defaultZoom;

    int xPanel = 1100, yPanel = 600;
    //int size = 15;
    int[][] life = new int[xPanel/size][yPanel/size];
    int[][] newLife = new int[xPanel/size][yPanel/size];
    boolean start = true;
    int check=0;
    boolean clicked = false;


    public void Panel()
    {
        setSize(1100, 700);
        setLayout(null);
        setBackground(Color.black);
        addMouseListener(this);
        addMouseListener(this);

        new Timer(80, this).start();
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

        for(int i=0;i<=rows;i++)
        {
            for (int j=0;j<cols;j++)
            {
                g.drawRect((j * size) + originX, (i*size)+originY, size, size);
            }
        }
        /*for(int i=0;i<life.length;i++)
        {
            g.drawLine(0, i*size, xPanel, i*size);   //row
            g.drawLine(i*size, 0, i*size, yPanel);   //col
        }*/
    }


    private void drawGraphics(Graphics g)
    {
        g.setColor(Color.blue);
        for(int x=0;x<life.length;x++)
        {
            for(int y=0;y<(yPanel/size);y++)
            {
                if(life[x][y]==1)
                    g.fillRect(x*size, y*size, size, size);
            }
        }
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

        controls.setCell(x, y, true);

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
