package ca.wbac.callbag;

public interface IPullable<T> extends ICallbag<T>{
    void pull();
}
