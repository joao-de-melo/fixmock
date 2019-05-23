package org.kodekuality.fixmock.fix.mapping.predicates;

import org.kodekuality.fixmock.fix.session.RawFixField;

import java.util.function.Predicate;

public class FieldValuePredicate implements Predicate<RawFixField> {
    private final Predicate<String> valuePredicate;

    public FieldValuePredicate(Predicate<String> valuePredicate) {
        this.valuePredicate = valuePredicate;
    }

    @Override
    public boolean test(RawFixField rawFixField) {
        return valuePredicate.test(rawFixField.getValue());
    }
}
