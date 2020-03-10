package ca.wbac.callbags.basics;

public interface ISink<I> extends ICallbag {
    void greet(ISource<I> talkback);

    void deliver(I data);
}
