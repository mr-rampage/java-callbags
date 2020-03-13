package ca.wbac.callbags.basics.source;

import ca.wbac.callbags.basics.Callbag;
import ca.wbac.callbags.basics.ISink;
import ca.wbac.callbags.basics.ISource;

import java.util.Timer;
import java.util.TimerTask;

final class Interval implements ISource<Integer> {
    private final long period;

    public Interval(long period) {
        this.period = period;
    }

    @Override
    public void greet(final ISink<Integer> sink) {
        sink.greet(new Callbag<>() {
            private final Timer timer = new Timer();
            private boolean started = false;

            @Override
            public void request() {
                if (!started) {
                    started = true;
                    timer.scheduleAtFixedRate(new TimerTask() {
                        private int tick = 0;

                        @Override
                        public void run() {
                            sink.deliver(tick++);
                        }
                    }, 0, period);
                }
            }

            @Override
            public void terminate() {
                timer.cancel();
            }

            @Override
            public void terminate(Throwable error) {
                timer.cancel();
            }
        });
    }
}
