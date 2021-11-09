package com.company;

public class Event
{
    int speed;
    int zoom;

    Event()
    {
        this.speed = 5;
        this.zoom = 100;
    }
    int getSpeed()
    {
        return this.speed;
    }
    int getZoom()
    {
        return this.zoom;
    }
    void setSpeed(int value)
    {
        this.speed = value;
    }
    void setZoom(int value)
    {
        this.zoom = value;
    }
}
