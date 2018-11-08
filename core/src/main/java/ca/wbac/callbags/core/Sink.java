package ca.wbac.callbags.core;

import java.util.function.Consumer;

public interface Sink<T> extends Consumer<Source<T>> {
}
