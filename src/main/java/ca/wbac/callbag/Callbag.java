package ca.wbac.callbag;

public abstract class Callbag<T> {
    public void greet(Callbag<T> callbag) {}
    public void data() {}
    public void data(T response) {}
    public void terminate() {}
}
