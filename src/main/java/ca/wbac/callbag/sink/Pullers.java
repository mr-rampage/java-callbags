package ca.wbac.callbag.sink;

import ca.wbac.callbag.Callbag;
import ca.wbac.callbag.Pipeable;

import java.util.function.Consumer;

public final class Pullers {
    public static Pipeable forEach(Consumer consumer) {
        return (Callbag inputSource) -> {
            inputSource.greet(new ForEach(consumer));
            return null;
        };
    }
}
