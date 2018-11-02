package ca.wbac.callbags.core;

public interface SinkTalkback<T> {
    void start(SourceTalkback sourceTalkback);

    void deliver(T data);

    default void terminate() {
    }
}
