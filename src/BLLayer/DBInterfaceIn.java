//DB layer use this interface to interact with BL
package BLLayer;

public interface DBInterfaceIn
{
    public void addDBListener(DBInterfaceOut l);
    public void detachDB();
}
