package ca.wbac.callbags.core;

public interface Source<T> {
    void start(SinkTalkback<T> sinkTalkback);
}
