package ca.wbac.callbags.core;

public interface SourceInitiator<T> {
    void start(SinkTalkback<T> sinkTalkback);
}
