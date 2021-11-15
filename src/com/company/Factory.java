package com.company;

import com.company.Grid;

public class Factory
{
    public static Grid grid = new Grid();
    public static int increaseNeighbour=1;
    public static int decreaseNeighbour=0;
    public static int defaultSpeed = 20;
    public static int defaultZoom = 20;
    public static int gridRows = 30;
    public static int gridCols = 55;
    public static Grid getGrid()
    {
        return grid;
    }

}
