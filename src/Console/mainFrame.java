package Console;

import java.util.Scanner;

import Factory.Constants;
import com.company.GameOfLife;
import com.company.Grid;

public class mainFrame
{
    gameControls gameControls;
    private static final int rows= Constants.gridRows;
    private static final int cols=Constants.gridCols;
    private static final int originX = 0;
    private static final int originY = 0;
    private int startX;
    private int startY;
    private int consoleC = 24;
    private int consoleR = 20;

    private static int  size =5;

    private static boolean[][] cell_life = new boolean[rows][cols];
    private static boolean[][] state_cell = new boolean[rows][cols];
    boolean start = true;
    int check=0;

    public mainFrame(GameOfLife g)
    {
        startX = 0;
        startY = 0;
//       System.out.println(" Choose your option:\n" + "1-> PLAY  2->Stop  3->Save State   4->Load State 5->Clear 6-> Enter Input" +
//               "7-> Exit \n");
        this.gameControls = new gameControls(g);
        fill_grid(gameControls.gameControls.getGrid());
        updateBoard(gameControls.gameControls.getGrid());
        while(true)
        {
            clear_full_screen();
            // draw_grid();
            System.out.println(" Choose your option:\n" + "1-> PLAY  2->Stop  3->Save State   4->Load State 5->Reset  6->Enter Input " +
                    " 7->Exit  8->Next State  9->Delete State  10->Speed Increase 11->Speed Decrease \n");
            Scanner input_obj = new Scanner(System.in);
            String choice = input_obj.nextLine();
            if (choice.equals("1"))
                start();
            else if (choice.equals("2"))
                stop();
            else if (choice.equals("3"))
                Save();
            else if (choice.equals("4"))
                Load();
            else if (choice.equals("5"))
                Clear();
            else if (choice.equals("6"))
            {
                if (enter_input() == -1)
                {
                    System.out.println("You have entered illegal col or row number! ");
                    break;
                }
            } else if (choice.equals("7"))
                break;
            else if(choice.equals("8"))
                nxt_button();
            else if(choice.equals("9"))
                delete_state();
            else if(choice.equals("10"))
                speed_increase();
            else if(choice.equals("11"))
                speed_decrease();
        }
    }
    // Drawing grid along with filling active cells with 0 based on cell_life hashmap
    public void draw_grid()
    {
        for (int i = 0; i < startY + consoleR; i++) {


            for (int j = 0; j < startX + consoleC ; j++)
            {
                System.out.print("|_");
                if(cell_life[i][j] == true)
                    System.out.print("0");
                else
                    System.out.print("_");

                System.out.print("_");
            }
            System.out.print("|\n");

        }
    }
    public void draw_grid_for_shapes()
    {
        for (int i = 0; i < rows; i++)
        {

            for (int j = 0; j < cols ; j++)
            {
                System.out.print("|_");

                if(state_cell[i][j] == true)
                    System.out.print("0");
                else
                    System.out.print("_");

                System.out.print("_");
            }
            System.out.print("|\n");

        }
    }
    public  void fill_grid(Grid g)
    {
        int val;
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                val= (int) (Math.random()* 2);
                if( val == 0) {
                    gameControls.gameControls.setCell(i,j,true);
                }
            }

        }

    }
    public int enter_input()
    {
        int x, y;
        Scanner input_obj= new Scanner(System.in);
        System.out.println("Enter row number of cell you want to be active from the range 0 -"+ rows);
        x= input_obj.nextInt();
        if(x < 0 || x > rows) {
            System.out.println("You have entered illegal row number! ");
            return -1;
        }
        System.out.println("Enter column number of cell you want to be active from the range 0 -" + cols);
        y= input_obj.nextInt();
        if(y < 0 || y > cols) {
            System.out.println("You have entered illegal col number! ");
            return -1;
        }

        set_cell(x,y);
        return 0;
    }
    public static void set_cell(int a,int b)
    {
        cell_life[a][b]= true;
    }
    public void updateBoard(Grid g)
    {
        //setDimensions(xPanel, yPanel);
        for(int i = 0; i< rows; i++)
        {
            for(int j = 0; j< cols; j++)
            {
                cell_life[i][j] = g.grid[i][j].isAlive();
            }
        }
        draw_grid();
    }
    public void update_for_shapes(Grid g)
    {
        //setDimensions(xPanel, yPanel);
        for(int i = 0; i< startY + consoleR; i++)
        {
            for(int j = 0; j< startX + consoleC; j++)
            {
                state_cell[i][j] = g.grid[i][j].isAlive();
            }
        }
        draw_grid_for_shapes();
    }

    // function to manage speed of the game!
    private void slow_down(int delay)
    {
        try {
            Thread.sleep(delay);
        }
        catch (InterruptedException excep)
        {
            Thread.currentThread().interrupt();
        }
    }

    //function to clear the whole screen!
    private static void clear_full_screen()
    {
        System.out.println("\003[H\003[2J");
        System.out.flush();
    }

    protected void start()
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
                        Thread.sleep(gameControls.gameControls.getSpeed());
                    }
                    catch(InterruptedException e)
                    {

                    }
                }
            }
        });
        GameLoop.start();
    }
    protected void nxt_button()
    {
        System.out.println(" Next Button clicked");
        gameControls.gameControls.nextButtonClick();
        updateBoard(gameControls.gameControls.getGrid());
    }
    protected void stop()
    {
        //start = false;
        System.out.println(" Stop button clicked");
        gameControls.gameControls.startStopButtonClick();
    }
    // clear or reset button!
    protected void Clear()
    {
        System.out.println(" Reset button clicked");
        gameControls.gameControls.resetButtonClicked();
        updateBoard(gameControls.gameControls.getGrid());
    }
    protected void Load()
    {
        System.out.println(" Load button clicked");
        gameControls.gameControls.loadStateButtonClick();
        update_for_shapes(gameControls.gameControls.getGrid());
    }

    protected void Save()
    {
        System.out.println(" Save button clicked");
        Scanner input_state_name = new Scanner(System.in);
        // state name!
        String state_name = input_state_name.nextLine();
        gameControls.gameControls.saveStateButtonClick(state_name);
        System.out.print("Out from save State");
        updateBoard(gameControls.gameControls.getGrid());
    }
    protected void delete_state()
    {
        gameControls.gameControls.deleteStateButtonClick();
        update_for_shapes(gameControls.gameControls.getGrid());
    }
    protected void speed_decrease()
    {

    }
    protected void speed_increase()
    {

    }

    protected void view_saved_states()
    {
        String[] list = gameControls.gameControls.getSavedStates();

        if(list != null)
        {
            System.out.println("Enter name of the state from following: \n");
            for (int i = 0; i < list.length; i++)
            {
                System.out.println(i + ". " + list[i] + " ");
            }
            System.out.println("\n");
            Scanner view_input = new Scanner(System.in);
            String choice = view_input.nextLine();
            Grid g = gameControls.gameControls.getState(choice);
            update_for_shapes(g);
        }
        else
            System.out.println("No state is saved yet! \n");
        //final String[] selectedState = {null};
    }

}


