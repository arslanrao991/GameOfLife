package com.company;

public class GameOfLife
{
    Canvas canvas;
    Grid grid;
    int counter;
    boolean gameStatus;

    void initGame()
    {
        this.canvas = new Canvas();
        this.grid = new Grid();
        this.counter = 0;
    }
    void start()
    {
        this.gameStatus = true;
        //will add functionality
    }
    void stop()
    {
        this.gameStatus = false;
        //will add functionality
    }
    void reset()
    {
        //will add functionality
    }
    boolean isGameRunning()
    {
        return this.gameStatus;
    }
    void startStopButtonClick()
    {
        if(isGameRunning())
            stop();
        else
            start();
    }
    void nextButtonClick()
    {
        //will add functionality
    }
    void speedChanges()
    {
        //will add functionality
    }
    void zoomChanged()
    {
        //will add functionality
    }
    void resetButtonClicked()
    {
        //will add functionality
    }
    void setCellSize(int value)
    {
        canvas.setCellSize(value);
    }

}
