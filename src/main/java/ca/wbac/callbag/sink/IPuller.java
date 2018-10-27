package ca.wbac.callbag.sink;

import ca.wbac.callbag.source.IPullable;

public interface IPuller<T> {
    void greet(IPullable<T> source);
    void push(T response);
    void goodbye();
}
