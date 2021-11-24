package UI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class jlist_view extends JFrame
{
    JFrame frame;
    JList<String> state_list;
    DefaultListModel<String> stateListmodel;

    JLabel txt = new JLabel("hello");
    JPanel list_panel;
    JButton  okay_button;
    public jlist_view(String[] states_for_view)
    {
        frame = new JFrame("Available states for view: ");
        frame.setBounds(300,300,400,300);

        state_list = new JList<>();
        state_list.setBorder(new EmptyBorder(10,20, 10, 10));
        stateListmodel = new DefaultListModel<>();

        list_panel = new JPanel();

        okay_button = new JButton(" Open");
      //  okay_button.setPreferredSize(new Dimension(30,60));


        state_list.setModel(stateListmodel);
        for (int i = 0; i < states_for_view.length; i++) {
            stateListmodel.addElement(states_for_view[i]);
        }


        state_list.getSelectionModel().addListSelectionListener(e -> {

            System.out.println("index of selected is: " + state_list.getSelectedIndex());
        });

        state_list.setBackground(Color.darkGray);
        state_list.setForeground(Color.white);

        okay_button.setBackground(Color.BLACK);
        okay_button.setBackground(Color.white);


        list_panel.setBackground(Color.darkGray);
        list_panel.setForeground(Color.blue);

        frame.setBackground(Color.BLACK);
        frame.setForeground(Color.ORANGE);


        list_panel.add(new JScrollPane(state_list));
        list_panel.setPreferredSize(new Dimension(450,200));

        frame.add(list_panel,BorderLayout.NORTH);
        frame.add(okay_button,BorderLayout.SOUTH);


        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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