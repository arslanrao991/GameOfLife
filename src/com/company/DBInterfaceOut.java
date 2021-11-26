package com.company;

import java.util.Hashtable;

public interface DBInterfaceOut
{
    public void saveState(Hashtable<Cell, Cell> h, String name);
    public void deleteRecentState();
    public Hashtable<Cell, Cell> loadState(String name);
    public Hashtable<Cell, Cell> loadRecentState();
    public void deleteSelectedState(String names);
    public String[] getStates();

}
