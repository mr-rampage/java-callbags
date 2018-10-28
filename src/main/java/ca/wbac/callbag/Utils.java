package ca.wbac.callbag;

import java.util.Arrays;
import java.util.function.Function;

public class Utils {
    public static void pipe(Callbag source, Pipeable... pipeables) {
        Arrays.stream((Function[]) pipeables)
                .reduce(Function.identity(), Function::andThen)
                .apply(source);
    }
}
