package swing;
import javax.swing.*;
import java.awt.*;

public class swing
{
    public void Example()
    {

        JFrame f = new JFrame("Game of Life");//creating instance of JFrame
        Graphics g;
        int spacing = 10;

        //JButton b=new JButton("Hello World!");//creating instance of JButton
        //b.setBounds(100,200,200, 40);//x axis, y axis, width, height

        //f.add(b);//adding button in JFrame

        f.setSize(900,600);//400 width and 500 height
        /*JPanel panel=new JPanel();
        panel.setBounds(90, 100, 700, 320);
        panel.setBackground(Color.white);
        f.add(panel);*/
        Board board = new Board();
        f.add(board);
        f.setVisible(true);//making the frame visible

    }
}
