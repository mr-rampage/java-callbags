package ca.wbac.callbags.basics.sink;

import java.util.function.Consumer;

public final class Sink {
    public static <T> ForEach<T> forEach(final Consumer<T> consumer) {
        return new ForEach<>(consumer);
    }
}
