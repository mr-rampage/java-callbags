package ca.wbac.callbags.basics.source;

import ca.wbac.callbags.basics.ISink;
import ca.wbac.callbags.basics.ISource;
import ca.wbac.callbags.basics.NoopCallbag;

import java.util.Timer;
import java.util.TimerTask;

final class Interval<E> implements ISource<Integer, E> {
    private final long period;

    public Interval(long period) {
        this.period = period;
    }

    @Override
    public void greet(final ISink<Integer, E> sink) {
        Timer timer = new Timer();

        sink.greet(new NoopCallbag<>() {
            @Override
            public void terminate() {
                timer.cancel();
            }

            @Override
            public void terminate(E error) {
                timer.cancel();
            }
        });

        timer.scheduleAtFixedRate(new TimerTask() {
            private int tick = 0;

            @Override
            public void run() {
                sink.deliver(tick++);
            }
        }, 0, period);
    }

    @Override
    public void request() {
    }
}
