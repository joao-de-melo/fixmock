package org.kodekuality.fixmock.fix.mapping.predicates;

import org.kodekuality.fixmock.fix.session.RawFixMessage;

public interface FixPredicate {
    boolean matches (RawFixMessage rawFixMessage);
}
