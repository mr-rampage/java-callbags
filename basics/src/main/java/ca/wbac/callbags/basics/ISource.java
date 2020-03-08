package ca.wbac.callbags.basics;

public interface ISource<T, E> extends ICallbag<E> {
    void greet(ISink<T, E> sink);

    void request();
}
