package se.hernebring.store;

public interface Storage {
    public void save();
    public void delete();
    public boolean isNull();
}
