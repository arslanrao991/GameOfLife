package UI;

import javax.swing.*;
import java.awt.*;

public class Zoom extends JPanel
{
    protected Graphics getComponentGraphics(Graphics g)
    {
        Graphics2D g2d=(Graphics2D)g;

        g2d.scale(2, 2);
        System.out.print("In getComponentGraphicsFunction");

        return super.getComponentGraphics(g2d);
    }
}
