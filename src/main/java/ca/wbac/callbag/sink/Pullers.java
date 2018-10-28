package ca.wbac.callbag.sink;

import ca.wbac.callbag.Callbag;
import ca.wbac.callbag.Pipeable;

import java.util.function.Consumer;

public final class Pullers {
    public static <T> Pipeable forEach(Consumer<T> consumer) {
        return (Callbag inputSource) -> {
            inputSource.greet(new ForEach<>(consumer));
            return null;
        };
    }
}
