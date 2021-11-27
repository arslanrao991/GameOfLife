package Database;

import com.company.Cell;
import com.company.DBInterfaceIn;
import com.company.DBInterfaceOut;
import com.company.GameOfLife;

import java.awt.*;
import java.io.*;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

public class textDB implements DBInterfaceOut
{
    DBInterfaceIn gameControls;
    public textDB(GameOfLife g)
    {
        gameControls = g;
        g.addDBListener(this);
    }
    public void deleteRecentState()                        //Delete The Recent State
    {
        FileWriter fwOb = null;
        BufferedReader br;
        File obj;
        String data,LastFile=null;
        try {
            br = new BufferedReader(new FileReader("SaveStates.txt"));
            while ((data = br.readLine()) != null)
            {
                LastFile = data;
            }
            DeleteLastLine();
            obj = new File(LastFile);
            obj.delete();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public static void DeleteLastLine()
    {
        RandomAccessFile f = null;
        try
        {
            f = new RandomAccessFile("SaveStates.txt", "rw");
            long length = f.length() - 1;
            byte b;
            do
            {
                length -= 1;
                f.seek(length);
                b = f.readByte();
            }
            while (b != 10);
            f.setLength(length + 1);
            f.close();
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


    public static  void  DeleteBetweenLine(String name)
    {
        File inputFile = new File("SaveStates.txt");
        File tempFile = new File("myTempFile.txt");
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String lineToRemove = name;
            String currentLine;

            while((currentLine = reader.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                String trimmedLine = currentLine.trim();
                if(trimmedLine.equals(lineToRemove)) continue;
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();
            inputFile.delete();
            boolean successful = tempFile.renameTo(inputFile);
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

    //delete specific state
    public void deleteSelectedState(String name)                        //Delete The Given State
    {
        DeleteBetweenLine(name);
        File obj = new File(name);
        obj.delete();
    }

    //save State into text file
    public void saveState(Hashtable ht,String Name)                 //SAVE STATE IN FILE
    {
        Cell c;
        File file_obj = new File("SaveStates.txt");
        BufferedWriter out;
        BufferedWriter out1;
        Desktop desktop = Desktop.getDesktop();
        Enumeration e = ht.elements();
        int x_axis,y_axis;
        try
        {
            if (file_obj.exists())
            {
                try
                {
                    out = new BufferedWriter(new FileWriter("SaveStates.txt",true));
                    out.write(Name+".txt");
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
                    out.write(Name+".txt");
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
            file_obj = new File(Name+".txt");

        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        try
        {
            out = new BufferedWriter(new FileWriter(Name+".txt"));
            while(e.hasMoreElements())                                     //WRITING TO TEXT FILE ALL HASHTABLE
            {

                c = (Cell) e.nextElement();

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

    //load last Added State
    public  Hashtable loadRecentState()
    {
        BufferedReader br;
        Hashtable<Cell,Cell> ht =new Hashtable<Cell, Cell>();
        File obj;
        String data,LastFile=null;
        int X_Axis = 0,Y_Axis=0,val=0,size;
        int array1[] = new int[200];
        try
        {
            br = new BufferedReader(new FileReader("SaveStates.txt"));     //GETTING RECENT NAME OF FILE
            while ((data = br.readLine()) != null)
            {
                LastFile = data;
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            br = new BufferedReader(new FileReader(String.valueOf(LastFile)));
            while((data = br.readLine()) != null)
            {
                for( int i = 0; i<=data.length();i++)                              //EXTRATCING FROM RECENT FILE
                {
                    if(i==data.length())
                    {
                        for (int j = 0; j < val; j++) {                            //CONVERTING INT ARRAY TO INTEGER
                            Y_Axis = 10 * Y_Axis + array1[j];
                        }
                        break;
                    }
                    if(Character.isDigit(data.charAt(i)))                          //STORING IN INT ARRAY
                    {
                        array1[val] = data.charAt(i)-48;
                        //System.out.println(array1[val]);
                        val = val+1;
                    }
                    else if(data.charAt(i) == ',' )                                  //CONVERTING INT ARRAY TO INTEGER
                    {
                        for (int j = 0; j < val; j++) {
                            X_Axis = 10 * X_Axis + array1[j];
                        }
                        val = 0;
                    }
                }
                Cell c = new Cell(X_Axis, Y_Axis);                                     //STORING IN HASHTABLE
                ht.put(c,c);
                val = 0;
                X_Axis = 0;
                Y_Axis = 0;
            }
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

    //get Names of Saved States
    public String[] getStates()
    {
        String stateNames[] = new String[60];
        int val = 0;
        BufferedReader br;
        try
        {
            br =new BufferedReader(new FileReader("SaveStates.txt"));
            while((stateNames[val] = br.readLine())!=null)
            {
                val = val+1;
            }
            stateNames[val] = "/0";
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return stateNames;
    }

    //load Specific State
    public Hashtable loadState(String name)
    {
        BufferedReader br;
        Hashtable<Cell,Cell> ht =new Hashtable<Cell,Cell>();
        File obj;
        String data;
        int X_Axis = 0,Y_Axis=0,val=0,size;
        int array1[] = new int[200];
        try
        {
            br = new BufferedReader(new FileReader(String.valueOf(name)));
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
