package UI;

import javax.swing.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class input_SaveState {
    JFrame f1;
    String input_value;
    public input_SaveState()
    {
        f1 = new JFrame();

        input_value = JOptionPane.showInputDialog(" start entering your value!","Shape1" );
        if(input_value == null)
        {
            JOptionPane.showMessageDialog(f1, "You have not entered anything!");
            input_value = JOptionPane.showInputDialog(" start entering your value!","Shape1");
        }
    }
    String get_string()
    {
        return input_value;
    }
    public static void main(String s[])
    {
        new input_SaveState();
    }
}
