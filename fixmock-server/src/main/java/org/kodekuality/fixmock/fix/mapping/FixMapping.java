package org.kodekuality.fixmock.fix.mapping;

import org.kodekuality.fixmock.fix.mapping.steps.FixStep;
import org.kodekuality.fixmock.fix.session.RawFixMessage;

import java.util.List;
import java.util.function.Predicate;

public class FixMapping {
    private final Predicate<RawFixMessage> predicate;
    private final List<FixStep> steps;

    public FixMapping(Predicate<RawFixMessage> predicate, List<FixStep> steps) {
        this.predicate = predicate;
        this.steps = steps;
    }

    public Predicate<RawFixMessage> getPredicate() {
        return predicate;
    }

    public List<FixStep> getSteps() {
        return steps;
    }
}
