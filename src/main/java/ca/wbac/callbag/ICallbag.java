package ca.wbac.callbag;

public interface ICallbag<T> {
    void greet(ICallbag<T> callbag);
    void goodbye();
}
