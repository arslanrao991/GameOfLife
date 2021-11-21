package Console;
import Factory.Constants;
import java.util.Scanner;

public class main_frame {
    //static final int cols = Factory.gridCols;
    //static final int rows = Factory.gridRows;
    int rows=24;
    int cols=24;
    static final int originX = 0;
    static final int originY = 0;
    static final int size = Constants.currentZoom;

    int xPanel = 1100, yPanel = 700;

    boolean[][] cell_life = new boolean[xPanel/size][yPanel/size];
    boolean start = true;
    int check=0;
    boolean cell_clicked = false;

    public main_frame()
    {
        System.out.println(" Choose your option:\n" + "1-> PLAY  2->Stop  3->Save State   4->Load State 5->Clear ");
        draw_grid();
        Scanner input_obj= new Scanner(System.in);
        String choice= input_obj.nextLine();
        if(choice.equals("1") )
            play();
        else if(choice.equals("2"))
            stop();
        else if(choice.equals("3"))
            Save();
        else if(choice.equals("4"))
            Load();
        else if(choice.equals("5"))
            Clear();

    }
    // Drawing grid along with filling active cells with 0 based on cell_life hashmap
    public void draw_grid()
    {
        for (int i = 0; i < rows; i++) {

            for (int k = 0; k < cols; k++) {
                System.out.print("____");
            }

            System.out.print("_\n");

            for (int j = 0; j < cols ; j++) {
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
    protected void play()
    {
        System.out.println(" Play button clicked");
    }

    protected void stop()
    {
        System.out.println(" Stop button clicked");
    }
    protected void Load()
    {
        System.out.println(" Load button clicked");
    }

    protected void Save()
    {
        System.out.println(" Save button clicked");
    }

    protected void Clear()
    {
        System.out.println(" Clear button clicked");
    }
}
