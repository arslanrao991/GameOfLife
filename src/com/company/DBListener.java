package com.company;

import java.util.Hashtable;

public interface DBListener
{
    public void saveState();
    public void deleteState();
    public Hashtable loadState();
    public Hashtable viewState();
}
