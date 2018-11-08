package ca.wbac.callbags.basics.source;

import ca.wbac.callbags.core.Source;
import ca.wbac.callbags.core.SourceFactory;
import ca.wbac.callbags.core.SourceTalkback;

import java.util.Timer;
import java.util.TimerTask;

public final class Interval implements SourceFactory<Integer> {
    private final long period;

    Interval(long period) {
        this.period = period;
    }

    @Override
    public Source<Integer> get() {
        return sinkTalkback -> sinkTalkback.start(new SourceTalkback() {
            private TimerTask repeat = new TimerTask() {
                private int i = 0;

                @Override
                public void run() {
                    sinkTalkback.deliver(i++);
                }
            };
            private Timer timer = new Timer("timer");
            private boolean started = false;

            @Override
            public void request() {
                if (!started) {
                    started = true;
                    timer.scheduleAtFixedRate(repeat, 0, period);
                }
            }

            @Override
            public void terminate() {
                timer.cancel();
            }
        });
    }
}
