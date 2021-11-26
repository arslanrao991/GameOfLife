package Console;

import Constants.Constants;
import java.util.Scanner;
import com.company.GameOfLife;
import com.company.Grid;

public class main_frame {
     Game_controls u_in;
    private static final int rows=Constants.gridRows;
    private static final int cols=Constants.gridCols;
    private final int StartX;
    private  final int StartY;
    private final int  consoleR=20;
    private  final int consoleC=24;

  private static int  size =5;


   private static boolean[][] cell_life = new boolean[rows][cols];
    private static boolean[][] state_cell = new boolean[rows][cols];
    boolean start = true;
    int check=0;

    public main_frame(GameOfLife g)
    {
        StartX = 0;
        StartY = 0;

        this.u_in = new Game_controls(g);
        fill_grid(u_in.gameControls.getGrid());
        updateBoard(u_in.gameControls.getGrid());
        while(true) {
            clear_full_screen();
           // draw_grid();
            System.out.println(" Choose your option:\n" + "1-> PLAY  2->Stop  3->Save State   4->Load State 5->Reset  6->Enter Input " +
                    " 7->Exit  8->Next State  9->Delete State  10->Speed Increase 11->Speed Decrease \n");
            Scanner input_obj = new Scanner(System.in);
            String choice = input_obj.nextLine();
            if (choice.equals("1"))
                play();
            else if (choice.equals("2"))
                stop();
            else if (choice.equals("3"))
                Save();
            else if (choice.equals("4"))
                Load();
            else if (choice.equals("5"))
                Clear();
            else if (choice.equals("6")) {
                if (enter_input() == -1) {
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
        for (int i = 0; i < StartY + consoleR; i++) {

            for (int j = 0; j <StartX + consoleC ; j++) {
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
        for (int i = 0; i <StartY + consoleR; i++) {

            for (int j = 0; j < StartX + consoleC; j++) {
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
                      u_in.gameControls.setCell(i,j,true);
                    //  set_cell(i, j);
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
        for(int i = 0; i<StartY + consoleR  ; i++)
        {
            for(int j = 0; j< StartX + consoleC; j++)
            {
                cell_life[i][j] = g.grid[i][j].isAlive();
            }
        }
         draw_grid();
    }
    public void update_for_shapes(Grid g)
    {
        //setDimensions(xPanel, yPanel);
        for(int i = 0; i< StartY + consoleR; i++)
        {
            for(int j = 0; j< StartX + consoleC ; j++)
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

      protected void play()
    {
        System.out.println(" Play button clicked");
        start = true;
        u_in.gameControls.startStopButtonClick();
        Thread GameLoop = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while(u_in.gameControls.isGameRunning())
                {
                    u_in.gameControls.next();
                    updateBoard(u_in.gameControls.getGrid());

                    try
                    {
                        Thread.sleep(u_in.gameControls.getSpeed());
                    }
                    catch(InterruptedException e)
                    {

                    }
                }

            }

        }
        );
        GameLoop.start();
    }
    protected void nxt_button()
    {
        System.out.println(" Next Button clicked");
        u_in.gameControls.nextButtonClick();
        updateBoard(u_in.gameControls.getGrid());
    }
    protected void stop()
    {
        start = false;
        System.out.println(" Stop button clicked");
        u_in.gameControls.startStopButtonClick();
    }
    // clear or reset button!
    protected void Clear()
    {
        System.out.println(" Reset button clicked");
        u_in.gameControls.resetButtonClicked();
        updateBoard(u_in.gameControls.getGrid());
    }
    protected void Load()
    {
        System.out.println(" Load button clicked");
        u_in.gameControls.loadStateButtonClick();
        update_for_shapes(u_in.gameControls.getGrid());
    }

    protected void Save()
    {
        System.out.println(" Save button clicked");
        System.out.println(" Enter name of your state: \n");
        Scanner input_state_name = new Scanner(System.in);
        String state_name = input_state_name.nextLine();
        u_in.gameControls.saveStateButtonClick(state_name);
        updateBoard(u_in.gameControls.getGrid());
    }
    protected void delete_state()
    {
        u_in.gameControls.deleteStateButtonClick();
        update_for_shapes(u_in.gameControls.getGrid());
    }
    protected void speed_decrease()
    {

    }
    protected void speed_increase()
    {

    }

    protected void view_saved_states()
    {
        String[] list = u_in.gameControls.getSavedStates();

        if(list != null)
        {
            System.out.println("Enter name of the state from following: \n");
            for (int i = 0; i < list.length; i++) {
                System.out.println(i + ". " + list[i] + " ");
            }
            System.out.println("\n");
            Scanner view_input = new Scanner(System.in);
            String choice = view_input.nextLine();
            Grid g = u_in.gameControls.getState(choice);
            update_for_shapes(g);
        }
        else
            System.out.println("No state is saved yet! \n");
    }

}

