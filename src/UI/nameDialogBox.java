package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class NameDialogBox
{
    JFrame f1;
    JLabel l1;
    String input_value;
    public NameDialogBox()
    {
        f1 = new JFrame();
        input_value = JOptionPane.showInputDialog(" start entering your value!","Shape1");
        if(input_value == null)
        {
            JOptionPane.showMessageDialog(f1, "You have not entered anything!");
            //input_value = JOptionPane.showInputDialog(" start entering your value!","Shape1");
        }
    }
    String get_string()
    {
        return input_value;
    }

    /*@Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == okay_button)
        {
            selectedState = new String(state_list.getSelectedValue());
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
    }*/
}

