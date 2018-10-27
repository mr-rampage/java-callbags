package ca.wbac.callbag;

public interface IPuller<T> {
    void greet(IPullable<T> source);
    void push(T response);
    void goodbye();
}
