package ca.wbac.callbags.basics;

public interface ISink<I, E> extends ICallbag<E> {
    void greet(ISource<I, E> talkback);

    void deliver(I data);
}
