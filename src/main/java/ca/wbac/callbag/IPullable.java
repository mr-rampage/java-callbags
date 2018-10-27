package ca.wbac.callbag;

public interface IPullable<T> {
    void greet(IPuller<T> sink);
    void pull();
    void goodbye();
}
