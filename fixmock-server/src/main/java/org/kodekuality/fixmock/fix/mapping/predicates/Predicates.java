package org.kodekuality.fixmock.fix.mapping.predicates;

import java.util.Arrays;
import java.util.function.Predicate;

public class Predicates {
    public static <T> Predicate<T> allOf (Predicate<T>... predicates) {
        return x -> Arrays.stream(predicates).allMatch(p -> p.test(x));
    }
    public static <T> Predicate<T> noneOf (Predicate<T>... predicates) {
        return x -> Arrays.stream(predicates).noneMatch(p -> p.test(x));
    }
    public static <T> Predicate<T> anyOf (Predicate<T>... predicates) {
        return x -> Arrays.stream(predicates).anyMatch(p -> p.test(x));
    }
}
