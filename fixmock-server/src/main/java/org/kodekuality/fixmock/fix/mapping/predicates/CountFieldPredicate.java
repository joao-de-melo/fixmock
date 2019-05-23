package org.kodekuality.fixmock.fix.mapping.predicates;

import org.kodekuality.fixmock.fix.session.RawFixField;
import org.kodekuality.fixmock.fix.session.RawFixMessage;

import java.util.function.Predicate;

public class CountFieldPredicate implements Predicate<RawFixMessage> {
    private final Predicate<Long> countPredicate;
    private final Predicate<RawFixField> fixFieldPredicate;

    public CountFieldPredicate(Predicate<Long> countPredicate, Predicate<RawFixField> fixFieldPredicate) {
        this.countPredicate = countPredicate;
        this.fixFieldPredicate = fixFieldPredicate;
    }

    @Override
    public boolean test(RawFixMessage rawFixMessage) {
        return countPredicate.test(rawFixMessage.getFields().stream().filter(fixFieldPredicate).count());
    }
}
