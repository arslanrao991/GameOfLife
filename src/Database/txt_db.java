package Database;
import java.io.File;    //Importing the file class
import java.io.FileWriter;
import java.io.IOException;

public class txt_db {

    int rows;
    int cols;
    int[][] cell_of_game;
    txt_db()
    {
         rows=4;
         cols=4;
     cell_of_game= new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
    }
    public void save_state_txt()
    {
        try {
            File file_obj = new File("save_grid_inFile.txt");
            if (file_obj.createNewFile()) {
                System.out.println("File is created with name " + file_obj.getName());
            }
            else
            {
                System.out.println("\n File already exists \n");
            }
        }
        catch(IOException exp)
        {
            System.out.println(" Error: file isn't created");
            exp.printStackTrace();
        }

        try{
            FileWriter write_state_data= new FileWriter("save_grid_inFile.txt");

            for (int x=0; x< rows; x++)
            {
                for (int y=0; y<cols; y++)
                {
                    write_state_data.write(cell_of_game[x][y] + "");
                    write_state_data.append('\n');
                }
            }


            write_state_data.close();

            System.out.println(" Successfully written to the file");
        }
        catch (IOException exp2)
        {
            System.out.println(" Error: Can't write to file");
            exp2.printStackTrace();
        }
    }

}
