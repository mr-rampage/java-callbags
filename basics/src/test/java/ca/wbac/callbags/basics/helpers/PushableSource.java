package ca.wbac.callbags.basics.helpers;

import ca.wbac.callbags.basics.ISink;
import ca.wbac.callbags.basics.ISource;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CompletableFuture;

public final class PushableSource<T> implements ISource<T> {
    private final Timer timer = new Timer();
    private final CompletableFuture<Integer> completableFuture;
    private final List<T> list;
    private ISink<T> sink;
    private boolean started = false;

    public PushableSource(List<T> list, CompletableFuture<Integer> completableFuture) {
        this.completableFuture = completableFuture;
        this.list = list;
    }

    @Override
    public void greet(ISink<T> sink) {
        this.sink = sink;
        sink.greet(this);
    }

    @Override
    public void request() {
        if (!started) {
            started = true;
            timer.scheduleAtFixedRate(new TimerTask() {
                private int sent = 0;

                @Override
                public void run() {
                    if (sent == list.size()) {
                        timer.cancel();
                        completableFuture.complete(sent);
                        sink.terminate();
                    } else {
                        sink.deliver(list.get(sent++));
                    }
                }
            }, 0, 1);
        }
    }
}
