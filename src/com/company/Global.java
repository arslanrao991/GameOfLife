package com.company;

import com.company.Grid;

public class Global
{
    public static int increaseNeighbour=1;
    public static int decreaseNeighbour=0;
    public static int defaultSpeed = 20;
    public static int defaultZoom = 20;
    public static int gridRows = 10;
    public static int gridCols = 10;
    public static Grid Grid()
    {
        return new Grid();
    }

}
