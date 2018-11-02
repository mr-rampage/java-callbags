package ca.wbac.callbags.core;

public interface SourceTalkback {
    void request();

    default void terminate() {
    }
}
