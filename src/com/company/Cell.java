package com.company;
public class Cell
{
    public int x_axis;
    public int y_axis;
    int neighbours;
    boolean isAlive;

    Cell(int x, int y)
    {
        this.x_axis=x;
        this.y_axis=y;
        this.isAlive = false;
        this.neighbours=0;
    }
    public int getNeighbours(){return neighbours;}

    public boolean equals(Object o)
    {
        if(!(o instanceof Cell))
            return false;
        return x_axis==((Cell)o).x_axis && y_axis==((Cell)o).y_axis;
    }
    public boolean isAlive(){return isAlive;}
    public void setCellStatus(boolean status)
    {
        this.isAlive=status;
    }



}
