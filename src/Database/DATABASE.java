package com.company;

import java.sql.*;
import java.util.Enumeration;
import java.util.Hashtable;

public class DATABASE {

    TextFile Txt = new TextFile();
    Connection connect;

    public void Connection() {
        try {
            String url = "jdbc:mysql://localhost/jdbc";        //connection string here test is the name of the database
            connect = DriverManager.getConnection(url, "root", "m.mahad12");
        } catch (Exception e) {
            System.out.println("NOT-CONNECTED");
            e.printStackTrace();
        }
    }

    public void SavaState(Hashtable hashtable , String Name)     //GETTING HASHTABLE AND ADDING X AND Y AXIS TO DATABASE
    {
        Connection();                                            //CREATING CONNECTION BETWEEN MYSQL AND JAVA
        Enumeration e = hashtable.elements();                    //USE FOR ITERATING HASHTABLE
        int x_axis, y_axis,val;

        String sql = "Insert Into StateName Values ('" + Name +  "')";
        try
        {
            Statement statement = connect.createStatement();
            statement.executeUpdate(sql);
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

        while (e.hasMoreElements())                               //ITERATING ELEMENTS IN HASHTABLE
        {
            TEST temp = new TEST();
            temp = (TEST) e.nextElement();

            x_axis = temp.x_axis;
            y_axis = temp.y_axis;
            sql = "Insert Into CELLS (X_Axis,Y_Axis,Name) Values ('" + x_axis + "','" + y_axis + "','"+Name+"')";  //RUN THE SQL COMMAND
            try
            {
                Statement statement = connect.createStatement();
                statement.executeUpdate(sql);
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
        //Txt.save_state_txt(hashtable);
    }

    public void DeleteRecentState()
    {
        Connection();
        int Id;
        String Names;
        String sql = "SELECT * from CELLS order by Id DESC LIMIT 1";
        try
        {
            Statement statement = connect.createStatement();
            ResultSet RT = statement.executeQuery(sql);
            RT.next();
            Names = RT.getString("Name");
            PreparedStatement statement1 = connect.prepareStatement("Delete FROM CELLS where Name=?");
            statement1.setString(1,Names);
            statement1.executeUpdate();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

        //Txt.clearTheFile();
    }

    public Hashtable LoadState() {

        Connection();
        String Names;
        Hashtable<TEST, TEST> ht = new Hashtable<TEST, TEST>();     //CREATES A HASHTABLE FOR SAVING STATE
        String sql = "SELECT * from CELLS order by Id DESC LIMIT 1,1";

        try
        {
            Statement statement = connect.createStatement();
            ResultSet RT = statement.executeQuery(sql);
            RT.next();
            Names = RT.getString("Name");

            sql = "SELECT * from CELLS where Name='"+ Names +"'";
            RT = statement.executeQuery(sql);
            while (RT.next())                                      //GETTING DATA FROM COLUMNS AND PLACING IN HASHTABLE
            {
                TEST temp = new TEST();
                temp.x_axis = RT.getInt("X_Axis");
                temp.y_axis = RT.getInt("Y_Axis");
                ht.put(temp, temp);
            }
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return ht;
    }

    public void DeleteState(String Names)
    {
        Connection();
        try
        {
            Statement statement = connect.createStatement();
            PreparedStatement statement1 = connect.prepareStatement("Delete FROM CELLS where Name=?");
            statement1.setString(1,Names);
            statement1.executeUpdate();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

    public Hashtable ViewState(String Names)
    {

        Connection();
        Hashtable<TEST, TEST> ht = new Hashtable<TEST, TEST>();     //CREATES A HASHTABLE FOR RETURNING TO BL
        String sql = "SELECT * from CELLS where Name='"+ Names +"'";                                                   // CALLING LoadState() TO RETRIEVE THE DATA FROM MYSQL
        try
        {
            Statement statement = connect.createStatement();
            ResultSet RT = statement.executeQuery(sql);
            while (RT.next())                                      //GETTING DATA FROM COLUMNS AND PLACING IN HASHTABLE
            {
                TEST temp = new TEST();
                temp.x_axis = RT.getInt("X_Axis");
                temp.y_axis = RT.getInt("Y_Axis");
                ht.put(temp, temp);
            }
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return ht;
    }
    public String[] ReturnStates()
    {
        Connection();
        String[] StateName = new String[30];
        int val = 0;
        String sql = "SELECT * from StateName";
        try
        {
            Statement statement = connect.createStatement();
            ResultSet RT = statement.executeQuery(sql);
            while (RT.next())                                      //GETTING DATA FROM COLUMNS AND PLACING IN HASHTABLE
            {
                StateName[val] = RT.getString("Name");
                val = val +1;
            }
            StateName[val] = "\0";
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return StateName;
    }
}

