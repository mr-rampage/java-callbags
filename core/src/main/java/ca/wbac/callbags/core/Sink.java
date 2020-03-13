package ca.wbac.callbags.core;

public interface Sink<I> extends Terminate {
    void greet(Source<I> talkback);

    void deliver(I data);
}
