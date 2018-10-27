package ca.wbac.callbag.source;

import ca.wbac.callbag.sink.IPuller;

public interface IPullable<T> {
    void greet(IPuller<T> sink);
    void pull();
    void goodbye();
}
