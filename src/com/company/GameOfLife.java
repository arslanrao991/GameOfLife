package com.company;
import Factory.Factory;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class GameOfLife<audioStream>
{
    public Grid grid;
    public UIListener uiController;
    int counter;
    int zoom;
    int speed;
    boolean gameStatus;
    File file = new File("C:\\Users\\myacc\\Data\\IdealProjects\\GameOfLife\\src\\com\\company\\gameOfLife.wav");
    AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
    Clip clip = AudioSystem.getClip();

    public  GameOfLife() throws LineUnavailableException, UnsupportedAudioFileException, IOException
    {
        this.grid = Factory.getGrid();
        //this.canvas = new Canvas(this.grid);
        this.speed = Factory.defaultSpeed;
        this.zoom = Factory.currentZoom;
        this.counter = 0;
        this.setUIController();
        clip.open(audioStream);


        /*Database db = new Database();
        db.connect();
        swing swing = new swing();
        swing.Example();*/
    }

    public void start()
    {
        this.gameStatus = true;
        clip.start();
    }
    public void stop()
    {
        this.gameStatus = false;
        clip.stop();
    }
    public void setUIController()
    {
        uiController = Factory.getController();
    }
    public void reset()
    {
        stop();
        grid.clear();
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
    public int getZoom()
    {
        return this.zoom;
    }
    public int getSpeed()
    {
        return this.speed;
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
        uiController.updateGraphics(grid);
    }

}
