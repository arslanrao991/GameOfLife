package com.company;

import swing.*;

public class GameOfLife
{
    Canvas canvas;
    Grid grid;
    int counter;
    int zoom;
    int speed;
    boolean gameStatus;

    public void initGame()
    {
        this.grid = new Grid();
        this.canvas = new Canvas(this.grid);
        this.speed = Global.defaultSpeed;
        this.zoom = Global.defaultZoom;
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
        canvas.gameGrid.clear();
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
    void speedChanges(Event e)
    {
        setSpeed(e.getSpeed());
    }
    void zoomChanged(Event e)
    {
        setZoom(e.getZoom());
    }
    void resetButtonClicked()
    {
        reset();
    }
}
