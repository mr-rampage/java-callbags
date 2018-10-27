package ca.wbac.callbag;

public interface IPuller<T> extends ICallbag<T> {
    void push(T response);
}
