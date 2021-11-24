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

    public void saveState(Hashtable<Cell, Cell> hashtable)                 //GETTING HASHTABLE AND ADDING X AND Y AXIS TO DATABASE
    {
        Cell c;
        deleteState();
        Connection();                                          //CREATING CONNECTION BETWEEN MYSQL AND JAVA
        Enumeration e = hashtable.elements();                  //USE FOR ITERATING HASHTABLE
        int x_axis, y_axis;
        int val = 1;
        while (e.hasMoreElements())                             //ITERATING ELEMENTS IN HASHTABLE
        {

            c = (Cell) e.nextElement();

            x_axis = c.x_axis;
            y_axis = c.y_axis;
            String sql = "Insert Into CELLS Values ('" + val + "','" + x_axis + "','" + y_axis + "')";  //RUN THE SQL COMMAND

            try
            {
                Statement statement = connect.createStatement();
                statement.executeUpdate(sql);
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
            val = val + 1;
        }
    }

    public void deleteState()
    {
        Connection();
        String url = "Delete From Cells";                      //DELETE THE ENTRIES FROM TABLE
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

    public Hashtable<Cell, Cell> loadState() {

        Connection();
        Hashtable<Cell, Cell> ht = new Hashtable<Cell, Cell>();     //CREATES A HASHTABLE FOR SAVING STATE
        String sql = "SELECT * FROM CELLS";

        try
        {
            Statement statement = connect.createStatement();
            ResultSet RT = statement.executeQuery(sql);

            while (RT.next())                                      //GETTING DATA FROM COLUMNS AND PLACING IN HASHTABLE
            {
                Cell temp = new Cell(RT.getInt("X_Axis"), RT.getInt("Y_Axis"));
                ht.put(temp, temp);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return ht;
    }

    public Hashtable<Cell, Cell> viewState()
    {

        Connection();
        Hashtable<Cell, Cell> ht = new Hashtable<Cell, Cell>();
        ht = loadState();
        return ht;
    }
}





