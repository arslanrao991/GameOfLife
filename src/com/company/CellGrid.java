package com.company;

public interface CellGrid
{
    boolean getCellStatus(int x, int y);
    void setCell(int x, int y, boolean status);
    void clear();
}
