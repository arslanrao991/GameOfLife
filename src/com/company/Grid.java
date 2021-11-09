package com.company;
public class Grid implements CellGrid
{
    int cellRows;
    int cellColumns;
    public Cell[][] grid;

    Grid()
    {
        this.cellRows=10;
        this.cellColumns = 10;
        this.grid=new Cell[this.cellRows][this.cellColumns];
        for(int i=0;i<this.cellRows;i++)
        {
            for(int j=0;j<this.cellColumns;j++)
            {
                this.grid[i][j] = new Cell(i, j);
            }
        }
    }
    public void updateNeighbours(Cell c)
    {
        int neighboursCount=0;
        if(c.x_axis != 0 && c.y_axis != 0 && grid[c.x_axis-1][c.y_axis-1].isAlive())
            neighboursCount++;
        if(c.x_axis != 0 && grid[c.x_axis-1][c.y_axis].isAlive())
            neighboursCount++;
        if(c.x_axis != 0 && c.y_axis+1 != cellColumns && grid[c.x_axis-1][c.y_axis+1].isAlive())
            neighboursCount++;
        if(c.x_axis+1 != cellRows && c.y_axis != 0 && grid[c.x_axis+1][c.y_axis-1].isAlive())
            neighboursCount++;
        if(c.x_axis+1 != cellRows && grid[c.x_axis+1][c.y_axis].isAlive())
            neighboursCount++;
        if(c.x_axis+1 != cellRows && c.y_axis+1 != cellColumns && grid[c.x_axis+1][c.y_axis+1].isAlive())
            neighboursCount++;
        if(c.y_axis != 0 && grid[c.x_axis][c.y_axis-1].isAlive())
            neighboursCount++;
        if(c.y_axis+1 != cellColumns && grid[c.x_axis][c.y_axis+1].isAlive())
            neighboursCount++;
        c.neighbours=neighboursCount;
    }
    public void print()
    {
        for(int i=0;i<this.cellRows;i++)
        {
            for(int j=0;j<this.cellColumns;j++)
            {
                System.out.print(this.grid[i][j].x_axis + ", " + this.grid[i][j].y_axis + " {"+ this.grid[i][j].neighbours+", "+ this.grid[i] [j].isAlive()+"}");
                System.out.print(" | ");
            }
            System.out.print("\n");
        }

    }

    @Override
    public boolean getCell(int x, int y)
    {
        return grid[x][y].isAlive();
    }

    @Override
    public void setCell(int x, int y, boolean status)
    {
        grid[x][y].setCellStatus(status);
    }

    @Override
    public void resizeGrid(int x, int y)
    {
        //will add functionality
    }

    public void clear()
    {
        grid = null;
        grid=new Cell[this.cellRows][this.cellColumns];
        for(int i=0;i<this.cellRows;i++)
        {
            for(int j=0;j<this.cellColumns;j++)
            {
                this.grid[i][j] = new Cell(i, j);
            }
        }
    }
}
