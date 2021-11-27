package Database;

import com.company.Cell;
import com.company.DBInterfaceIn;
import com.company.DBInterfaceOut;
import com.company.GameOfLife;
import java.io.*;
import java.util.Enumeration;
import java.util.Hashtable;


public class TextDB implements DBInterfaceOut
{

    protected DBInterfaceIn gameControls;
    public TextDB(GameOfLife g)
    {
        gameControls = g;
        g.addDBListener(this);
    }
    public void deleteRecentState()                        //Delete The Recent State
    {
        BufferedReader br;
        File obj;
        String data, recentSavedFile=null;
        File f = new File("SaveStates.txt");
        if(!f.exists() )
        {
            return;
        }
        try
        {
            br = new BufferedReader(new FileReader("SaveStates.txt"));
            while ((data = br.readLine()) != null)
            {
                recentSavedFile = data;
            }
            br.close();
            if(recentSavedFile==null)
                return;
            DeleteLine(recentSavedFile);
            obj = new File(recentSavedFile);
            obj.delete();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static  void  DeleteLine(String name)
    {
        File inputFile = new File("SaveStates.txt");
        File tempFile = new File("myTempFile.txt");
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;

            while ((currentLine = reader.readLine()) != null)
            {
                // trim newline when comparing with lineToRemove
                String trimmedLine = currentLine.trim();
                if (trimmedLine.equals(name))
                    continue;
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();
            inputFile.delete();
            tempFile.renameTo(inputFile);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
    public void deleteState(String name)                        //Delete The Given State
    {
        DeleteLine(name);
        File obj = new File(name);
        obj.delete();
    }
    public void saveState(Hashtable ht,String name)                 //SAVE STATE IN FILE
    {
        Cell cell;
        File file_obj = new File("SaveStates.txt");
        BufferedWriter out;
        Enumeration e = ht.elements();
        int x_axis,y_axis;
        try
        {
            if (file_obj.exists())
            {
                try
                {
                    out = new BufferedWriter(new FileWriter("SaveStates.txt",true));
                    out.write(name+".txt");
                    out.append('\n');
                    out.close();
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            }
            else
            {
                file_obj.createNewFile();
                try
                {
                    out = new BufferedWriter(new FileWriter("SaveStates.txt"));
                    out.write(name+".txt");
                    out.append('\n');
                    out.close();
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            }
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }
        try
        {
            out = new BufferedWriter(new FileWriter(name+".txt"));
            while(e.hasMoreElements())                                     //WRITING TO TEXT FILE ALL HASHTABLE
            {
                cell = (Cell) e.nextElement();

                x_axis = cell.x_axis;
                y_axis = cell.y_axis;

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
    public  Hashtable<Cell, Cell> loadRecentState()
    {
        BufferedReader br;
        Hashtable<Cell, Cell> ht =new Hashtable<>();
        String data,recentSavedFile=null;
        int x_Axis = 0, y_Axis=0, val=0;
        int[] array1 = new int[200];
        File f = new File("SaveStates.txt");
        if(!f.exists() )
        {
            return ht;
        }
        try
        {
            br = new BufferedReader(new FileReader("SaveStates.txt"));     //GETTING RECENT NAME OF FILE
            while ((data = br.readLine()) != null)
            {
                recentSavedFile = data;
            }
            br.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        if(recentSavedFile==null)
            return ht;
        try
        {
            br = new BufferedReader(new FileReader(recentSavedFile));
            while((data = br.readLine()) != null)
            {
                for( int i = 0; i<=data.length();i++)                              //Extracting FROM RECENT FILE
                {
                    if(i==data.length())
                    {
                        for (int j = 0; j < val; j++) {                            //CONVERTING INT ARRAY TO INTEGER
                            y_Axis = 10 * y_Axis + array1[j];
                        }
                        break;
                    }
                    if(Character.isDigit(data.charAt(i)))                          //STORING IN INT ARRAY
                    {
                        array1[val] = data.charAt(i)-48;
                        val = val+1;
                    }
                    else if(data.charAt(i) == ',' )                                  //CONVERTING INT ARRAY TO INTEGER
                    {
                        for (int j = 0; j < val; j++) {
                            x_Axis = 10 * x_Axis + array1[j];
                        }
                        val = 0;
                    }
                }
                Cell c = new Cell(x_Axis, y_Axis);                                       //Storing IN HASHTABLE
                ht.put(c,c);
                val = 0;
                x_Axis = 0;
                y_Axis = 0;
            }
            br.close();
        }
        catch (FileNotFoundException e)
        {

            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return ht;
    }
    public String[] getStates()
    {
        String[] statesName = new String[60];
        int val = 0;
        BufferedReader br;
        File f = new File("SaveStates.txt");
        if(!f.exists() )
        {
            return statesName;
        }
        try
        {
            br =new BufferedReader(new FileReader("SaveStates.txt"));
            while((statesName[val] = br.readLine())!=null)
            {
                val = val+1;
            }
            statesName[val] = "\0";
            br.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return statesName;
    }

    public Hashtable<Cell, Cell> loadState(String Name)
    {
        BufferedReader br;
        Hashtable<Cell,Cell> ht =new Hashtable<>();
        String data;
        int X_Axis = 0,Y_Axis=0,val=0;
        int[] array1 = new int[200];
        try
        {
            br = new BufferedReader(new FileReader(String.valueOf(Name)));
            while((data = br.readLine()) != null)
            {
                for( int i = 0; i<=data.length();i++)
                {
                    if(i==data.length())
                    {
                        for (int j = 0; j < val; j++) {
                            Y_Axis = 10 * Y_Axis + array1[j];
                        }
                        break;
                    }
                    if(Character.isDigit(data.charAt(i)))
                    {
                        array1[val] = data.charAt(i)-48;
                        val = val+1;
                    }
                    else if(data.charAt(i) == ',' )
                    {
                        for (int j = 0; j < val; j++) {
                            X_Axis = 10 * X_Axis + array1[j];
                        }
                        val = 0;
                    }
                }
                Cell c = new Cell(X_Axis, Y_Axis);

                ht.put(c,c);
                val = 0;
                X_Axis = 0;
                Y_Axis = 0;
            }
            br.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return ht;
    }
}
