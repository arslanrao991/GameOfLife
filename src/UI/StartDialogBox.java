package UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class StartDialogBox extends JFrame implements ActionListener
{
    JFrame frame;
    JList<String> uiOPtions;
    JList<String> dbOPtions;
    DefaultListModel<String> uiSelection;
    DefaultListModel<String> dbSelection;

    JPanel listPanel1;
    JPanel listPanel2;

    JButton  okay_button;
    public String ui;
    public String db;
    public StartDialogBox()
    {
        ui = null;
        db = null;
        frame = new JFrame("Select UI & DB");
        frame.setBounds(300,300,300,300);

        uiOPtions = new JList<>();
        uiOPtions.setBorder(new EmptyBorder(10,20, 10, 10));
        dbOPtions = new JList<>();
        dbOPtions.setBorder(new EmptyBorder(10,20, 10, 10));

        uiSelection = new DefaultListModel<>();
        dbSelection = new DefaultListModel<>();


        listPanel1 = new JPanel();
        listPanel2 = new JPanel();

        okay_button = new JButton(" Open");
        okay_button.addActionListener(this);


        uiOPtions.setModel(uiSelection);
        dbOPtions.setModel(dbSelection);

        uiSelection.addElement("GUI");
        uiSelection.addElement("Console");
        dbSelection.addElement("MYSQL");
        dbSelection.addElement("TextDB");


        uiOPtions.getSelectionModel().addListSelectionListener(e ->
        {
            //do nothing

        });

        uiOPtions.setBackground(Color.darkGray);
        uiOPtions.setForeground(Color.white);
        dbOPtions.setBackground(Color.darkGray);
        dbOPtions.setForeground(Color.white);

        okay_button.setBackground(Color.BLACK);
        okay_button.setBackground(Color.white);


        listPanel1.setBackground(Color.darkGray);
        listPanel1.setForeground(Color.blue);
        listPanel2.setBackground(Color.darkGray);
        listPanel2.setForeground(Color.blue);

        frame.setBackground(Color.BLACK);
        frame.setForeground(Color.ORANGE);


        listPanel1.add(new JScrollPane(uiOPtions));
        listPanel1.setPreferredSize(new Dimension(150,50));

        listPanel2.add(new JScrollPane(dbOPtions));
        listPanel2.setPreferredSize(new Dimension(150,50));

        frame.add(listPanel1, BorderLayout.WEST);
        frame.add(listPanel2, BorderLayout.EAST);
        frame.add(okay_button,BorderLayout.SOUTH);


        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == okay_button)
        {
            if(uiOPtions.getSelectedValue() == null || dbOPtions.getSelectedValue() == null)
            {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                return;
            }

            ui = new String(uiOPtions.getSelectedValue());
            db = new String(dbOPtions.getSelectedValue());
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
    }
    public String getSelectedUI()
    {
        return ui;
    }
    public String getSelectedDB()   {return db;}

    public static void main(String[] args)
    {
        StartDialogBox nm = new StartDialogBox();
    }
}
