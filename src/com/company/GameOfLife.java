package com.company;

import UI.*;

public class GameOfLife
{
    //Canvas canvas;
    public Grid grid;
    int counter;
    int zoom;
    int speed;
    boolean gameStatus;

    public void initGame()
    {
        this.grid = Factory.getGrid();
        //this.canvas = new Canvas(this.grid);
        this.speed = Factory.defaultSpeed;
        this.zoom = Factory.defaultZoom;
        this.counter = 0;

        /*Database db = new Database();
        db.connect();
        swing swing = new swing();
        swing.Example();*/
    }

    void start()
    {
        this.gameStatus = true;
    }
    void stop()
    {
        this.gameStatus = false;
    }
    void reset()
    {
        stop();
    }
    boolean isGameRunning()
    {
        return this.gameStatus;
    }
    void setZoom(int value)
    {
        this.zoom=value;
    }
    void setSpeed(int value)
    {
        this.speed=value;
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
        counter++;
        //will add functionality
    }
    void speedChanges(int value)
    {
        setSpeed(value);
    }
    void zoomChanged(int value)
    {
        setZoom(value);
    }
    void resetButtonClicked()
    {
        reset();
    }
}
