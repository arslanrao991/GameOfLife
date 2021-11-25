package Database;


import com.company.DBInterfaceIn;
import com.company.Cell;
import com.company.DBInterfaceOut;
import com.company.GameOfLife;

import java.io.*;
import java.util.Enumeration;
import java.util.Hashtable;

public class textDB implements DBInterfaceOut
{
    DBInterfaceIn gameControls;
    public textDB(GameOfLife g)
    {
        this.gameControls = g;
        g.addDBListener(this);
    }
    public void deleteState()                        //CLEARS THE STATES FROM FILE
    {
        FileWriter fwOb = null;
        try
        {
            fwOb = new FileWriter("save_grid_inFile.txt", false);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        pwOb.close();
        try
        {
            fwOb.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void saveState(Hashtable<Cell, Cell> h)                 //SAVE STATES IN FILES
    {
        Cell c, newCell;
        Enumeration e = h.elements();
        int x_axis,y_axis;
        try
        {
            File file_obj = new File("save_grid_inFile.txt");
            if (file_obj.createNewFile())
            {                       //IF FILE IS NOT CREATED IT CREATES IT FOR FIRST TIME
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

        try
        {
            BufferedWriter out = new BufferedWriter(new FileWriter("save_grid_inFile.txt"));

            while(e.hasMoreElements())                //WRITING TO TEXT FILE ALL HASHTABLE
            {

                c = (Cell) e.nextElement();
                newCell = new Cell(c.x_axis, c.y_axis);
                x_axis = c.x_axis;
                y_axis = c.y_axis;

                out.write(String.valueOf(x_axis));
                out.append(',');
                out.write(String.valueOf(y_axis));
                out.append('\n');
            }

            out.close();
        }
        catch (IOException exp2)
        {
            System.out.println(" Error: Can't write to file");
            exp2.printStackTrace();
        }
    }



    @Override
    public Hashtable<Cell, Cell> loadState() {
        return null;
    }

    @Override
    public Hashtable<Cell, Cell> viewState() {
        return null;
    }
}

