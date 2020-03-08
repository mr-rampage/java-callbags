package ca.wbac.callbags.basics.helpers;

import ca.wbac.callbags.basics.ISink;
import ca.wbac.callbags.basics.ISource;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CompletableFuture;

public final class PushableSource<T, E> implements ISource<T, E> {
    private final Timer timer = new Timer();
    private final CompletableFuture<Integer> completableFuture;
    private final List<T> list;

    private PushableSource(List<T> list, CompletableFuture<Integer> completableFuture) {
        this.completableFuture = completableFuture;
        this.list = list;
    }

    public static <T, E> PushableSource<T, E> of(List<T> list, CompletableFuture<Integer> completableFuture) {
        return new PushableSource<>(list, completableFuture);
    }

    @Override
    public void greet(ISink<T, E> sink) {
        sink.greet(this);

        timer.scheduleAtFixedRate(new TimerTask() {
            private int sent = 0;

            @Override
            public void run() {
                if (sent == list.size()) {
                    timer.cancel();
                    sink.terminate();
                    completableFuture.complete(sent);
                } else {
                    sink.deliver(list.get(sent++));
                }
            }
        }, 0, 10);
    }

    @Override
    public void request() {

    }
}
