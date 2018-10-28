package ca.wbac.callbag;

public class Utils {
    public static Callbag pipe(Callbag source, Pipeable ...pipeables) {
        Callbag pipeline = source;
        for (Pipeable pipeable : pipeables) {
            pipeline = pipeable.apply(pipeline);
        }
        return pipeline;
    }
}
