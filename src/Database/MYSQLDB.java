package Database;

import com.BL.DBInterfaceIn;
import com.BL.Cell;
import com.BL.DBInterfaceOut;
import com.BL.GameOfLife;

import java.sql.*;
import java.util.Enumeration;
import java.util.Hashtable;

public class MYSQLDB implements DBInterfaceOut
{
    Connection connect;
    protected DBInterfaceIn gameControls;

    public MYSQLDB(GameOfLife g)
    {
        gameControls = g;
        g.addDBListener(this);
    }

    public void Connection()
    {
        try
        {
            String url = "jdbc:mysql://localhost/gol";
            connect = DriverManager.getConnection(url, "root", "root");
        }
        catch (Exception e)
        {
            System.out.println("NOT-CONNECTED");
            e.printStackTrace();
        }
    }

    public void saveState(Hashtable<Cell, Cell> hashtable , String name)     //GETTING HASHTABLE AND ADDING X AND Y AXIS TO DATABASE
    {
        Cell cell;
        Connection();                                            //CREATING CONNECTION BETWEEN MYSQL AND JAVA
        Enumeration e = hashtable.elements();                    //USE FOR ITERATING HASHTABLE
        int x_axis, y_axis;

        String sql = "Insert Into StateName Values ('" + name +  "')";
        try
        {
            Statement statement = connect.createStatement();
            statement.executeUpdate(sql);
        }
        catch (SQLException throwable)
        {
            throwable.printStackTrace();
        }

        while (e.hasMoreElements())                               //ITERATING ELEMENTS IN HASHTABLE
        {
            cell = (Cell) e.nextElement();
            x_axis = cell.x_axis;
            y_axis = cell.y_axis;

            sql = "Insert Into CELLS (X_Axis,Y_Axis,Name) Values ('" + x_axis + "','" + y_axis + "','"+name+"')";  //RUN THE SQL COMMAND
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
    }


    public void deleteRecentState()
    {
        Connection();
        String names;
        String sql = "SELECT * from CELLS order by Id DESC LIMIT 1";
        try
        {
            Statement statement = connect.createStatement();
            ResultSet RT = statement.executeQuery(sql);
            if(!RT.next())
                return;
            names = RT.getString("Name");
            PreparedStatement statement1 = connect.prepareStatement("Delete FROM CELLS where Name=?");
            statement1.setString(1,names);
            statement1.executeUpdate();
        }
        catch (SQLException throwable)
        {
            throwable.printStackTrace();
        }

    }

    @Override
    public Hashtable<Cell, Cell> loadState(String name)
    {
        Cell cell;
        Connection();
        Hashtable<Cell, Cell> ht = new Hashtable<>();     //CREATES A HASHTABLE FOR RETURNING TO BL
        String sql = "SELECT * from CELLS where Name='"+ name +"'";                                                   // CALLING LoadState() TO RETRIEVE THE DATA FROM MYSQL
        try
        {
            Statement statement = connect.createStatement();
            ResultSet RT = statement.executeQuery(sql);
            while (RT.next())                                      //GETTING DATA FROM COLUMNS AND PLACING IN HASHTABLE
            {
                cell = new Cell(RT.getInt("X_Axis"), RT.getInt("Y_Axis"));
                ht.put(cell, cell);
            }
        }
        catch (SQLException throwable)
        {
            throwable.printStackTrace();
        }
        return ht;
    }


    public Hashtable<Cell, Cell> loadRecentState()
    {
        Cell cell;
        Connection();
        String names;
        Hashtable<Cell, Cell> ht = new Hashtable<>();     //CREATES A HASHTABLE FOR SAVING STATE
        String sql = "SELECT * from CELLS order by Id DESC LIMIT 1,1";

        try
        {
            Statement statement = connect.createStatement();
            ResultSet RT = statement.executeQuery(sql);
            if(!RT.next())
                return ht;

            names = RT.getString("Name");

            sql = "SELECT * from CELLS where Name='"+ names +"'";
            RT = statement.executeQuery(sql);
            while (RT.next())                                      //GETTING DATA FROM COLUMNS AND PLACING IN HASHTABLE
            {
                cell = new Cell(RT.getInt("X_Axis"), RT.getInt("Y_Axis"));
                ht.put(cell, cell);
            }
        }
        catch (SQLException throwable)
        {
            throwable.printStackTrace();
        }
        return ht;
    }


    public void deleteState(String names)
    {
        Connection();
        try
        {
            connect.createStatement();
            PreparedStatement statement1 = connect.prepareStatement("Delete FROM CELLS where Name=?");
            statement1.setString(1,names);
            statement1.executeUpdate();
        }
        catch (SQLException throwable)
        {
            throwable.printStackTrace();
        }
    }


    public String[] getStates()
    {
        Connection();
        String[] stateNames = new String[30];
        int val = 0;
        String sql = "SELECT DISTINCT(CELLS.NAME) from CELLS";
        try
        {
            Statement statement = connect.createStatement();
            ResultSet RT = statement.executeQuery(sql);
            while (RT.next())                                      //GETTING DATA FROM COLUMNS AND PLACING IN HASHTABLE
            {
                stateNames[val] = RT.getString("Name");
                val = val +1;
            }
            stateNames[val] = "\0";
        }
        catch (SQLException throwable)
        {
            throwable.printStackTrace();
        }
        return stateNames;
    }
}






