package swing;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel
{
    static final int cols = 1000;
    static final int rows = 35;
    static final int originX = 0;
    static final int originY = 100;

    static final int width = 10;
    static final int height = 10;



    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.darkGray);
        g.fillRect(0, 0, 2000,600);
        g.setColor(Color.gray);
        for(int i=0;i<=rows;i++)
        {
            for (int j=0;j<cols;j++)
            {
                g.drawRect((j * width) + originX, (i*height)+originY, width, height);
            }
        }
    }
}
