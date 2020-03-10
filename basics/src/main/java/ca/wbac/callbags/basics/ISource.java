package ca.wbac.callbags.basics;

public interface ISource<T> extends ICallbag {
    void greet(ISink<T> sink);

    void request();
}
