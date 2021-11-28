package Console;

import java.util.Objects;
import java.util.Scanner;

import Factory.Constants;
import com.BL.GameOfLife;
import com.BL.Grid;

public class MainFrame
{
    ConsoleControls gameControls;
    private static final int rows= Constants.gridRows;
    private static final int cols=Constants.gridCols;
    protected int startX;
    protected int startY;
    private int consoleC;
    private int consoleR;

    private int speed;
    private int zoom;

    private static boolean[][] life;
    private static boolean[][] tempGrid;

    public MainFrame(GameOfLife g)
    {
        startX = 0;
        startY = 0;
        speed = Constants.currentSpeed;
        zoom = Constants.currentZoom;
        consoleC = cols/zoom;
        consoleR = rows/zoom;
        life = new boolean[rows][cols];
        tempGrid = new boolean[rows][cols];

        this.gameControls = new ConsoleControls(g);
        updateBoard(gameControls.gameControls.getGrid());
        label:
        while(true)
        {
            clearConsole();
            // draw_grid();
            System.out.println("""
                     Choose your option:
                    1-> Start/Stop
                    2->View Saved States
                    3->Save State
                    4->Load State
                    5->Reset/Clear
                    6->Enter Input
                    7->Exit
                    8->Next State
                    9->Delete State
                    10->Speed Increase
                    11->Speed Decrease
                    12->Zoom In
                    13->Zoom out""");
            Scanner input_obj = new Scanner(System.in);
            String choice = input_obj.nextLine();
            switch (choice)
            {
                case "1":
                    startStopButtonClicked();
                    break;
                case "2":
                    viewStatesButtonClicked();
                    break;
                case "3":
                    saveStateButtonClicked();
                    break;
                case "4":
                    loadStateButtonClicked();
                    break;
                case "5":
                    resetButtonClicked();
                    break;
                case "6":
                    if (selectCell() == -1)
                    {
                        System.out.println("You have entered illegal col or row number! ");
                        break label;
                    }
                    break;
                case "7":
                    break label;
                case "8":
                    nextButtonClicked();
                    break;
                case "9":
                    deleteStateButtonClicked();
                    break;
                case "10":
                    incSpeed();
                    break;
                case "11":
                    decSpeed();
                    break;
                case "12":
                    incZoom();
                    break;
                case "13":
                    decZoom();
                    break;
            }
        }
    }
    // Drawing grid along with filling active cells with 0 based on cell_life hashmap
    public void drawBoard()
    {
        for (int i = 0; i < startY + consoleR; i++)
        {
            for (int j = 0; j < startX + consoleC ; j++)
            {
                //System.out.print(" ");
                if(life[i][j])
                    System.out.print(" # ");
                else
                    System.out.print(" . ");

            }
            System.out.print(" \n");
        }
        System.out.print("\n\n");
    }
    public void drawViewStateBoard()
    {
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols ; j++)
            {

                if(tempGrid[i][j])
                    System.out.print(" 0 ");
                else
                    System.out.print(" . ");

            }
            System.out.print(" \n");

        }
    }
    public int selectCell()
    {
        int x, y;
        Scanner input_obj= new Scanner(System.in);
        System.out.println("-- Set Cell --\n");
        System.out.println("Enter Row# (0 -"+ rows+" )");
        x= input_obj.nextInt();
        if(x < 0 || x > rows)
        {
            System.out.println("You have entered illegal row number! ");
            return -1;
        }
        System.out.println("Enter Column# (0 -"+ cols+" )");
        y= input_obj.nextInt();
        if(y < 0 || y > cols)
        {
            System.out.println("You have entered illegal col number! ");
            return -1;
        }

        setCell(x,y);
        return 0;
    }
    public void setCell(int a, int b)
    {
        if(!gameControls.getCell(a, b))
            gameControls.setCell(a, b, true);
        else
            gameControls.setCell(a, b, false);
        updateBoard(gameControls.gameControls.getGrid());
    }
    public void updateBoard(Grid g)
    {
        for(int i = 0; i< rows; i++)
        {
            for(int j = 0; j< cols; j++)
            {
                life[i][j] = g.getCellStatus(i, j);
            }
        }
        drawBoard();

    }
    public void updateViewStateBoard(Grid g)
    {
        for(int i = 0; i< startY + consoleR; i++)
        {
            for(int j = 0; j< startX + consoleC; j++)
            {
                tempGrid[i][j] = g.getCellStatus(i, j);
            }
        }
        drawViewStateBoard();
    }

    //function to clear the whole screen!
    private static void clearConsole()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    protected void startStopButtonClicked()
    {
        System.out.println(" Play button clicked");
        //start = true;
        gameControls.gameControls.startStopButtonClick();
        Thread GameLoop = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while(gameControls.gameControls.isGameRunning())
                {
                    gameControls.gameControls.next();
                    updateBoard(gameControls.gameControls.getGrid());

                    try
                    {
                        Thread.sleep(speed);
                    }
                    catch(InterruptedException e)
                    {
                        //do nothing
                    }
                }
            }
        });
        GameLoop.start();
    }
    protected void nextButtonClicked()
    {
        //clear_full_screen();
        System.out.println(" Next Button clicked");
        gameControls.gameControls.nextButtonClick();
        updateBoard(gameControls.gameControls.getGrid());
    }
    // clear or reset button!
    protected void resetButtonClicked()
    {
        System.out.println(" Reset button clicked");
        gameControls.gameControls.resetButtonClicked();
        updateBoard(gameControls.gameControls.getGrid());
    }
    protected void loadStateButtonClicked()
    {
        System.out.println(" Load button clicked");
        gameControls.gameControls.loadStateButtonClick();
        updateBoard(gameControls.gameControls.getGrid());
    }

    protected void saveStateButtonClicked()
    {
        System.out.println(" Save button clicked");
        Scanner input_state_name = new Scanner(System.in);
        // state name!
        System.out.print("Enter State Name-> ");
        String state_name = input_state_name.nextLine();
        gameControls.gameControls.saveStateButtonClick(state_name);
        System.out.print("Out from save State");
        updateBoard(gameControls.gameControls.getGrid());
    }
    protected void deleteStateButtonClicked()
    {
        gameControls.gameControls.deleteStateButtonClick();
    }
    protected void decSpeed()
    {
        if(speed>Constants.maxSpeed)
        {
            gameControls.gameControls.speedChanged(speed - 180);
            speed-=180;
        }
        updateBoard(gameControls.gameControls.getGrid());
    }
    protected void incSpeed()
    {
        if(speed<Constants.minSpeed)
        {
            gameControls.gameControls.speedChanged(speed + 180);
            speed+=180;
        }
        updateBoard(gameControls.gameControls.getGrid());
    }
    protected void incZoom()
    {
        if(zoom<Constants.maxZoomIn)
        {
            gameControls.gameControls.speedChanged(zoom + 5);
            zoom+=5;
        }
        consoleC = cols/zoom;
        consoleR = rows/zoom;
        updateBoard(gameControls.gameControls.getGrid());
    }
    protected void decZoom()
    {
        if(zoom>Constants.maxZoomOut)
        {
            gameControls.gameControls.speedChanged(zoom - 5);
            zoom-=5;
        }
        consoleC = cols/zoom;
        consoleR = rows/zoom;
        updateBoard(gameControls.gameControls.getGrid());
    }

    protected void viewStatesButtonClicked()
    {
        String[] list = gameControls.gameControls.getSavedStates();

        if(list != null)
        {
            System.out.println("Enter name of the state from following: \n");
            for (int i = 0; !Objects.equals(list[i], "\0"); i++)
            {
                System.out.println(i + ". " + list[i] + " ");
            }
            System.out.println("\n");
            Scanner view_input = new Scanner(System.in);
            String choice = view_input.nextLine();
            Grid g = gameControls.gameControls.getState(choice);
            updateViewStateBoard(g);
        }
        else
            System.out.println("No state is saved yet! \n");
    }

}


