package com.company;
import java.util.*;
import Factory.Constants;

public class Grid
{
    public int cellRows;
    public int cellColumns;
    public Cell[][] grid;
    public Hashtable currentShape; //stores alive cells
    public Hashtable newShape; //stores new alive cells after counter
    public Cell[][] initialShape;
    private boolean savedInitialState;
    int generation;

    public Grid()
    {
        this.cellRows = Constants.gridRows;
        this.cellColumns = Constants.gridCols;
        this.currentShape = new Hashtable();
        this.newShape = new Hashtable();
        this.initialShape = new Cell[this.cellRows][this.cellColumns];
        this.savedInitialState = false;
        this.generation=0;

        this.grid=new Cell[this.cellRows][this.cellColumns];
        for(int i=0;i<this.cellRows;i++)
        {
            for(int j=0;j<this.cellColumns;j++)
            {
                this.grid[i][j] = new Cell(i, j);
                this.initialShape[i][j] = new Cell(i, j);
            }
        }
    }

    public boolean getCellStatus(int x, int y)
    {
        return grid[x][y].isAlive();
    }

    synchronized public void setCell(int x, int y, boolean status)
    {
        try
        {
            Cell c = grid[x][y];
            if ( status)
            {
                c.setCellStatus(true);
                currentShape.put(c, c);
            }
            else
            {
                c.setCellStatus(false);
                currentShape.remove(c, c);
            }
        }
        catch(ArrayIndexOutOfBoundsException error)
        {
            //nothing
        }
    }


    public void clear()
    {
        currentShape.clear();
        newShape.clear();
        for(int i=0;i<this.cellRows;i++)
        {
            for(int j=0;j<this.cellColumns;j++)
            {
                this.grid[i][j].setCellStatus(false);
            }
        }
    }
    public void reset()
    {
        clear();
        for(int i=0;i<this.cellRows;i++)
        {
            for(int j=0;j<this.cellColumns;j++)
            {
                if(initialShape[i][j].isAlive())
                {
                    this.grid[i][j].setCellStatus(true);
                    currentShape.put(this.grid[i][j], this.grid[i][j]);
                }
            }
        }
    }

    public void next()
    {
        this.generation++;
        Cell c;
        int x, y;

        newShape.clear();
        Enumeration enumerate = currentShape.keys();
        while(enumerate.hasMoreElements())
        {
            c = (Cell) enumerate.nextElement();
            c.neighbours = 0;
        }

        enumerate = currentShape.keys();
        while(enumerate.hasMoreElements())
        {
            c = (Cell) enumerate.nextElement();
            x = c.x_axis;
            y = c.y_axis;
            addNeighbour( x-1, y-1 );
            addNeighbour( x, y-1 );
            addNeighbour( x+1, y-1 );
            addNeighbour( x-1, y );
            addNeighbour( x+1, y );
            addNeighbour( x-1, y+1 );
            addNeighbour( x, y+1 );
            addNeighbour( x+1, y+1 );
        }

        enumerate = currentShape.keys();
        //game of life Rule# 1
        while (enumerate.hasMoreElements())
        {
            c = (Cell) enumerate.nextElement();
            if(c.getNeighbours()!= 3 && c.getNeighbours()!=2)
            {
                c.neighbours=0;
                c.setCellStatus(false);
                currentShape.remove(c);
            }
        }

        enumerate = newShape.keys();
        //game of life Rule# 2
        while (enumerate.hasMoreElements())
        {
            c = (Cell) enumerate.nextElement();
            if(c.getNeighbours()==3)
            {
                setCell(c.x_axis, c.y_axis, true);
            }
        }
    }

    void addNeighbour(int x, int y)
    {
        try
        {
            Cell c = (Cell) newShape.get(grid[x][y]);
            if(c == null)
            {
                c = grid[x][y];
                c.neighbours = 1;
                newShape.put(c, c);
            }
            else
            {
                c.neighbours++;
            }
        }
        catch (ArrayIndexOutOfBoundsException error)
        {
            //do nothing
        }
    }

    public void saveInitialShape()
    {
        Cell c;

        if(savedInitialState)
            return;

        Enumeration enumerate = currentShape.keys();
        while(enumerate.hasMoreElements())
        {
            c = (Cell) enumerate.nextElement();
            this.initialShape[c.x_axis][c.y_axis].setCellStatus(true);
        }
        savedInitialState = true;
    }
}
