package se.hernebring.store;

public interface Store {
    public void save();
    public void delete();
    public void getId(int id);
    public boolean isNull();
}
