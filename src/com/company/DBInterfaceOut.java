package com.company;

import java.util.Hashtable;

public interface DBInterfaceOut
{
    void saveState(Hashtable<Cell, Cell> h, String name);
    void deleteRecentState();
    Hashtable<Cell, Cell> loadState(String name);
    Hashtable<Cell, Cell> loadRecentState();
    void deleteState(String names);
    String[] getStates();

}
