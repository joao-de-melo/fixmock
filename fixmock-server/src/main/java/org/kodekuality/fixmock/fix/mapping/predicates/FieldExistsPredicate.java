package org.kodekuality.fixmock.fix.mapping.predicates;

import org.kodekuality.fixmock.fix.session.RawFixField;
import org.kodekuality.fixmock.fix.session.RawFixMessage;

import java.util.function.Predicate;

public class FieldExistsPredicate implements Predicate<RawFixMessage> {
    private final Predicate<RawFixField> fixFieldPredicate;

    public FieldExistsPredicate(Predicate<RawFixField> fixFieldPredicate) {
        this.fixFieldPredicate = fixFieldPredicate;
    }

    @Override
    public boolean test(RawFixMessage rawFixMessage) {
        return rawFixMessage.getFields().stream().anyMatch(fixFieldPredicate);
    }
}
