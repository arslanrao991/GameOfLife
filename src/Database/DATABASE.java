package com.company;

import java.sql.*;
import java.util.Enumeration;
import java.util.Hashtable;

public class DATABASE
{
    Connection connect;

    public void Connection()
    {
        try
        {
            String url = "jdbc:mysql://localhost/jdbc";        //connection string here test is the name of the database
            connect = DriverManager.getConnection(url, "root", "m.mahad12");
        }
        catch (Exception e)
        {
            System.out.println("NOT-CONNECTED");
            e.printStackTrace();
        }
    }
    public void SavaState(Hashtable hashtable)                 //GETTING HASHTABLE AND ADDING X AND Y AXIS TO DATABASE
    {
        Connection();                                          //CREATING CONNECTION BETWEEN MYSQL AND JAVA
        Enumeration e = hashtable.elements();                  //USE FOR ITERATING HASHTABLE
        int x_axis,y_axis;
        int val=1;
        while(e.hasMoreElements())                             //ITERATING ELEMENTS IN HASHTABLE
        {
            TEST temp = new TEST();
            temp= (TEST) e.nextElement();

            x_axis = temp.x_axis;
            y_axis = temp.y_axis;
            String sql = "Insert Into CELLS Values ('"+val+"','"+x_axis+"','"+y_axis+"')";  //RUN THE SQL COMMAND

            try
            {
                Statement statement = connect.createStatement();
                statement.executeUpdate(sql);
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
            val=val+1;
        }
    }
    public void DeleteState()
    {
        Connection();
        String url = "Delete From Cells";                       //DELETE THE ENTRIES FROM TABLE
        try
        {
            Statement statement = connect.createStatement();
            statement.executeUpdate(url);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public Hashtable LoadState()
    {
        Hashtable<TEST,TEST> ht = new Hashtable<TEST,TEST>();     //CREATES A HASHTABLE FOR SAVING STATE
        String sql = "SELECT * FROM CELLS";

        try
        {
            Statement statement = connect.createStatement();
            ResultSet RT = statement.executeQuery(sql);

            while(RT.next())                                      //GETTING DATA FROM COLUMNS AND PLACING IN HASHTABLE
            {
                TEST temp = new TEST();
                temp.x_axis = RT.getInt("X_Axis");
                temp.y_axis = RT.getInt("Y_Axis");
                ht.put(temp,temp);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return ht;
    }

    public Hashtable ViewState()
    {
        Connection();
        Hashtable<TEST,TEST> ht = new Hashtable<TEST,TEST>();     //CREATES A HASHTABLE FOR RETURNING TO BL
        ht = LoadState();                                         //CALLING LoadState() TO RETRIEVE THE DATA FROM MYSQL

        return ht;
    }
}
