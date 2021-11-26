package UI;

import Constants.Constants;
import com.company.GameOfLife;
import com.company.Grid;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.*;


public class GameOfLifeFrame extends JFrame implements KeyListener, ActionListener
{
    private final JFrame f = new JFrame("Game Of Life");
    private final Board board;
    JButton startBtn;
    JButton nextBtn;
    JButton resetBtn;

    JButton saveStateBtn;
    JButton deleteStateBtn;
    JButton loadStateBtn;
    JButton viewStateBtn;
    JLabel genLabel;
    /*JButton a;
    JButton b;*/
    JSlider speed_Slider;
    JSlider zoom_Slider;


    double bottomControlsPanelRatio = 15.75;

    public GameOfLifeFrame(GameOfLife g)
    {
        f.setBackground(Color.blue);
        f.setLayout(null);

        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setUndecorated(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        board = new Board(screenSize.width, (int) (screenSize.height-(screenSize.getHeight()*bottomControlsPanelRatio/100)), g);

        startBtn = new JButton("Start");
        nextBtn = new JButton("Next");
        resetBtn = new JButton("Clear");
        saveStateBtn = new JButton("Save State");
        deleteStateBtn = new JButton("Delete State");
        loadStateBtn = new JButton("Load State");
        viewStateBtn = new JButton("View State");
        genLabel = new JLabel("0");
        speed_Slider = new JSlider(1, 7, 4);
        zoom_Slider = new JSlider(1, 7, 4);


        f.addKeyListener(this);

        f.add(board);
        f.setVisible(true);//making the frame visible
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int menu1Y = (screenSize.height - (int) (screenSize.getHeight() * bottomControlsPanelRatio/100) + (int) (screenSize.getHeight() * bottomControlsPanelRatio/100)/3 - (int) (screenSize.getHeight() * bottomControlsPanelRatio/100)*26/100);
        int menu1Height = (int) (screenSize.getHeight() * bottomControlsPanelRatio/100)*26/100;
        int menu1Spacing = (int) (screenSize.getWidth()*3/100);

        int menu2Y = menu1Y + menu1Height*2;
        int menu2Spacing = (int) (screenSize.width * 1.56 /100);;

        int startBtnWidth = (int) (screenSize.width * 20 /100);
        int starBtnX = (int) (screenSize.width/2) - startBtnWidth/2;

        int nextBtnWidth = (int) (screenSize.width *12/ 100);
        int nextBtnX = (int) (starBtnX + startBtnWidth + menu1Spacing);

        int resetBtnWidth = (int) (screenSize.width *12/ 100);
        int resetBtnX = (int) (nextBtnX + nextBtnWidth + menu1Spacing - 10);

        int zoomSliderWidth = (int) (screenSize.width *12/ 100);
        int zoomSliderX = starBtnX - menu1Spacing - zoomSliderWidth;

        int speedSliderWidth = (int) (screenSize.width *12/ 100);
        int speedSliderX = zoomSliderX - zoomSliderWidth - menu1Spacing + 10;

        int menu2BtnWidth = (int) (screenSize.width/2)/4 - menu2Spacing/2;
        int saveBtnX = (int) (screenSize.width/4) - menu2Spacing/2;
        int deleteBtnX =  saveBtnX + menu2BtnWidth + menu2Spacing;
        int loadBtnX = deleteBtnX + menu2BtnWidth +menu2Spacing;
        int viewBtnX = loadBtnX + menu2BtnWidth +menu2Spacing;

        //adding Buttons to Frame
        startBtn.setBounds(starBtnX, menu1Y , startBtnWidth, menu1Height);
        nextBtn.setBounds(nextBtnX, menu1Y+2, nextBtnWidth, menu1Height-2);
        resetBtn.setBounds(resetBtnX, menu1Y+2, resetBtnWidth, menu1Height-2);
        speed_Slider.setBounds(speedSliderX, menu1Y+2, speedSliderWidth, menu1Height-2);
        zoom_Slider.setBounds(zoomSliderX, menu1Y+2, zoomSliderWidth, menu1Height-2);

        saveStateBtn.setBounds(saveBtnX, menu2Y, menu2BtnWidth, menu1Height);
        deleteStateBtn.setBounds(deleteBtnX, menu2Y, menu2BtnWidth, menu1Height);
        loadStateBtn.setBounds(loadBtnX, menu2Y, menu2BtnWidth, menu1Height);
        viewStateBtn.setBounds(viewBtnX, menu2Y, menu2BtnWidth, menu1Height);


        zoom_Slider.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                zoom_Slider_StateChanged(evt);
            }

            private void zoom_Slider_StateChanged(ChangeEvent evt)
            {
                int zoom = 0;

                if (zoom_Slider.getValue() == Board.size/5)
                    return;

                zoom =(zoom_Slider.getValue()*5);
                board.controls.gameControls.zoomChanged(zoom);
                board.updateBoard(board.controls.gameControls.getGrid());
                genLabel.setText(Integer.toString(board.controls.gameControls.getGeneration()));


            }
        } );
        speed_Slider.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                speed_Slider_StateChanged(evt);
            }

            private void speed_Slider_StateChanged(ChangeEvent evt)
            {
                int speed = 0;

                if (speed_Slider.getValue() == Board.delay/160)
                    return;

                speed = Constants.minSpeed - speed_Slider.getValue()*160;
                board.controls.gameControls.speedChanged(speed);
                board.updateBoard(board.controls.gameControls.getGrid());
                genLabel.setText(Integer.toString(board.controls.gameControls.getGeneration()));


            }
        } );


        startBtn.addActionListener(this);
        nextBtn.addActionListener(this);
        resetBtn.addActionListener(this);
        saveStateBtn.addActionListener(this);
        deleteStateBtn.addActionListener(this);
        loadStateBtn.addActionListener(this);
        viewStateBtn.addActionListener(this);
        /*a.addActionListener(this);
        b.addActionListener(this);*/

        startBtn.setLayout(null);
        startBtn.setFocusable(false);
        nextBtn.setLayout(null);
        nextBtn.setFocusable(false);
        resetBtn.setLayout(null);
        resetBtn.setFocusable(false);
        saveStateBtn.setLayout(null);
        saveStateBtn.setFocusable(false);
        deleteStateBtn.setLayout(null);
        deleteStateBtn.setFocusable(false);
        loadStateBtn.setLayout(null);
        loadStateBtn.setFocusable(false);
        viewStateBtn.setLayout(null);
        viewStateBtn.setFocusable(false);
        speed_Slider.setLayout(null);
        speed_Slider.setFocusable(false);
        zoom_Slider.setLayout(null);
        zoom_Slider.setFocusable(false);

        startBtn.setBackground(Color.darkGray);
        startBtn.setForeground(Color.white);
        nextBtn.setBackground(Color.darkGray);
        nextBtn.setForeground(Color.white);
        resetBtn.setBackground(Color.darkGray);
        resetBtn.setForeground(Color.white);
        saveStateBtn.setBackground(Color.darkGray);
        saveStateBtn.setForeground(Color.white);
        deleteStateBtn.setBackground(Color.darkGray);
        deleteStateBtn.setForeground(Color.white);
        loadStateBtn.setBackground(Color.darkGray);
        loadStateBtn.setForeground(Color.white);
        viewStateBtn.setBackground(Color.darkGray);
        viewStateBtn.setForeground(Color.white);
        speed_Slider.setBackground(Color.darkGray);
        speed_Slider.setForeground(Color.white);
        zoom_Slider.setBackground(Color.darkGray);
        zoom_Slider.setForeground(Color.white);

        genLabel.setBounds(resetBtnX+resetBtnWidth+20, menu1Y, 100, 30);




        f.add(startBtn);
        f.add(nextBtn);
        f.add(resetBtn);
        f.add(saveStateBtn);
        f.add(deleteStateBtn);
        f.add(loadStateBtn);
        f.add(viewStateBtn);
        f.add(genLabel);
        f.add(speed_Slider);
        f.add(zoom_Slider);

    }


    @Override
    public void keyTyped(KeyEvent e)
    {
        //do nothing
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            try
            {
                if(board.startY!=0)
                    board.startY -=1;
                board.repaint();
            }
            catch (ArrayIndexOutOfBoundsException error)
            {
                //do nothing
            }

        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            try
            {
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                if(board.startY < Constants.gridRows - board.yPanel/Board.size)
                    board.startY += 1;
                board.repaint();
            }
            catch (ArrayIndexOutOfBoundsException error)
            {
                //do nothing
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            try
            {
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

                if(board.startX < Constants.gridCols -board.xPanel/Board.size)
                    board.startX += 1;
                board.repaint();
            }
            catch (ArrayIndexOutOfBoundsException error)
            {
                //do nothing
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            try
            {
                if(board.startX!=0)
                    board.startX -= 1;
                board.repaint();
            }
            catch (ArrayIndexOutOfBoundsException error)
            {
                //do nothing
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        //do nothing
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == startBtn)
        {
            board.controls.gameControls.startStopButtonClick();
            startBtn.setText("Stop");
            resetBtn.setText("Reset");

            Thread GameLoop = new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    while(board.controls.gameControls.isGameRunning())
                    {
                        board.controls.gameControls.next();
                        board.updateBoard(board.controls.gameControls.getGrid());
                        genLabel.setText(Integer.toString(board.controls.gameControls.getGeneration()));
                        try
                        {
                            Thread.sleep(board.controls.gameControls.getSpeed());
                        }
                        catch(InterruptedException e)
                        {

                        }
                    }
                    startBtn.setText("Start");
                    resetBtn.setText("Clear");
                }
            });
            GameLoop.start();
        }
        else if(e.getSource() == nextBtn)
        {
            board.controls.gameControls.nextButtonClick();
            board.updateBoard(board.controls.gameControls.getGrid());
            genLabel.setText(Integer.toString(board.controls.gameControls.getGeneration()));

        }
        else if(e.getSource() == resetBtn)
        {
            board.controls.gameControls.resetButtonClicked();
            board.updateBoard(board.controls.gameControls.getGrid());
            genLabel.setText(Integer.toString(board.controls.gameControls.getGeneration()));
        }
        else if(e.getSource() == saveStateBtn)
        {
            input_SaveState input_ss = new input_SaveState();
            System.out.println("Entered value is: "+ input_ss.get_string());
            board.controls.gameControls.saveStateButtonClick(input_ss.get_string());
            board.updateBoard(board.controls.gameControls.getGrid());
            genLabel.setText(Integer.toString(board.controls.gameControls.getGeneration()));
        }
        else if(e.getSource() == deleteStateBtn)
        {
            board.controls.gameControls.deleteStateButtonClick();
            board.updateBoard(board.controls.gameControls.getGrid());
            genLabel.setText(Integer.toString(board.controls.gameControls.getGeneration()));
        }
        else if(e.getSource() == loadStateBtn)
        {
            board.controls.gameControls.loadStateButtonClick();
            board.updateBoard(board.controls.gameControls.getGrid());
            genLabel.setText(Integer.toString(board.controls.gameControls.getGeneration()));
        }
        else if(e.getSource() == viewStateBtn)
        {
            String[] s = board.controls.gameControls.getSavedStates();
            JListPanel statesListPanel = new JListPanel(s);
            final String[] selectedState = {null};


            Thread viewStateLoop = new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    while(selectedState[0] == null)
                    {
                        selectedState[0] = statesListPanel.getSelectedState();
                    }
                    Grid g = board.controls.gameControls.getState(selectedState[0]);
                    JFrame2 f2 = new JFrame2(g);
                    selectedState[0]= null;
                }
            });
            viewStateLoop.start();



        }

    }

    public void updateFrameDimension()
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        board.setDimensions(screenSize.width, (int) (screenSize.height-(screenSize.getHeight()*18.75/100)));
    }
}
