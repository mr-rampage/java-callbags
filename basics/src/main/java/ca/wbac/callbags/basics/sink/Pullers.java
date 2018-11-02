package ca.wbac.callbags.basics.sink;

import ca.wbac.callbags.core.SinkTerminator;

import java.util.function.Consumer;

public class Pullers {

    public static <T> SinkTerminator<T> forEach(Consumer<T> consumer) {
        return inputSource -> inputSource.start(new ForEach<>(consumer));
    }
}
