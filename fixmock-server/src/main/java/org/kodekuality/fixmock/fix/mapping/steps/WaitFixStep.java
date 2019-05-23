package org.kodekuality.fixmock.fix.mapping.steps;

import java.util.concurrent.TimeUnit;

public class WaitFixStep implements FixStep {
    private final long interval;
    private final TimeUnit unit;

    public WaitFixStep(long interval, TimeUnit unit) {
        this.interval = interval;
        this.unit = unit;
    }

    public long getInterval() {
        return interval;
    }

    public TimeUnit getUnit() {
        return unit;
    }
}
