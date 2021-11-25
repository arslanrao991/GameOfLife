package com.company;

import java.util.Hashtable;

public interface DBInterfaceOut
{
    public void saveState(Hashtable<Cell, Cell> h);
    public void deleteState();
    public Hashtable<Cell, Cell> loadState();
    public Hashtable<Cell, Cell> viewState();

}
