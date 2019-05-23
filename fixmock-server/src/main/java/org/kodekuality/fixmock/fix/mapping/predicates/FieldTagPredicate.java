package org.kodekuality.fixmock.fix.mapping.predicates;

import org.kodekuality.fixmock.fix.session.RawFixField;

import java.util.function.Predicate;

public class FieldTagPredicate implements Predicate<RawFixField>  {
    private final Predicate<Integer> tagPredicate;

    public FieldTagPredicate(Predicate<Integer> tagPredicate) {
        this.tagPredicate = tagPredicate;
    }

    @Override
    public boolean test(RawFixField rawFixField) {
        return tagPredicate.test(rawFixField.getTag());
    }
}
