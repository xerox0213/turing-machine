package g61453.atl.oo;

public interface Observable {
    void notifyObserver(String event, Object state);

    void addObserver(Observer observer);
}
