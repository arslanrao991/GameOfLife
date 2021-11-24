package UI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;
import java.awt.*;

public class jlist_view extends JFrame
{
    JFrame frame = new JFrame("Available states for view: ");
    JList<String> state_list = new JList<>();
    DefaultListModel<String> stateListmodel = new DefaultListModel<>();

    JLabel txt = new JLabel();
    JPanel list_panel = new JPanel();
    JSplitPane splitPane = new JSplitPane();

    public jlist_view(String[] states_for_view)
    {
        state_list.setModel(stateListmodel);
        for (int i = 0; i < states_for_view.length; i++) {
            stateListmodel.addElement(states_for_view[i]);
        }
       // model.addElement("Oranges");

        state_list.getSelectionModel().addListSelectionListener(e -> {
            String p = state_list.getSelectedValue();
            txt.setText(p);
            System.out.println("index of selected is: " + state_list.getSelectedIndex());
        });
        txt.setBackground(Color.BLACK);
        txt.setForeground(Color.GRAY);
        splitPane.setBackground(Color.ORANGE);
        splitPane.setForeground(Color.ORANGE);
        splitPane.setLeftComponent(new JScrollPane(state_list));
        list_panel.add(txt);
        list_panel.setBackground(Color.BLACK);
        list_panel.setForeground(Color.BLACK);
        splitPane.setRightComponent(list_panel);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(splitPane);
        frame.setBounds(300,300,400,400);
        frame.setBackground(Color.BLACK);
        frame.setForeground(Color.ORANGE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String s[])
    {

        String states[] = new String[3];
        states[0] = "Rectangle";
        states[1] = "Box";
        states[2] = "Line";
        new jlist_view(states);
    }
}