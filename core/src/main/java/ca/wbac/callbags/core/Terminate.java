package ca.wbac.callbags.core;

public interface Terminate {
    default void terminate() {
    }

    default void terminate(Throwable error) {
    }
}
