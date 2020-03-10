package ca.wbac.callbags.basics.sink;

import ca.wbac.callbags.basics.ISource;

import java.util.function.Consumer;

public final class Sink {
    public static <T> Consumer<ISource<T>> forEach(final Consumer<T> consumer) {
        var sink = new ForEach<>(consumer);
        return source -> source.greet(sink);
    }
}
