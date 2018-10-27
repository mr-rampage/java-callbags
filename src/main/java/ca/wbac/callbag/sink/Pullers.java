package ca.wbac.callbag.sink;

import java.util.function.Consumer;

public class Pullers {
    public static <T> ForEach<T> forEach(Consumer<T> consumer) {
        return new ForEach<>(consumer);
    }
}
