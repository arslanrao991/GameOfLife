package com.company;

public class Canvas
{
    int cellSize;
    CellGrid gameGrid;

    Canvas()
    {
        this.cellSize = 2;
        this.gameGrid = Global.Grid();
    }

    void setCellSize(int newSize)
    {
        this.cellSize = newSize;
    }

    void draw(int x, int y)
    {
        //will add functionality
    }

    void update()
    {
        //will add functionality
    }
}
