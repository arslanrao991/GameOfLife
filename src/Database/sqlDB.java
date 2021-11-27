package Database;

import com.company.DBInterfaceIn;
import com.company.Cell;
import com.company.DBInterfaceOut;
import com.company.GameOfLife;

import java.sql.*;
import java.util.Enumeration;
import java.util.Hashtable;

public class sqlDB implements DBInterfaceOut
{
    Connection connect;
    DBInterfaceIn gameControls;

    public sqlDB(GameOfLife g)
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
        Cell c;
        Connection();                                            //CREATING CONNECTION BETWEEN MYSQL AND JAVA
        Enumeration e = hashtable.elements();                    //USE FOR ITERATING HASHTABLE
        int x_axis, y_axis,val;

        String sql = "Insert Into StateName Values ('" + name +  "')";
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
            c = (Cell) e.nextElement();
            x_axis = c.x_axis;
            y_axis = c.y_axis;

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
        int Id;
        String names;
        String sql = "SELECT * from CELLS order by Id DESC LIMIT 1";
        try
        {
            Statement statement = connect.createStatement();
            ResultSet RT = statement.executeQuery(sql);
            RT.next();
            names = RT.getString("Name");
            PreparedStatement statement1 = connect.prepareStatement("Delete FROM CELLS where Name=?");
            statement1.setString(1,names);
            statement1.executeUpdate();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

    }

    @Override
    public Hashtable<Cell, Cell> loadState(String name)
    {
        Cell c;
        Connection();
        Hashtable<Cell, Cell> ht = new Hashtable<Cell, Cell>();     //CREATES A HASHTABLE FOR RETURNING TO BL
        String sql = "SELECT * from CELLS where Name='"+ name +"'";                                                   // CALLING LoadState() TO RETRIEVE THE DATA FROM MYSQL
        try
        {
            Statement statement = connect.createStatement();
            ResultSet RT = statement.executeQuery(sql);
            while (RT.next())                                      //GETTING DATA FROM COLUMNS AND PLACING IN HASHTABLE
            {
                c = new Cell(RT.getInt("X_Axis"), RT.getInt("Y_Axis"));
                ht.put(c, c);
            }
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return ht;
    }


    public Hashtable<Cell, Cell> loadRecentState()
    {
        Cell c;

        Connection();
        String Names;
        Hashtable<Cell, Cell> ht = new Hashtable<Cell, Cell>();     //CREATES A HASHTABLE FOR SAVING STATE
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
                c = new Cell(RT.getInt("X_Axis"), RT.getInt("Y_Axis"));
                ht.put(c, c);
            }
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return ht;
    }


    public void deleteState(String names)
    {
        Connection();
        try
        {
            Statement statement = connect.createStatement();
            PreparedStatement statement1 = connect.prepareStatement("Delete FROM CELLS where Name=?");
            statement1.setString(1,names);
            statement1.executeUpdate();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
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
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return stateNames;
    }
}






