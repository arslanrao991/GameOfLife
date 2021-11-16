package com.company;
import Factory.Factory;

public class GameOfLife
{
    public Grid grid;
    public UIListener uiControler;
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
        uiControler = Factory.getControler();

        /*Database db = new Database();
        db.connect();
        swing swing = new swing();
        swing.Example();*/
    }

    public void start()
    {
        this.gameStatus = true;
    }
    public void stop()
    {
        this.gameStatus = false;
    }
    public void reset()
    {
        stop();
    }
    public boolean isGameRunning()
    {
        return this.gameStatus;
    }
    public void setZoom(int value)
    {
        this.zoom=value;
    }
    public void setSpeed(int value)
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
        grid.next();
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
    void updateState()
    {
        uiControler.updateGraphics(grid);
    }

}
