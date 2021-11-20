package com.company;

import java.io.*;
import java.util.Enumeration;
import java.util.Hashtable;

public class TextFile
{
    public static void clearTheFile()                        //CLEARS THE STATES FROM FILE
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

    public void save_state_txt(Hashtable ht)                 //SAVE STATES IN FILES
    {
        TEST test = new TEST();
        Enumeration e = ht.elements();
        int x_axis,y_axis;
        try {
            File file_obj = new File("save_grid_inFile.txt");
            if (file_obj.createNewFile()) {                       //IF FILE IS NOT CREATED IT CREATES IT FOR FIRST TIME
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
            BufferedWriter out = new BufferedWriter(new FileWriter("save_grid_inFile.txt"));

            while(e.hasMoreElements())                //WRITING TO TEXT FILE ALL HASHTABLE
            {
                TEST temp = new TEST();
                temp = (TEST) e.nextElement();

                x_axis = temp.x_axis;
                y_axis = temp.y_axis;

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
}
