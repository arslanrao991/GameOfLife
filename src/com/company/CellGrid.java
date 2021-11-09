package com.company;

public interface CellGrid
{
    boolean getCell(int x, int y);
    void setCell(int x, int y, boolean status);
    void resizeGrid(int x, int y);
    void clear();
}
